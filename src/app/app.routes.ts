import { Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { HowDoesItWorkComponent } from './pages/how-does-it-work/how-does-it-work.component';
import { ScenarioSelectionComponent } from './pages/scenario-selection/scenario-selection.component';
import { BrowserTopicsComponent } from './pages/browser-topics/browser-topics.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';

export const routes: Routes = [
    { path: '', component: HomePageComponent },
    { path: 'home', component:HowDoesItWorkComponent },
    { path: 'createproject', component:CreateProjectComponent },

    { path: 'scenario', component:ScenarioSelectionComponent },

];
