package com.EjemploModel.controller;

import com.EjemploModel.model.Usuario;
import com.EjemploModel.service.UsuarioService;

import java.util.List;

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
  public String guardarUsuario(@ModelAttribute Usuario usuario) {

    usuarioService.guardar(usuario);
    return "redirect:/usuarios";
  }
}