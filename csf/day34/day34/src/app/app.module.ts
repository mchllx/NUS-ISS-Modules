import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MaterialModule } from './modules/material/material.module';
import { FormsModule } from '@angular/forms';
import { EmployeelistComponent } from './components/employeelist.component';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeAddEditComponent } from './components/employee-add-edit.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NewslistComponent } from './components/newslist.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeelistComponent,
    EmployeeAddEditComponent,
    NewslistComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    // provideClientHydration(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
