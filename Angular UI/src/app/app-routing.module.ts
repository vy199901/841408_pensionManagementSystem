import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { PensionDisburseComponent } from './component/pension-disburse/pension-disburse.component';
import { PensionerInputComponent } from './component/pensioner-input/pensioner-input.component';
import { PensionerListComponent } from './component/pensioner-list/pensioner-list.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  {path:'',component:HomeComponent,pathMatch:'full'},
  {path:'login',component:LoginComponent,pathMatch:'full'},
  {path:'pensionCalculation',component:PensionerInputComponent,pathMatch:'full',canActivate:[AuthGuard]},
  {path:'pensionerlist',component:PensionerListComponent,pathMatch:'full',canActivate:[AuthGuard]},
  {path:'disbursePension',component:PensionDisburseComponent,pathMatch:'full',canActivate:[AuthGuard]},
  {path:'**',component:HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
