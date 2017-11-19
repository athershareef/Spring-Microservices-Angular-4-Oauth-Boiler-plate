import {Injectable} from '@angular/core';
import {Http, Response} from "@angular/http";
import {RecipeService} from "../recipes/recipe.service";
import 'rxjs/Rx';
import {AuthService} from "../auth/auth.service";

@Injectable()
export class DataStorageService {

  constructor(private http: Http, private recipeService: RecipeService, private authService: AuthService) {
  }

  storeRecipes() {
    let mytoken = this.authService.getToken();
    return this.http.put('https://angularhttp-d5716.firebaseio.com/recipes.json?auth=' + mytoken, this.recipeService.getRecipes());
  }

  getRecipes() {
    let mytoken = this.authService.getToken();

    this.http.get('https://angularhttp-d5716.firebaseio.com/recipes.json?auth=' + mytoken, this.recipeService.getRecipes())
      .subscribe((response: Response) => {
        this.recipeService.setRecipes(response.json());
      });
  }

}
