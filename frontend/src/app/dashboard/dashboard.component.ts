import { Component} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
    isSwipedLeft = false;
    isSwipedRight = false;

    // Define JSON data for the card content
    cardData = [
      {
        name: 'Ripple',
        experience: 0,
        location: 'San Francisco',
        work: 'Yes'
      },
      {
        name: 'Convex',
        experience: 3,
        location: 'San Jose',
        work: 'No'
      },
      {
        name: 'Wisper',
        experience: 5,
        location: 'New York',
        work: 'Yes'
      },
      {
        name: 'zilliz',
        experience: 3,
        location: 'Chicago',
        work: 'No'
      }
    ];
  

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
