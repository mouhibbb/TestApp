import { CommonModule } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NewProjectService } from '../../service/new-project.service';

@Component({
  selector: 'app-create-project',
  imports: [FormsModule,CommonModule],
  templateUrl: './create-project.component.html',
  styleUrl: './create-project.component.css'
})
export class CreateProjectComponent {
  isCreatingProject: boolean = true;  // Par défaut sur "Créer un projet"
  projectName: string = '';
  projectDescription: string = '';
  existingProjects: { id: string, name: string }[] = []; // Liste des projets
  selectedProject: string = '';  // Projet sélectionné pour l'ouverture

  constructor(private projectService:NewProjectService){}
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
    alert("Veuillez remplir tous les champs avant de valider.");
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

        console.log("📝 Liste des projets mis à jour :", this.existingProjects);
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
},
    error:(error)=>{
      console.error('❌ Erreur lors de la création du projet:', error);
      alert('Échec de la création du projet : ' + error.message);
}
})
  
  console.log(this.projectName,this.projectDescription, this.isCreatingProject ? "Création" : "Ouverture");
  
}
selectProject(event:Event){
  const projectId=(event.target as HTMLSelectElement).value
  console.log(projectId);
  this.projectService.getRegisteredScenariByProjectId(projectId).subscribe({
    next:(response)=>{console.log(response)},
    error:(error)=>{console.error(error);
    }
    })
  
}
}
