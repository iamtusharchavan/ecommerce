import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent {

  list:any;

  constructor(public http:HttpClient, public app:AppComponent) {
    let url=app.baseUrl+"buyer/history/"+app.id;

     http.get(url).subscribe((data:any)=>{
        if(data==null){
          window.alert("something is wrong");
        }else{
          this.list=data;
          console.log(this.list);
        }
     })
  }

  whatTOShow:number=0;
  prodid!:number;
  stars:number=5;
  addRating(pid:number){
    console.log(pid)
    this.whatTOShow=1;
    this.prodid=pid;
    console.log(this.prodid);
  }

  updateRating(){
    this.whatTOShow=0;
    let array: number[] = [];
    array[0]=this.app.id;
    array[1]=this.prodid;
    array[2]=this.stars;

    let url=this.app.baseUrl+"buyer/addRating";

    this.http.post(url,array).subscribe((data:any)=>{
      if (data==null) {
        window.alert("Something is Wrong")
      }else{
        
        if (data==1) {
          window.alert("rating updated successfully")
        } else {
          window.alert("something is wrong")
        }
      }
    })
  }

}
