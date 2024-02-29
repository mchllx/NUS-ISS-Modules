import { AfterViewInit, Component, Input, OnInit, ViewChild, inject } from '@angular/core';
import { Pokemon } from '../models';
import { Observable, of, switchMap, tap } from 'rxjs';
import { PokemonService } from '../services/pokemon.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrl: './pokemon.component.css'
})
export class PokemonComponent implements OnInit, AfterViewInit{
  
  private pokeSvc: PokemonService = inject(PokemonService)
  private router = inject(Router)

  pokemon$!: Observable<Pokemon[] | undefined >
  displayedColumns = ['name', 'url','image', 'abilities', 'view'];

  name!: string
  limit: number = 10000
  offset: number = 0

  length: number = 0
  pageIndex: number = 0
  pageSize: number = 10
  pageEvent!: PageEvent

  @Input()
  newPokemon: Pokemon[] = []
  dataSource = new MatTableDataSource<Pokemon>(this.newPokemon)
  
  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngAfterViewInit(): void {
    console.info('>>>initialising viewchild')
    this.dataSource.paginator = this.paginator
  }

  ngOnInit() {
    const term = this.activatedRoute.snapshot.params['term']
    this.name = term

    console.info('>>>initialising', this.name)
    this.load(this.name)
  }

  private paginator!: MatPaginator;

  // loads once upon initalisation, loads again after subscription pipeline completes
  @ViewChild(MatPaginator) set matPaginator(mp: MatPaginator) {
    console.log("loading page")
    this.paginator = mp;
    this.dataSource.paginator = this.paginator;
  }

  load(name: string) {
    // // return of(undefined) to avoid type error
    this.pokemon$ = this.pokeSvc.getPoke(name, this.limit, this.offset)
    .pipe(
      switchMap((value: Pokemon[]) => {

        if (value === undefined ) {
          return of(undefined)

        } else {
          for (let index = 0; index < value.length; index++) {
            const p = value[index]

            console.info('>>>retrieving search data', p)
            this.newPokemon.push(p)

          }
        }
        return of(value)
      }),

      tap(value => {
        console.info('>>> value', value)
      })
    )
  }

  onClick(id: number) {
    console.info(">>>retrieving id", id)
    this.router.navigate(['/pokemon',id])

  }

  back() {
    console.log('>>>previous page')
    this.router.navigate(['/search'])
  }
}

  // Doesn't work for async data
  // handlePage(e: PageEvent) {
  //   console.info(">>>changing page")
  //   this.pageEvent = e
  //   this.length = e.length
  //   this.pageSize = e.pageSize
  //   this.pageIndex = e.pageIndex
  // }

// newPokemon: Pokemon[] = [
  //   {
  //     id: 0,
  //     name: "test",
  //     url: "https://db.pokemongohub.net/images/official/full/568.webp",
  //     abilities: ["test1, test2"]
  //   }
  // ]