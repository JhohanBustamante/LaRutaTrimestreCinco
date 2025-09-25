import { Header } from '../header/header';
import { Peticion } from '../../servicios/peticion';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';






@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [Header, FormsModule, RouterModule, CommonModule ],
  templateUrl: './perfil.html',
  styleUrl: './perfil.css'
})
export class Perfil {

  comunidades: any[] = []
  usuario: any = {}

  ngOnInit(): void {
    this.buscarUsuario();
  }

  comunidadEditar: any = {
    tematica: '',
    nombre: '',
    descripcion: '',
    tipo: '',
    id_creador: this.usuario.id,
  };

  constructor(private peticion: Peticion, private cdr: ChangeDetectorRef, private route: ActivatedRoute) { }
  abrirModal(comunidad: any) {
    this.comunidadEditar = { ...comunidad };
  }

  buscarUsuario() {
    let apodo = localStorage.getItem('apodo') || undefined;
    let get = {
      host: this.peticion.urlReal,
      path: "/usuario/apodo/" + apodo,
      payload: {
      }
    }
    this.peticion.get(get.host + get.path).then((res: any) => {
      this.usuario = res.usuario;
      this.cargarComunidades()
      this.cdr.detectChanges()
    }).catch(() => {
      console.log("Usuario logueado:", this.usuario.usuario);
      console.log("Error al encontrar usuario")
    })
  }

  cargarComunidades() {
    let get = {
      host: this.peticion.urlReal,
      path: "/comunidad/creador/" + this.usuario.id,
      payload: {
      }
    }
    this.peticion.get(get.host + get.path).then((res: any) => {
      this.comunidades = res
      this.cdr.detectChanges()
    }).catch(() => {
      console.log("Error al obtener comunidades")
    })
  }

  eliminarComunidad(idSeleccionado: number) {
    let del = {
      host: this.peticion.urlReal,
      path: "/comunidad/eliminar/" + idSeleccionado
    };

    this.peticion.delete(del.host + del.path, {}).then((res: any) => {
      Swal.fire({
        title: 'Eliminada',
        text: 'La comunidad fue eliminada',
        icon: 'success',
        confirmButtonText: 'Correcto'
      })
      this.cargarComunidades();
      this.cdr.detectChanges()

    }).catch((err: any) => {
      console.error("error al eliminar la comunidad", err);
      Swal.fire({
        title: 'Error',
        text: err.error,
        icon: 'error',
        confirmButtonText: 'Cerrar'
      });
    });
  }
}


