import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScenarioSelectionService {

  private apiUrl = 'http://localhost:8082/api/scenarios'; // Remplace par ton URL backend

  constructor(private http: HttpClient) {}

  getScenarios(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  sendScenario(scenarioId: any): Observable<any> {
    console.log("Scenario",scenarioId);
    
    return this.http.post(`${this.apiUrl}/${scenarioId}`,{});
  }
}
