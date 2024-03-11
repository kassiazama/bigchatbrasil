import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { ReactiveFormsModule } from "@angular/forms";
import { NgxMaskDirective, provideNgxMask } from "ngx-mask";
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { GerenciadorComponent } from './gerenciador/gerenciador.component';
import { ShowErrorDirective } from "./config/show-error-directive";
import { MensagemComponent } from './mensagem/mensagem.component';
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { ToastrModule, ToastrService } from "ngx-toastr";
import { ModalModule } from "ngx-bootstrap/modal";

@NgModule({
  declarations: [
    AppComponent,
    CadastroComponent,
    LoginComponent,
    MenuComponent,
    GerenciadorComponent,
    ShowErrorDirective,
    MensagemComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgxMaskDirective,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    ModalModule.forRoot(),

  ],
  providers: [provideNgxMask(), ToastrService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
