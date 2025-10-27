package com.gestionempresa.controller;

import com.gestionempresa.model.DevolucionAProveedor;
import com.gestionempresa.repository.DevolucionAProveedorRepository;
import com.gestionempresa.repository.ProveedorRepository;
import com.gestionempresa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class DevolucionAProveedorController {

    private final DevolucionAProveedorRepository devolucionAProveedorRepository;
    private final ProveedorRepository proveedorRepository;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/devoluciones-proveedor")
    public String guardarDevolucion(@ModelAttribute("devolucionProveedorForm") DevolucionAProveedor devolucion,
                                    RedirectAttributes redirectAttributes) {
        try {
            if (devolucion.getProveedor() != null && devolucion.getProveedor().getId() != null) {
                proveedorRepository.findById(devolucion.getProveedor().getId()).ifPresent(devolucion::setProveedor);
            } else {
                devolucion.setProveedor(null);
            }

            if (devolucion.getUsuario() != null && devolucion.getUsuario().getId() != null) {
                usuarioRepository.findById(devolucion.getUsuario().getId()).ifPresent(devolucion::setUsuario);
            } else {
                devolucion.setUsuario(null);
            }

            devolucionAProveedorRepository.save(devolucion);
            redirectAttributes.addFlashAttribute("mensajeExito", "Devolución guardada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al guardar la devolución: " + ex.getMessage());
        }
        return "redirect:/#devoluciones-proveedor";
    }

    @PostMapping("/devoluciones-proveedor/eliminar")
    public String eliminarDevolucion(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            devolucionAProveedorRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Devolución eliminada.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar. Es posible que tenga detalles asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error inesperado al eliminar la devolución.");
        }
        return "redirect:/#devoluciones-proveedor";
    }
}