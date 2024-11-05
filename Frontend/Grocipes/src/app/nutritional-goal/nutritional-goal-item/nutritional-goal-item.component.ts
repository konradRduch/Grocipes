import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { NutritionalGoal } from 'src/app/model/nutritional-goal.model';
import { NutritionalGoalService } from 'src/app/service/nutritional-goal.service';

@Component({
  selector: 'app-nutritional-goal-item',
  templateUrl: './nutritional-goal-item.component.html',
  styleUrls: ['./nutritional-goal-item.component.css']
})
export class NutritionalGoalItemComponent implements OnInit{
  @Input() nutritionalGoal: NutritionalGoal | undefined;
  id: number | undefined;
  @Output() nutritionalGoalDeleted = new EventEmitter<number>();
  

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private nutritionalGoalService: NutritionalGoalService){
    
  }
  
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
      }
    );
    this.id = this.nutritionalGoal?.id;
  }

  onEdit(){
    this.router.navigate([`nutritional-goal/nutritional-goal-edit/${this.id}`]);
  }

  onDelete(){
    if (this.id) {
      this.nutritionalGoalService.deleteNutritionalGoal(this.id).subscribe();
      this.nutritionalGoalDeleted.emit(this.id);
    }
  }


  isUpcoming(goalStartDate: string): boolean {
    const today = new Date();
    const start = new Date(goalStartDate);
    return start > today;
  }
  
  isActive(goalStartDate: string, goalEndDate: string): boolean {
    const today = new Date();
    const start = new Date(goalStartDate);
    const end = new Date(goalEndDate);
    return today >= start && today <= end;
  }
  
  isCompleted(goalEndDate: string): boolean {
    const today = new Date();
    const end = new Date(goalEndDate);
    return today > end;
  }






}
