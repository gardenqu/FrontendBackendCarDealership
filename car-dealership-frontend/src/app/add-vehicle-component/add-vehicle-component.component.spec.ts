import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVehicleComponentComponent } from './add-vehicle-component.component';

describe('AddVehicleComponentComponent', () => {
  let component: AddVehicleComponentComponent;
  let fixture: ComponentFixture<AddVehicleComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddVehicleComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddVehicleComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
