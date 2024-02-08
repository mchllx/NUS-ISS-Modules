import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

// import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewchildComponent } from './components/viewchild.component';
import { TestComponent } from './components/test.component';
import { ServiceWorkerModule } from '@angular/service-worker';

@NgModule({
  declarations: [
    AppComponent,
    ViewchildComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
  ],
  providers: [
    // provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
