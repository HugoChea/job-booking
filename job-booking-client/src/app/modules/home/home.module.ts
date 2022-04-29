import { NgModule } from '@angular/core';

import { HomeRoutingModule } from './home-routing.module';
import { LandingComponent } from './landing/landing.component';
import { SharedModule } from '@shared/shared.module';


@NgModule({
  declarations: [
    LandingComponent,
  ],
  imports: [
    SharedModule,
    HomeRoutingModule
  ]
})
export class HomeModule { }
