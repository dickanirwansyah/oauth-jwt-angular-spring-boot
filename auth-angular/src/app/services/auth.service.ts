import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
/**using map rxJs 6 */
import { map } from 'rxjs/operators';

@Injectable()
export class AuthService {

     AUTH_TOKEN = '/oauth/token';

     constructor(
         private router: Router,
         private http: Http
     ){}

     getOAuth2(loginData){

        let params = new URLSearchParams();
        params.append('username', loginData.username);
        params.append('password', loginData.password);
        params.append('grant_type', 'password');
        params.append('client_id', 'client');

        let headers = new Headers({'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa("client:secret")});

        let options = new RequestOptions({headers: headers});
        console.log(params.toString());

        this.http.post('http://localhost:8080/oauth/token', params.toString(), options)
            .pipe(map(res => res.json()))
            .subscribe(
                data => this.saveToken(data),
                error => alert(error + ' login failed')
            );
     }

     saveToken(token){
        var expireDateToken = new Date().getTime() + (1000 * token.expires_in);
        Cookie.set('access_token', token.access_token, expireDateToken);
        console.log('prosessing token');
        this.router.navigate(['/']);
     }

     /** check credentials atau cookies */
     checkTokenInCookies(){
         if(!Cookie.check('access_token')){
             this.router.navigate(['/auth-login']);
         }
     }

     /** clear or logout */
     logout(){
         Cookie.delete('access_token');
         window.location.reload();
         console.log('prosessing clear token in cookies.');
     }
}