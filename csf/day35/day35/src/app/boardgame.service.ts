import { Injectable } from "@angular/core";
import { Game } from "./models";
import { Observable } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";

@Injectable()
export class BoardgameService {

    //old: private http = inject(HttpClient)
    constructor(private http: HttpClient) {
    }

    // GET http://localhost:8080/api/games/search?name=abc
    //returns an array of games [{},{},{}]
    searchBoardgame(name: string): Observable<Game[]> {

        console.info('in search board game')

        //set params, best practice
        const params = new HttpParams()
            .set("name", name)
            // .set("email", "fred@gmail.com")

        // this.http.get<Game[]>('http://localhost:8080/api/games/search', {k: v})
        // this.http.get<Game[]>('http://localhost:8080/api/games/search', {params: params})
        // returns an observable
        return this.http.get<Game[]>('http://localhost:8080/api/games/search', {params})

        //multiple params (error-prone, so just set params)
        // return this.http.get<Game[]>('http://localhost:8080/api/games/search', {
        //     params: {
        //         name: name,
        //         email: "fred@gmail.com"
        //     }
        // })

    }

}