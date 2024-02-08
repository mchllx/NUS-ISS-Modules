import { Component, OnInit, ViewChild } from '@angular/core';
import { ViewchildComponent } from './components/viewchild.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {

  @ViewChild(ViewchildComponent)
  childComponent!: ViewchildComponent;

  greeting: string="Message from parent"
  public Item: any = "";
  public isOnline!: boolean

  ngOnInit(): void {
    this.updateOnlineStatus()

    window.addEventListener('online', this.updateOnlineStatus.bind(this))
    window.addEventListener('offline', this.updateOnlineStatus.bind(this))
  }

  constructor() {
    this.isOnline = false
  }

  changeViewchildText() : void {
    this.childComponent.changeText();
  }

  addItem(itemName: any): void {
    this.Item = itemName;

  }

  updateOnlineStatus(): void {
    this.isOnline = window.navigator.onLine
    console.info("Connected to internet")
    console.info("isOnline tag:" + [this.isOnline])
  }

}
