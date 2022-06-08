import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TextAreaMaxBytesComponent } from './components/text-area-max-bytes/text-area-max-bytes.component';
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { TextAreaMaxLengthComponent } from './components/text-area-max-length/text-area-max-length.component';

@NgModule({
  declarations: [
    AppComponent,
    TextAreaMaxBytesComponent,
    TextAreaMaxLengthComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
