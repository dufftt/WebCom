import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckOutPage } from './check-out-page';

describe('CheckOutPage', () => {
  let component: CheckOutPage;
  let fixture: ComponentFixture<CheckOutPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CheckOutPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckOutPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
