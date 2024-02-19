import { Component, Input, OnInit, inject } from '@angular/core';
import { Cart } from '../models';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  @Input()
  newCart: Cart[] = []

  removeCart(c: Cart): void {
    console.log(c)
    if (c.quantity > 1) {
      console.log("updating item") 
      c.quantity--
    } else {
      console.log("removing item from cart") 
      this.removeItem(c)
    } 
  }

  removeItem(c: Cart): boolean {
    let size = this.newCart.length

    for (let i = 0 ; i < size; i++) {
      if (this.newCart[i].id === c.id) {
        this.newCart.splice(i, 1)
        return true
      }
    }

    console.log("item not found")
    return false
  }

}
