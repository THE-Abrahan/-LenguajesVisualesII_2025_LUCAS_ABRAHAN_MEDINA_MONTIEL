package com.gestionempresa.controller;

import com.gestionempresa.model.Producto;
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
public class ProductoController {

    private final DashboardService dashboardService;

    @PostMapping("/productos")
    public String crearOActualizarProducto(@ModelAttribute("productoForm") Producto producto, RedirectAttributes redirectAttributes) {
        try {
            dashboardService.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("mensajeExito", "Producto guardado correctamente.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("mensajeError", ex.getMessage());
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo guardar el producto.");
        }
        return "redirect:/#productos";
    }

    @PostMapping("/productos/eliminar")
    public String eliminarProducto(@RequestParam String id, RedirectAttributes ra) {
        try {
            dashboardService.eliminarProducto(id);
            ra.addFlashAttribute("mensajeExito", "Producto eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el producto. Es posible que esté en uso en ventas, compras o stock.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el producto por un error inesperado.");
        }
        return "redirect:/#productos";
    }
}