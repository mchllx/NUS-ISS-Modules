import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Posts } from './posts';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  baseURL: string = "http://localhost:3000/posts"

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })

  }

  httpOptions2: any = new HttpHeaders().set('Content-Type', 'application/json')

  constructor(private httpClient: HttpClient) {
  }

  getAllPosts(): Observable<Posts[]> {
    return this.httpClient.get<Posts[]>(this.baseURL).pipe(
      catchError(this.errorHandler)
    )  
  }

  createPost(post: Posts):Observable<Posts> {
    return this.httpClient.post<Posts>(this.baseURL, post, this.httpOptions)
  }

  editPost(id: number, post:Posts) : Observable<Posts> {
    return this.httpClient.put<Posts>(this.baseURL + `/${id}`, post, this.httpOptions)
  }

  deletePost(id: number): Observable<Posts> {
    return this.httpClient.delete<Posts>(this.baseURL + `/${id}`, this.httpOptions)
  }

  //method 2
  deletePost2(id: number): Observable<Posts> {
    return this.httpClient.delete<Posts>(this.baseURL + `/${id}`, {headers: this.httpOptions2})
  }

  getPostbyId(id: number) : Observable<Posts> {
    return this.httpClient.get<Posts>(this.baseURL + `/${id}`, {headers: this.httpOptions2})
  }

  //custom error message
  errorHandler(error: any) {
    let errorMessage: any = ''

    if (error.error instanceof ErrorEvent) {
      errorMessage = error.errorMessage
    } else {
      errorMessage = `Error code: ${error.status}` 
    }

    return throwError(() => new Error(errorMessage))

  }

  
}
