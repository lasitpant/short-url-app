import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {Url} from "./short-url/url";
import { retry, catchError } from 'rxjs/operators';
import {Stats} from "./short-url/stats";

@Injectable({
  providedIn: 'root'
})
export class UrlService implements  OnInit {

  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  ngOnInit(){

  }

  generateShortLink(payload: Url): Observable<any> {
    return this.http.post<any>(this.apiUrl + '/generate-short-link/', payload)
      .pipe(map(response => {
        return response;
      }));
  }

  getBrowserStats(): Observable<Stats[]>{
    return this.http.get<any>(this.apiUrl+'/browser-stats/')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }
  getDeviceStats(): Observable<Stats[]>{
    return this.http.get<any>(this.apiUrl+'/device-stats/')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }
  getOsStats(): Observable<Stats[]>{
    return this.http.get<any>(this.apiUrl+'/os-stats/')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
