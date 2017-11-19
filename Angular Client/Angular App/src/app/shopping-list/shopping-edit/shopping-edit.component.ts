import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Ingredient} from '../../shared/Ingredient';
import {ShoppingListService} from '../shopping-list.service';
import {NgForm} from "@angular/forms";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-shopping-edit',
  templateUrl: './shopping-edit.component.html',
  styleUrls: ['./shopping-edit.component.css']
})
export class ShoppingEditComponent implements OnInit, OnDestroy {

  @ViewChild('f') shoppingListForm: NgForm;

  editedSubs: Subscription;
  editMode = false;
  editedItemIndex: number;
  editedItem: Ingredient;

  constructor(private shoppingService: ShoppingListService) {
  }

  ngOnInit() {
    this.editedSubs = this.shoppingService.startedEditing.subscribe((index: number) => {
      this.editMode = true;
      this.editedItemIndex = index;
      this.editedItem = this.shoppingService.getIngredient(index);
      this.shoppingListForm.setValue({
        name: this.editedItem.name,
        amount: this.editedItem.amount
      })
    });
  }

  onAddItem(form: NgForm) {

    const value = form.value;
    const newIngredient = new Ingredient(value.name, value.amount);
    if (this.editMode) {
      this.shoppingService.updateIngredient(this.editedItemIndex, newIngredient);
    }
    else {
      this.shoppingService.addIngredient(newIngredient);
    }
    this.onClear();
  }

  ngOnDestroy(): void {
    this.editedSubs.unsubscribe();
  }

  onClear() {
    this.shoppingListForm.reset();
    this.editMode = false;
  }

  onDelete() {
    this.shoppingService.deleteIngredient(this.editedItemIndex);
    this.onClear();
  }
}
