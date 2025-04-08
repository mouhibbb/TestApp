import { Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { HowDoesItWorkComponent } from './pages/how-does-it-work/how-does-it-work.component';
import { ScenarioSelectionComponent } from './pages/scenario-selection/scenario-selection.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';
import { ExecutProcessComponent } from './pages/execut-process/execut-process.component';
import { guardUserGuard } from './guard/guard-user.guard';

export const routes: Routes = [
    { path: '', component: HomePageComponent },
    { path: 'urlToTest', component:HowDoesItWorkComponent, canActivate:[guardUserGuard]},
    { path: 'home', component:CreateProjectComponent, canActivate:[guardUserGuard] },
    { path: 'scenario', component:ScenarioSelectionComponent , canActivate:[guardUserGuard]},
    { path: 'executProcess', component:ExecutProcessComponent , canActivate:[guardUserGuard]},

];
