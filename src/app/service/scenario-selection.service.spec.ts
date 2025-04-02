import { TestBed } from '@angular/core/testing';

import { ScenarioSelectionService } from './scenario-selection.service';

describe('ScenarioSelectionService', () => {
  let service: ScenarioSelectionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScenarioSelectionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
