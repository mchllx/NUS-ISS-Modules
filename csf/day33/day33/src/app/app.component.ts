import { Component, OnInit, ViewChild } from '@angular/core';
import { ViewchildComponent } from './components/viewchild.component';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';

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

  form: FormGroup = new FormGroup({});

  labels = ['fish', 'meat', 'vegetables']
  dietArray!: FormArray
  rsvpForm!: FormGroup

  private fb!: FormBuilder
  
  ngOnInit(): void {
    this.updateOnlineStatus()

    window.addEventListener('online', this.updateOnlineStatus.bind(this))
    window.addEventListener('offline', this.updateOnlineStatus.bind(this))

    // this.dietArray = this.fb.array(
    //   this.labels.map(()=>this.fb.control("diet")
    // ),
    //   this.rsvpForm = this.fb.group({
    //   diet:this.dietArray
    // }))
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

  onSwipeUp():void {
    console.info("Swiped up")
  }

}
