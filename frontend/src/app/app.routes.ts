import { Routes } from '@angular/router';
import { InicioSesion } from './componentes/inicio-sesion/inicio-sesion';
import { Blog } from './componentes/blog/blog';

export const routes: Routes = [
    { path: "", component: InicioSesion, pathMatch: "full" },
    { path:"blog", component: Blog, pathMatch: "full"},
];
