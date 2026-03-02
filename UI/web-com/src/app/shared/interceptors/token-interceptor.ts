import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoadingService } from '../service/loading-service';
import { finalize } from 'rxjs';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const loadingService = inject(LoadingService);
  loadingService.show();
  const newReq = req.clone({
    headers: req.headers.set('Authorization',`Bearer ${localStorage.getItem('token')}`)
  })
  return next(newReq).pipe(
    finalize(() => loadingService.hide())
  );
};
