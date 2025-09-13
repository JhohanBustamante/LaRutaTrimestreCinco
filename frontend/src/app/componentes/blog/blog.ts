import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Peticion } from '../../servicios/peticion';

@Component({
  selector: 'app-blog',
  imports: [CommonModule, FormsModule],
  templateUrl: './blog.html',
  styleUrl: './blog.css'
})
export class Blog implements OnInit {

  constructor(private cdr: ChangeDetectorRef, private peticion: Peticion){}

  ngOnInit(): void { 
    this.mostrarUsaurios() 
  }

  mostrarUsaurios(){
    let post={
      host: this.peticion.urlReal,
      path: "/usuarios/info",
      payload: {}
    }
      this.peticion.get(post.host+post.path).then((res:any)=>{
        console.log(res)
      })
  }


  // cargarTodas(lugarId: string){
  //   let post = {
  //     host: this.peticion.urlReal,
  //     path: "/lotes/cargarPorLugar/" + lugarId,
  //     payload:{}
  //   }
  //   this.peticion.get(post.host + post.path).then((res: any) => {
  //     this.datos = res.datos.datos
  //     console.log(this.datos[0])
  //   })
  // }
}
