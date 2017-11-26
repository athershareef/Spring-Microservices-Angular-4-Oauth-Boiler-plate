export class Item {
  public itemId: number;

  public name: string;

  public category: string;

  public price: number;

  public imageUrl: string;

  public brand: string;


  constructor(name: string, category: string, price: number, imageUrl: string, brand: string, itemId?: number) {
    this.itemId = itemId;
    this.name = name;
    this.category = category;
    this.price = price;
    this.imageUrl = imageUrl;
    this.brand = brand;
  }


}
