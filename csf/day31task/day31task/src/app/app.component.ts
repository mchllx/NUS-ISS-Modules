import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  numList: number[] = [1, 2]

  @Input()
  newValue: number = 0
  firstValue: number = 0
  secondValue: number = 0

  process(value: number, n: number) {
    console.log(">>> app.component received", value)
    console.log(">>> comp", n)
    console.log(">>> app.component before", this.newValue)

    switch (n) {
      case 1:
        this.firstValue = value
        break
      case 2:
        this.secondValue = value
        break
    }

    this.newValue = this.firstValue + this.secondValue

    //check if over max or under min (neg value)
    if (this.newValue > 30) {
      console.log(">>> over max")
      alert("over max")
      this.newValue = 30
    } else if (this.newValue < 0) {
      console.log(">>> under min")
      this.newValue = 0
    } 

    console.log(">>> app.component after", this.newValue)

  }

}
