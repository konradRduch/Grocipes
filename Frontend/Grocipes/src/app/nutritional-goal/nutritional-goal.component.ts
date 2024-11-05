import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NutritionalGoal } from '../model/nutritional-goal.model';
import { NutritionalGoalService } from '../service/nutritional-goal.service';

@Component({
  selector: 'app-nutritional-goal',
  templateUrl: './nutritional-goal.component.html',
  styleUrls: ['./nutritional-goal.component.css']
})
export class NutritionalGoalComponent implements OnInit, OnDestroy{

  id: number | undefined;
  nutritionalGoals: NutritionalGoal[]| undefined;
  nutritionalGoal: NutritionalGoal| undefined;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private nutritionalGoalService: NutritionalGoalService){
    
  }
  
  ngOnInit(): void {
    this.nutritionalGoalService.getNutritionalGoals().subscribe(
      (data: NutritionalGoal[]) => {
        console.log(data);
        this.nutritionalGoals = [...data];
      }
    );

    if(this.nutritionalGoals !==undefined){
      this.nutritionalGoalService.nutritionalGoalChanged$.subscribe(
        (data: NutritionalGoal[]) => {
          this.nutritionalGoals = [...data];
        }
      );
    }
  }

  ngOnDestroy(): void {
   
  }
  add(){
    this.router.navigate(['nutritional-goal/nutritional-goal-add']);
  }
  onNutritionalGoalDeleted(id: number) {
    this.nutritionalGoals = this.nutritionalGoals?.filter(list => list.id !== id);
  }
  
   // Metody do filtrowania celów
   getUpcomingGoals(): NutritionalGoal[] {
    const today = new Date();
    return this.nutritionalGoals?.filter(goal => new Date(goal.goalStartDate) > today) || [];
  }

  getActiveGoals(): NutritionalGoal[] {
    const today = new Date();
    return this.nutritionalGoals?.filter(goal => 
      new Date(goal.goalStartDate) <= today && new Date(goal.goalEndDate) >= today
    ) || [];
  }

  getCompletedGoals(): NutritionalGoal[] {
    const today = new Date();
    return this.nutritionalGoals?.filter(goal => new Date(goal.goalEndDate) < today) || [];
  }

}
