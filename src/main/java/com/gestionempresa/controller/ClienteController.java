package com.gestionempresa.controller;

import com.gestionempresa.model.Cliente;
import com.gestionempresa.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final DashboardService dashboardService;

    @PostMapping("/clientes")
    public String crearOActualizarCliente(@ModelAttribute("clienteForm") Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            dashboardService.guardarCliente(cliente);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cliente guardado correctamente.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo guardar el cliente (verifique que el CI no esté duplicado).");
        }
        return "redirect:/#clientes";
    }

    @PostMapping("/clientes/eliminar")
    public String eliminarCliente(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            dashboardService.eliminarCliente(id);
            ra.addFlashAttribute("mensajeExito", "Cliente eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el cliente. Es posible que tenga registros asociados (ventas, pedidos, etc.).");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el cliente por un error inesperado.");
        }
        return "redirect:/#clientes";
    }
}