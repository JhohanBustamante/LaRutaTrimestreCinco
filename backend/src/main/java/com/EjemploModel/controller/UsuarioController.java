package com.EjemploModel.controller;

import com.EjemploModel.model.Usuario;
import com.EjemploModel.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class UsuarioController {
  private final UsuarioService usuarioService;
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }
  @GetMapping("/")
  public String index() {
    return "index";
  }
  @GetMapping("/usuarios")
  public String listarUsuarios(Model model) {
    model.addAttribute("usuarios", usuarioService.listarTodos());
    return "usuarios";
  }
  @GetMapping("/registro")
  public String mostrarFormulario(Model model) {
    model.addAttribute("usuario", new Usuario());
    return "registro";
  }
  @PostMapping("/guardar")
  public String guardarUsuario(@ModelAttribute Usuario usuario) {

    usuarioService.guardar(usuario);
    return "redirect:/usuarios";
  }
}