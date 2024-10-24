import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-body-parameters',
  templateUrl: './update-body-parameters.component.html',
  styleUrls: ['./update-body-parameters.component.css']
})
export class UpdateBodyParametersComponent implements OnInit {
  
  bodyParametersForm: FormGroup = new FormGroup({});
  
  ngOnInit(): void {
    
    this.initForm();
  }

  private initForm() {
    this.bodyParametersForm = new FormGroup({
      weight: new FormControl(null, Validators.required),
      height: new FormControl(null, Validators.required),
      abdominal_circumference: new FormControl(null, Validators.required),
      body_fat_leve: new FormControl(null, Validators.required),
      physical_activity: new FormControl(false),
      userId: new FormControl(null)
    });
  }








}
