import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main.component';
import { FormComponent } from './components/form.component';
import { canLeave, canProceed } from './guards';
import { NoticeComponent } from './components/notice.component';

const routes: Routes = [
  {path: '', component:MainComponent},
  {path: 'notice', component:NoticeComponent},
  {path: 'form', component:FormComponent,
    canActivate: [canProceed],
    canDeactivate: [canLeave]},

  {path: '**', redirectTo:'/', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
