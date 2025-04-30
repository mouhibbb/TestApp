import { Component } from '@angular/core';
import { ScenarioSelectionService } from '../../service/scenario-selection.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-execut-process',
  imports: [CommonModule,FormsModule],
  templateUrl: './execut-process.component.html',
  styleUrl: './execut-process.component.css'
})
export class ExecutProcessComponent {
  selectedJob:string=''
  jobs: any = [];
  scenario:any
  selectedJobId: number = 0;

  constructor(private httpClient:HttpClient,private scenarioService:ScenarioSelectionService) {}
  ngOnInit(): void {
    this.loadJobs();
  }
  private api = 'http://localhost:8082/api'

  loadJobs() {
    const email=localStorage.getItem("userEmail")??'';
    const params=new HttpParams().set('email', email);
    this.httpClient.get(`${this.api}/job`,{params}).subscribe({
    
      next: (response) => {console.log('Job return avec succès', response)
        this.jobs=response
      }
      ,
      error: (error) => console.error('Erreur lors du get du job', error)
    });
  }

  executJob(id:number){
    console.log(id);
    this.httpClient.get(`${this.api}/job/${id}`).subscribe({
      next:(response)=>{console.log(response)
        this.scenario=response
        console.log(this.scenario);
     for(const i of this.scenario){  
     this.scenarioService.sendScenario(i.id).subscribe({
          next:(response=>{console.log(response);
          }),
          error:(error)=>{console.error(error);
          }
        })
        }},
      error:(error)=>{console.error(error);
      }
    })
    
   
  }
}
