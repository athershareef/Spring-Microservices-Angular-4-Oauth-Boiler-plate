import {Injectable} from '@angular/core';
import {UserComService} from '../services/user-com.service';
import {Subject} from 'rxjs/Subject';
import {Item} from '../model/item';

@Injectable()
export class ItemService {

  itemsChanged = new Subject<Item[]>();

  private items: Item[];


  constructor(private userComService: UserComService) {
  }

  getItems() {
    this.userComService.getItems().subscribe((items: Item[]) => {
      this.items = items;
      this.itemsChanged.next(this.items)
      console.log(items);
    }, error => {
      console.log(error);
    });
  }

  createRegistry(ids: number[]) {
    this.userComService.createRegistry(ids).subscribe((response) => {
      console.log(response);
    }, error => {
      console.log(error);
    });
  }

}
