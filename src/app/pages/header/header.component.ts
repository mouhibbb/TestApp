import { Component } from '@angular/core';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-header',
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isLoggedIn: boolean = false;
  intervalId: any;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.checkLoginStatus();

this.intervalId=setInterval(()=>{this.checkLoginStatus(),500})
  }
ngOnDestroy():void{
clearInterval(this.intervalId)
}
  checkLoginStatus(): void {
const currentStatus=!!localStorage.getItem('userEmail')
if(this.isLoggedIn!==currentStatus){
  this.isLoggedIn=currentStatus
}
  }

  deconnexion() {
    localStorage.removeItem("userEmail");
    localStorage.removeItem("projectId");

    this.isLoggedIn = false;
    this.router.navigate(['']);
  }

  connexion() {
    // Par exemple rediriger vers la page login
    this.router.navigate(['/login']);
    console.log("Connexion");
  }
}
