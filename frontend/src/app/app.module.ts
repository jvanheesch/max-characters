import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TextAreaMaxBytesComponent } from './components/text-area-max-bytes/text-area-max-bytes.component';

@NgModule({
  declarations: [
    AppComponent,
    TextAreaMaxBytesComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
