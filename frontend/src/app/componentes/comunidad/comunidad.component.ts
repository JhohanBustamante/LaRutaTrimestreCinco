import { Header } from '../header/header';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Peticion } from '../../servicios/peticion';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';




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
    let get = {
      host: this.peticion.urlReal,
      path: "/api/comunidades",
      payload: {
      }
    }
    this.peticion.get(get.host + get.path).then((res: any) => {
      console.log(res)
      this.comunidades = res
      this.cdr.detectChanges()
    }).catch(() => {
      console.log("Error al obtener comunidades")
    })
  }

  crearComunidad() {
    let post = {
      host: this.peticion.urlReal,
      path: "/api/crear",
      payload: {
        tematica: this.nuevaComunidad.tematica,
        nombre: this.nuevaComunidad.nombre,
        descripcion: this.nuevaComunidad.descripcion,
        tipo: this.nuevaComunidad.tipo,
        idCreador: 1,
      }
    }

    this.peticion.post(post.host + post.path, post.payload).then((res: any) => {
      console.log("Comunidad creada:", res);
      if (res.estado) {
        Swal.fire({
          title: '¡Éxito!',
          text: res.mensaje,
          icon: 'success',
          confirmButtonText: 'Ok'
        });

        this.cargarComunidades();
        this.nuevaComunidad = { tematica: '', nombre: '', descripcion: '', tipo: '', idCreador: 1 }
      } else {
        Swal.fire({
          title: 'Error',
          text: res.mensaje,
          icon: 'error',
          confirmButtonText: 'Cerrar'
        });
      }
    })

      .catch((err: any) => {
        console.error("Error al crear la comunidad", err);
        Swal.fire({
          title: 'Error',
          text: 'Ya existe una comunidad con ese nombre',
          icon: 'error',
          confirmButtonText: 'Cerrar'
        });
      });
  }

}
