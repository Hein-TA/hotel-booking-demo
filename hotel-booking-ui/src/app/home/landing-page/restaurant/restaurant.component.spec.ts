import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GastroComponent } from './gastro.component';

describe('GastroComponent', () => {
  let component: GastroComponent;
  let fixture: ComponentFixture<GastroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GastroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
