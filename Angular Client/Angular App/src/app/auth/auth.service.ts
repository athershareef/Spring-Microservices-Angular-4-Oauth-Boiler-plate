import {Injectable} from '@angular/core';
import * as firebase from "firebase";
import {Router} from "@angular/router";

@Injectable()
export class AuthService {

  token: string;

  constructor(private router: Router) {
  }

  signUpUser(email: string, password: string) {
    firebase.auth().createUserWithEmailAndPassword(email, password)
      .then(() => {
        this.router.navigate(['/']);
      })
      .catch(error => alert("User not created :(, Please try again"));
  }

  signInUser(email: string, password: string) {
    firebase.auth().signInWithEmailAndPassword(email, password)
      .then(response => {
        firebase.auth().currentUser.getToken()
          .then((token: string) => {
            this.token = token;
            this.router.navigate(['/']);
          })
      }, error => {
        alert("Unable to Sign In, please check the id and password")
      });
  }

  getToken() {
    firebase.auth().currentUser.getToken()
      .then((token: string) => {
        this.token = token;
      })
    return this.token;
  }

  isAuthenticated() {
    return this.token != null;
  }

  logout() {
    firebase.auth().signOut();
    this.token = null;
  }

}
