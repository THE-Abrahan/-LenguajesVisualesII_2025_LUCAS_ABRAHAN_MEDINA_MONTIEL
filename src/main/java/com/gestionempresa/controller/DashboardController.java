package com.gestionempresa.controller;

import com.gestionempresa.model.*;
import com.gestionempresa.repository.ClienteRepository;
import com.gestionempresa.repository.CompraRepository;
import com.gestionempresa.repository.CiudadRepository;
import com.gestionempresa.repository.PedidoProveedorRepository;
import com.gestionempresa.repository.ProductoRepository;
import com.gestionempresa.repository.StockRepository;
import com.gestionempresa.repository.VentaRepository;
import com.gestionempresa.service.DashboardService;
import com.gestionempresa.repository.DevolucionAProveedorDetalleRepository;
import com.gestionempresa.repository.DepartamentoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final com.gestionempresa.repository.MarcaRepository marcaRepository;
    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;
    private final com.gestionempresa.repository.PedidoClienteRepository pedidoClienteRepository;
    private final PedidoProveedorRepository pedidoProveedorRepository;
    private final CiudadRepository ciudadRepository;
    private final com.gestionempresa.repository.CompraDetalleRepository compraDetalleRepository;
    private final com.gestionempresa.repository.CobroDetalleRepository cobroDetalleRepository;
    private final com.gestionempresa.repository.PresupuestoRepository presupuestoRepository;
    private final com.gestionempresa.repository.PresupuestoDetalleRepository presupuestoDetalleRepository;
    private final com.gestionempresa.repository.AjusteRepository ajusteRepository;
    private final com.gestionempresa.repository.AjusteDetalleRepository ajusteDetalleRepository;
    private final com.gestionempresa.repository.ProveedorRepository proveedorRepository;
    private final com.gestionempresa.repository.UsuarioRepository usuarioRepository;
    private final com.gestionempresa.repository.EmpleadoRepository empleadoRepository;
    private final com.gestionempresa.repository.CajaRepository cajaRepository;
    private final com.gestionempresa.repository.ArqueoRepository arqueoRepository;
    private final com.gestionempresa.repository.PagoRepository pagoRepository;
    private final StockRepository stockRepository;
    private final com.gestionempresa.repository.OrdenDePagoRepository ordenDePagoRepository;
    private final com.gestionempresa.repository.AjusteTipoRepository ajusteTipoRepository;
    private final com.gestionempresa.repository.CobroTipoRepository cobroTipoRepository;
    private final com.gestionempresa.repository.CobroRepository cobroRepository;
    private final com.gestionempresa.repository.PedidoClienteDetalleRepository pedidoClienteDetalleRepository;
    private final com.gestionempresa.repository.CuentaPorCobrarRepository cuentaPorCobrarRepository;
    private final com.gestionempresa.repository.CuentaPorPagarRepository cuentaPorPagarRepository;
    private final com.gestionempresa.repository.CotizacionRepository cotizacionRepository;
    private final com.gestionempresa.repository.MonedaRepository monedaRepository;
    private final com.gestionempresa.repository.OrdenDeCompraRepository ordenDeCompraRepository;
    private final com.gestionempresa.repository.OrdenDeCompraDetalleRepository ordenDeCompraDetalleRepository;
    private final com.gestionempresa.repository.PedidoProveedorDetalleRepository pedidoProveedorDetalleRepository;
    private final com.gestionempresa.repository.VentaDetalleRepository ventaDetalleRepository;
    private final DepartamentoRepository departamentoRepository;
    private final com.gestionempresa.repository.OrdenDePagoDetalleRepository ordenDePagoDetalleRepository;
    private final DevolucionAProveedorDetalleRepository devolucionAProveedorDetalleRepository;

    private void cargarDatosComunes(Model model) {
        dashboardService.cargarDatosComunes(model);
        model.addAttribute("compraDetalles", compraDetalleRepository.findAll());
        // Cargar el resto de las listas de detalles que faltaban
        model.addAttribute("presupuestoDetalles", presupuestoDetalleRepository.findAll());
        model.addAttribute("ordenPagoDetalles", ordenDePagoDetalleRepository.findAll());
        model.addAttribute("ventaDetalles", ventaDetalleRepository.findAll());
        model.addAttribute("pedidoClienteDetalles", pedidoClienteDetalleRepository.findAll());
        model.addAttribute("pedidoProveedorDetalles", pedidoProveedorDetalleRepository.findAll());
        model.addAttribute("ordenDeCompraDetalles", ordenDeCompraDetalleRepository.findAll());
        model.addAttribute("ajustesDetalle", ajusteDetalleRepository.findAll());
        model.addAttribute("cobroDetalles", cobroDetalleRepository.findAll());
        model.addAttribute("devolucionProveedorDetalles", devolucionAProveedorDetalleRepository.findAll());
    }

    @ModelAttribute
    public void addCommonForms(Model model) {
        if (!model.containsAttribute("clienteForm")) {
            Cliente cliente = new Cliente();
            cliente.setCiudad(new Ciudad());
            model.addAttribute("clienteForm", cliente);
        }
        if (!model.containsAttribute("productoForm")) {
            Producto producto = new Producto();
            producto.setMarca(new com.gestionempresa.model.Marca());
            model.addAttribute("productoForm", producto);
        }
        if (!model.containsAttribute("marcaForm")) {
            model.addAttribute("marcaForm", new com.gestionempresa.model.Marca());
        }
        if (!model.containsAttribute("proveedorForm")) {
            Proveedor proveedor = new Proveedor();
            proveedor.setCiudad(new Ciudad());
            model.addAttribute("proveedorForm", proveedor);
        }
        if (!model.containsAttribute("pedidoForm")) {
            com.gestionempresa.model.PedidoCliente pedido = new com.gestionempresa.model.PedidoCliente();
            pedido.setCliente(new Cliente());
            pedido.setUsuario(new com.gestionempresa.model.Usuario()); // Initialize usuario
            model.addAttribute("pedidoForm", pedido);
        }
        if (!model.containsAttribute("ventaForm")) {
            com.gestionempresa.model.Venta venta = new com.gestionempresa.model.Venta();
            venta.setCliente(new Cliente());
            model.addAttribute("ventaForm", venta);
        }
        if (!model.containsAttribute("compraForm")) {
            com.gestionempresa.model.Compra compra = new com.gestionempresa.model.Compra();
            compra.setProveedor(new Proveedor());
            compra.setUsuario(new com.gestionempresa.model.Usuario());
            compra.setOrdenDeCompra(new com.gestionempresa.model.OrdenDeCompra());
            model.addAttribute("compraForm", compra);
        }
        if (!model.containsAttribute("stockForm")) {
            model.addAttribute("stockForm", new com.gestionempresa.model.Stock());
        }
        if (!model.containsAttribute("cobroForm")) {
            com.gestionempresa.model.Cobro cobro = new com.gestionempresa.model.Cobro();
            cobro.setArqueo(new com.gestionempresa.model.Arqueo());
            model.addAttribute("cobroForm", cobro);
        }
        if (!model.containsAttribute("pagoForm")) {
            model.addAttribute("pagoForm", new com.gestionempresa.model.Pago());
        }
        if (!model.containsAttribute("presupuestoForm")) {
            com.gestionempresa.model.Presupuesto p = new com.gestionempresa.model.Presupuesto();
            p.setCliente(new Cliente());
            p.setUsuario(new com.gestionempresa.model.Usuario());
            model.addAttribute("presupuestoForm", p);
        }
        if (!model.containsAttribute("ajusteForm")) {
            model.addAttribute("ajusteForm", new com.gestionempresa.model.Ajuste());
        }
        if (!model.containsAttribute("monedaForm")) {
            model.addAttribute("monedaForm", new com.gestionempresa.model.Moneda());
        }
        if (!model.containsAttribute("cajaForm")) {
            model.addAttribute("cajaForm", new com.gestionempresa.model.Caja());
        }
        if (!model.containsAttribute("cobroTipoForm")) {
            model.addAttribute("cobroTipoForm", new com.gestionempresa.model.CobroTipo());
        }
        if (!model.containsAttribute("ajusteTipoForm")) {
            model.addAttribute("ajusteTipoForm", new com.gestionempresa.model.AjusteTipo());
        }
        if (!model.containsAttribute("empleadoForm")) {
            com.gestionempresa.model.Empleado emp = new com.gestionempresa.model.Empleado();
            emp.setCiudad(new Ciudad());
            model.addAttribute("empleadoForm", emp);
        }
        if (!model.containsAttribute("usuarioForm")) {
            com.gestionempresa.model.Usuario u = new com.gestionempresa.model.Usuario();
            u.setEmpleado(new com.gestionempresa.model.Empleado());
            model.addAttribute("usuarioForm", u);
        }
        if (!model.containsAttribute("arqueoForm")) {
            com.gestionempresa.model.Arqueo a = new com.gestionempresa.model.Arqueo();
            a.setCaja(new com.gestionempresa.model.Caja());
            a.setUsuario(new com.gestionempresa.model.Usuario());
            model.addAttribute("arqueoForm", a);
        }
        if (!model.containsAttribute("cotizacionForm")) {
            com.gestionempresa.model.Cotizacion c = new com.gestionempresa.model.Cotizacion();
            c.setMoneda(new com.gestionempresa.model.Moneda());
            model.addAttribute("cotizacionForm", c);
        }
        if (!model.containsAttribute("departamentoForm")) {
            model.addAttribute("departamentoForm", new Departamento());
        }
        if (!model.containsAttribute("ciudadForm")) {
            Ciudad ciudad = new Ciudad();
            ciudad.setDepartamento(new Departamento());
            model.addAttribute("ciudadForm", ciudad);
        }
        if (!model.containsAttribute("devolucionProveedorForm")) {
            DevolucionAProveedor dev = new DevolucionAProveedor();
            dev.setProveedor(new Proveedor());
            dev.setUsuario(new com.gestionempresa.model.Usuario());
            model.addAttribute("devolucionProveedorForm", dev);
        }
        if (!model.containsAttribute("ordenDePagoForm")) {
            com.gestionempresa.model.OrdenDePago op = new com.gestionempresa.model.OrdenDePago();
            op.setUsuario(new com.gestionempresa.model.Usuario());
            model.addAttribute("ordenDePagoForm", op);
        }
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        cargarDatosComunes(model);
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/security")
    public String securitySettings(Model model) {
        // Aquí añadirías la lógica para obtener la configuración de seguridad del usuario actual
        return "user-security"; // Asume que existe una plantilla 'user-security.html'
    }

    @PostMapping("/pedidos")
    public String crearPedido(@ModelAttribute("pedidoForm") com.gestionempresa.model.PedidoCliente pedido,
                              RedirectAttributes redirectAttributes) {
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Debes seleccionar un cliente para el pedido.");
            return "redirect:/";
        }
        if (pedido.getUsuario() == null || pedido.getUsuario().getId() == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Debes seleccionar un usuario para el pedido.");
            return "redirect:/";
        }
        clienteRepository.findById(pedido.getCliente().getId()).ifPresent(pedido::setCliente);
        try {
            dashboardService.guardarPedidoCliente(pedido);
            redirectAttributes.addFlashAttribute("mensajeExito", "Pedido creado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al crear el pedido.");
        }
        return "redirect:/";
    }

    @PostMapping("/pedidos/detalle")
    public String agregarPedidoDetalle(@RequestParam Integer pedcliId,
                                       @RequestParam String stockProductProdId,
                                       @RequestParam(required = false) BigDecimal peddetCantidad,
                                       @RequestParam(required = false) BigDecimal peddetPrecio,
                                       RedirectAttributes redirectAttributes) {
        try {
            // validate pedido exists
            java.util.Optional<com.gestionempresa.model.PedidoCliente> pedidoOpt = pedidoClienteRepository.findById(pedcliId);
            if (!pedidoOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("mensajeError", "Pedido no encontrado.");
                return "redirect:/";
            }
            // validate producto exists
            java.util.Optional<com.gestionempresa.model.Producto> prodOpt = productoRepository.findById(stockProductProdId);
            if (!prodOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("mensajeError", "Producto no encontrado.");
                return "redirect:/";
            }
            com.gestionempresa.model.PedidoClienteDetalle detalle = new com.gestionempresa.model.PedidoClienteDetalle();
            detalle.setPedcliId(pedcliId);
            detalle.setStockProductoProdId(stockProductProdId);
            detalle.setPedcliDetCantidad(peddetCantidad == null ? BigDecimal.ZERO : peddetCantidad);
            detalle.setPedcliDetPrecioUnitario(peddetPrecio == null ? BigDecimal.ZERO : peddetPrecio);
            // save
            pedidoClienteDetalleRepository.save(detalle);
            redirectAttributes.addFlashAttribute("mensajeExito", "Línea de pedido agregada.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al agregar línea: " + ex.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/pedidos/detalle/eliminar")
    public String eliminarPedidoDetalle(@RequestParam Integer pedcliId,
                                        @RequestParam String stockProductProdId,
                                        RedirectAttributes ra) {
        try {
            pedidoClienteDetalleRepository.deleteById(new com.gestionempresa.model.PedidoClienteDetalleId(pedcliId, stockProductProdId));
            ra.addFlashAttribute("mensajeExito", "Detalle de pedido eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el detalle de pedido.");
        }
        return "redirect:/#pedidos";
    }

    @PostMapping("/ventas")
    public String crearVenta(@ModelAttribute("ventaForm") com.gestionempresa.model.Venta venta,
                             RedirectAttributes redirectAttributes) {
        if (venta.getCliente() == null || venta.getCliente().getId() == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Debes seleccionar un cliente para la venta.");
            return "redirect:/";
        }
        clienteRepository.findById(venta.getCliente().getId()).ifPresent(venta::setCliente);
        // optionally resolve usuario if provided
        if (venta.getUsuario() != null && venta.getUsuario().getId() != null) {
            usuarioRepository.findById(venta.getUsuario().getId()).ifPresent(venta::setUsuario);
        }
        try {
            dashboardService.guardarVenta(venta);
            redirectAttributes.addFlashAttribute("mensajeExito", "Venta registrada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al registrar la venta.");
        }
        return "redirect:/";
    }

    @PostMapping("/compras")
    public String crearCompra(@ModelAttribute("compraForm") com.gestionempresa.model.Compra compra,
                              RedirectAttributes redirectAttributes) {
        // proveedor must be set
        if (compra.getProveedor() != null && compra.getProveedor().getId() != null) {
            proveedorRepository.findById(compra.getProveedor().getId()).ifPresent(compra::setProveedor);
        }
        if (compra.getUsuario() != null && compra.getUsuario().getId() != null) {
            usuarioRepository.findById(compra.getUsuario().getId()).ifPresent(compra::setUsuario);
        }
        if (compra.getOrdenDeCompra() != null && compra.getOrdenDeCompra().getId() != null) {
            ordenDeCompraRepository.findById(compra.getOrdenDeCompra().getId()).ifPresent(compra::setOrdenDeCompra);
        }
        try {
            dashboardService.guardarCompra(compra);
            redirectAttributes.addFlashAttribute("mensajeExito", "Compra registrada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al registrar la compra.");
        }
        return "redirect:/";
    }

    @PostMapping("/compras/detalle")
    public String agregarCompraDetalle(@RequestParam String compNumerofactura,
                                       @RequestParam String stockProductoProdId,
                                       @RequestParam(required = false) BigDecimal cantidad,
                                       @RequestParam(required = false) BigDecimal precioUnitario,
                                       RedirectAttributes redirectAttributes) {
        try {
            // validar compra existente
            java.util.Optional<com.gestionempresa.model.Compra> compraOpt = compraRepository.findById(compNumerofactura);
            if (!compraOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("mensajeError", "Compra no encontrada.");
                return "redirect:/";
            }
            // validar producto existente
            java.util.Optional<com.gestionempresa.model.Producto> prodOpt = productoRepository.findById(stockProductoProdId);
            if (!prodOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("mensajeError", "Producto no encontrado.");
                return "redirect:/";
            }
            com.gestionempresa.model.CompraDetalle det = new com.gestionempresa.model.CompraDetalle();
            det.setCompNumerofactura(compNumerofactura);
            det.setStockProductoProdId(stockProductoProdId);
            det.setCantidad(cantidad == null ? BigDecimal.ZERO : cantidad);
            det.setPrecioUnitario(precioUnitario == null ? BigDecimal.ZERO : precioUnitario);
            compraDetalleRepository.save(det);
            redirectAttributes.addFlashAttribute("mensajeExito", "LÃ­nea de compra agregada.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al agregar detalle: " + ex.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/compras/detalle/eliminar")
    public String eliminarCompraDetalle(@RequestParam String compNumerofactura,
                                         @RequestParam String stockProductoProdId,
                                         RedirectAttributes ra) {
        try {
            compraDetalleRepository.deleteById(new com.gestionempresa.model.CompraDetalleId(compNumerofactura, stockProductoProdId)); // Ahora CompraDetalleId existe
            ra.addFlashAttribute("mensajeExito", "Detalle de compra eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el detalle de compra.");
        }
        return "redirect:/#compras";
    }


    @PostMapping("/cobros")
    public String crearCobro(@ModelAttribute("cobroForm") com.gestionempresa.model.Cobro cobro,
                             RedirectAttributes redirectAttributes) {
        if (cobro.getArqueo() != null && cobro.getArqueo().getId() != null) {
            arqueoRepository.findById(cobro.getArqueo().getId()).ifPresent(cobro::setArqueo);
        }
        if (cobro.getUsuario() != null && cobro.getUsuario().getId() != null) {
            usuarioRepository.findById(cobro.getUsuario().getId()).ifPresent(cobro::setUsuario);
        }
        try {
            dashboardService.guardarCobro(cobro);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cobro registrado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al registrar el cobro.");
        }
        return "redirect:/";
    }

    @PostMapping("/pagos")
    public String crearPago(@ModelAttribute("pagoForm") com.gestionempresa.model.Pago pago,
                            RedirectAttributes redirectAttributes) {
        try {
            // Resolver relaciones para evitar entidades transientes
            if (pago.getOrdenDePago() != null && pago.getOrdenDePago().getNumero() != null) {
                ordenDePagoRepository.findById(pago.getOrdenDePago().getNumero()).ifPresent(pago::setOrdenDePago);
            }
            if (pago.getUsuario() != null && pago.getUsuario().getId() != null) {
                usuarioRepository.findById(pago.getUsuario().getId()).ifPresent(pago::setUsuario);
            }
            pagoRepository.save(pago);
            redirectAttributes.addFlashAttribute("mensajeExito", "Pago registrado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al registrar el pago.");
        }
        return "redirect:/";
    }

    @PostMapping("/presupuestos")
    public String crearPresupuesto(@ModelAttribute("presupuestoForm") com.gestionempresa.model.Presupuesto presupuesto,
                                   RedirectAttributes redirectAttributes) {
        try {
            if (presupuesto.getCliente() == null || presupuesto.getCliente().getId() == null) {
                redirectAttributes.addFlashAttribute("mensajeError", "Debe seleccionar un cliente.");
                return "redirect:/#presupuestos";
            }
            if (presupuesto.getUsuario() == null || presupuesto.getUsuario().getId() == null) {
                redirectAttributes.addFlashAttribute("mensajeError", "Debe seleccionar un usuario.");
                return "redirect:/#presupuestos";
            }
            clienteRepository.findById(presupuesto.getCliente().getId()).ifPresent(presupuesto::setCliente);
            usuarioRepository.findById(presupuesto.getUsuario().getId()).ifPresent(presupuesto::setUsuario);

            dashboardService.guardarPresupuesto(presupuesto);
            redirectAttributes.addFlashAttribute("mensajeExito", "Presupuesto guardado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al guardar presupuesto.");
        }
        return "redirect:/";
    }

    @PostMapping("/ajustes")
    public String crearAjuste(@ModelAttribute("ajusteForm") com.gestionempresa.model.Ajuste ajuste,
                              RedirectAttributes redirectAttributes) {
        try {
            // Resolver la entidad Usuario para evitar errores de estado transitorio
            if (ajuste.getUsuario() != null && ajuste.getUsuario().getId() != null) {
                usuarioRepository.findById(ajuste.getUsuario().getId()).ifPresent(ajuste::setUsuario);
            }

            dashboardService.guardarAjuste(ajuste);
            redirectAttributes.addFlashAttribute("mensajeExito", "Ajuste guardado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al guardar ajuste.");
        }
        return "redirect:/";
    }

    @PostMapping("/stocks")
    public String guardarStock(@ModelAttribute("stockForm") com.gestionempresa.model.Stock stock,
                               RedirectAttributes redirectAttributes) {
        try {
            // Ensure product reference exists
            if (stock.getProductId() != null) {
                productoRepository.findById(stock.getProductId()).ifPresent(p -> stock.setProducto(p));
            }
            // Default cantidadActual to 0 if null
            if (stock.getCantidadActual() == null) stock.setCantidadActual(BigDecimal.ZERO);
            dashboardService.guardarStock(stock);
            redirectAttributes.addFlashAttribute("mensajeExito", "Stock actualizado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al actualizar stock: " + ex.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/proveedores")
    public String crearProveedor(@ModelAttribute("proveedorForm") Proveedor proveedor,
                                 RedirectAttributes redirectAttributes) {
        if (proveedor.getNombre() == null || proveedor.getNombre().isBlank()
            || proveedor.getRuc() == null || proveedor.getRuc().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "El nombre y el RUC del proveedor son obligatorios.");
            return "redirect:/";
        }
        // resolve ciudad to a managed entity if provided
        if (proveedor.getCiudad() != null && proveedor.getCiudad().getId() != null) {
            ciudadRepository.findById(proveedor.getCiudad().getId()).ifPresent(proveedor::setCiudad);
        } else {
            proveedor.setCiudad(null);
        }
        // Campos de documento/razon social no existen en el DDL; se eliminan validaciones previas
        dashboardService.guardarProveedor(proveedor);
        redirectAttributes.addFlashAttribute("mensajeExito", "Proveedor registrado correctamente.");
        return "redirect:/";
    }

    @PostMapping("/marcas")
    public String crearMarca(@ModelAttribute("marcaForm") com.gestionempresa.model.Marca marca,
                             RedirectAttributes redirectAttributes) {
        if (marca.getDescripcion() == null || marca.getDescripcion().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "La descripciÃ³n de la marca es obligatoria.");
            return "redirect:/";
        }
        dashboardService.guardarMarca(marca);
        redirectAttributes.addFlashAttribute("mensajeExito", "Marca registrada correctamente.");
        return "redirect:/#marcas";
    }

    @PostMapping("/monedas")
    public String crearMoneda(@ModelAttribute("monedaForm") com.gestionempresa.model.Moneda moneda,
                              RedirectAttributes redirectAttributes) {
        if (moneda.getDescripcion() == null || moneda.getDescripcion().isBlank()
            || moneda.getSigla() == null || moneda.getSigla().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "Descripcion y sigla de la moneda son obligatorias.");
            return "redirect:/";
        }
        try {
            monedaRepository.save(moneda);
            redirectAttributes.addFlashAttribute("mensajeExito", "Moneda creada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la moneda.");
        }
        return "redirect:/";
    }

    @PostMapping("/departamentos")
    public String crearDepartamento(@ModelAttribute("departamentoForm") Departamento departamento,
                                    RedirectAttributes redirectAttributes) {
        if (departamento.getNombre() == null || departamento.getNombre().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "El nombre del departamento es obligatorio.");
            return "redirect:/#departamentos";
        }
        try {
            departamentoRepository.save(departamento);
            redirectAttributes.addFlashAttribute("mensajeExito", "Departamento creado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear el departamento.");
        }
        return "redirect:/#departamentos";
    }

    @PostMapping("/ciudades")
    public String crearCiudad(@ModelAttribute("ciudadForm") Ciudad ciudad,
                              RedirectAttributes redirectAttributes) {
        if (ciudad.getNombre() == null || ciudad.getNombre().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "El nombre de la ciudad es obligatorio.");
            return "redirect:/#ciudades";
        }
        if (ciudad.getDepartamento() == null || ciudad.getDepartamento().getId() == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Debes seleccionar un departamento.");
            return "redirect:/#ciudades";
        }
        // Resolve department to avoid transient entity issues
        departamentoRepository.findById(ciudad.getDepartamento().getId()).ifPresent(ciudad::setDepartamento);

        try {
            ciudadRepository.save(ciudad);
            redirectAttributes.addFlashAttribute("mensajeExito", "Ciudad creada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la ciudad.");
        }
        return "redirect:/#ciudades";
    }


    // ---- Eliminaciones (borrado simple por ID) ----

    private <T, ID> String eliminarEntidad(ID id, JpaRepository<T, ID> repository, String nombreEntidad, String ancla, RedirectAttributes ra) {
        try {
            repository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", nombreEntidad + " eliminado/a correctamente.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar. Es posible que tenga registros asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error inesperado al eliminar.");
        }
        return "redirect:/" + ancla;
    }

    @PostMapping("/marcas/eliminar") public String eliminarMarca(@RequestParam Integer id, RedirectAttributes ra) {
        return eliminarEntidad(id, marcaRepository, "Marca", "#marcas", ra);
    }

    @PostMapping("/monedas/eliminar")
    public String eliminarMoneda(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            monedaRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Moneda eliminada.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la moneda. Es posible que esté en uso en cotizaciones.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la moneda por un error inesperado.");
        }
        return "redirect:/#monedas";
    }

    @PostMapping("/cajas/eliminar")
    public String eliminarCaja(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            cajaRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Caja eliminada.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la caja. Es posible que tenga arqueos asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la caja por un error inesperado.");
        }
        return "redirect:/#cajas";
    }

    @PostMapping("/departamentos/eliminar")
    public String eliminarDepartamento(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            departamentoRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Departamento eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar. Asegúrate de que no haya ciudades asociadas.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error inesperado al eliminar el departamento.");
        }
        return "redirect:/#departamentos";
    }

    @PostMapping("/ciudades/eliminar")
    public String eliminarCiudad(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            ciudadRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Ciudad eliminada.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar. Asegúrate de que no esté en uso por clientes, proveedores, etc.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error inesperado al eliminar la ciudad.");
        }
        return "redirect:/#ciudades";
    }

    @PostMapping("/proveedores/eliminar")
    public String eliminarProveedor(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            proveedorRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Proveedor eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el proveedor. Es posible que tenga compras o pedidos asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el proveedor por un error inesperado.");
        }
        return "redirect:/#proveedores";
    }

    @PostMapping("/stocks/eliminar")
    public String eliminarStock(@RequestParam String productId, RedirectAttributes ra) {
        try {
            stockRepository.deleteById(productId);
            ra.addFlashAttribute("mensajeExito", "Stock eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el stock.");
        }
        return "redirect:/#stock";
    }

    @PostMapping("/ventas/eliminar")
    public String eliminarVenta(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            ventaRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Venta eliminada.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la venta. Es posible que tenga cuentas por cobrar o detalles asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la venta por un error inesperado.");
        }
        return "redirect:/#ventas";
    }

    @PostMapping("/pedidos/eliminar")
    public String eliminarPedido(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            pedidoClienteRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Pedido eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el pedido. Es posible que tenga detalles o ventas asociadas.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el pedido por un error inesperado.");
        }
        return "redirect:/#pedidos";
    }

    @PostMapping("/compras/eliminar")
    public String eliminarCompra(@RequestParam String numeroFactura, RedirectAttributes ra) {
        try {
            compraRepository.deleteById(numeroFactura);
            ra.addFlashAttribute("mensajeExito", "Compra eliminada.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la compra. Es posible que tenga detalles o cuentas por pagar asociadas.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la compra por un error inesperado.");
        }
        return "redirect:/#compras";
    }

    @PostMapping("/empleados/eliminar")
    public String eliminarEmpleado(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            empleadoRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Empleado eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el empleado. Es posible que tenga un usuario asociado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el empleado por un error inesperado.");
        }
        return "redirect:/#empleados";
    }

    @PostMapping("/usuarios/eliminar")
    public String eliminarUsuario(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            usuarioRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Usuario eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el usuario. Es posible que tenga arqueos, ventas u otras operaciones asociadas.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el usuario por un error inesperado.");
        }
        return "redirect:/#usuarios";
    }

    @PostMapping("/arqueos/eliminar")
    public String eliminarArqueo(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            arqueoRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Arqueo eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el arqueo. Es posible que tenga cobros asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el arqueo por un error inesperado.");
        }
        return "redirect:/#arqueos";
    }

    @PostMapping("/cotizaciones/eliminar")
    public String eliminarCotizacion(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            cotizacionRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Cotización eliminada.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la cotización.");
        }
        return "redirect:/#cotizaciones";
    }

    @PostMapping("/pagos/eliminar")
    public String eliminarPago(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            pagoRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Pago eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el pago.");
        }
        return "redirect:/#pagos";
    }

    @PostMapping("/cobros/eliminar")
    public String eliminarCobro(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            cobroRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Cobro eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el cobro. Es posible que tenga detalles asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el cobro por un error inesperado.");
        }
        return "redirect:/#cobros";
    }

    @PostMapping("/presupuestos/eliminar")
    public String eliminarPresupuesto(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            presupuestoRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Presupuesto eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el presupuesto. Es posible que tenga detalles asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el presupuesto por un error inesperado.");
        }
        return "redirect:/#presupuestos";
    }

    @PostMapping("/ajustes/eliminar")
    public String eliminarAjuste(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            ajusteRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Ajuste eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el ajuste. Es posible que tenga detalles asociados.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el ajuste por un error inesperado.");
        }
        return "redirect:/#ajustes";
    }

    @PostMapping("/cobro-tipos/eliminar")
    public String eliminarCobroTipo(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            cobroTipoRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Tipo de cobro eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el tipo de cobro.");
        }
        return "redirect:/#cobrotipos";
    }

    @PostMapping("/ajuste-tipos/eliminar")
    public String eliminarAjusteTipo(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            ajusteTipoRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Tipo de ajuste eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el tipo de ajuste.");
        }
        return "redirect:/#ajustetipos";
    }

    @PostMapping("/cajas")
    public String crearCaja(@ModelAttribute("cajaForm") com.gestionempresa.model.Caja caja,
                            RedirectAttributes redirectAttributes) {
        if (caja.getDescripcion() == null || caja.getDescripcion().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "La descripcion de la caja es obligatoria.");
            return "redirect:/";
        }
        try {
            cajaRepository.save(caja);
            redirectAttributes.addFlashAttribute("mensajeExito", "Caja creada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la caja.");
        }
        return "redirect:/";
    }

    @PostMapping("/cobro-tipos")
    public String crearCobroTipo(@ModelAttribute("cobroTipoForm") com.gestionempresa.model.CobroTipo cobroTipo,
                                 RedirectAttributes redirectAttributes) {
        if (cobroTipo.getNombre() == null || cobroTipo.getNombre().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "El nombre del tipo de cobro es obligatorio.");
            return "redirect:/";
        }
        try {
            cobroTipoRepository.save(cobroTipo);
            redirectAttributes.addFlashAttribute("mensajeExito", "Tipo de cobro creado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear el tipo de cobro.");
        }
        return "redirect:/";
    }

    @PostMapping("/ajuste-tipos")
    public String crearAjusteTipo(@ModelAttribute("ajusteTipoForm") com.gestionempresa.model.AjusteTipo ajusteTipo,
                                  RedirectAttributes redirectAttributes) {
        if (ajusteTipo.getTipo() == null || ajusteTipo.getTipo().isBlank()) {
            redirectAttributes.addFlashAttribute("mensajeError", "El tipo de ajuste es obligatorio.");
            return "redirect:/";
        }
        try {
            ajusteTipoRepository.save(ajusteTipo);
            redirectAttributes.addFlashAttribute("mensajeExito", "Tipo de ajuste creado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear el tipo de ajuste.");
        }
        return "redirect:/";
    }

    @PostMapping("/empleados")
    public String crearEmpleado(@ModelAttribute("empleadoForm") com.gestionempresa.model.Empleado empleado, RedirectAttributes redirectAttributes) {
        try {
            // Resolve Ciudad to avoid transient instance issues
            if (empleado.getCiudad() != null && empleado.getCiudad().getId() != null) {
                ciudadRepository.findById(empleado.getCiudad().getId()).ifPresent(empleado::setCiudad);
            } else {
                empleado.setCiudad(null);
            }

            empleadoRepository.save(empleado);
            redirectAttributes.addFlashAttribute("mensajeExito", "Empleado creado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear el empleado.");
        }
        return "redirect:/#empleados"; // Esta línea ya era correcta, pero la confirmo.
    }

    @PostMapping("/usuarios")
    public String crearUsuario(@ModelAttribute("usuarioForm") com.gestionempresa.model.Usuario usuario,
                               @RequestParam(value = "contrasenaRaw", required = false) String contrasenaRaw,
                               RedirectAttributes redirectAttributes) {
        try {
            if (usuario.getId() != null) {
                com.gestionempresa.model.Usuario existingUser = usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + usuario.getId()));

                // Si se proporciona una nueva contraseña, se usa. Si no, se mantiene la existente.
                if (contrasenaRaw != null && !contrasenaRaw.isBlank()) {
                    usuario.setContrasena(contrasenaRaw);
                } else {
                    usuario.setContrasena(existingUser.getContrasena()); // Mantener la contraseña existente si no se proporciona una nueva
                }
            } else {
                // Para un nuevo usuario, la contraseña es obligatoria.
                if (contrasenaRaw == null || contrasenaRaw.isBlank()) {
                    throw new IllegalArgumentException("La contraseña es obligatoria para nuevos usuarios.");
                }
                usuario.setContrasena(contrasenaRaw);
            }

            if (usuario.getEmpleado() != null && usuario.getEmpleado().getId() != null) {
                empleadoRepository.findById(usuario.getEmpleado().getId()).ifPresent(usuario::setEmpleado);
            } else {
                usuario.setEmpleado(null);
            }

            usuarioRepository.save(usuario);
            redirectAttributes.addFlashAttribute("mensajeExito", "Usuario guardado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo guardar el usuario: " + ex.getMessage());
        }
        return "redirect:/#usuarios";
    }

    @PostMapping("/arqueos")
    public String crearArqueo(@ModelAttribute("arqueoForm") com.gestionempresa.model.Arqueo arqueo,
                              RedirectAttributes redirectAttributes) {
        try {
            if (arqueo.getCaja() != null && arqueo.getCaja().getId() != null) {
                cajaRepository.findById(arqueo.getCaja().getId()).ifPresent(arqueo::setCaja);
            } else {
                arqueo.setCaja(null);
            }
            if (arqueo.getUsuario() != null && arqueo.getUsuario().getId() != null) {
                usuarioRepository.findById(arqueo.getUsuario().getId()).ifPresent(arqueo::setUsuario);
            } else {
                arqueo.setUsuario(null);
            }
            arqueoRepository.save(arqueo);
            redirectAttributes.addFlashAttribute("mensajeExito", "Arqueo creado correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear el arqueo.");
        }
        return "redirect:/#arqueos";
    }

    @PostMapping("/cotizaciones")
    public String crearCotizacion(@ModelAttribute("cotizacionForm") com.gestionempresa.model.Cotizacion cotizacion,
                                  RedirectAttributes redirectAttributes) {
        try {
            if (cotizacion.getMoneda() != null && cotizacion.getMoneda().getId() != null) {
                monedaRepository.findById(cotizacion.getMoneda().getId()).ifPresent(cotizacion::setMoneda);
            } else {
                cotizacion.setMoneda(null);
            }
            cotizacionRepository.save(cotizacion);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cotizacion creada correctamente.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la cotizacion.");
        }
        return "redirect:/";
    }

    @PostMapping("/cuentas/cobrar/eliminar")
    public String eliminarCuentaCobrar(@RequestParam Integer venId,
                                       @RequestParam Integer cuotaNumero,
                                       RedirectAttributes ra) {
        try {
            cuentaPorCobrarRepository.deleteById(new com.gestionempresa.model.CuentaPorCobrar.Id(venId, cuotaNumero));
            ra.addFlashAttribute("mensajeExito", "Cuenta por cobrar eliminada.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la cuenta por cobrar.");
        }
        return "redirect:/#cuentas";
    }

    @PostMapping("/cuentas/pagar/eliminar")
    public String eliminarCuentaPagar(@RequestParam Integer cuotaNumero,
                                      @RequestParam String compNumerofactura,
                                      RedirectAttributes ra) {
        try { // Se corrige la llamada para usar el método personalizado del repositorio.
            cuentaPorPagarRepository.deleteById(new com.gestionempresa.model.CuentaPorPagar.Id(cuotaNumero, compNumerofactura));
            ra.addFlashAttribute("mensajeExito", "Cuenta por pagar eliminada.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar la cuenta por pagar: " + e.getMessage());
        }
        return "redirect:/#cuentas";
    }

    @PostMapping("/presupuestos/detalle")
    public String agregarPresupuestoDetalle(@RequestParam Integer presId,
                                            @RequestParam String prodId,
                                            @RequestParam(required=false) java.math.BigDecimal cantidad,
                                            @RequestParam(required=false) java.math.BigDecimal precioUnitario,
                                            RedirectAttributes ra) {
        try {
            com.gestionempresa.model.PresupuestoDetalle det = new com.gestionempresa.model.PresupuestoDetalle();
            det.setPresId(presId);
            det.setProdId(prodId);
            det.setPresdetCantidad(cantidad);
            det.setPresdetPrecioUnitario(precioUnitario);
            presupuestoDetalleRepository.save(det);
            ra.addFlashAttribute("mensajeExito", "Detalle de presupuesto agregado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo agregar el detalle del presupuesto.");
        }
        return "redirect:/#presupuestos";
    }

    @PostMapping("/ordenes-pago/detalle")
    public String agregarOrdenPagoDetalle(@RequestParam Integer ordpagNumero,
                                          @RequestParam Integer cuotaNumero,
                                          @RequestParam String compNumerofactura,
                                          @RequestParam(required=false) java.math.BigDecimal monto,
                                          RedirectAttributes ra) {
        try {
            com.gestionempresa.model.OrdenDePagoDetalle det = new com.gestionempresa.model.OrdenDePagoDetalle();
            det.setOrdenDePagoOrdpagNumero(ordpagNumero);
            det.setCuenpagNumerocuota(cuotaNumero);
            det.setCompNumerofactura(compNumerofactura);
            det.setMonto(monto);
            ordenDePagoDetalleRepository.save(det);
            ra.addFlashAttribute("mensajeExito", "Detalle de orden de pago agregado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo agregar el detalle de orden de pago.");
        }
        return "redirect:/#pagos";
    }

    @PostMapping("/ordenes-pago/detalle/eliminar")
    public String eliminarOrdenPagoDetalle(@RequestParam Integer ordpagNumero,
                                           @RequestParam Integer cuotaNumero,
                                           @RequestParam String compNumerofactura,
                                           RedirectAttributes ra) {
        try {
            // Usamos deleteById con el objeto Id de la clave compuesta.
            ordenDePagoDetalleRepository.deleteById(new com.gestionempresa.model.OrdenDePagoDetalleId(ordpagNumero, cuotaNumero, compNumerofactura));
            ra.addFlashAttribute("mensajeExito", "Detalle de orden de pago eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el detalle de orden de pago.");
        }
        return "redirect:/#pagos";
    }

    @PostMapping("/cobros/detalle")
    public String agregarCobroDetalle(@RequestParam Integer cobId,
                                      @RequestParam Integer venId,
                                      @RequestParam Integer cuotaNumero,
                                      RedirectAttributes ra) {
        try {
            com.gestionempresa.model.CobroDetalle det = new com.gestionempresa.model.CobroDetalle();
            det.setCobId(cobId); det.setVenId(venId); det.setCuotaNumero(cuotaNumero);
            cobroDetalleRepository.save(det);
            ra.addFlashAttribute("mensajeExito", "Detalle de cobro agregado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo agregar el detalle de cobro.");
        }
        return "redirect:/#cobros";
    }

    @PostMapping("/cobros/detalle/eliminar")
    public String eliminarCobroDetalle(@RequestParam Integer cobId,
                                       @RequestParam Integer venId,
                                       @RequestParam Integer cuotaNumero,
                                       RedirectAttributes ra) {
        try {
            cobroDetalleRepository.deleteById(new com.gestionempresa.model.CobroDetalleId(cobId, venId, cuotaNumero));
            ra.addFlashAttribute("mensajeExito", "Detalle de cobro eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el detalle de cobro.");
        }
        return "redirect:/#cobros";
    }
    @PostMapping("/cuentas/cobrar")
    public String crearCuentaCobrar(@RequestParam Integer venId,
                                    @RequestParam Integer cuotaNumero,
                                    @RequestParam(required = false) java.math.BigDecimal monto,
                                    @RequestParam(required = false) String estado,
                                    @RequestParam(required = false) String fechaVencimiento,
                                    RedirectAttributes redirectAttributes) {
        try {
            com.gestionempresa.model.CuentaPorCobrar c = new com.gestionempresa.model.CuentaPorCobrar();
            com.gestionempresa.model.CuentaPorCobrar.Id id = new com.gestionempresa.model.CuentaPorCobrar.Id();
            id.setVenId(venId);
            id.setCuotaNumero(cuotaNumero);
            c.setId(id);
            c.setMonto(monto);
            c.setEstado(estado);
            if (fechaVencimiento != null && !fechaVencimiento.isBlank()) {
                c.setFechaVencimiento(java.time.LocalDate.parse(fechaVencimiento));
            }
            cuentaPorCobrarRepository.save(c);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cuenta por cobrar creada.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la cuenta por cobrar.");
        }
        return "redirect:/#cuentas";
    }

    @PostMapping("/cuentas/pagar")
    public String crearCuentaPagar(@RequestParam Integer cuotaNumero,
                                   @RequestParam String compNumerofactura,
                                   @RequestParam(required = false) java.math.BigDecimal monto,
                                   @RequestParam(required = false) String estado,
                                   RedirectAttributes redirectAttributes) {
        try {
            com.gestionempresa.model.CuentaPorPagar c = new com.gestionempresa.model.CuentaPorPagar();
            com.gestionempresa.model.CuentaPorPagar.Id id = new com.gestionempresa.model.CuentaPorPagar.Id(); id.setCuenpagNumerocuota(cuotaNumero);
            id.setCompraNumerofactura(compNumerofactura);
            c.setId(id);
            c.setMonto(monto);
            c.setEstado(estado);
            cuentaPorPagarRepository.save(c);
            redirectAttributes.addFlashAttribute("mensajeExito", "Cuenta por pagar creada.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la cuenta por pagar.");
        }
        return "redirect:/#cuentas";
    }

    @PostMapping("/ordenes-pago")
    public String crearOrdenPago(@ModelAttribute("ordenDePagoForm") com.gestionempresa.model.OrdenDePago ordenDePago,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (ordenDePago.getUsuario() != null && ordenDePago.getUsuario().getId() != null) {
                usuarioRepository.findById(ordenDePago.getUsuario().getId()).ifPresent(ordenDePago::setUsuario);
            } else {
                ordenDePago.setUsuario(null); // Asegurarse de que sea nulo si no se selecciona
            }
            ordenDePagoRepository.save(ordenDePago);
            redirectAttributes.addFlashAttribute("mensajeExito", "Orden de pago creada.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la orden de pago.");
        }
        return "redirect:/#pagos";
    }

    @PostMapping("/ajustes/detalle")
    public String agregarAjusteDetalle(@RequestParam Integer ajusId,
                                       @RequestParam Integer ajustipId,
                                       @RequestParam String prodId,
                                       @RequestParam(required = false) java.math.BigDecimal cantidad,
                                       RedirectAttributes redirectAttributes) {
        try {
            com.gestionempresa.model.AjusteDetalle det = new com.gestionempresa.model.AjusteDetalle();
            det.setAjusId(ajusId);
            det.setAjustipId(ajustipId);
            det.setProdId(prodId);
            det.setCantidad(cantidad == null ? java.math.BigDecimal.ZERO : cantidad);
            ajusteDetalleRepository.save(det);
            redirectAttributes.addFlashAttribute("mensajeExito", "Detalle de ajuste agregado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo agregar el detalle de ajuste.");
        }
        return "redirect:/#ajustes";
    }

    @PostMapping("/ajustes/detalle/eliminar")
    public String eliminarAjusteDetalle(@RequestParam Integer ajusId,
                                       @RequestParam Integer ajustipId,
                                       @RequestParam String prodId,
                                       RedirectAttributes ra) {
        try {
            ajusteDetalleRepository.deleteById(new com.gestionempresa.model.AjusteDetalleId(ajusId, prodId, ajustipId));
            ra.addFlashAttribute("mensajeExito", "Detalle de ajuste eliminado correctamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el detalle de ajuste.");
        }
        return "redirect:/#ajustes";
    }


    @PostMapping("/pedidos-proveedor")
    public String crearPedidoProveedor(@ModelAttribute com.gestionempresa.model.PedidoProveedor pedidoProveedor,
                                       RedirectAttributes redirectAttributes) {
        try {
            if (pedidoProveedor.getProveedor() != null && pedidoProveedor.getProveedor().getId() != null) {
                proveedorRepository.findById(pedidoProveedor.getProveedor().getId()).ifPresent(pedidoProveedor::setProveedor);
            }
            if (pedidoProveedor.getUsuario() != null && pedidoProveedor.getUsuario().getId() != null) {
                usuarioRepository.findById(pedidoProveedor.getUsuario().getId()).ifPresent(pedidoProveedor::setUsuario);
            }
            pedidoProveedorRepository.save(pedidoProveedor);
            redirectAttributes.addFlashAttribute("mensajeExito", "Pedido a proveedor creado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear el pedido a proveedor.");
        }
        return "redirect:/#compras";
    }

    @PostMapping("/pedidos-proveedor/detalle")
    public String crearPedidoProveedorDetalle(@RequestParam Integer pedprovId,
                                              @RequestParam String productoId,
                                              @RequestParam java.math.BigDecimal cantidad,
                                              @RequestParam java.math.BigDecimal precioUnitario,
                                              RedirectAttributes redirectAttributes) {
        try {
            com.gestionempresa.model.PedidoProveedorDetalle det = new com.gestionempresa.model.PedidoProveedorDetalle();
            pedidoProveedorRepository.findById(pedprovId).ifPresent(det::setPedidoProveedor);
            productoRepository.findById(productoId).ifPresent(det::setProducto);
            det.setCantidad(cantidad);
            det.setPrecioUnitario(precioUnitario);
            pedidoProveedorDetalleRepository.save(det);
            redirectAttributes.addFlashAttribute("mensajeExito", "Detalle de pedido a proveedor agregado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo agregar el detalle de pedido a proveedor.");
        }
        return "redirect:/#compras";
    }

    @PostMapping("/ordenes-compra")
    public String crearOrdenCompra(@RequestParam(required = false) Integer pedprovId,
                                   @RequestParam(required = false) Integer usuarioId,
                                   @RequestParam(required = false) String estado,
                                   @RequestParam(required = false) String fecha,
                                   @RequestParam(required = false) String hora,
                                   RedirectAttributes redirectAttributes) {
        try {
            com.gestionempresa.model.OrdenDeCompra oc = new com.gestionempresa.model.OrdenDeCompra();
            if (pedprovId != null) { pedidoProveedorRepository.findById(pedprovId).ifPresent(oc::setPedidoProveedor); }
            if (usuarioId != null) { usuarioRepository.findById(usuarioId).ifPresent(oc::setUsuario); }
            if (fecha != null && !fecha.isBlank()) { oc.setFecha(java.time.LocalDate.parse(fecha)); }
            if (hora != null && !hora.isBlank()) { oc.setHora(java.time.LocalTime.parse(hora)); }
            oc.setEstado(estado);
            ordenDeCompraRepository.save(oc);
            redirectAttributes.addFlashAttribute("mensajeExito", "Orden de compra creada.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo crear la orden de compra.");
        }
        return "redirect:/#compras";
    }

    @PostMapping("/ordenes-compra/detalle")
    public String crearOrdenCompraDetalle(@RequestParam Integer ordcompId,
                                          @RequestParam String productoId,
                                          @RequestParam java.math.BigDecimal cantidad,
                                          @RequestParam java.math.BigDecimal precioUnitario,
                                          RedirectAttributes redirectAttributes) {
        try {
            com.gestionempresa.model.OrdenDeCompraDetalle det = new com.gestionempresa.model.OrdenDeCompraDetalle();
            ordenDeCompraRepository.findById(ordcompId).ifPresent(det::setOrdenDeCompra);
            productoRepository.findById(productoId).ifPresent(det::setProducto);
            det.setCantidad(cantidad);
            det.setPrecioUnitario(precioUnitario);
            ordenDeCompraDetalleRepository.save(det);
            redirectAttributes.addFlashAttribute("mensajeExito", "Detalle de orden de compra agregado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo agregar el detalle de orden de compra.");
        }
        return "redirect:/#compras";
    }

    @PostMapping("/ventas/detalle")
    public String crearVentaDetalle(@RequestParam Integer venId,
                                    @RequestParam String prodId,
                                    @RequestParam java.math.BigDecimal cantidad,
                                    @RequestParam java.math.BigDecimal precioUnitario,
                                    RedirectAttributes redirectAttributes) {
        try {
            com.gestionempresa.model.VentaDetalle det = new com.gestionempresa.model.VentaDetalle();
            det.setVentaVenId(venId);
            det.setStockProductoProdId(prodId);
            det.setDetventaCantidad(cantidad);
            det.setDetventaPrecioUnitario(precioUnitario);
            ventaDetalleRepository.save(det);
            redirectAttributes.addFlashAttribute("mensajeExito", "Detalle de venta agregado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "No se pudo agregar el detalle de venta.");
        }
        return "redirect:/#ventas";
    }

    @PostMapping("/ventas/detalle/eliminar")
    public String eliminarVentaDetalle(@RequestParam Integer venId,
                                      @RequestParam String prodId,
                                      RedirectAttributes ra) {
        try {
            ventaDetalleRepository.deleteById(new com.gestionempresa.model.VentaDetalleId(prodId, venId));
            ra.addFlashAttribute("mensajeExito", "Detalle de venta eliminado.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar el detalle de venta.");
        }
        return "redirect:/#ventas";
    }

    @PostMapping("/pedidos-proveedor/eliminar")
    public String eliminarPedidoProveedor(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            pedidoProveedorRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Pedido a proveedor eliminado.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar. Es posible que tenga detalles u órdenes de compra asociadas.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error inesperado al eliminar el pedido a proveedor.");
        }
        return "redirect:/#compras";
    }

    @PostMapping("/pedidos-proveedor/detalle/eliminar")
    public String eliminarPedidoProveedorDetalle(@RequestParam Integer pedprovId,
                                                 @RequestParam String productoId,
                                                 RedirectAttributes redirectAttributes) {
        try {
            // Buscar detalles que coincidan con el pedido y producto y eliminarlos.
            java.util.List<com.gestionempresa.model.PedidoProveedorDetalle> encontrados =
                    pedidoProveedorDetalleRepository.findByPedidoProveedor_IdAndProducto_Id(pedprovId, productoId);

            if (encontrados == null || encontrados.isEmpty()) {
                redirectAttributes.addFlashAttribute("mensajeError", "Detalle no encontrado para el pedido/prod especificado.");
                return "redirect:/#compras";
            }

            for (com.gestionempresa.model.PedidoProveedorDetalle det : encontrados) {
                pedidoProveedorDetalleRepository.delete(det);
            }
            redirectAttributes.addFlashAttribute("mensajeExito", "Detalle de pedido a proveedor eliminado.");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al eliminar el detalle: " + ex.getMessage());
        }
        return "redirect:/#compras";
    }

    @PostMapping("/ordenes-compra/eliminar")
    public String eliminarOrdenDeCompra(@RequestParam("id") Integer id, RedirectAttributes ra) {
        try {
            ordenDeCompraRepository.deleteById(id);
            ra.addFlashAttribute("mensajeExito", "Orden de compra eliminada.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("mensajeError", "No se pudo eliminar. Es posible que tenga detalles o compras asociadas.");
        } catch (Exception e) {
            ra.addFlashAttribute("mensajeError", "Error inesperado al eliminar la orden de compra.");
        }
        return "redirect:/#compras";
    }

}
