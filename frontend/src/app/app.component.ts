import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  constructor() {}

  // Function to toggle the sidebar
  toggleSidebar() {
    // You can implement the logic here to open or close the sidebar
    // You may use an Angular service or Input/Output to communicate with the sidebar component
  }
}
