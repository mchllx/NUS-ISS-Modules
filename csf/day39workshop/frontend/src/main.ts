import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';

// FIX: Package "hammerjs" was found but does not support schematics
import 'hammerjs';

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
