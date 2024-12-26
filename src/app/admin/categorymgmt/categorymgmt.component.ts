import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-categorymgmt',
  templateUrl: './categorymgmt.component.html',
  styleUrls: ['./categorymgmt.component.css']
})
export class CategorymgmtComponent {
  list:any;

  constructor(public http:HttpClient, public app:AppComponent){

  //api to call get all categories

  let url=app.baseUrl+'admin/getAllCategories';

  http.get(url).subscribe((data:any)=>
    {
        if(data==null){
          window.alert("something is wrong")
        }
        else{
          this.list=data;
        }

  })
  }

  name:String='';

  addCategory(){
    let url=this.app.baseUrl+'admin/addNewCategory'+this.app.id;
    this.http.post(url,this.name).subscribe((data:any)=>{

      if(data==null){
        window.alert('something is wrong')
      }
      else{
        this.list.push(data);
        this.name='';
      }
    })

  }

}
