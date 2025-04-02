import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewProjectService {
  private api = 'http://localhost:8082/api/project'; // Remplace par ton URL backend

  constructor(private httpClient:HttpClient) { }

   registerProject(projectData:any): Observable<any> {
      console.log('Données de l\'utilisateur à envoyer à l\'API:', projectData); // Affiche les données dans la console
      return this.httpClient.post(`${this.api}`,projectData)
    }
    getRegisteredProject(email:any): Observable<any> {
      console.log(email);
      return this.httpClient.get(`${this.api}`,{params:{email}})
    }
    getRegisteredScenariByProjectId(id:string){
     return this.httpClient.get(`${this.api}`,{params:{id}})
    }
}
