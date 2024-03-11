import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MensagemService } from "./mensagem.service";
import { ClienteService } from "../cadastro/cliente.service";
import { PlanoService } from "../cadastro/plano.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'app-mensagem',
  templateUrl: './mensagem.component.html',
  styleUrls: ['./mensagem.component.css']
})
export class MensagemComponent implements OnInit {
  form: FormGroup;
  saldo: any;

  get isPrePago(): boolean {
    return this.saldo?.tipoPlano === 'PRE_PAGO'
  }
  constructor(private _formBuilder: FormBuilder,
              private _mensagemService: MensagemService,
              private _clienteService: ClienteService,
              private _toastr: ToastrService,
              private _planoService: PlanoService) {
  }

  ngOnInit() {
    this.form = this._createForm();

    this._clienteService.user$.subscribe(cliente => {
      this.form.get('cliente').setValue(cliente);
      this._planoService.findSaldoByCliente(this.form.get('cliente')?.value?.id).subscribe({
        next: (s) => {
          this.saldo = s;
        }
      });
    });

  }

  private _createForm() {
    return this._formBuilder.group({
      id: [null],
      cliente: [null, Validators.required],
      telefone: [null, Validators.required],
      mensagem: [null, Validators.required],
      isWhatsApp: [false],
    })
  }

  save() {
    this._mensagemService.save(this.form.getRawValue()).
    subscribe({
      next: (s) => {
        this.saldo = s;
        this._toastr.success('Mensagem enviada com sucesso!')
      }, error: (e) => {
        this._toastr.error('Mensagem não enviada!', e.error.message)
      }
    });
  }

}
