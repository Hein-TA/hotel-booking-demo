import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountInfomationComponent } from './account-infomation.component';

describe('AccountInfomationComponent', () => {
  let component: AccountInfomationComponent;
  let fixture: ComponentFixture<AccountInfomationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountInfomationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountInfomationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
