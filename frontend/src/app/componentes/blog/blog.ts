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

  trackById(index: number, item: any) {
  return item.id;
}

  datos: any = []

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
      this.datos = res;
      console.log(this.datos);
      this.cdr.detectChanges(); 
      })
  }


}
