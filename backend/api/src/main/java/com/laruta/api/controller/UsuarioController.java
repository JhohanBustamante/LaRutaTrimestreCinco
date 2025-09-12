package com.laruta.api.controller;

import com.laruta.api.model.Usuario;
import com.laruta.api.servicio.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    private final UsuarioServicio usuarioService;
    public UsuarioController(UsuarioServicio usuarioService) {
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
