import { Component } from '@angular/core';
import { Router,RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
constructor(private router:Router){}
isLoggedIn: boolean = false;

ngOnInit():void{
  this.checkLoginStatus()
}
checkLoginStatus():void{
  this.isLoggedIn=!!localStorage.getItem('userEmail')
}
deconnexion(){
  localStorage.removeItem("userEmail")
  this.isLoggedIn=false
  this.router.navigate([''])
}

}
