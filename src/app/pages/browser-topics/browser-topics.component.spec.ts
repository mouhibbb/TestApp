import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowserTopicsComponent } from './browser-topics.component';

describe('BrowserTopicsComponent', () => {
  let component: BrowserTopicsComponent;
  let fixture: ComponentFixture<BrowserTopicsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrowserTopicsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrowserTopicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
