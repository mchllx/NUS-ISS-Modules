import { Component, ViewChild } from '@angular/core';
import { ViewchildComponent } from './components/viewchild.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {

  @ViewChild(ViewchildComponent)
  childComponent!: ViewchildComponent;

  greeting: string="Message from parent"

  public Item: any = "";

  changeViewchildText() : void {
    this.childComponent.changeText();
  }

  addItem(itemName: any): void {
    this.Item = itemName;

  }

}
