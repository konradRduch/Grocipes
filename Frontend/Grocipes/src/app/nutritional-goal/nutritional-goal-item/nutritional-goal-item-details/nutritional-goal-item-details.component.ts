import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { NutritionalGoal } from 'src/app/model/nutritional-goal.model';
import { NutritionalGoalService } from 'src/app/service/nutritional-goal.service';
import { UserDataService } from 'src/app/service/user-data.service';

@Component({
  selector: 'app-nutritional-goal-item-details',
  templateUrl: './nutritional-goal-item-details.component.html',
  styleUrls: ['./nutritional-goal-item-details.component.css']
})
export class NutritionalGoalItemDetailsComponent implements OnInit{

  id: number | undefined;
  nutritionalGoal: NutritionalGoal | undefined;
  user: any | undefined;

  goalDailyDemand: {protein: number, carbs: number, fats: number, calories: number} | undefined;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private nutritionalGoalService: NutritionalGoalService, private userDataService: UserDataService){

  }

  ngOnInit(): void {
    
    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        this.nutritionalGoalService.getNutritionalGoal(this.id).subscribe(
          (data: NutritionalGoal)=>{
            this.nutritionalGoal = data;
            console.log(this.nutritionalGoal);
          }
        );
      }
    )
    this.userDataService.getUserProfileInfo().subscribe(
      (data: any) => {
        this.user = data;
      }
    );

    this.nutritionalGoalService.getGoalDailyDemands().subscribe(
      (data: {protein: number, carbs: number, fats: number, calories: number}) =>{
        this.goalDailyDemand = data;
        console.log(data)
      }
    );

  }


}
