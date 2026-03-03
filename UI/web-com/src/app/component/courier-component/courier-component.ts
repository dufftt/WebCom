import { Component } from '@angular/core';

// 1. Define the Data Class/Interface
export interface Carrier {
  carrierID: number;
  carrierName: string;
  days: number;
  price: number;
}

@Component({
  selector: 'app-courier-component',
  templateUrl: './courier-component.html',
  styleUrls: ['./courier-component.scss']
})
export class CourierComponent {
  // 2. Create the list of data
  carrier: Carrier[] = [
    { carrierID: 1, carrierName: 'FedEx', days: 1, price: 20.00 },
    { carrierID: 2, carrierName: 'UPS', days: 2, price: 15.50 },
    { carrierID: 3, carrierName: 'USPS', days: 5, price: 8.99 }
  ];

  // 3. Track the selection (initialize with the first one)
  selectedCarrier: Carrier = this.carrier[0];

  continueToPayment() {
    console.log('Selected Carrier:', this.selectedCarrier);
    // Navigate to next step...
  }
}
