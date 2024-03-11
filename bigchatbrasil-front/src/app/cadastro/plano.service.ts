import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PlanoService {

  constructor(public httpClient: HttpClient) {
  }

  public _host = 'http://localhost:8080/plano';

  public findByCliente(id: number): Observable<any> {
    return this.httpClient.get<any>(
      `${this._host}/find-by-cliente/${id}`)
  };

  public findSaldoByCliente(id: number): Observable<any> {
    return this.httpClient.get<any>(
      `${this._host}/find-saldo-by-cliente/${id}`)
  };

  public atualizarSaldo(id: number, tipoPlano: string, saldo: number): Observable<any> {
    return this.httpClient.get<any>(
      `${this._host}/atualizar-saldo/${id}/${tipoPlano}/${saldo}`)
  };

  public atualizarPlano(id: number, tipoPlano: string): Observable<any> {
    return this.httpClient.get<any>(
      `${this._host}/atualizar-plano/${id}/${tipoPlano}`)
  };

}
