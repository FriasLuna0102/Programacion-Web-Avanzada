package org.example.practica2.controller;

import org.example.practica2.model.Estudiante;
import org.example.practica2.services.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/listar")
    public String listEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.findAll());
        return "estudiantes/listEstudiantes";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/crearEstudiantes";
    }

    @PostMapping("/crear")
    public String crearEstudiante(Estudiante estudiante) {
        estudianteService.save(estudiante);
        return "redirect:/estudiante/listar";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.findById(id);
        model.addAttribute("estudiante", estudiante);
        return "estudiantes/actualizarEstudiante";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEstudiante(Estudiante estudiante) {
        estudianteService.update(estudiante);
        return "redirect:/estudiante/listar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.delete(id);
        return "redirect:/estudiante/listar";
    }

}
