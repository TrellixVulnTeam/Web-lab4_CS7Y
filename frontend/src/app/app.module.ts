import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { TopNavigationBarComponent } from './top-navigation-bar/top-navigation-bar.component';
import { BottomNavigationBarComponent } from './bottom-navigation-bar/bottom-navigation-bar.component';
import {HttpClientModule} from "@angular/common/http";
import { MainPageComponent } from './main-page/main-page.component';
import { UnauthorizedErrorPageComponent } from './unauthorized-error-page/unauthorized-error-page.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';

const routes: Routes = [
  {path: 'registration', component: RegistrationFormComponent},
  {path: 'login', component: LoginFormComponent},
  {path: 'main', component: window.screen.width >= 1210 ?
      MainPageComponent : window.screen.width >= 836 ? MainPageComponent : MainPageComponent}, // MainPage -> TabletMainPage -> MobileMainPage
  {path: 'unauthorized', component: UnauthorizedErrorPageComponent},
  {path: '', component: WelcomePageComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    TopNavigationBarComponent,
    BottomNavigationBarComponent,
    MainPageComponent,
    UnauthorizedErrorPageComponent,
    WelcomePageComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [LoginFormComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
