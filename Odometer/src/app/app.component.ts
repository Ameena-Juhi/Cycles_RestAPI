import { Component } from '@angular/core';
import { Odometer } from './Odometer';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Odometer';
  odometer = new Odometer(4);
  value: number = 1;

  constructor() { }

  setReading(value: number): void {
    this.odometer.setReading(value);
  }

  getNextReading(): void {
      this.odometer.incrementReading();
    
  }

  getPreviousReading(): void {
      this.odometer.decrementReading();
    
  }

  reset():void{
    this.odometer = new Odometer(4); 
  }
}
