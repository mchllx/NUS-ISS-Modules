import { AfterViewInit, Component, OnInit, inject } from '@angular/core';
import { PokemonService } from '../services/pokemon.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Comment, Pokemon } from '../models';
import { Observable, Subscriber, Subscription, of, switchMap, tap } from 'rxjs';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent implements OnInit, AfterViewInit{
  p!: Pokemon
  c!: Comment[]
  comments$!: Observable<Comment[] | undefined> 

  private pokeSvc: PokemonService = inject(PokemonService)
  private router = inject(Router)
  private subscription!: Subscription

  name!: string
  pid!: number
  detail$!: Promise<Pokemon>

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngAfterViewInit() {
    this.comments$ = this.pokeSvc.getCommentByPid(this.pid)
  }

  ngOnInit() {
    this.subscription = this.activatedRoute.params.subscribe(params => {
      const term = params['term'];
      console.log('id', term);
      this.pid = term;
    })

      detail$: this.pokeSvc.getPokeById(this.pid)
        .then(
        value => {
          console.log('>>>value', value)
          this.p = {
            id: value.id,
            name: value.name,
            url: value.url,
            image: value.image,
            abilities: value.abilities
          }

          console.log('>>>retrieving pokemon details', value)
        })
        .catch(
          error => {
          console.log('>>>type error:', error)
        }
      )
    
      this.comments$ = this.pokeSvc.getCommentByPid(this.pid)
        .pipe(
          switchMap((value: Comment[]) => {

            if (value === undefined ) {
              return of(undefined)
    
            } else {
              for (let index = 0; index < value.length; index++) {
                const c = value[index]
    
                console.info('>>>retrieving comments from server', c)
                this.c = []
                this.c.push(c)
              }
            }
            return of(value)
          }),
    
          tap(value => {
            console.info('>>> value', value)
          })
        )
    }
        
  onClick(pid: number) {
    console.log('>>>loading comment form', pid)
    this.router.navigate(['/comment', pid])

  }

  back() {
    console.log('previous page')
    this.router.navigate(['/search'])
  }

}
