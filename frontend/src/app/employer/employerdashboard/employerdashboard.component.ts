import { Component } from '@angular/core';

@Component({
  selector: 'app-employerdashboard',
  templateUrl: './employerdashboard.component.html',
  styleUrls: ['./employerdashboard.component.css']
})
export class EmployerdashboardComponent {
  cardData = [
    {
      name: 'John',
      password:'12345'
    }]

    currentCardIndex = 0; // Initialize with the first user
    currentCard = this.cardData[this.currentCardIndex]; // Initialize the current card
}
