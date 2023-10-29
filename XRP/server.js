const xrpl = require("xrpl");
const crypto = require('crypto');
const cc = require('five-bells-condition')
const express = require('express');
const app = express();

app.use(express.json());

app.get('/api', (req, res) => {
    res.status(200).json({"msg":"hello from api"});
    console.log('Hello World from shane api')
})

app.get('/api/makeaccount', async(req, res) => {
    const client = new xrpl.Client("wss://s.altnet.rippletest.net:51233")
    await client.connect()
    const fund_result = await client.fundWallet()
    const client_wallet = fund_result.wallet
    console.log(client_wallet)
    res.status(200).json({"s_key": client_wallet.seed});
})

app.post('/api/jobinterest', async(req, res) => {
    const {s_key_user, s_key_comp} = req.body
    console.log(s_key_user, s_key_comp);

    const client = new xrpl.Client("wss://s.altnet.rippletest.net:51233")
    await client.connect()
    const client_wallet = xrpl.Wallet.fromSeed(s_key_user)
    const company_wallet = xrpl.Wallet.fromSeed(s_key_comp)
  
    // Construct condition and fulfillment ---------------------------------------
    const preimageData = crypto.randomBytes(32);
    const myFulfillment = new cc.PreimageSha256();
    myFulfillment.setPreimage(preimageData);
    const conditionHex = myFulfillment.getConditionBinary().toString('hex').toUpperCase();

    // Set the escrow finish time --------------------------------------------------
    let finishAfter = new Date((new Date().getTime() / 1000)); // 0 day from now
    finishAfter = new Date(finishAfter * 1000);
    console.log("This escrow will finish after: ", finishAfter);


    console.log('Condition:', conditionHex);
    console.log('Fulfillment:', myFulfillment.serializeBinary().toString('hex').toUpperCase());

    // Prepare transaction -------------------------------------------------------
    const prepared = await client.autofill({
        "TransactionType": "EscrowCreate",
        "Account": /* "rwUovZSYAD6DtgAmMR3VxC8TtZAcHrJPLm", */  client_wallet.address,
        "Amount": xrpl.xrpToDrops("5"),
        "Destination": /* "rQDQytvKuTQ4MrvCBmo5f1WnZkV1dQuwox",  */ company_wallet.address,
        // "DestinationTag": 2023,
        "Condition": conditionHex,
        // "Fee": "12",
        "CancelAfter": xrpl.isoTimeToRippleTime(finishAfter.toISOString()), 
    })

    const max_ledger = prepared.LastLedgerSequence
    console.log("Prepared transaction instructions:", prepared)
    console.log("Transaction cost:", xrpl.dropsToXrp(prepared.Fee), "XRP")
    console.log("Transaction expires after ledger:", max_ledger)

    
    // Sign prepared instructions ------------------------------------------------
    const signed = client_wallet.sign(prepared)
    console.log("Identifying hash:", signed.hash)
    console.log("Signed blob:", signed.tx_blob)
    
    // Submit signed blob --------------------------------------------------------
    try {
        const submit_result = await client.submitAndWait(signed.tx_blob)
        const escrowSeq = submit_result.result.Sequence
        // submitAndWait() doesn't return until the transaction has a final result.
        // Raises XrplError if the transaction doesn't get confirmed by the network.
        // Does not handle disaster recovery.
        console.log("Transaction result:", submit_result)
        res.status(200).json({"msg":"transaction done", "escrowSeq": escrowSeq});
    } catch(err) {
        console.log("Error submitting transaction:", err)
        res.status(400).json({"msg":"transaction faiiled"});
    }

    // Disconnect when done (If you omit this, Node.js won't end the process)
    client.disconnect()
})

app.post('/api/jobdecisionchecked', async(req, res) => {
    const {s_key_user, sequence_num} = req.body

    const client = new xrpl.Client("wss://s.altnet.rippletest.net:51233")
    await client.connect()
    const client_wallet = xrpl.Wallet.fromSeed(s_key_user)

    const prepared = await client.autofill({
        "Account": client_wallet.address,
        "TransactionType": "EscrowCancel",
        "Owner": client_wallet.address,
        "OfferSequence": sequence_num,
    })

    const max_ledger = prepared.LastLedgerSequence
    console.log("Prepared transaction instructions:", prepared)
    console.log("Transaction cost:", xrpl.dropsToXrp(prepared.Fee), "XRP")
    console.log("Transaction expires after ledger:", max_ledger)

    
    // Sign prepared instructions ------------------------------------------------
    const signed = client_wallet.sign(prepared)
    console.log("Identifying hash:", signed.hash)
    console.log("Signed blob:", signed.tx_blob)
    
    // Submit signed blob --------------------------------------------------------
    try {
        const submit_result = await client.submitAndWait(signed.tx_blob)
        // submitAndWait() doesn't return until the transaction has a final result.
        // Raises XrplError if the transaction doesn't get confirmed by the network.
        // Does not handle disaster recovery.
        console.log("Transaction result:", submit_result)
        res.status(200).json({"msg":"transaction calcelation done"});
    } catch(err) {
        console.log("Error submitting transaction:", err)
        res.status(400).json({"msg":"transaction calcelation failed"});
    }

    // Disconnect when done (If you omit this, Node.js won't end the process)
    client.disconnect()
})

app.listen(4000, () => {
    console.log('Server listening on port 4000');  
});