import { Routes } from '@angular/router';
import { InicioSesion } from './componentes/inicio-sesion/inicio-sesion';
import { BlogPrincipal } from './componentes/blog-principal/blog-principal';
import { Perfil } from './componentes/perfil/perfil';
import { ServicioComponent } from './componentes/servicio/servicio.component';
import { ComunidadComponent } from './componentes/comunidad/comunidad.component';
import { FavoritosComponent } from './componentes/favoritos/favoritos.component';
import { Registro } from './componentes/registro/registro';

export const routes: Routes = [
    { path: "", component: InicioSesion, pathMatch: "full" },
    { path: "blog", component: BlogPrincipal },
    { path: "perfil", component: Perfil },
    { path: "servicios", component: ServicioComponent },
    { path: "comunidades", component: ComunidadComponent },
    { path: "favoritos", component: FavoritosComponent },
    { path: "registro", component: Registro}
];
