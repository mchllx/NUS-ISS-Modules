import { Component, Input } from '@angular/core';
import { Cart, Inventory } from './models';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  cart: Cart[] = []
  inventory!: Inventory[]

  createItem(inven: Inventory) {

    // let id = uuid().substring(0,8).toString()
    let quantity = 1

    let newCart: Cart = {
      id: parseInt(inven.id), 
      inven_id: inven.id,
      image: inven.image,
      description: inven.description, 
      quantity: quantity
    }

    //check if item exists or cart empty
    if (this.hasItemById(newCart.id, newCart) === true) {
      console.log("updating item in cart")
    } else {
      console.log("adding to cart")
    }
  }

  // isEmpty(newCart: Cart): boolean {
  //   if (this.cart.length < 1) {
  //     console.info("cart is empty")
  //     return true
  //   }

  //   console.info("cart is not empty")
  //   return false
  // }

  //check if item is already in cart 
  hasItemById (id: number, newCart: Cart): boolean {
      
    let size = this.cart.length

    for (let i = 0 ; i < size; i++) {
      if (this.cart[i].id === newCart.id) {
        console.log("item exists")
        this.cart[i].quantity ++
        return true
      }
    }

    console.log("item does not exist")
    this.cart.push(newCart)
    return false
  } 
}
