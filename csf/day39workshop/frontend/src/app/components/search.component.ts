import { Component, OnDestroy, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject, of, switchMap, tap } from 'rxjs';
import { Pokemon } from '../models';
import { PokemonService } from '../services/pokemon.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit, OnDestroy {

  private fb: FormBuilder = inject(FormBuilder)
  private pokeSvc: PokemonService = inject(PokemonService)
  private router = inject(Router)

  pokemon$!: Observable<Pokemon | undefined>
  pokemon: Pokemon[] = []

  form!: FormGroup

  ngOnInit(): void {
    this.form = this.createSearchForm()
  }

  ngOnDestroy(): void {
    this.form.reset()
  }

  constructor() {
  }

  // @Output()
  // newSearch = new Subject<Pokemon>()

  // @ts-ignore
  search() : void  {
    const p = this.form.value as Pokemon
    // const pokeName: string = this.form.value['name']
    console.log('>>> Searching:', p.name)
    this.router.navigate(['/search', p.name])
  }

  private createSearchForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required])
    })
  }

}
