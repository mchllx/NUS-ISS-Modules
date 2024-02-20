import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { FormComponent } from './components/form.component';
import { RouteService } from './components/route.service';
import { ReactiveFormsModule } from '@angular/forms';
import { NoticeComponent } from './components/notice.component';
import Dexie from 'dexie';
import { StorageService } from './components/storage.service';
import { UserStorage } from './components/user.storage.service';

@NgModule({
  declarations: [
    AppComponent, MainComponent, FormComponent, NoticeComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, ReactiveFormsModule
  ],
  providers: [
    RouteService,
    StorageService,
    UserStorage
    // provideClientHydration()
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
