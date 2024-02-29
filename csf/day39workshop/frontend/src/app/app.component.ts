import { Component } from '@angular/core';
import { Pokemon } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  pokemon!: Pokemon[]

  newPokemon(pokemon: Pokemon) {

    // let id = uuid().substring(0,8).toString()
    // parseInt()

    let newPoke: Pokemon = {
      id: pokemon.id,
      name: pokemon.name,
      url: pokemon.url,
      image: pokemon.image, 
      abilities: pokemon.abilities
    }

    console.log('>>> Loading:', pokemon.name)

    this.pokemon.push(newPoke)

  }

}
