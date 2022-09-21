import { HttpInterceptor } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private authService:AuthService) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> 
  {

    let token = this.authService.getToken();

    if(token != null)
    {
      const tokenizedReq = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token) });

      return next.handle(tokenizedReq);
    }
    return next.handle(req);
  }

}
