import {Injectable} from '@angular/core';
import {Recipe} from './recipe.model';
import {Ingredient} from '../shared/Ingredient';
import {ShoppingListService} from '../shopping-list/shopping-list.service';
import {Subject} from "rxjs/Subject";

@Injectable()
export class RecipeService {

  recipesChanged = new Subject<Recipe[]>();

  private recipes: Recipe[] = [
    new Recipe('Paneer Tikka Masala', 'Test Description', 'https://i0.wp.com/www.cookingfromheart.com/wp-content' +
      '/uploads/2017/03/Paneer-Tikka-Masala-4.jpg?resize=600%2C400', [
      new Ingredient('Paneer', 10),
      new Ingredient('Onions', 3),
      new Ingredient('Tomato', 10)
    ]),
    new Recipe('Vegetable Dum Biryani', 'Tasty Dish',
      'https://i1.wp.com/www.foodvedam.com/wp-content/uploads/2015/04/w24.jpg?resize=600%2C400', [
        new Ingredient('Veggies', 10),
        new Ingredient('Onions', 4),
        new Ingredient('Rice', 1)
      ])
  ];

  constructor(private shoppingService: ShoppingListService) {
  }

  setRecipes(recipes: Recipe[]) {
    this.recipes = recipes;
    this.recipesChanged.next(this.recipes);
  }

  getRecipes() {
    return this.recipes.slice();
  }

  deleteRecipe(id: number) {
    this.recipes.splice(id, 1);
    this.recipesChanged.next(this.recipes);
  }

  getRecipeByID(id: number) {
    return this.recipes[id];
  }

  addRecipe(newRecipe: Recipe) {
    this.recipes.push(newRecipe);
    this.recipesChanged.next(this.recipes);
  }

  updateRecipe(id: number, newRecipe: Recipe) {
    this.recipes[id] = newRecipe;
    this.recipesChanged.next(this.recipes);
  }

  addIngredientsToShoppingList(ingredients: Ingredient[]) {
    this.shoppingService.addIngredients(ingredients);
  }
}
