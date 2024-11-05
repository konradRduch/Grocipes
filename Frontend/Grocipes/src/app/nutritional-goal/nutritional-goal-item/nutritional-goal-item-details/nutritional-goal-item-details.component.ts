import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { NutritionalGoal } from 'src/app/model/nutritional-goal.model';
import { NutritionalGoalService } from 'src/app/service/nutritional-goal.service';

@Component({
  selector: 'app-nutritional-goal-item-details',
  templateUrl: './nutritional-goal-item-details.component.html',
  styleUrls: ['./nutritional-goal-item-details.component.css']
})
export class NutritionalGoalItemDetailsComponent implements OnInit{

  id: number | undefined;
  nutritionalGoal: NutritionalGoal | undefined;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private nutritionalGoalService: NutritionalGoalService){

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
  }


}
