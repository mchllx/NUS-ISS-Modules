import { Component, OnInit, Output, inject } from '@angular/core';
import { Inventory, Cart } from '../models';
import { Subject } from 'rxjs';
import { CartComponent } from './cart.component';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrl: './inventory.component.css'
})

export class InventoryComponent implements OnInit{
  
  cart: Cart[] = []

  inventory: Inventory[] = [{
    image: "https://marketfresh.com.sg/cdn/shop/products/IMG_3961_copy_1024x.jpg?v=1445760100",
    description: "This is is an orange"
  },
  {
    image: "https://www.applesfromny.com/wp-content/uploads/2020/05/20Ounce_NYAS-Apples2.png",
    description: "This is an apple"
  },
  {
    image: "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/545px-Banana-Single.jpg",
    description: "This is a banana"
  }]

  ngOnInit(): void {
    this.inventory
  }

  //send data from inventory (child) to cart (parent)
  @Output()
  newItem = new Subject<Cart>()

  addCart(j: number): void {
    console.log('adding to cart')
    const quantity = 1

    this.newItem.next({
      inventory: {
        image: this.inventory[j].image,
        description: this.inventory[j].description,
    },
      quantity: quantity
    } 
    )

    console.log(this.cart.length)
    console.log('added', quantity, 'to cart')

  }

}
