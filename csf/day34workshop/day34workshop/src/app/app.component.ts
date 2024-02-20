import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BgServiceService } from './bg-service.service';
import { Observable, tap } from 'rxjs';
import { Game } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements OnInit {

  private fb = inject(FormBuilder)
  private bgSvc = inject(BgServiceService)

  form!: FormGroup
  search$!: Observable<Game[]>
  searchP$!: Promise<Game[] | undefined >
  commentP$!: Promise<Comment[] | undefined >

  processForm() {
    const searchValue : any = this.form.value['search']
    console.info('>>> value: ', searchValue)

    // check chuk's code for promise pipeline here
    // this.searchP$ = this.bgSvc.searchBoardgamePromise(searchValue)
    //   .then(result => {
    //     console.info('>>> result', result)
    //     return result
    //   })

      //return undefined (returns nothing in the value, is not a null)

      //javascript true and false is not boolean
      //if string is empty or undefined = false, vice versa


    //calls service
    this.search$ = this.bgSvc.searchBoardgame(searchValue)
      .pipe(
        tap(value => {
          console.info('>>> value:', value)
        })
      )
  }

  ngOnInit(): void {
    //creates form upon initiation
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      search: this.fb.control<string>('', [Validators.required])
    })
  }
}