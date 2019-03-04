import {Injectable} from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {TopicsService} from './topics.service';
import {Topic} from './topic.model';
import {Observable} from 'rxjs';

@Injectable()
export class PreFetchGuard implements Resolve<Topic[]> {

  constructor(private service: TopicsService) {}

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): Observable<Topic[]> | Promise<Topic[]> | Topic[] {
    return null;
  }
}
