import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Subscription } from 'rxjs';
import { UserData } from '../model/userData.model';
import { UserDataService } from '../service/user-data.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {
  userProfileInfo: any = {}

  constructor(private authService: AuthService, private userDataService: UserDataService, private router: Router) {
  }

  ngOnInit(): void {
    this.userDataService.getUserProfileInfo().subscribe(
      (userProfileInfo: any) => {
       this.userProfileInfo = userProfileInfo;
      }
    );
  }

  ngOnDestroy(): void {
  }

  logOut() {
    this.authService.logout();
  }

  update() {
    this.router.navigate(['profile/body-parameters-update']);
    //profile/body-parameters-update
  }
  history() {
    this.router.navigate(['profile/body-parameters-history']);
  }

}
