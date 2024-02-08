import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrl: './test.component.css'
})
export class TestComponent implements OnInit {
  
  @Input('parentData')
  greeting!: string

  @Output()
  public eventEmitter: EventEmitter<any> = new EventEmitter();

  constructor() {
  }
  
  ngOnInit() : void {
    throw new Error('Method not implemented')
  }

  sendItem(itemName: any) : void {
    this.eventEmitter.emit(itemName);
  }

}
