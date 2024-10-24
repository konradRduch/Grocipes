import { Component, OnInit } from '@angular/core';
import { BodyParameter } from 'src/app/model/body-parameter.model';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-body-parameters-history',
  templateUrl: './body-parameters-history.component.html',
  styleUrls: ['./body-parameters-history.component.css']
})
export class BodyParametersHistoryComponent implements OnInit{
  
  bodyMeasurement: BodyParameter[] = [];

  constructor(private userDataService: UserDataService){
  }

  ngOnInit(): void {
    this.userDataService.getBodyMeasurements().subscribe(
      (bodyMeasurementdata: BodyParameter[]) => {
        this.bodyMeasurement = bodyMeasurementdata;
      }
    );

  }

}
