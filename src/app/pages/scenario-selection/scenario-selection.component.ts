import { Component } from '@angular/core';
import { ScenarioSelectionService } from '../../service/scenario-selection.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule } from '@angular/forms';

@Component({
  selector: 'app-scenario-selection',
  imports: [FormsModule,CommonModule],
  templateUrl: './scenario-selection.component.html',
  styleUrl: './scenario-selection.component.css'
})
export class ScenarioSelectionComponent {
  selectedScenario:string=''
  scenarios: any[] = [];

  constructor(private scenarioService: ScenarioSelectionService) {}
  ngOnInit(): void {
    this.loadScenarios();
  }

  loadScenarios() {
    this.scenarioService.getScenarios().subscribe({
      next:(data)=>{
        this.scenarios = data;
      },
      
      
     error: (error) => {
        console.error('Erreur lors du chargement des scénarios', error);
      }
  });
  }
  sendScenario(){
if(this.selectedScenario){
  console.log(this.selectedScenario);
  console.log(this.scenarios);
  
  const scenarioToSend=this.scenarios.find(s=>s.id==this.selectedScenario);
  
  console.log("scenarioToSend",scenarioToSend);
  
  if (scenarioToSend) {    
    this.scenarioService.sendScenario(scenarioToSend.id).subscribe({
      next:(response=>{console.log('Scénario envoyé avec succès', response);
      }),
  error:(error)=>{console.error('erreur',error);
  },
  complete:()=>{console.log("requete terminé");
  }
  })
  }
}
    
  }
}
