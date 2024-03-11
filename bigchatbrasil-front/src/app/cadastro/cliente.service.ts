import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  userSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  user$ = this.userSubject.asObservable();

  constructor(public httpClient: HttpClient) {
  }

  public _host = 'http://localhost:8080/cliente';

  public findOne(email: string): Observable<any> {
    return this.httpClient.get<any>(
      `${this._host}/find-by-email/${email}`)
  };

  public save(cliente: any): Observable<any> {
    return this.httpClient.post<string>(
      `${this._host}`,
      cliente)
  };


}
