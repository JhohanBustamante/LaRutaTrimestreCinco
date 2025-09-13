import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Peticion {

  constructor(private http: HttpClient) { }

  urlReal: string = "http://localhost:8081"
  requestOptions: any = {}

  post = (url: string, payload: {}) => {

    let promesa = new Promise((resolve, reject) => {

      this.requestOptions =  {
        headers: new HttpHeaders({
        }), withCredentials: true
      }
      this.http.post(url, payload, this.requestOptions).toPromise()
      .then((res:any)=>{
        resolve(res)
      }).catch((error:any)=>{
        reject(error)
      })
    })
    return promesa
  }

  get = (url: string) => {

    let promesa = new Promise((resolve, reject) => {

      this.requestOptions =  {
        headers: new HttpHeaders({
         //"":""
        }), withCredentials: true
      }
      this.http.get(url, this.requestOptions).toPromise()
      .then((res:any)=>{
        resolve(res)
      }).catch((error:any)=>{
        reject(error)
      })
    })
    return promesa
  }

  put = (url: string, payload: {}) => {

    let promesa = new Promise((resolve, reject) => {

      this.requestOptions =  {
        headers: new HttpHeaders({
         //"":""
        }), withCredentials: true
      }
      this.http.put(url, payload, this.requestOptions).toPromise()
      .then((res:any)=>{
        resolve(res)
      }).catch((error:any)=>{
        reject(error)
      })
    })
    return promesa
  }

  delete = (url: string, payload: {}) => {

    let promesa = new Promise((resolve, reject) => {

      this.requestOptions =  {
        headers: new HttpHeaders({
         //"":""
        }), withCredentials: true, 
        body: payload
      }
      this.http.request("delete", url, this.requestOptions).toPromise()
      .then((res:any)=>{
        resolve(res)
      }).catch((error:any)=>{
        reject(error)
      })
    })
    return promesa
  }

  UploadFile(file:File, api:string):Observable<any>{
    const formData = new FormData
    formData.append('file', file)
    return this.http.post(api, formData)
  }

}