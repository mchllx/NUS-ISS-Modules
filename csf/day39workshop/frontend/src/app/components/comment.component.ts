import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PokemonService } from '../services/pokemon.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../models';
import { v4 as uuidv4 } from 'uuid';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.css'
})
export class CommentComponent implements OnInit {

  private fb: FormBuilder = inject(FormBuilder)
  private pokeSvc: PokemonService = inject(PokemonService)
  private router = inject(Router)

  form!: FormGroup

  pid!: number

  comment$: Promise<void> | undefined

  ngOnInit(): void {
    this.form = this.createCommentForm()

    const term = this.activatedRoute.snapshot.params['term']
    console.log('pid', term)
    this.pid = term 
  }

  ngOnDestroy(): void {
    this.form.reset()
  }

  constructor(private activatedRoute: ActivatedRoute) {
  }

  // @Output()
  // newSearch = new Subject<Pokemon>()

  // @ts-ignore
  postComment() : void  {
    const c = this.form.value as Comment
      c.pid = this.pid
      c.id = uuidv4().substring(0,8)
      c.timestamp = new Date()
    // const pokeName: string = this.form.value['name']
    console.log('>>> Posting comment:', c)
    
    this.comment$ = this.pokeSvc.postComment(c.id, c)
    .then(
      value => {
        console.log('awaiting response from server')

        alert(`successful: ${value.name}, ${value.text}, ${value.timestamp}`);

        console.log('>>>ang: successful', value)
      })
      .catch(
        error => {
          console.log('server error', error)
          alert('failed')
        }
      )

  }

  private createCommentForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(4)]),
      text: this.fb.control<string>('', [Validators.required, Validators.minLength(1), Validators.maxLength(120)])
    })
  }

  back() {
    console.log('going back to previous page')
    this.router.navigate(['/pokemon', this.pid])
  }

}
