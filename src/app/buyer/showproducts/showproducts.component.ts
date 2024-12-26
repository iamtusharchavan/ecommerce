import { HttpClient } from '@angular/common/http';
import { NonNullAssert } from '@angular/compiler';
import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-showproducts',
  templateUrl: './showproducts.component.html',
  styleUrls: ['./showproducts.component.css']
})
export class ShowproductsComponent {
  list:any;
  list2:any;

  constructor(public http:HttpClient, public app:AppComponent){
    let url = app.baseUrl+'admin/getAllCategories';

    http.get(url).subscribe((data:any)=>{
      if(data==null){
        window.alert('Something is wrong')
      }else{
        this.list=data;
        console.log(this.list);
      }
    })
  }

  minPrice:number=0;
  maxPrice:number=0;
  minRating:number=0;
  categoryid:number=0;

  load(){
    let arr = [this.categoryid, this.minPrice, this.maxPrice, this.minRating];
    console.log(arr);
    let url = this.app.baseUrl+'buyer/getAllProductsByFilter';

    this.http.post(url, arr).subscribe((data:any)=>{
      if(data==null){
       window.alert('Something is wrong');
      }
      else if(data.length==0){
        window.alert('No product avaible');
      }
      else{
        this.list2=data;
        console.log(this.list2);
      }
    });
  }

  addTocart(id:number){
    let url = this.app.baseUrl+"buyer/addToCart/"+id+"/"+this.app.id;
    this.http.get(url).subscribe((data:any)=>{
      if(data==null){
          window.alert("something is wrong");
      }else{
          if(data==1){
            window.alert("Product is added to cart");
          }else{
              window.alert("product is already added to cart");
          }
      }
    });

  }

}
