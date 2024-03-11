import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MensagemService {
  constructor(public httpClient: HttpClient) {
  }

  public _host = 'http://localhost:8080/mensagem';

  public save(mensagem: any): Observable<any> {
    return this.httpClient.post<string>(
      `${this._host}/save-registra-consumo`,
      mensagem)
  };


}

