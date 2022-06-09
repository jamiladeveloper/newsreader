import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http : HttpClient) { }

  getAllCategories() : Observable<any>{
    return this.http.get("http://localhost:8080/categories/all");
  }

  getAllFeeds() : Observable<any> {
    return this.http.get("http://localhost:8080/rssfeed/all");
  }

  getConfiguration() : Observable<any> {
    return this.http.get("http://localhost:8080/configuration/get");
  }

  getFeedByCatId(categoryId : string) : Observable<any> {
    return this.http.get("http://localhost:8080/rssfeed?id=" + categoryId);
  }

  createNewCategory(categoryName : string) {

  }

  addNewRssFeed(categoryName : string, rssLink : string) {

  }

}
