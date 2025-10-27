package com.gestionempresa.controller;

import com.gestionempresa.model.Ciudad;
import com.gestionempresa.model.Departamento;
import com.gestionempresa.repository.CiudadRepository;
import com.gestionempresa.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AltaController {

    private final DepartamentoRepository departamentoRepository;
    private final CiudadRepository ciudadRepository;

    @GetMapping("/alta/departamento")
    public String altaDepartamento(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new Departamento());
        }
        return "alta/departamento";
    }

    @PostMapping("/alta/departamento")
    public String crearDepartamento(@ModelAttribute("form") Departamento departamento,
                                    BindingResult br,
                                    RedirectAttributes ra) {
        if (departamento.getNombre() == null || departamento.getNombre().isBlank()) {
            ra.addFlashAttribute("mensajeError", "El nombre del departamento es obligatorio.");
            ra.addFlashAttribute("form", departamento);
            return "redirect:/alta/departamento";
        }
        departamentoRepository.save(departamento);
        ra.addFlashAttribute("mensajeExito", "Departamento creado");
        return "redirect:/alta/departamento";
    }

    @GetMapping("/alta/ciudad")
    public String altaCiudad(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new Ciudad());
        }
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "alta/ciudad";
    }

    @PostMapping("/alta/ciudad")
    public String crearCiudad(@ModelAttribute("form") Ciudad ciudad,
                              BindingResult br,
                              RedirectAttributes ra) {
        if (ciudad.getNombre() == null || ciudad.getNombre().isBlank()) {
            ra.addFlashAttribute("mensajeError", "El nombre de la ciudad es obligatorio.");
            ra.addFlashAttribute("form", ciudad);
            return "redirect:/alta/ciudad";
        }
        ciudadRepository.save(ciudad);
        ra.addFlashAttribute("mensajeExito", "Ciudad creada");
        return "redirect:/alta/ciudad";
    }
}