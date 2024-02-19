import { Component, OnInit, Output, inject } from '@angular/core';
import { Inventory, Cart } from '../models';
import { Subject } from 'rxjs';
import { CartComponent } from './cart.component';
import { v4 as uuid } from 'uuid';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrl: './inventory.component.css'
})

export class InventoryComponent{

  cart: Cart[] = []

  //send data from inventory (child) to cart (parent)
  @Output()
  newItem = new Subject<Inventory>()

  inventory: Inventory[] = [{
    id: "1",
    image: "https://marketfresh.com.sg/cdn/shop/products/IMG_3961_copy_1024x.jpg?v=1445760100",
    description: "This is an orange"
  },
  {
    id: "2",
    image: "https://www.applesfromny.com/wp-content/uploads/2020/05/20Ounce_NYAS-Apples2.png",
    description: "This is an apple"
  },
  {
    id: "3",
    image: "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/545px-Banana-Single.jpg",
    description: "This is a banana"
  }] 

  addCart(i: Inventory): void {
    console.log('loading')
    this.newItem.next(i)
    
  }

}
