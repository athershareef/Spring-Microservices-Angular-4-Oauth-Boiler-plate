import {Component, OnInit} from '@angular/core';
import * as firebase from "firebase";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
    firebase.initializeApp({
      apiKey: "AIzaSyAr557aa-tXwbmOtQLJdWFFctE4W1-gd_0",
      authDomain: "angularhttp-d5716.firebaseapp.com"
    });
  }

  feature = '' +
    'recipe';

  onFeatureAdded(eventName: string) {
    this.feature = eventName;
  }
}
