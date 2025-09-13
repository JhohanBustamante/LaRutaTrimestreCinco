package com.EjemploModel.controller;

import com.EjemploModel.model.Usuario;
import com.EjemploModel.service.UsuarioService;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Controller
public class UsuarioController {
  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping("/usuarios/info")
  public List<Usuario> listarUsuarios() {
    return usuarioService.listarTodos();
  }

  @GetMapping("/usuarios/info/{id}")
  public Usuario buscarPorId(@PathVariable Long id) {
    return usuarioService.buscarPorId(id);
  }

  @PostMapping("/guardar")
  public ResponseEntity<?> guardarUsuario(@RequestBody Usuario newUsuario) {
    if (usuarioService.buscarPorApodo(newUsuario.getApodo()) != null) {
      return ResponseEntity
          .badRequest()
          .body(Map.of("estado", false, "mensaje", "Ya existe un usuario con ese apodo"));
    } else if (usuarioService.buscarPorCorreo(newUsuario.getCorreo()) != null) {
      return ResponseEntity
          .badRequest()
          .body(Map.of("estado", false, "mensaje", "Ya existe un usuario con ese correo"));
    } else {
      Usuario guardado = usuarioService.guardar(newUsuario);
      return ResponseEntity
          .ok(Map.of("estado", true, "usuario", guardado));
    }
  }

  @PostMapping("/iniciarSesion")
  public ResponseEntity<?> iniciarSesion(@RequestBody Usuario usuario) {
    Usuario iniciado = usuarioService.validacionInicio(usuario.getApodo(), usuario.getCorreo(),
        usuario.getContrasena());
    if (iniciado == null) {
      return ResponseEntity.ok(Map.of("estado", false));
    }
    return ResponseEntity.ok(Map.of("estado", true, "resultado", iniciado));
  }

  @PutMapping("/actualizar/{id}")
public ResponseEntity<?> actualizarUsuario(
    @PathVariable Long id,
    @RequestBody Usuario datosNuevos) {

  Usuario existenteCorreo = usuarioService.buscarPorCorreo(datosNuevos.getCorreo());
  if (existenteCorreo != null && !existenteCorreo.getId().equals(id)) {
    return ResponseEntity
        .badRequest()
        .body(Map.of("estado", false, "mensaje", "Ya existe un usuario con ese correo"));
  }

  Usuario existenteApodo = usuarioService.buscarPorApodo(datosNuevos.getApodo());
  if (existenteApodo != null && !existenteApodo.getId().equals(id)) {
    return ResponseEntity
        .badRequest()
        .body(Map.of("estado", false, "mensaje", "Ya existe un usuario con ese apodo"));
  }

  Usuario actualizado = usuarioService.actualizarUsuario(id, datosNuevos);
  if (actualizado == null) {
    return ResponseEntity
        .badRequest()
        .body(Map.of("estado", false, "mensaje", "Usuario no encontrado"));
  } else {
    return ResponseEntity.ok(
        Map.of("estado", true, "mensaje", "Usuario actualizado correctamente", "usuario", actualizado));
  }
}

}