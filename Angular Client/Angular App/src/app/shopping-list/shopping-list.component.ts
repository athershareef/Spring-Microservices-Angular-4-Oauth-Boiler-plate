import {Component, OnDestroy, OnInit} from '@angular/core';
import {Ingredient} from '../shared/Ingredient';
import {ShoppingListService} from './shopping-list.service';
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit, OnDestroy {

  shoppingServiceSubs: Subscription;

  ingredients: Ingredient[];

  constructor(private shoppingService: ShoppingListService) {
  }

  ngOnInit() {
    this.ingredients = this.shoppingService.getIngredientsList();
    this.shoppingServiceSubs = this.shoppingService.ingredientsChanged.subscribe(
      (ingredients: Ingredient[]) => {
        this.ingredients = ingredients;
      }
    );
  }

  ngOnDestroy(): void {
    this.shoppingServiceSubs.unsubscribe();
  }


  onEditItem(index: number) {
    this.shoppingService.startedEditing.next(index);
  }
}
