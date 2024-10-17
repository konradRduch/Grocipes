import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ShoppingList } from 'src/app/model/shopping-list.model';


enum Color {
  BLUE,
  GRAY,
  GREEN,
  YELLOW,
  TURQUOISE,
  BLACK
}
@Component({
  selector: 'app-shopping-list-item',
  templateUrl: './shopping-list-item.component.html',
  styleUrls: ['./shopping-list-item.component.css']
})
export class ShoppingListItemComponent implements OnInit{
  @Input() shoppList: ShoppingList | undefined;
  id: number | undefined;
  cardColor: number| undefined;
  active: boolean = true;
  enum: typeof Color = Color;

  constructor(private router: Router, private activatedRoute: ActivatedRoute){

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        console.log(this.id);
      }
    )
    this.cardColor = this.shoppList?.color;
    this.id = this.shoppList?.id;
  }

  
  onEdit(){
    this.router.navigate(['shopping-list/shopping-list-edit/',`${this.id}`]);
  }
  onDelete(){

  }

}
