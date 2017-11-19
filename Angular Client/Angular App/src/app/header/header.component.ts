import {Component} from '@angular/core';
import {DataStorageService} from "../shared/data-storage.service";
import {AuthService} from "../auth/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {

  constructor(private storageService: DataStorageService, private authService: AuthService) {
  }

  onSaveData() {
    this.storageService.storeRecipes()
      .subscribe((response) => {
          alert('Data saved to firebase !');
        },
        (error) => {
          alert('Unable to Save Data');
        });
  }

  onFetchData() {
    this.storageService.getRecipes();
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  onLogout() {
    this.authService.logout();
  }
}
