package com.backend.controller;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class RippleIntegration {

    public String makeAccountRequest() {
        String makeAccountUrl = "http://localhost:4000/api/makeaccount";
        return sendGETRequest(makeAccountUrl);
    }

    public String jobInterestRequest(String userSeed, String companySeed) {
        String jobInterestUrl = "http://localhost:4000/api/jobinterest";
        String requestBody = "{\"s_key_user\": \"" + userSeed + "\", \"s_key_comp\": \"" + companySeed + "\"}";
        return sendPOSTRequest(jobInterestUrl, requestBody);
    }

    public String jobDecisionCheckedRequest(String userSeed, int sequenceNum) {
        String jobDecisionCheckedUrl = "http://localhost:4000/api/jobdecisionchecked";
        String requestBody = "{\"s_key_user\": \"" + userSeed + "\", \"sequence_num\": " + sequenceNum + "}";
        return sendPOSTRequest(jobDecisionCheckedUrl, requestBody);
    }

    private String sendGETRequest(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "GET Request failed.";
        }
    }

    private String sendPOSTRequest(String apiUrl, String requestBody) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "POST Request failed.";
        }
    }
}