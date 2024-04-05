import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleVehicleComponentComponent } from './single-vehicle-component.component';

describe('SingleVehicleComponentComponent', () => {
  let component: SingleVehicleComponentComponent;
  let fixture: ComponentFixture<SingleVehicleComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SingleVehicleComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SingleVehicleComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
