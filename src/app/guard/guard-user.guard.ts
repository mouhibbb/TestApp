import { isPlatformBrowser } from '@angular/common';
import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const guardUserGuard: CanActivateFn = (route, state) => {
  const router = inject(Router); // Correctly inject the Router
  const platformId=inject(PLATFORM_ID);
  if(isPlatformBrowser(platformId)){
    const userEmail=localStorage.getItem("userEmail")
    if(userEmail){return true}

  }
    router.navigate(['']); // Redirect to the default route
    return false; // Block navigation
};
