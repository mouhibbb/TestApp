import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Ajoute ceci
import { UserService } from '../../service/user.service';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  standalone:true,
  imports: [CommonModule,FormsModule],
  
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {
  isRegistering = false;
  firstname: string = '';

  nom :String = '';
  prenom : String= '';
  email :string= '';
  password :String= '';
  confirmPassword :String= '';
  isActivated:Boolean=false;
  role:String="User"
  constructor(private userService:UserService,private router:Router){}

  onToggleForm(event: Event) {
    event.preventDefault();
    console.log("Toggle form triggered!");
    this.isRegistering = !this.isRegistering;
  }
  
    inscription(){
      if (this.password !== this.confirmPassword) {
        console.error('Les mots de passe ne correspondent pas.');
        return }
  
      else{
        //const salt = bcrypt.genSaltSync(10); // Nombre de tours (10 est une valeur sûre)
       // const hashedPassword = bcrypt.hashSync(this.password, salt);
  
        const userData = {
        nom: this.nom,
        prenom: this.prenom,
        email: this.email,
        password: this.password,
        confirmPassword:this.confirmPassword,
        isActivated:this.isActivated,
        role:this.role
        
        };
        this.userService.registerUser(userData).subscribe({
          next: (response) => {
            console.log('Utilisateur enregistré avec succès:', response);
            // Réinitialisez le formulaire après l'enregistrement
  
          },
          error: (error) => {
            console.error('Erreur lors de l\'enregistrement:', error);
          },
          complete: () => {
            console.log('Requête terminée'); // Optionnel
            alert('Compte créé avec succès ! En attente de validation par l\'administrateur.');
            console.log(this.isRegistering);
            
            this.isRegistering=!this.isRegistering
          },
        });  
      }
    }
  
  loginUser(){
    const loginData = {
      email: this.email,
      password: this.password

  }
    console.log(loginData);
    
    this.userService.loginUser(loginData).subscribe({
      next: (response) => {
        console.log('Connexion réussie:', response);
        if (this.password) {
          console.log('Mot de passe valide');
          // Rediriger l'utilisateur ou stocker les informations de session
         // this.router.navigate(['createproject']);
          localStorage.setItem('userEmail',this.email)
          this.router.navigateByUrl('home')
          
        } else {
          console.log('Mot de passe incorrect');
        }
        
      },
      error: (error) => {
        console.error('Erreur lors de la connexion:', error);
        if (error.status === 403) {
          // Si le statut HTTP est 403 (compte non activé)
          alert(error.error); // Affiche le message d'erreur renvoyé par l'API
        } else {
          alert('Échec de la connexion : veuillez vérifier vos identifiants.');
        }      }
    });

}
  

}
