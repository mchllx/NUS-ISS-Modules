import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, lastValueFrom } from "rxjs";
import { Pokemon, Comment } from "../models";

import { environment } from '../../environments/environment'

const URL = environment.url

// TODO: HttpClient
@Injectable({
    providedIn: 'root'
})

export class PokemonService {

    constructor(private http: HttpClient) {
    }

    // GET http://localhost:8080/api/pokemon?name=pikachu
    getPokeByName(name: string): Observable<Pokemon> {
        console.info('GET: Pokemon by name, connecting to server')

        const params = new HttpParams()
            .set("name", name)

    // return this.http.get<Pokemon[]>("http://localhost:8080/api/pokemon/", {params})
    return this.http.get<Pokemon>(`${URL}/api/pokemon`, {params})
    }

    // GET http://localhost:8080/api/pokemon/25
    getPokeById(id: number): Promise<Pokemon> {
        console.info('GET: Pokemon by ID, connecting to server')

        const params = new HttpParams()
            .set("id", id)

    // return this.http.get<Pokemon[]>(`http://localhost:8080/api/pokemon/${id}`) 
    return lastValueFrom(this.http.get<Pokemon>(`${URL}/api/pokemon/${id}`))
    // return lastValueFrom(this.http.get<Pokemon>(`${URL}/api/pokemon}`, {params}))
    }

    // GET http://localhost:8080/api/pokemon?limit=10&offset=0
    getPoke(name: string, limit: number, offset: number): Observable<Pokemon[]> {
        console.info('GET: Pokemon, connecting to server')

        const params = new HttpParams()
            .set("name", name)
            .set("limit", limit)
            .set("offset", offset)

    return this.http.get<Pokemon[]>(`${URL}/api/pokemon`, {params})
    }

    // GET http://localhost:8080/api/comment?pid=285, shroomish
    getCommentByPid(pid: number): Observable<Comment[]> {
        console.info('GET: Comment by pokeid, connecting to server')

        const params = new HttpParams()
            .set("pid", pid)

    // return this.http.get<Pokemon[]>("http://localhost:8080/api/pokemon/", {params})
    return this.http.get<Comment[]>(`${URL}/api/comment`, {params})
    }

    // POST http://localhost:8080/api/comment/cid=eeeeeeee
    postComment(cid: string, comment: Comment): Promise<Comment> {
        console.info('POST: Comment, connecting to server')

        const params = new HttpParams()
            .set("cid", cid)
        
    return lastValueFrom(this.http.post<Comment>(`${URL}/api/comment/${cid}`, {comment}))
    }

    // // GET http://localhost:8080/api/pokemon?type=electric
    // searchPokeByType(type: string): Promise<Pokemon> {
    //     console.info('GET: Pokemon by type, connecting to server')

    //     const params = new HttpParams()
    //         .set("type", type)

    // return lastValueFrom(this.http.get<Pokemon>(`${URL}/api/pokemon/`, {params}))
    // }




}