import {Component, OnInit} from '@angular/core';
import {ItemService} from './item.service';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {

  ids = [1, 3];

  constructor(private itemService: ItemService) {
  }

  ngOnInit() {
  }

  getItems() {
    this.itemService.getItems();
  }

  createRegistry() {
    this.itemService.createRegistry(this.ids);
  }
}
