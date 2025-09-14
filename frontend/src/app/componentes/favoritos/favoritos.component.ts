import { Component } from '@angular/core';
import { Header } from '../header/header';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Peticion } from '../../servicios/peticion';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-favoritos',
  imports: [Header, CommonModule, FormsModule],
  templateUrl: './favoritos.component.html',
  styleUrl: './favoritos.component.css'
})
export class FavoritosComponent {
  
  comunidades: any[] = []
  nombreBuscado: string= '';
  resultadoBusqueda: any =null;

  constructor(private peticion: Peticion){}

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

  buscarComunidad(){
    
    if(!this.nombreBuscado.trim()){
      Swal.fire({
        title: 'Error',
        text: 'Debe escribir un nombre para buscar',
        icon: 'warning',
        confirmButtonText: 'Ok'
      });
      return;
    }
    this.peticion.get(`${this.peticion.urlReal}/api/comunidad/nombre/${this.nombreBuscado}`)
    .then((res: any)=> {
      this.resultadoBusqueda = res;
      Swal.fire({
        title: 'Encontrado',
        text: `Se encontro la comunidad: ${res.nombre}`,
        icon: 'success',
        confirmButtonText: 'Ok'
      })
    }).catch((err: any) => {
      console.log("Error al buscar la comunidad:", err);
        this.resultadoBusqueda = null;
        Swal.fire({
          title: 'No encontrado',
          text: 'No existe ninguna comunidad con ese nombre',
          icon: 'error',
          confirmButtonText: 'Cerrar'
        });
      });
  }
}
