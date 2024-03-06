import { Component } from '@angular/core';

@Component({
  selector: 'app-explorecandidates',
  templateUrl: './explorecandidates.component.html',
  styleUrls: ['./explorecandidates.component.css']
})
export class ExplorecandidatesComponent {
  isSwipedLeft = false;
  isSwipedRight = false;

  // Define JSON data for the card content
  cardData = [
    {
      candidatename: 'James',
      email: 'james@gmail.com',
      skills: 'C++',
      interest: 'frontend',
      projectdescription:'Worked in Cal Hacks'
    },
    {
      candidatename: 'Nick',
      email: 'Nick@gmail.com',
      skills: 'Java',
      interest: 'frontend',
      projectdescription:'Worked in Cal Hacks'
    },
    {
      candidatename: 'Alex',
      email: 'alex@gmail.com',
      skills: 'Python',
      interest: 'backend',
      projectdescription:'Worked in Cal Hacks'
    },
    {
      candidatename: 'Lio',
      email: 'lio@gmail.com',
      skills: 'C#',
      interest: 'full-stack',
      projectdescription:'Worked in Cal Hacks'
    },
  ];

  jobList = [
    {
      jobid: '1',
      jobtitle: 'Software Engineer'
    },
    {
      jobid: '2',
      jobtitle: 'Program Manager'
    },
    {
      jobid: '3',
      jobtitle: 'Human Resource'
    },
  ]

  
  currentCardIndex = 0; // Initialize with the first user
  currentCard = this.cardData[this.currentCardIndex]; // Initialize the current card
  
  swipeLeft() {
    this.isSwipedLeft = true;
    // Handle left swipe logic here
    this.toggleUser();
  }
  
  swipeRight() {
    this.isSwipedRight = true;
    // Handle right swipe logic here
    this.toggleUser();
  }
  
  toggleUser() {
    this.isSwipedLeft = false;
    this.isSwipedRight = false;
    this.currentCardIndex = (this.currentCardIndex + 1) % this.cardData.length;
    this.currentCard = this.cardData[this.currentCardIndex];
  }
}
