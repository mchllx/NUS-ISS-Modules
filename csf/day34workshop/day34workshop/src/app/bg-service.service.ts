import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, lastValueFrom } from 'rxjs';
import { Game } from './models';

@Injectable({
  providedIn: 'root'
})
export class BgServiceService {

  //injection
  constructor(private http: HttpClient) {
  }

  searchBoardgame(name: string): Observable<Game[]> {
    console.info('in search board game')

        //set params, best practice
        const params = new HttpParams()
            .set("name", name)
            // .set("email", "fred@gmail.com")

        return this.http.get<Game[]>('http://localhost:8080/api/games/search', {params})

  }

  //GET http://localhost:8080/api/games/search?gameid=123
  //promises = firstValuefrom or lastValueFrom
  // searchBoardgamePromise(name: string): Promise<Game> {
  //   return lastValueFrom(this.http.get<Promise[]>(this.searchBoardgamePromise(name)))
  //   // this.searchBoardgame(name)
  // }

  //http://localhost:8080/review/game/174430
  searchCommentsPromise(gameId: number): Promise<Comment[]> {
    return lastValueFrom(
      this.http.get<Comment[]>(`http://localhost:8080/review/game/${gameId}`))
  }

}
