import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Apollo } from 'apollo-angular';
import { DocumentNode } from 'graphql';
import { map, Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';

export type ApiMode = 'REST' | 'GRAPHQL';

export interface ApiRequestConfig<T> {
  mode?: ApiMode; // If not provided, falls back to the global defaultMode
  rest?: {
    url: string; // e.g., '/api/getProducts' (apiUrl gets prepended automatically)
    method?: 'GET' | 'POST' | 'PUT' | 'DELETE';
    body?: any;
  };
  graphql?: {
    query: DocumentNode;
    variables?: any;
    extractKey: string; // The property key to extract from result.data (e.g., 'getProductsList')
    isMutation?: boolean; // Set to true if executing a GraphQL mutation
  };
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  #http = inject(HttpClient);
  #apollo = inject(Apollo);
  #apiUrl = environment.apiUrl;

  // Global toggle to switch between REST and GraphQL everywhere
  defaultMode: ApiMode = 'REST'; 

  request<T>(config: ApiRequestConfig<T>): Observable<T> {
    const mode = config.mode || this.defaultMode;

    if (mode === 'GRAPHQL' && config.graphql) {
      if (config.graphql.isMutation) {
        return this.#apollo.mutate<any>({
          mutation: config.graphql.query, // Apollo uses 'mutation' key here
          variables: config.graphql.variables
        }).pipe(map(result => result.data?.[config.graphql!.extractKey] as T));
      } else {
        // Using .query() instead of .watchQuery() to mimic standard REST one-time fetching behavior
        return this.#apollo.query<any>({
          query: config.graphql.query,
          variables: config.graphql.variables
        }).pipe(map(result => result.data[config.graphql!.extractKey] as T));
      }
    }

    if (mode === 'REST' && config.rest) {
      const method = config.rest.method || 'GET';
      return this.#http.request<T>(method, this.#apiUrl + config.rest.url, { body: config.rest.body });
    }

    throw new Error('Invalid API configuration or missing mode parameters.');
  }
}
