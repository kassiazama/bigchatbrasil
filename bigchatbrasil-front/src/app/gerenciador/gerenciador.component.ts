import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { ClienteService } from "../cadastro/cliente.service";
import { PlanoService } from "../cadastro/plano.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'app-gerenciador',
  templateUrl: './gerenciador.component.html',
  styleUrls: ['./gerenciador.component.css']
})
export class GerenciadorComponent implements OnInit {
  form: FormGroup;
  formPessoa: FormGroup;
  limiteChanged: boolean = false;

  get tipoPlanoValue(): string {
    return this.form.get('tipoPlano').value;
  }

  constructor(private _formBuilder: FormBuilder,
              private _clienteService: ClienteService,
              private _toastr: ToastrService,
              private _planoService: PlanoService) {
  }

  ngOnInit() {
    this.form = this._createForm();
    this.formPessoa = this._createFormPessoa();

    this._clienteService.user$.subscribe(cliente => {
      this.formPessoa.reset(cliente);
      this._planoService.findByCliente(cliente?.id).subscribe({
        next: (p) => {
          this.form.reset(p)
        }
      })
    });


    this.form.get('limiteTotal').valueChanges.subscribe((r) => {
      if (r) {
        this.limiteChanged = true;
      }
    });
  }


  private _createFormPessoa() {
    return this._formBuilder.group({
      id: [null],
      nomeCompleto: [null],
      email: [null],
      telefone: [null],
      cpf: [null],
      cnpj: [null],
      nomeEmpresa: [null],
    })
  }

  private _createForm() {
    return this._formBuilder.group({
      id: [null],
      cliente: [null],
      tipoPlano: [null],
      saldoConsumido: [null],
      saldoPrePagoTotal: [null],
      saldoAdicionado: [null],
      limiteTotal: [null],
    })
  }

  atualizarSaldo() {
    let saldo = this.form.get('tipoPlano').value === 'PRE_PAGO' ? this.form.get('saldoAdicionado').value : this.form.get('limiteTotal').value;
    let mensagem = this.form.get('tipoPlano').value === 'PRE_PAGO' ? 'Saldo adicionado com sucesso!' : 'Limite atualizado com sucesso!'

    this._planoService.atualizarSaldo(this.form.get('id').value, this.form.get('tipoPlano').value,
      saldo).subscribe({
      next: (p) => {
        this.form.reset(p)
        this._toastr.success(mensagem)
      }
    })
  }

  atualizarPlano() {
    this._planoService.atualizarPlano(this.form.get('id').value, this.form.get('tipoPlano').value).subscribe({
      next: (p) => {
        this.form.reset(p)
        this._toastr.success('Plano atualizado com sucesso!')
      }
    })
  }
}
