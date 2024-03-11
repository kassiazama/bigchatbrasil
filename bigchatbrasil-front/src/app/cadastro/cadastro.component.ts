import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { ClienteService } from "./cliente.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {
  form: FormGroup;

  constructor(private _formBuilder: FormBuilder,
              private _router: Router,
              private _toastr: ToastrService,
              private _clienteService: ClienteService) {
  }

  ngOnInit() {
    this.form = this._createForm();
  }

  private _createForm() {
    return this._formBuilder.group({
      id: [null],
      nomeCompleto: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      telefone: [null, Validators.required],
      cpf: [null, Validators.required],
      cnpj: [null, Validators.required],
      nomeEmpresa: [null, Validators.required]
    })
  }

  save() {
    this._clienteService.save(this.form.getRawValue()).subscribe({
      next: (result) => {
        this._toastr.success('Cadastro realizado com sucesso!')
        this._router.navigate(['/mensagem']);
        this._clienteService.userSubject.next(result)
      }, error: () => {
        this._toastr.warning('Por favor, tente novamente', 'Não foi possível realizar o cadastro')
      }
    })
  }

}
