import { Header } from '../header/header';
import { Component, OnInit } from '@angular/core';
import { Peticion } from '../../servicios/peticion';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-comunidad',
  imports: [Header, CommonModule],
  templateUrl: './comunidad.component.html',
  styleUrl: './comunidad.component.css'
})
export class ComunidadComponent implements OnInit {

  comunidades: any[] =[]

  constructor(private comunidadService: Peticion){ }

  ngOnInit(): void {
    this.cargarComunidades();
  }

  cargarComunidades(): void{
    this.comunidadService.getComunidades().subscribe(
      (data:any[])=> {
        console.log('Datos recibidos de la API:', data);
        this.comunidades=data;
      },
      error =>{
        console.error('error al cargar comunidades', error);
      }
    )
  }
      
  }

