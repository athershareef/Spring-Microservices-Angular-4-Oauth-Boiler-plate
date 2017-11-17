import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../model/user';

@Injectable()
export class GiftComService {

  private ServerURL = 'http://localhost:8080';
  private authURL = '/google/login';
  private principal = new Object();

  constructor(private http: HttpClient, private router: Router) {
  }


  signInUser(user: User) {

    const headers = new HttpHeaders({
      // 'Content-Type': 'application/w-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa(user.email + ':' + user.password),
      'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
    });
    headers.append('Content-Type', 'application/json');

    console.log(headers);
    return this.http.post(this.ServerURL + '/login', null, {headers: headers, withCredentials: true});
  }

  signUpUser(user) {
    return this.http.post(this.ServerURL + '/create', user, {withCredentials: true});
  }

  signUpGoogleUser(token) {
    const headers = new HttpHeaders({
      // 'Content-Type': 'application/w-www-form-urlencoded',
      'Authorization': 'Bearer ' + token,
      'Signup': 'true',
      'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
    });
    headers.append('Content-Type', 'application/json');

    console.log(headers);
    return this.http.post(this.ServerURL + '/login', null, {headers: headers, withCredentials: true});

  }


  getCurrentUser() {
    return this.http.post(this.ServerURL + '/login', null, {withCredentials: true});
  }


  signOut() {
    return this.http.get(this.ServerURL + '/logout', {withCredentials: true});
  }


  getGoogleOauth() {
    return this.ServerURL + this.authURL;
  }

  validateGoogleOAuth(token) {
    const headers = new HttpHeaders({
      // 'Content-Type': 'application/w-www-form-urlencoded',
      'Authorization': 'Bearer ' + token,
      'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
    });
    headers.append('Content-Type', 'application/json');

    console.log(headers);
    return this.http.post(this.ServerURL + '/login', null, {headers: headers, withCredentials: true});
  }

  updateUser(id: number, user: User) {
    return this.http.put(this.ServerURL + '/user/' + id, user, {withCredentials: true});
  }
}
