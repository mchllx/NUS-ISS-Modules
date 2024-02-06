import { Component, Input, Output, numberAttribute } from '@angular/core';
import { BehaviorSubject, Observable, Subject, first } from 'rxjs';

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrl: './number.component.css'
})
export class NumberComponent {

  // {transform: numberAttribute}
  @Input()
  number: string="0"
  intNum: number=parseInt(this.number) 

  min: string="0"
  max: string="30"
  value: string="5"

  @Input()
  url: string="./assets/numbers/number"
  ext: string=".jpg"

  @Output()
  numSelected=new Subject<number>()

  // initialValue=new Subject<string>()

  process($event: any) {
    this.numSelected.next(parseInt($event.target.number))
    console.info(">>>number.components range: ", parseInt($event.target.number))

  }

  up() {
    console.info(">>>number.components up by one:", this.intNum--)
    this.numSelected.next(-1)
  }

  down() {
    console.info(">>>number.components down by one:", this.intNum++)
    this.numSelected.next(+1)
  }

}
