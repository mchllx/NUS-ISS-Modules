import { Component, Input } from '@angular/core';
import { Subject } from 'rxjs';
import { Cart } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  cart!: Cart[]

  createItem(newItem: Cart) {
    this.cart.push(newItem)
  }
}
