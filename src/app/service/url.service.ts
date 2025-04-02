import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { concatMap, Observable, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlService {
  private api = 'http://localhost:8082/api'

  constructor(private httpClient : HttpClient) { }
  registerUrl(urlData: any): Observable<any> {
    console.log('Données de l\'utilisateur à envoyer à l\'API:', urlData); // Affiche les données dans la console
    // this.httpClient.post(`${this.api}/url`, urlData);
    // return this.httpClient.get(`${this.api}/url`)
    return this.httpClient.post(`${this.api}/url`,urlData)
    
  }
  saveScenarioService(scenarioData:any){
    console.log("scenarioData",scenarioData);
    
   return this.httpClient.post(`${this.api}/scenarios`,scenarioData)
  }
}
