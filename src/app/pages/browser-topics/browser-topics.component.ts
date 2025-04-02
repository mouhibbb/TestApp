import { Component } from '@angular/core';
import { FrequentlyAskedQuestionsComponent } from '../frequently-asked-questions/frequently-asked-questions.component';
import { GetInTouchComponent } from '../get-in-touch/get-in-touch.component';

@Component({
  selector: 'app-browser-topics',
  imports: [FrequentlyAskedQuestionsComponent,GetInTouchComponent,],
  templateUrl: './browser-topics.component.html',
  styleUrl: './browser-topics.component.css'
})
export class BrowserTopicsComponent {

}
