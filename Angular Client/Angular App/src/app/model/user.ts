export class User {
  public userId: number;
  public username: string;
  public password: string;
  public email: string;
  public role: String[];


  constructor(username: string, email: string, password: string, userId?: number) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = [];
  }
}
