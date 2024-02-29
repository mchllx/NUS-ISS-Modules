import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PokemonComponent } from './components/pokemon.component';
import { SearchComponent } from './components/search.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material/material/material.module';
import { provideNativeDateAdapter } from '@angular/material/core';
import { HttpClientModule } from '@angular/common/http';
import { PokemonService } from './services/pokemon.service';
import { RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from './components/details.component';
import { CommentComponent } from './components/comment.component';

const appRoutes: Routes = [
  { path: '', component: SearchComponent },
  { path: 'search/:term', component: PokemonComponent },
  { path: 'pokemon/:term', component: DetailsComponent },
  { path: 'comment/:term', component: CommentComponent },
  { path: '**', redirectTo: '/', pathMatch:'full' }
]

@NgModule({
  declarations: [
    AppComponent, PokemonComponent, SearchComponent, DetailsComponent, CommentComponent
  ],

  imports: [
    BrowserModule, ReactiveFormsModule, MaterialModule,
    HttpClientModule, RouterModule.forRoot(appRoutes)
  ],

  providers: [
    provideNativeDateAdapter(), provideAnimationsAsync(),
    PokemonService
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
