import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { NavBar } from './component/nav-bar/nav-bar';
import { LoadingService } from './shared/service/loading-service';
import { LoadingComponent } from './shared/component/loading-component/loading-component';


@Component({
  selector: 'app-root',
  imports: [NavBar, RouterOutlet,LoadingComponent],
  template: `
 <app-nav-bar />
    <router-outlet />

    @if (loadingService.loadingSignal()) {
      <app-loading-component/>
    }
  `,
  styles: [``]
})
export class App {
  protected readonly title = signal('WebCom');
  loadingService = inject(LoadingService)
}
