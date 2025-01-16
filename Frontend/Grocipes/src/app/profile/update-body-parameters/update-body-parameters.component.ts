import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserActivityStatus } from 'src/app/enums/user-activity-status.enum';
import { BodyParameter } from 'src/app/model/body-parameter.model';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-update-body-parameters',
  templateUrl: './update-body-parameters.component.html',
  styleUrls: ['./update-body-parameters.component.css']
})
export class UpdateBodyParametersComponent implements OnInit {
  
  allStatuses = UserActivityStatus.getAllStatuses();
  bodyParametersForm: FormGroup = new FormGroup({});
  currentBodyParameter: BodyParameter | undefined;

  constructor(private router: Router, private userDataService: UserDataService){
    
  }

  ngOnInit(): void {
    this.userDataService.getUserProfileInfo().subscribe(
      (data: BodyParameter) => {
        this.currentBodyParameter = data;
        console.log(data)
        this.initForm();
      }
    );
    this.initForm();
  }

  private initForm() {
    this.bodyParametersForm = new FormGroup({
      weight: new FormControl(this.currentBodyParameter?.weight, Validators.required),
      height: new FormControl(this.currentBodyParameter?.height, Validators.required),
      abdominal_circumference: new FormControl(this.currentBodyParameter?.abdominal_circumference, Validators.required),
      body_fat_level: new FormControl(this.currentBodyParameter?.body_fat_leve, Validators.required),
      physical_activity: new FormControl(this.currentBodyParameter?.physical_activity, Validators.required)  
    });
  }


  onSubmit(){
    if(this.bodyParametersForm.valid){
      const formValues = this.bodyParametersForm.value;
      console.log(formValues);
      
      this.userDataService.addBodyMeasurement(formValues).subscribe();
      this.onCancel();
    }else {
      this.router.navigate(['profile']);
    }

  }

  onCancel(){
    this.bodyParametersForm.reset();
    this.router.navigate(['profile']);
  }

  getDisplayName(status: UserActivityStatus): string {
    return UserActivityStatus.getDisplayName(status);
  }




}
