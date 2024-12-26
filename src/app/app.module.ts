import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { BuyerComponent } from './buyer/buyer.component';
import { SellerComponent } from './seller/seller.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CategorymgmtComponent } from './admin/categorymgmt/categorymgmt.component';
import { ProductComponent } from './seller/product/product.component';
import { ShowproductsComponent } from './buyer/showproducts/showproducts.component';
import { CartproductComponent } from './buyer/cartproduct/cartproduct.component';
import { HistoryComponent } from './buyer/history/history.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    BuyerComponent,
    SellerComponent,
    CategorymgmtComponent,
    ProductComponent,
    ShowproductsComponent,
    CartproductComponent,
    HistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
