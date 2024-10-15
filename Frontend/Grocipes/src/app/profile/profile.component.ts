import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy{
  userName: string | undefined;
  private userSub: Subscription  = new Subscription();

  constructor(private authService: AuthService){
  }
 
  ngOnInit(): void {
    this.userSub = this.authService.user.subscribe(user =>{
    this.userName =  user?.email
     
    }
    );
  }
  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }



  logOut(){
    this.authService.logout();
  }
}
