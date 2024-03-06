import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobpostingComponent } from './jobposting.component';

describe('JobpostingComponent', () => {
  let component: JobpostingComponent;
  let fixture: ComponentFixture<JobpostingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobpostingComponent]
    });
    fixture = TestBed.createComponent(JobpostingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
