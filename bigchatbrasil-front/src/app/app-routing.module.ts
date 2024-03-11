import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from "./cadastro/cadastro.component";
import { LoginComponent } from "./login/login.component";
import { GerenciadorComponent } from "./gerenciador/gerenciador.component";
import { MensagemComponent } from "./mensagem/mensagem.component";

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'cadastrar', component: CadastroComponent },
  { path: 'gerenciador', component: GerenciadorComponent },
  { path: 'mensagem', component: MensagemComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
