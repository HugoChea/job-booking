import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('@modules/home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'authentication',
    loadChildren: () =>
      import('@modules/authentication/authentication.module').then(m => m.AuthenticationModule)
  },
  {
    path: 'recruiter',
    loadChildren: () =>
      import('@modules/recruiter/recruiter.module').then(m => m.RecruiterModule)
  },
  {
    path: 'authentication',
    loadChildren: () =>
      import('@modules/applicant/applicant.module').then(m => m.ApplicantModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
