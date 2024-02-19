import { Component, Input, OnInit, inject } from '@angular/core';
import { Cart } from '../models';
import { InventoryComponent } from './inventory.component';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  @Input()
  newCart = new Subject<Cart>()

  ngOnInit(): void {
  }

  cart: Cart[] = []
  
  updateCart(): void {
    console.log(this.cart.values)
  }

  removeCart(j: number): void {
    console.log(j)
    console.log(this.cart)
    console.log("removing item from cart") 
  }

}
