package com.gestionempresa.service;

import com.gestionempresa.model.Ajuste;
import com.gestionempresa.repository.ClienteRepository;
import com.gestionempresa.model.Cliente;
import com.gestionempresa.model.Cobro;
import com.gestionempresa.model.Compra;
import com.gestionempresa.repository.CompraRepository;
import com.gestionempresa.model.Marca;
import com.gestionempresa.model.PedidoCliente;
import com.gestionempresa.model.Producto;
import com.gestionempresa.model.Proveedor;
import com.gestionempresa.model.Venta;
import com.gestionempresa.repository.AjusteRepository;
import com.gestionempresa.repository.AjusteTipoRepository;
import com.gestionempresa.repository.ArqueoRepository;
import com.gestionempresa.repository.CajaRepository;
import com.gestionempresa.repository.CiudadRepository;
import com.gestionempresa.repository.CobroPorRepository;
import com.gestionempresa.repository.CobroRepository;
import com.gestionempresa.repository.CobroTipoRepository;
import com.gestionempresa.repository.CotizacionRepository;
import com.gestionempresa.repository.CuentaPorCobrarRepository;
import com.gestionempresa.repository.CuentaPorPagarRepository;
import com.gestionempresa.repository.DepartamentoRepository;
import com.gestionempresa.repository.DevolucionAProveedorRepository;
import com.gestionempresa.repository.DevolucionAProveedorDetalleRepository;
import com.gestionempresa.repository.EmpleadoRepository;
import com.gestionempresa.repository.MonedaRepository;
import com.gestionempresa.repository.OrdenDeCompraRepository;
import com.gestionempresa.repository.OrdenDePagoRepository;
import com.gestionempresa.repository.PagoRepository;
import com.gestionempresa.repository.PedidoClienteRepository;
import com.gestionempresa.repository.PedidoProveedorRepository;
import com.gestionempresa.repository.PresupuestoRepository;
import com.gestionempresa.repository.ProductoRepository;
import com.gestionempresa.repository.ProveedorRepository;
import com.gestionempresa.repository.StockRepository;
import com.gestionempresa.repository.UsuarioRepository;
import com.gestionempresa.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;
    private final com.gestionempresa.repository.MarcaRepository marcaRepository;
    private final StockRepository stockRepository;
    private final VentaRepository ventaRepository;
    private final CompraRepository compraRepository;
    private final PedidoClienteRepository pedidoClienteRepository;
    private final PedidoProveedorRepository pedidoProveedorRepository;
    private final CuentaPorCobrarRepository cuentaPorCobrarRepository;
    private final CuentaPorPagarRepository cuentaPorPagarRepository;
    private final PagoRepository pagoRepository;
    private final CobroRepository cobroRepository;
    private final com.gestionempresa.repository.CompraDetalleRepository compraDetalleRepository; // Assuming it's used elsewhere
    private final PresupuestoRepository presupuestoRepository;
    private final AjusteRepository ajusteRepository;
    private final CiudadRepository ciudadRepository;
    private final DepartamentoRepository departamentoRepository;
    private final MonedaRepository monedaRepository;
    private final CajaRepository cajaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArqueoRepository arqueoRepository;
    private final CobroTipoRepository cobroTipoRepository;
    private final AjusteTipoRepository ajusteTipoRepository;
    private final CotizacionRepository cotizacionRepository;
    private final DevolucionAProveedorRepository devolucionAProveedorRepository;
    private final OrdenDePagoRepository ordenDePagoRepository;
    private final OrdenDeCompraRepository ordenDeCompraRepository;
    private final CobroPorRepository cobroPorRepository;
    private final DevolucionAProveedorDetalleRepository devolucionAProveedorDetalleRepository;

    public Cliente guardarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()
            || cliente.getApellido() == null || cliente.getApellido().isBlank()
            || cliente.getCi() == null || cliente.getCi().isBlank()) {
            throw new IllegalArgumentException("Nombre, apellido y número de CI son obligatorios.");
        }
        if (cliente.getCiudad() == null || cliente.getCiudad().getId() == null) {
            throw new IllegalArgumentException("Debes seleccionar una ciudad.");
        }
        // Asegurarse de que la ciudad es una entidad gestionada por JPA
        ciudadRepository.findById(cliente.getCiudad().getId()).ifPresent(cliente::setCiudad);
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Producto guardarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()
            || producto.getId() == null || producto.getId().isBlank()
            || producto.getPrecioCompra() == null) {
            throw new IllegalArgumentException("Completa el código, nombre y precio de compra del producto.");
        }
        if (producto.getMarca() != null && producto.getMarca().getId() != null) {
            marcaRepository.findById(producto.getMarca().getId()).ifPresent(producto::setMarca);
        } else {
            // It's better to throw an exception if marca is mandatory
            throw new IllegalArgumentException("La marca es obligatoria para el producto.");
        }
        return productoRepository.save(producto);
    }

    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public com.gestionempresa.model.Stock guardarStock(com.gestionempresa.model.Stock stock) {
        return stockRepository.save(stock);
    }

    public PedidoCliente guardarPedidoCliente(PedidoCliente pedido) {
        return pedidoClienteRepository.save(pedido);
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Cobro guardarCobro(Cobro cobro) {
        return cobroRepository.save(cobro);
    }

    public Compra guardarCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public com.gestionempresa.model.CompraDetalle guardarCompraDetalle(com.gestionempresa.model.CompraDetalle detalle) { // Assuming it's used elsewhere
        return compraDetalleRepository.save(detalle);
    }

    public com.gestionempresa.model.Presupuesto guardarPresupuesto(com.gestionempresa.model.Presupuesto p) {
        return presupuestoRepository.save(p);
    }

    public Ajuste guardarAjuste(Ajuste a) {
        return ajusteRepository.save(a);
    }

    public Map<String, Long> resumenConteos() {
        Map<String, Long> resumen = new LinkedHashMap<>();
        resumen.put("Clientes", clienteRepository.count());
        resumen.put("Productos", productoRepository.count());
        resumen.put("Proveedores", proveedorRepository.count());
        resumen.put("Stock", stockRepository.count());
        resumen.put("Ventas", ventaRepository.count());
        resumen.put("Compras", compraRepository.count());
        resumen.put("Pedidos de clientes", pedidoClienteRepository.count());
        resumen.put("Pedidos a proveedores", pedidoProveedorRepository.count());
        resumen.put("Cuentas por cobrar", cuentaPorCobrarRepository.count());
        resumen.put("Cuentas por pagar", cuentaPorPagarRepository.count());
        resumen.put("Pagos", pagoRepository.count());
        return resumen;
    }

    public void cargarDatosComunes(Model model) {
        // Add lists of entities to the model
        model.addAttribute("clientes", clienteRepository.findAll(Sort.by("nombre")));
        model.addAttribute("productos", productoRepository.findAll(Sort.by("nombre")));
        model.addAttribute("proveedores", proveedorRepository.findAll(Sort.by("nombre")));
        model.addAttribute("marcas", marcaRepository.findAll(Sort.by("descripcion")));
        model.addAttribute("stocks", stockRepository.findAll());
        model.addAttribute("ventas", ventaRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("compras", compraRepository.findAll(Sort.by("fecha").descending()));
        model.addAttribute("pedidosClientes", pedidoClienteRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("pedidosProveedores", pedidoProveedorRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("cuentasCobrar", cuentaPorCobrarRepository.findAll());
        model.addAttribute("cuentasPagar", cuentaPorPagarRepository.findAll());
        model.addAttribute("pagos", pagoRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("cobros", cobroRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("presupuestos", presupuestoRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("ajustes", ajusteRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("ciudades", ciudadRepository.findAll(Sort.by("nombre")));
        model.addAttribute("departamentos", departamentoRepository.findAll(Sort.by("nombre")));
        model.addAttribute("monedas", monedaRepository.findAll(Sort.by("descripcion")));
        model.addAttribute("cajas", cajaRepository.findAll(Sort.by("descripcion")));
        model.addAttribute("empleados", empleadoRepository.findAll(Sort.by("apellido")));
        model.addAttribute("usuarios", usuarioRepository.findAll(Sort.by("nombre")));
        model.addAttribute("arqueos", arqueoRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("cobroTipos", cobroTipoRepository.findAll(Sort.by("nombre")));
        model.addAttribute("ajusteTipos", ajusteTipoRepository.findAll(Sort.by("tipo")));
        model.addAttribute("cotizaciones", cotizacionRepository.findAll(Sort.by("id").descending()));
    // Obtener devoluciones sin orden explícito para evitar problemas de mapeo de propiedades
    model.addAttribute("devoluciones", devolucionAProveedorRepository.findAll());
        model.addAttribute("ordenesPago", ordenDePagoRepository.findAll(Sort.by("numero").descending()));
        model.addAttribute("ordenesCompra", ordenDeCompraRepository.findAll(Sort.by("id").descending()));
        model.addAttribute("cobrosPor", cobroPorRepository.findAll());
        model.addAttribute("devolucionProveedorDetalles", devolucionAProveedorDetalleRepository.findAll());

        // Add summaries and other data
        model.addAttribute("resumenConteos", resumenConteos());
    }

    public void eliminarProducto(String id) {
        productoRepository.deleteById(id);
    }
}