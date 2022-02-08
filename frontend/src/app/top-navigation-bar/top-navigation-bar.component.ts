import {Component, Input, OnInit, Output} from '@angular/core';
import {LoginFormComponent} from "../login-form/login-form.component";
import {HttpService} from "../http.service";

@Component({
  selector: 'app-top-navigation-bar',
  templateUrl: './top-navigation-bar.component.html',
  styleUrls: ['./top-navigation-bar.component.css'],
  providers: [LoginFormComponent]
})
export class TopNavigationBarComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }


}
