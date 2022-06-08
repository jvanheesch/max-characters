import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextAreaMaxLengthComponent } from './text-area-max-length.component';

describe('TextAreaMaxLengthComponent', () => {
  let component: TextAreaMaxLengthComponent;
  let fixture: ComponentFixture<TextAreaMaxLengthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TextAreaMaxLengthComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TextAreaMaxLengthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
