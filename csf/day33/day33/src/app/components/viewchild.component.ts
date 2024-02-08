import { Component } from '@angular/core';

@Component({
  selector: 'app-viewchild',
  templateUrl: './viewchild.component.html',
  styleUrl: './viewchild.component.css'
})
export class ViewchildComponent {

  //definite assignment
  // message !:string

  message: string= "String message"

  changeText() : void {
    this.message="String updated by Viewchild"
  }

}
