import { Component, Input } from '@angular/core';
import { Recipe } from 'src/app/recepie.model';

@Component({
  selector: 'app-recipes-item',
  templateUrl: './recipes-item.component.html',
  styleUrls: ['./recipes-item.component.css']
})
export class RecipesItemComponent {
  @Input() recipe!: Recipe;

}
