import {Component, OnDestroy, OnInit} from '@angular/core';
import {Recipe} from '../recipe.model';
import {RecipeService} from "../recipe.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit, OnDestroy {

  ngOnDestroy(): void {
    this.recipesChangedSubs.unsubscribe();
  }

  recipes: Recipe[];
  recipesChangedSubs: Subscription;

  constructor(private recipeService: RecipeService, private router: Router, private route: ActivatedRoute,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.recipesChangedSubs = this.recipeService.recipesChanged.subscribe((recipes: Recipe[]) => {
      this.recipes = recipes;
    });
    this.recipes = this.recipeService.getRecipes();
  }

  onNewRecipe() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

}
