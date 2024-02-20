import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrl: './number.component.css'
})
export class NumberComponent {

  // {transform: numberAttribute}
  // @Input({alias: 'num'})
  @Input()
  value: number=0

  @Input()
  min: number=0

  @Input()
  max: number=16
  neg: number=1
  status!: string

  @Output()
  selected=new Subject<number>()

  incr(num: number) {
    // check when exceed min/max

    this.value += num

    if (this.value <= this.min) {
      this.value = this.max
    } else if (this.value > this.max) {
      this.value = this.min 
    }
    
    console.info(">>>", this.value)
    this.selected.next(this.value)
  }

  negative() {
    this.neg *= -1

    if (this.neg < 0) {
      this.status = "negative"
    } else {
    this.status = "positive"
    }
    console.info(">>>", this.status)
   
  }

  pressed() {
    console.info(">>>", this.value*this.neg)
    this.selected.next(this.value * this.neg)

  }

}
