import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVehicleComponentComponent } from './update-vehicle-component.component';

describe('UpdateVehicleComponentComponent', () => {
  let component: UpdateVehicleComponentComponent;
  let fixture: ComponentFixture<UpdateVehicleComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateVehicleComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateVehicleComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
