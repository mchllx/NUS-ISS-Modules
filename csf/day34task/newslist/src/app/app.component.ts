import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, OnDestroy {

  private fb = inject(FormBuilder)

  form!: FormGroup
  valueSub!: Subscription
  statusSub!: Subscription

  formStatus: boolean = false

  //angular convention $ = observable
  formStatus$!: Observable<boolean>

  //subscription must be handled
  ngOnInit(): void {
    //creates form upon initiation
    this.form = this.createForm()

    // if this is not assigned, it is always subscribed, assign to var to unsubscribe later
    // this.form.valueChanges.subscribe({
    //   next: (values) => {
    //     console.info('form values:', values)
    //   }
    // })

    //assign subscription
    this.valueSub = this.form.valueChanges.subscribe({
      next: (values) => {
        console.info('form values:', values)
      }
    })

    // this.statusSub = this.form.statusChanges.subscribe({
    //   next: (status) => {
    //     console.info('form status:", status')
    //   }
    // })

    

    //tap = side effect
    this.statusSub = this.form.statusChanges
    .pipe(
      map(status => "VALID" === status ),
      // tap(status => this.formStatus === status)
    )
      .subscribe({
        next: (status) => {
          console.info('form status', status)
        }
      })

      //produces observable of boolean type
      this.formStatus$
        .pipe(
          map(status => 'VALID' === status)
        )
  }

  //when it leaves the instance (??), for e.g. invalidate session
  ngOnDestroy(): void {
    //must be destroyed, if not there will be a memory leak
    this.valueSub.unsubscribe()
    this.statusSub.unsubscribe()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      email: this.fb.control<string>('', [Validators.required])
    })
  }
}
