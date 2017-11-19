import {Injectable, OnInit} from '@angular/core';
import {Ingredient} from '../shared/Ingredient';
import {Subject} from "rxjs/Subject";

@Injectable()
export class ShoppingListService implements OnInit {

  ingredientsChanged = new Subject<Ingredient[]>();

  startedEditing = new Subject<number>();

  private ingredients: Ingredient[] = [new Ingredient('Paneer', 20), new Ingredient('Rice', 35)];


  constructor() {
  }

  getIngredientsList() {
    return this.ingredients;
  }

  getIngredient(index: number) {
    return this.ingredients[index];
  }

  ngOnInit() {
  }

  addIngredient(ingredient: Ingredient) {
    this.ingredients.push(ingredient);
    this.ingredientsChanged.next(this.ingredients);
  }

  addIngredients(ingredients: Ingredient[]) {
    this.ingredients.push(...ingredients);
    this.ingredientsChanged.next(this.ingredients);
  }

  updateIngredient(index: number, ingredient: Ingredient) {
    this.ingredients[index] = ingredient;
    this.ingredientsChanged.next(this.ingredients);
  }

  deleteIngredient(editedItemIndex: number) {
    this.ingredients.splice(editedItemIndex, 1);
    this.ingredientsChanged.next(this.ingredients);
  }
}
