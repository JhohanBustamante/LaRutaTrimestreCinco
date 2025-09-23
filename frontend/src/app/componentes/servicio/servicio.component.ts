import { CommonModule } from '@angular/common';
import { Header } from '../header/header';
import { Peticion } from '../../servicios/peticion';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Footer } from '../footer/footer';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-servicio',
  imports: [CommonModule, Header, Footer],
  templateUrl: './servicio.component.html',
  styleUrl: './servicio.component.css'
})
export class ServicioComponent implements OnInit {

    servicios: any[] = []
    idComunidad: number =0


constructor(private peticion: Peticion, private cdr: ChangeDetectorRef, private route: ActivatedRoute) { }


ngOnInit(): void {
  const id = this.route.snapshot.paramMap.get('id');
  if (id) {
    this.idComunidad = +id;
    this.cargarServicios();
  }
}


  cargarServicios() {
    let get = {
      host: this.peticion.urlReal,
      path: "/api/servicio/comunidad/" + this.idComunidad,
      payload: {}
    }
    this.peticion.get(get.host + get.path).then((res: any) => {
      this.servicios = res
      console.log("Servicios cargados:", this.servicios);
      this.cdr.detectChanges()
    }).catch((err) => {
      console.error("Error al obtener servicios", err)
    })
  }
}
