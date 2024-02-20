import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrl: './number.component.css'
})

export class NumberComponent implements OnInit, OnDestroy{

  private activatedRoute = inject(ActivatedRoute)

  numDisplay: number = 0
  imgWidth: number = 300

  sub!: Subscription 

  ngOnInit(): void {
    console.info('number OnInt')

    // @PathVariable
    // this.numDisplay = this.activatedRoute.snapshot.params['num']

    this.imgWidth = this.activatedRoute.snapshot.queryParams['width']
    // this.numDisplay = this.activatedRoute.snapshot.queryParams[']
    this.sub = this.activatedRoute.params.subscribe(
      // next
      value => {
        console.info('>>> value', value)
        this.numDisplay = value['num']
      }
    )
  }

  ngOnDestroy(): void {
    console.info('number OnDestroy')
    this.sub.unsubscribe()
  }

}
