import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoadingService {
  
  loadingSignal = signal<boolean>(false);
  private activeRequest = 0

  show(){
    this.activeRequest++;
    this.loadingSignal.set(true);
  }

  hide(){
    this.activeRequest--;
    if(this.activeRequest<=0){
      this.activeRequest=0;
        this.loadingSignal.set(false);
    }
    
  }
}
