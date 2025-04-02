import { ChangeDetectorRef, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UrlService } from '../../service/url.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-how-does-it-work',
  standalone:true,
  imports: [FormsModule,CommonModule,RouterModule],
  templateUrl: './how-does-it-work.component.html',
  styleUrl: './how-does-it-work.component.css'
})
export class HowDoesItWorkComponent {
url:string=''
scenarioName:string=''
inputs: any[] = [];
formData: { [key: string]: any } = {}; // Stocke les valeurs saisies


constructor(private urlService: UrlService,private cdRef: ChangeDetectorRef,private router:Router){console.log("inputs",this.inputs);
}
keepUrl(){
  const urlData = {
    url:this.url
    
    };
  this.urlService.registerUrl(urlData).subscribe({
    next: (response) => {
      console.log('url enregistré avec succès:', response);
      this.inputs = response
      this.cdRef.detectChanges();  // Force Angular à rafraîchir la vue
      if (Array.isArray(response)) { 
        this.inputs = response;
        console.log('Données mises à jour:', this.inputs);
      } else {
        console.error('Erreur: la réponse n\'est pas un tableau !', response);
      }      
      // Réinitialisez le formulaire après l'enregistrement

    },
    error: (error) => {
      console.error('Erreur lors de l\'enregistrement:', error);
    },
    complete: () => {
      console.log('Requête terminée'); // Optionnel
    },
  });
}
// Sauvegarder le scénario
saveScenario() {
  // Récupérer toutes les valeurs des inputs avant l'envoi
  this.formData = this.inputs.map(input => ({
    name: input.name,
    value: input.value,
    isClicked:input.isClicked||false,
    type:input.type
  }));
  const scenarioData = {
    url: this.url,
    name:this.scenarioName,
    project:localStorage.getItem("projectId")||'',
    inputs: this.formData

  };
  console.log("scenarioData",scenarioData);
  
  this.urlService.saveScenarioService(scenarioData).subscribe({
    next: (response) => {console.log('Scénario enregistré avec succès:', response),this.router.navigate(['scenario'])},
    error: (error) => console.error('Erreur lors de l\'enregistrement du scénario:', error)
  });
}
markClicked(input: any, event: Event) {
  event.preventDefault(); // Empêche la soumission accidentelle
  input.isClicked = !input.isClicked; // Toggle l'état cliqué
  input.name=input.text
  input.value=input.isClicked
  input.type='submit'
}

onFileSelected(event: any) {
  const file = event.target.files[0];
  if (!file) return;

  const reader = new FileReader();
  reader.onload = (e: any) => {
    const data = new Uint8Array(e.target.result);
    const workbook = XLSX.read(data, { type: 'array' });

    // Supposons que les données sont dans la première feuille
    const firstSheetName = workbook.SheetNames[0];
    const worksheet = workbook.Sheets[firstSheetName];

    // Convertir en JSON
    const excelData: any[] = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

    // Vérifier que les données sont correctes
    if (excelData.length > 0) {
      console.log(excelData);
      
      this.inputs.forEach(input=>{
        const row = excelData.find(r=>r[0]===input.name);
        
        if (row) {
          console.log("row",row);
          
          let value=row[1]||'';
          console.log("value",value,value.type);
          
          if (input.type=="date") {
            console.log("mouhib");
            
            value=convertToISODate(value)
          }
          input.value=value
        }
      })



    }

    console.log('Données Excel chargées :', this.inputs);
  };
  
  reader.readAsArrayBuffer(file);
}

}
function convertToISODate(excelDate: number): string {
  console.log("dateStr",excelDate);
  
  const excelEpoch = new Date(1900, 0, 1); // Excel commence au 1er janvier 1900
  const date = new Date(excelEpoch.getTime() + (excelDate - 1) * 86400000); // Convertit en date
console.log(date.toISOString().split('T')[0]);

  return date.toISOString().split('T')[0]; // Retourne YYYY-MM-DD
  
}