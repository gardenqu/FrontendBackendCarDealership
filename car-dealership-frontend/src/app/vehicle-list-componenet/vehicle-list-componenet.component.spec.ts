import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleListComponenetComponent } from './vehicle-list-componenet.component';

describe('VehicleListComponenetComponent', () => {
  let component: VehicleListComponenetComponent;
  let fixture: ComponentFixture<VehicleListComponenetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VehicleListComponenetComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VehicleListComponenetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
