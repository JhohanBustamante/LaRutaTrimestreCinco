import { Header } from '../header/header';
import { Component, OnInit } from '@angular/core';
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

  datosNoPermitidos: (string | null | undefined)[]= ["", null, undefined];

  
  comunidades: any[] = []

  nuevaComunidad: any = {
    tematica: '',
    nombre: '',
    descripcion: '',
    tipo: '',
    id_creador: 1,
  };

    comunidadEditar: any = {
    tematica: '',
    nombre: '',
    descripcion: '',
    tipo: '',
    id_creador: 1,
  };

  abrirModal(comunidad: any){
    this.comunidadEditar = {...comunidad};
  }

  constructor(private peticion: Peticion) { }

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
      this.comunidades = res
    }).catch(() => {
      console.log("Error al obtener comunidades")
    })
  }

  crearComunidad() {

  
      const newNombre = this.datosNoPermitidos.findIndex((dato)=> dato=== this.nuevaComunidad.nombre);
      const newDescripcion = this.datosNoPermitidos.findIndex((dato)=> dato=== this.nuevaComunidad.descripcion);
      const newTipo = this.datosNoPermitidos.findIndex((dato)=> dato=== this.nuevaComunidad.tipo);
      const newTematica = this.datosNoPermitidos.findIndex((dato)=> dato=== this.nuevaComunidad.tematica);

  
  if (newNombre !== -1) {
    Swal.fire({
      title: 'Error',
      text: 'Nombre de usuario no valido',
      icon: 'error'
    });
    return;
  }else if(newDescripcion!== -1){
    Swal.fire({
      title: 'Error',
      text: 'Descripción no valida',
      icon: 'warning'
    });
    return;
  }else if(newTipo!== -1){
    Swal.fire({
      title: 'Error',
      text: 'Campo tipo no valido',
      icon: 'warning'
    });
    return;
  }
  else if(newTematica!== -1){
    Swal.fire({
      title: 'Error',
      text: 'Campo tematica no valida',
      icon: 'warning'
    });
    return;
  }
  

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
      } 
    })

      .catch((err: any) => {
        console.error("Error al crear la comunidad", err);
        Swal.fire({
          title: 'Error',
          text: err.error?.mensaje || 'Error al crear la comunidad, terrible',
          icon: 'error',
          confirmButtonText: 'Cerrar'
        });
      });
  }

  eliminarComunidad(idSeleccionado: number){
    let del= {
      host: this.peticion.urlReal,
      path: "/api/eliminar/" + idSeleccionado
     };

    this.peticion.delete( del.host + del.path, {} ).then((res: any)=>{
        Swal.fire({
          title:'Eliminada',
          text:'La comunidad fue eliminada',
          icon: 'success',
          confirmButtonText: 'Correcto'
        })
        this.cargarComunidades();
       }).catch((err:any)=> {
          console.error("error al eliminar la comunidad", err);
          Swal.fire({
            title: 'Error',
            text: 'Error al eliminar la comunidad',
            icon: 'error',
            confirmButtonText: 'Cerrar'
          });
       });
  }

  actualizarComunidad(comunidad: any){
    let act={
      host: this.peticion.urlReal,
      path: 'api/actualizar/'+ comunidad.id,
      payload: comunidad
    }
  }

}
