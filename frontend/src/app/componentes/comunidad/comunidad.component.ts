import { Header } from '../header/header';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Peticion } from '../../servicios/peticion';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { HttpHeaders } from '@angular/common/http';

const headers = new HttpHeaders({
  'Authorization': `Bearer ${localStorage.getItem("token")}`
});


@Component({
  selector: 'app-comunidad',
  imports: [Header, CommonModule, FormsModule],
  templateUrl: './comunidad.component.html',
  styleUrl: './comunidad.component.css'
})
export class ComunidadComponent implements OnInit {

  comunidades: any[] = []

  nuevaComunidad: any = {
    tematica: '',
    nombre: '',
    descripcion: '',
    tipo: '',
    id_creador: 1,
  };
  

  constructor(private peticion: Peticion, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.cargarComunidades();
  }

  cargarComunidades() {
    let post = {
      host: this.peticion.urlReal,
      path: "/api/comunidades",
      payload: {
      }
    }
    this.peticion.get(post.host + post.path).then((res: any) => {
      console.log(res)
      this.comunidades = res
      this.cdr.detectChanges()
    }).catch((err) => {
      console.log(err)
      console.log("Error al obtener comunidades")
    })
  }

}

// const headers = new HttpHeaders({
//   'Authorization': `Bearer ${localStorage.getItem("token")}`
// });

// this.http.post("http://localhost:8080/comunidad/crear", payload, { headers })
//   .subscribe(res => console.log(res));