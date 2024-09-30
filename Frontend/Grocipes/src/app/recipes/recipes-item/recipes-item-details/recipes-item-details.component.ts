import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecipesService } from 'src/app/service/recepies.service';
import { Recipe } from '../../../model/recepie.model';

@Component({
  selector: 'app-recipes-item-details',
  templateUrl: './recipes-item-details.component.html',
  styleUrls: ['./recipes-item-details.component.css'],
  providers: [RecipesService]
})
export class RecipesItemDetailsComponent implements OnInit{
  @Input() recipe: Recipe | undefined;

  constructor(private route: ActivatedRoute, private groceriesService: RecipesService){
  }
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const productTitle = params.get('title');
      if (productTitle) {
        this.recipe = this.groceriesService.getRecipeByName(productTitle);
        console.log(this.recipe);
      }
    });
  }
}
