import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
private api = 'http://localhost:8082/api'
  constructor(private http: HttpClient) { }


  registerUser(userData: any): Observable<any> {
    console.log('Données de l\'utilisateur à envoyer à l\'API:', userData); // Affiche les données dans la console

    return this.http.post(`${this.api}/users`, userData);
    
  }

  loginUser(loginData: any): Observable<any> {
    return this.http.post(`${this.api}/users/login`, loginData);
  }
}
