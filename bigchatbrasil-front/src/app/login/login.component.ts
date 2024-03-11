import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Subject} from "rxjs";
import {ToastrService} from "ngx-toastr";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";
import {Router} from "@angular/router";
import {ClienteService} from "../cadastro/cliente.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  ngUnsubscribe: Subject<any> = new Subject();
  bsModalRef: BsModalRef;



  @ViewChild('template') template: TemplateRef<any>;

  constructor(private _formBuilder: FormBuilder,
              private _toastr: ToastrService,
              private _router: Router,
              private _clienteService: ClienteService,
              private _modal: BsModalService) {
  }

  ngOnInit() {
    this.form = this._createForm();

  }

  private _createForm() {
    return this._formBuilder.group({
      email: [null],
    })
  }

  closeModal() {
    this.bsModalRef.hide();
  }

  login() {
    this._clienteService.findOne(this.form.get('email').value).subscribe({
      next: (result) => {
        if (result) {
          this._toastr.success('Login efetuado com sucesso!')
          this._router.navigate(['/mensagem']);
          this._clienteService.userSubject.next(result)
        } else {
          this._openModal();
        }
      }, error: () => {
        this._toastr.warning('Por favor, tente novamente', 'Não foi possível realizar o login')
      }
    })
  }

  private _openModal() {
    this.bsModalRef = this._modal.show(this.template);
  }

}

