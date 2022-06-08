import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextAreaMaxBytesComponent } from './text-area-max-bytes.component';

describe('TextAreaMaxBytesComponent', () => {
  let component: TextAreaMaxBytesComponent;
  let fixture: ComponentFixture<TextAreaMaxBytesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TextAreaMaxBytesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TextAreaMaxBytesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
