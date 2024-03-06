import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExplorecandidatesComponent } from './explorecandidates.component';

describe('ExplorecandidatesComponent', () => {
  let component: ExplorecandidatesComponent;
  let fixture: ComponentFixture<ExplorecandidatesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExplorecandidatesComponent]
    });
    fixture = TestBed.createComponent(ExplorecandidatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
