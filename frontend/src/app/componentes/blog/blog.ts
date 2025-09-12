import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-blog',
  imports: [CommonModule, FormsModule],
  templateUrl: './blog.html',
  styleUrl: './blog.css'
})
export class Blog implements OnInit {

  constructor(private cdr: ChangeDetectorRef){}
  prueba: String = ""
  descripcion: String = ""


  alerta = () => {
    this.descripcion==""?alert("Llena el campo"):alert(this.descripcion)
  }
  ngOnInit(): void {  

  }
}
