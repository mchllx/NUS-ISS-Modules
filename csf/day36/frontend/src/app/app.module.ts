import { NgModule } from '@angular/core';

//Import RouterModule
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { MainComponent } from './components/main.component';
import { CatComponent } from './components/cat.component';
import { NumberComponent } from './components/number.component';
import { NumberByServiceComponent } from './components/number-by-service.component';

// List of Routes
const appRoutes: Routes = [
  //Define the 'landing' page, blank path = landing page
  { path: '', component: MainComponent },
  { path: 'cat', component: CatComponent },

  // :num == {num} in sb
  { path: 'number/:num', component: NumberComponent },
  { path: 'number-by-service', component: NumberByServiceComponent },

  //Wildcard route - last, path matching is sequential
  { path: '**', redirectTo: '/', pathMatch: 'full'}

]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, CatComponent, NumberComponent, NumberByServiceComponent
  ],
  imports: [
    BrowserModule,
    //Register the list of Routes
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    NumberByServiceComponent
    // provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
