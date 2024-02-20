import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription, map, switchMap, tap } from 'rxjs';
import { BoardgameService } from './boardgame.service';
import { Game } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  private fb = inject(FormBuilder)
  private bgSvc = inject(BoardgameService)

  form!: FormGroup
  search$!: Observable<Game[]>
  comment$!: Observable<Comment[]> | undefined

  processForm() {
    const searchValue : any = this.form.value['search']
    console.info('>>> value: ', searchValue)

    //calls service
    //maps always returns a value
    this.comment$ = this.bgSvc.searchBoardgame(searchValue)
      .pipe(
        // @ts-ignore (use sparingly LOL...)
        switchMap((value: Game[]) => {
          if (value.length < 0)
          return undefined

          const g = value[0]

          //this returns a value wrapped in an observable for e.g.Observable[Comment]
          this.bgSvc.searchComments(g.gameId)

        }),
        
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
