import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';

//@Service
@Injectable({
  providedIn: 'root'
})

export class NewsService {

  apiKey: string = "8b82e6bfc99a4a3ea993395c5f1f304e"
  baseURL: string = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey="

  constructor(private httpClient: HttpClient) { }

  public fetchNewsData(): Promise<any> {
    return lastValueFrom(this.httpClient.get(this.baseURL + this.apiKey))
  }
}
