import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  list:any; //all products
  list2:any;

  constructor(public http:HttpClient, public app:AppComponent)
  {
    let url=app.baseUrl+'seller/getAllProducts'+app.id
    http.get(url).subscribe((data:any)=>
      {
        if(data==null){
          window.alert('something is wrong')
        }
        else{
          this.list=data;
        }

    });

    let url2=app.baseUrl+'admin/getAllCategories'
    http.get(url2).subscribe((data:any)=>
      {
        if(data==null){
          window.alert('Something is wrong')
        }
        else{
          this.list2=data;
        }
      });
  }

  id:number=0;
  name:string='';
  price:number=0;
  quantity:number=0;
  description:string='';
  discount:number=0;
  rating:any=0
  catid:number=0;

  addProduct(){
    let product={
      "name":this.name,
      "userid":this.app.id,
      "price":this.price,
      "quantity":this.quantity,
      "description":this.description,
      "discount":this.discount,
      "categoryid":this.catid,
      "rating":this.rating,
    };

    if(this.catid && this.name && this.app.id && this.quantity && this.price){
      let url=this.app.baseUrl+'seller/addNewProduct';
      this.http.post(url, product).subscribe((data:any)=>
        {
          if(data==null){ 
            window.alert("something is wrong")
          }
          else{
            this.list.push(data);
            this.resetForm();
          }
  
        })
    }
    else{
      window.alert('Please fill all required data..');
    }

   
  }

  resetForm() {
    this.name = '';
    this.price = 0;
    this.quantity = 0;
    this.description = '';
    this.discount = 0;
    this.rating = 0;
    this.catid = 0;
  }

}
