import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Subscription } from 'rxjs';
import { DataStorageService } from '../service/data-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy{
  isAuthenticated: boolean = false;
  isAdmin: boolean = false;
  private userSub: Subscription  = new Subscription();

  constructor(private authService: AuthService,private dataStorageService: DataStorageService){
  }

  ngOnInit(): void {
    this.userSub = this.authService.user.subscribe(user =>{
        this.isAuthenticated = !!user;
        if(user?.roles==="ADMIN"){
          this.isAdmin = true;
        }else{
          this.isAdmin = false;
        }
      }
    );
  }
  ngOnDestroy(): void {
    this.userSub.unsubscribe();
  }

  
}
