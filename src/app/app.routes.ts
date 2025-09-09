import { Routes } from '@angular/router';
import { InicioSesion } from './componentes/inicio-sesion/inicio-sesion';

export const routes: Routes = [
    { path: "", component: InicioSesion, pathMatch: "full" },
];
