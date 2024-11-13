import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { switchMap, timeInterval } from 'rxjs';
import { NutritionalGoalType } from 'src/app/enums/nutritional-goal-type.enum';
import { NutritionalGoal } from 'src/app/model/nutritional-goal.model';
import { NutritionalGoalService } from 'src/app/service/nutritional-goal.service';
import { UserDataService } from 'src/app/service/user-data.service';




@Component({
  selector: 'app-nutritional-goal-add',
  templateUrl: './nutritional-goal-add.component.html',
  styleUrls: ['./nutritional-goal-add.component.css'],
})
export class NutritionalGoalAddComponent implements OnInit {
  allGoals = NutritionalGoalType.getAllGoals();
  nutritionalGoalForm: FormGroup = new FormGroup({});
  currentWeight: number | undefined;
  currentAbdominalCircumference: number | undefined;
  currentBodyFatLevel: number | undefined;
  nutritionalGoals: NutritionalGoal[] = [];
  estimatedTime: string = "0";

  blockedRanges: { start: NgbDate, end: NgbDate }[] = [];
  
  constructor(private router: Router, private nutritionalGoalService: NutritionalGoalService, private userDataService: UserDataService) {
  }

  ngOnInit(): void {
    // this.updateDateControlState();
    this.initForm();
    this.userDataService.getUserProfileInfo().subscribe(
      (data: any) => {
        console.log(data);
        this.currentWeight = data.weight;
        this.currentAbdominalCircumference = data.abdominal_circumference;
        this.currentBodyFatLevel = data.body_fat_leve;
        this.initForm();
      }
    );
    this.nutritionalGoalService.getNutritionalGoals().subscribe(
      (data: NutritionalGoal[]) => {
        this.nutritionalGoals = data;
        console.log(data);
        data.forEach(goal => {
          const timeInterval: { start: NgbDate; end: NgbDate } = {
            start: new NgbDate(0, 0, 0),
            end: new NgbDate(0, 0, 0)
          };
          if (goal.goalStartDate) {
            const [startYear, startMonth, startDay] = goal.goalStartDate.split('-').map(Number);
            timeInterval.start = new NgbDate(startYear, startMonth, startDay);
          }
          if (goal.goalEndDate) {
            const [endYear, endMonth, endDay] = goal.goalEndDate.split('-').map(Number);
            timeInterval.end = new NgbDate(endYear, endMonth, endDay);
          }
          this.blockedRanges.push(timeInterval)

        })
      }
    );
  }

  private initForm() {
    this.nutritionalGoalForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      typeOfGoal: new FormControl(NutritionalGoalType.WEIGHT_MAINTENANCE, Validators.required),
      goalStartDate: new FormControl(null, Validators.required),
      goalEndDate: new FormControl(null, Validators.required),
      targetWeight: new FormControl(this.currentWeight, [Validators.required]),
      targetAbdominalCircumference: new FormControl(this.currentAbdominalCircumference, Validators.required),
      targetBodyFatLevel: new FormControl(this.currentBodyFatLevel, Validators.required),
      active: new FormControl(false)
    });

    this.nutritionalGoalForm.get('goalStartDate')?.valueChanges.subscribe((startDate: string) => {
      this.calculateEndDate();
    });
   

    this.nutritionalGoalForm.get('typeOfGoal')?.valueChanges.subscribe((goalType: NutritionalGoalType) => {
      const targetWeightControl = this.nutritionalGoalForm.get('targetWeight');
      const targetAbdominalCircumferenceControl = this.nutritionalGoalForm.get('targetAbdominalCircumference');
      const targetBodyFatLevelControl = this.nutritionalGoalForm.get('targetBodyFatLevel');
      if (goalType === NutritionalGoalType.WEIGHT_MAINTENANCE) {
        targetWeightControl?.setValue(this.currentWeight);  // Przywróć wartość do aktualnej wagi
        targetAbdominalCircumferenceControl?.setValue(this.currentAbdominalCircumference); // Przywróć wartość do aktualnego obwodu
        targetBodyFatLevelControl?.setValue(this.currentBodyFatLevel); // Przywróć wartość do aktualnego poziomu tkanki tłuszczowej

        const targetWeight = this.nutritionalGoalForm.get('targetWeight')?.value; // Pobierz wartość z targetWeight
        const typeOfGoal = this.nutritionalGoalForm.get('typeOfGoal')?.value; // Pobierz wartość z typeOfGoal
        this.nutritionalGoalService.getEstimatedTimeToAchieveGoal(this.currentWeight!, targetWeight, typeOfGoal).subscribe(
          (data: string) => {
            console.log(data);
            this.estimatedTime = data;
          }
        );
      }
      // Uaktualnij walidację
      targetWeightControl?.updateValueAndValidity();
      targetAbdominalCircumferenceControl?.updateValueAndValidity();
      targetBodyFatLevelControl?.updateValueAndValidity();
    });
    console.log(this.nutritionalGoalForm);
  }


  onSubmit() {
    if (this.nutritionalGoalForm.valid) {
      const formValues = this.nutritionalGoalForm.value;
      console.log(formValues);
      // Przykład podmiany wartości
      const updatedValues = {
        ...formValues,
        goalStartDate: this.formatJsonToLocalDate(formValues.goalStartDate), // formatuje datę
        goalEndDate: this.formatJsonToLocalDate(formValues.goalEndDate),     // formatuje datę
      };
      this.nutritionalGoalService.addNutritionalGoal(updatedValues).pipe(
        switchMap(() => this.nutritionalGoalService.getNutritionalGoals())
      ).subscribe(
        (updatedNutrtionalGoal: NutritionalGoal[])=>{
          this.nutritionalGoalService.nutritionalGoalChanged.next(updatedNutrtionalGoal);
          this.onCancel();
        }
      );
    } else {
      this.router.navigate(['nutritional-goal']);
    }

  }



  onCancel() {
    this.nutritionalGoalForm.reset();
    this.router.navigate(['nutritional-goal']);
  }

  getDisplayName(status: NutritionalGoalType): string {
    return NutritionalGoalType.getDisplayName(status);
  }


  onWeightChange(event: Event) {
    const goalType = this.nutritionalGoalForm.get('typeOfGoal')?.value;
    const value = (event.target as HTMLInputElement).value;
    const numericValue = parseFloat(value);

    // Sprawdzamy, czy wartość jest liczbą
    if (isNaN(numericValue)) {
      return; // Jeśli nie jest liczbą, nie wykonuj żadnych akcji
    }

    if (goalType === NutritionalGoalType.WEIGHT_GAIN) {
      // Przy zwiększaniu wagi
      if (numericValue < this.currentWeight!) {
        this.nutritionalGoalForm.get('targetWeight')?.setValue(this.currentWeight); // Resetuj do aktualnej wagi
      }
    } else if (goalType === NutritionalGoalType.WEIGHT_LOSE) {
      // Przy zmniejszaniu wagi
      if (numericValue > this.currentWeight!) {
        this.nutritionalGoalForm.get('targetWeight')?.setValue(this.currentWeight); // Resetuj do aktualnej wagi
      }
    } else if (goalType === NutritionalGoalType.WEIGHT_MAINTENANCE) {
      // Utrzymanie wagi
      if (numericValue !== this.currentWeight!) {
        this.nutritionalGoalForm.get('targetWeight')?.setValue(this.currentWeight); // Resetuj do aktualnej wagi
      }
    }
  }

  onAbdominalCircumferenceChange(event: Event) {
    const goalType = this.nutritionalGoalForm.get('typeOfGoal')?.value;
    const value = (event.target as HTMLInputElement).value;
    const numericValue = parseFloat(value);

    // Sprawdzamy, czy wartość jest liczbą
    if (isNaN(numericValue)) {
      return; // Jeśli nie jest liczbą, nie wykonuj żadnych akcji
    }

    // Logika dla obwodu brzucha
    if (goalType === NutritionalGoalType.WEIGHT_LOSE) {
      // Użytkownik może tylko zmniejszać obwód brzucha
      if (numericValue > this.currentAbdominalCircumference!) {
        this.nutritionalGoalForm.get('targetAbdominalCircumference')?.setValue(this.currentAbdominalCircumference); // Resetuj do aktualnego obwodu
      }
    } else if (goalType === NutritionalGoalType.WEIGHT_GAIN) {
      // Użytkownik może tylko zwiększać obwód brzucha
      if (numericValue < this.currentAbdominalCircumference!) {
        this.nutritionalGoalForm.get('targetAbdominalCircumference')?.setValue(this.currentAbdominalCircumference); // Resetuj do aktualnego obwodu
      }
    } else if (goalType === NutritionalGoalType.WEIGHT_MAINTENANCE) {
      // Utrzymanie wagi
      if (numericValue !== this.currentAbdominalCircumference!) {
        this.nutritionalGoalForm.get('targetAbdominalCircumference')?.setValue(this.currentAbdominalCircumference); // Resetuj do aktualnego obwodu
      }
    }
  }

  onBodyFatLevelChange(event: Event) {
    const goalType = this.nutritionalGoalForm.get('typeOfGoal')?.value;
    const value = (event.target as HTMLInputElement).value;
    const numericValue = parseFloat(value);

    // Sprawdzamy, czy wartość jest liczbą
    if (isNaN(numericValue)) {
      return; // Jeśli nie jest liczbą, nie wykonuj żadnych akcji
    }

    // Logika dla poziomu tkanki tłuszczowej
    if (goalType === NutritionalGoalType.WEIGHT_LOSE) {
      // Użytkownik może tylko zmniejszać poziom tkanki tłuszczowej
      if (numericValue > this.currentBodyFatLevel!) {
        this.nutritionalGoalForm.get('targetBodyFatLevel')?.setValue(this.currentBodyFatLevel); // Resetuj do aktualnego poziomu tkanki tłuszczowej
      }
    } else if (goalType === NutritionalGoalType.WEIGHT_GAIN) {
      // Użytkownik może tylko zwiększać poziom tkanki tłuszczowej
      if (numericValue < this.currentBodyFatLevel!) {
        this.nutritionalGoalForm.get('targetBodyFatLevel')?.setValue(this.currentBodyFatLevel); // Resetuj do aktualnego poziomu tkanki tłuszczowej
      }
    } else if (goalType === NutritionalGoalType.WEIGHT_MAINTENANCE) {
      // Utrzymanie wagi
      if (numericValue !== this.currentBodyFatLevel!) {
        this.nutritionalGoalForm.get('targetBodyFatLevel')?.setValue(this.currentBodyFatLevel); // Resetuj do aktualnego poziomu tkanki tłuszczowej
      }
    }
  }

  onWeightBlur() {
    const targetWeight = this.nutritionalGoalForm.get('targetWeight')?.value; // Pobierz wartość z targetWeight
    const typeOfGoal = this.nutritionalGoalForm.get('typeOfGoal')?.value; // Pobierz wartość z typeOfGoal
    
    this.nutritionalGoalService.getEstimatedTimeToAchieveGoal(this.currentWeight!, targetWeight, typeOfGoal).subscribe(
      (data: string) => {
        console.log(data);
        this.estimatedTime = data;
        this.calculateEndDate();
      }
    );
  }
  
  getFormattedEstimatedTime(): number {
    const time = parseFloat(this.estimatedTime) * 7; // Konwersja stringa na liczbę
    return Math.floor(time); // Zaokrąglenie w dół
  }

  public calculateEndDate() {
    const dateFromInput= this.nutritionalGoalForm.get('goalStartDate')?.value;
    console.log(dateFromInput)
    if(dateFromInput){
      const startDate = this.formatJsonToLocalDate(dateFromInput);
      console.log(startDate);
      console.log(this.estimatedTime)
      const time = parseFloat(this.estimatedTime);
      if (startDate && time) {
        const start = new Date(startDate);
        start.setDate(start.getDate() + time * 7); // Dodanie `estimatedTime` w tygodniach
        this.nutritionalGoalForm.get('goalEndDate')?.setValue(this.formatDate2(start));
      }
    }
  }





  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }
  private formatDate2(date: Date): { year: number; month: number; day: number } {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return { year: year, month: +month, day: +day };
  }

  private formatJsonToLocalDate(date: any) {
    console.log(date)
    return `${date.year}-${date.month.toString().padStart(2, '0')}-${date.day.toString().padStart(2, '0')}`;
  }

  convertToNgbDate(dateString: string): NgbDate {
    const [year, month, day] = dateString.split('-').map(Number);
    return new NgbDate(year, month, day);
  }


}
