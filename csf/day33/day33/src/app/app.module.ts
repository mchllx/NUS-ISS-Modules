import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule, HammerModule, provideClientHydration } from '@angular/platform-browser';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';

// import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewchildComponent } from './components/viewchild.component';
import { TestComponent } from './components/test.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MaterialmoduleModule } from './module/materialmodule.module';

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
    MatSlideToggleModule,
    MaterialmoduleModule,
    HammerModule
  ],
  providers: [
    // provideClientHydration()
  
    provideAnimationsAsync('noop'),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
