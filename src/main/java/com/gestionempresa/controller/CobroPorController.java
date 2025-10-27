package com.gestionempresa.controller;

import com.gestionempresa.model.CobroPor;
import com.gestionempresa.repository.CobroPorRepository;
import com.gestionempresa.repository.CobroTipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class CobroPorController {

    private final CobroPorRepository cobroPorRepository;
    private final CobroTipoRepository cobroTipoRepository;

    @PostMapping("/cobros/por")
    public String agregarCobroPor(@RequestParam Integer cobId,
                                  @RequestParam Integer venId,
                                  @RequestParam Integer cuotaNumero,
                                  @RequestParam Integer cobtipId,
                                  @RequestParam BigDecimal monto,
                                  RedirectAttributes ra) {
        try {
            CobroPor.Id id = new CobroPor.Id(cobId, venId, cuotaNumero, cobtipId);
            CobroPor cobroPor = new CobroPor();
            cobroPor.setId(id);
            cobroPor.setMonto(monto);

            // Resolve related entities to avoid transient issues, although not strictly needed if only saving IDs.
            cobroTipoRepository.findById(cobtipId).ifPresent(cobroPor::setCobroTipo);

            cobroPorRepository.save(cobroPor);
            ra.addFlashAttribute("mensajeExito", "Forma de cobro agregada.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo agregar la forma de cobro: " + e.getMessage());
        }
        return "redirect:/#cobro-por";
    }

    @PostMapping("/cobros/por/eliminar")
    public String eliminarCobroPor(@RequestParam Integer cobId,
                                   @RequestParam Integer venId,
                                   @RequestParam Integer cuotaNumero,
                                   @RequestParam Integer cobtipId,
                                   RedirectAttributes ra) {
        try {
            CobroPor.Id id = new CobroPor.Id(cobId, venId, cuotaNumero, cobtipId);
            cobroPorRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Forma de cobro eliminada.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la forma de cobro: " + e.getMessage());
        }
        return "redirect:/#cobro-por";
    }
}