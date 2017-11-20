import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../model/user';

@Injectable()
export class UserComService {

  private userPathURL = 'http://localhost:8080/user/';

  private serverURL = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }


  getItems() {
    return this.http.get(this.serverURL + 'item', {withCredentials: true});
  }

  forgotPassword(email: String) {
    return this.http.post(this.userPathURL + 'forgot', email);
  }


  signInUser(user: User) {

    const headers = new HttpHeaders({
      // 'Content-Type': 'application/w-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa(user.email + ':' + user.password),
      'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
    });
    headers.append('Content-Type', 'application/json');

    return this.http.post(this.userPathURL + 'login', null, {headers: headers, withCredentials: true});
  }

  signUpUser(user) {
    return this.http.post(this.userPathURL + 'create', user, {withCredentials: true});
  }

  signUpGoogleUser(token) {
    const headers = new HttpHeaders({
      // 'Content-Type': 'application/w-www-form-urlencoded',
      'Authorization': 'Bearer ' + token,
      'Signup': 'true',
      'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
    });
    headers.append('Content-Type', 'application/json');

    return this.http.post(this.userPathURL + 'login', null, {headers: headers, withCredentials: true});

  }


  getCurrentUser() {
    return this.http.post(this.userPathURL + 'login', null, {withCredentials: true});
  }


  signOut() {
    return this.http.get(this.serverURL + 'logout', {withCredentials: true});
  }

  validateGoogleOAuth(token) {
    const headers = new HttpHeaders({
      // 'Content-Type': 'application/w-www-form-urlencoded',
      'Authorization': 'Bearer ' + token,
      'X-Requested-With': 'XMLHttpRequest' // to suppress 401 browser popup
    });
    headers.append('Content-Type', 'application/json');

    return this.http.post(this.userPathURL + 'login', null, {headers: headers, withCredentials: true});
  }

  updateUser(id: number, user: User) {
    return this.http.put(this.userPathURL + 'id/' + id, user, {withCredentials: true});
  }

  createRegistry(ids: number[]) {
    return this.http.post(this.serverURL + 'registry/create', ids, {withCredentials: true});
  }
}
