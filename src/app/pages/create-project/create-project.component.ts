import { CommonModule } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NewProjectService } from '../../service/new-project.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-create-project',
  imports: [FormsModule,CommonModule],
  templateUrl: './create-project.component.html',
  styleUrl: './create-project.component.css'
})
export class CreateProjectComponent {
  jobName:string=''
  isCreatingProject: boolean = true;  // Par défaut sur "Créer un projet"
  projectName: string = '';
  selectedScenarios: number[] = [];
  projectDescription: string = '';
  existingProjects: { id: string, name: string }[] = []; // Liste des projets
  existingScenario: { id: string, name: string }[] = []; // Liste des projets
  repeatTime: string = '';
  selectedProject: string = '';  // Projet sélectionné pour l'ouverture

  constructor(private projectService:NewProjectService,private router:Router,private httpClient:HttpClient){}
  ngOnInit(): void {
    this.loadExistingProjects();
  }

  // Simuler le chargement des projets depuis la base de données
  loadExistingProjects() {
    // Remplace ceci par un appel à une API réelle
    this.existingProjects = [
    ];
  }
validateProject() {
  console.log("this.isCreatingProject",this.isCreatingProject);
  
  if (this.isCreatingProject && (!this.projectName || !this.projectDescription)) {
    return;
  }
  if (!this.isCreatingProject) {
    this.projectService.getRegisteredProject(localStorage.getItem("userEmail") || "").subscribe({
      next: (response) => {
        console.log("✅ Projets récupérés :", response);

        // Transformer la réponse en format compatible avec existingProjects
        this.existingProjects = response.map((project: any) => ({
          id: project.idProjet, // Assurez-vous que c'est bien l'ID du projet
          name: project.name
        }));

      },
      error: (error) => {
        console.error("❌ Erreur lors de la récupération des projets :", error);
      }
    });
    return;
  }
  const ProjectData = {
    name: this.projectName,
    description: this.projectDescription,
    email:localStorage.getItem("userEmail")
  };
  this.projectService.registerProject(ProjectData).subscribe({
    next:(response)=>{console.log('🎉 Projet créé avec succès:', response);
    localStorage.setItem("projectId",response)
    this.router.navigate(["urlToTest"])
    
},
    error:(error)=>{
      console.error('❌ Erreur lors de la création du projet:', error);
      alert('Échec de la création du projet : ' + error.message);
}
})
  
  
}
selectProject(event: Event) {
  const projectId = (event.target as HTMLSelectElement).value;
  console.log(projectId);

  this.projectService.getRegisteredScenariByProjectId(projectId).subscribe({
    next: (response) => {
      console.log("response", response);
      
      // Assurez-vous que response est bien un tableau du type attendu
      if (Array.isArray(response)) {
        this.existingScenario = response.map(scenario => ({
          id: scenario.id.toString(),  // S'assurer que l'ID est bien une string
          name: scenario.name
        }));
      } else {
        console.error("La réponse n'est pas un tableau valide", response);
      }
    },
    error: (error) => {
      console.error(error);
    }
  });
}
validateScenario(){

}
addScenario(){
  this.router.navigateByUrl("urlToTest")
}
private api = 'http://localhost:8082/api'

saveJob() {
  const jobData = {
    jobCreater:localStorage.getItem('userEmail'),
    jobName:this.jobName,
    scenarioIds: this.selectedScenarios,
    scheduledTime:this.repeatTime
  };
  console.log(jobData);

  this.httpClient.post(`${this.api}/job`, jobData).subscribe({
    
    next: (response) => console.log('Job enregistré avec succès', response),
    error: (error) => console.error('Erreur lors de l\'enregistrement du job', error)
  });
}

}
