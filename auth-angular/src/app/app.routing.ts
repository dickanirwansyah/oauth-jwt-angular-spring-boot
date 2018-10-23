import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DocumentComponent } from './document/document.component';
import { UsersComponent } from './users/users.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
    { path: 'auth-login', component: LoginComponent},
    { path: 'document', component: DocumentComponent},
    { path: 'users', component: UsersComponent},
    { path: 'home', component: HomeComponent},
    {
        path: '**', redirectTo: 'home'
    }
]

@NgModule({
    imports : [RouterModule.forRoot(routes)],
    exports : [RouterModule]
})
export class AppRouting{

}