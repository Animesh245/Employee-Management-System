import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Deparment } from './deparment';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseURL = "http://localhost:8080/api/v1/departments";

  constructor(private httpClient: HttpClient) { }

  getDepartmentList(): Observable<Deparment[]>{
    return this.httpClient.get<Deparment[]>(`${this.baseURL}/`);
  }

  //this is a post method, will be used to send data to api
  createDepartment(deparment: Deparment): Observable<Object>{
    return this.httpClient.post<Object>(`${this.baseURL}/`, deparment);
  }

  getDepartmentById(id: number): Observable<Deparment>{
    return this.httpClient.get<Deparment>(`${this.baseURL}/${id}`);
  }

  updateDepartment(id: number, deparment: Deparment): Observable<Object>{
    return this.httpClient.put<Object>(`${this.baseURL}/${id}`, deparment);
  }

  deleteDepartment(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
