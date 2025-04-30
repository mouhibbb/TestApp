import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutProcessComponent } from './execut-process.component';

describe('ExecutProcessComponent', () => {
  let component: ExecutProcessComponent;
  let fixture: ComponentFixture<ExecutProcessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExecutProcessComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExecutProcessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
