import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminRegisterComponent } from './admin-register/admin-register.component';
import { AuthGuard } from './guards/auth.guard';
import { LoginComponent } from './login/login.component';
import { QueryResultComponent } from './query-result/query-result.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [

  { path:"login", component: LoginComponent},
  {path:"", component:LoginComponent},
  { path:"register", component: RegisterComponent},
  { path:"adminLogin" , component: AdminLoginComponent},
  { path:"adminRegister", component: AdminRegisterComponent},
 // { path:'', redirectTo:"/login", pathMatch:"full"},
  { path:'user',
   canActivate:[AuthGuard],
   loadChildren:()=> import('./modules1/user/user.module').then((m)=>m.UserModule)},
   { path:'admin',
   canActivate:[AuthGuard],
   loadChildren:()=> import('./modules/admin/admin.module').then((m)=>m.AdminModule)}
]
 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
