import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

class Venta {
    private Usuario usuario;
    private List<Producto> productos;
    private Date fecha;

    public Venta(Usuario usuario, List<Producto> productos) {
        this.usuario = usuario;
        this.productos = productos;
        this.fecha = new Date();
    }

    // Getters
    public Usuario getUsuario() { return usuario; }
    public List<Producto> getProductos() { return productos; }
    public Date getFecha() { return fecha; }
}

class GestorVentas {
    private List<Venta> ventas;
    private List<Usuario> usuarios;
    private List<Producto> productos;

    public GestorVentas(List<Venta> ventas, List<Usuario> usuarios, List<Producto> productos) {
        this.ventas = ventas;
        this.usuarios = usuarios;
        this.productos = productos;
    }

    public void realizarVenta() {
        Usuario usuario = seleccionarUsuario();
        if (usuario == null) return;

        List<Producto> productosVendidos = new ArrayList<>();
        while (true) {
            Producto producto = seleccionarProducto();
            if (producto == null) break;

            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a vender:"));
            if (cantidad > producto.getCantidad()) {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock.");
                continue;
            }

            producto.setCantidad(producto.getCantidad() - cantidad);
            productosVendidos.add(new Producto(producto.getNombre(), producto.getPrecio(), cantidad));
        }

        if (!productosVendidos.isEmpty()) {
            ventas.add(new Venta(usuario, productosVendidos));
            JOptionPane.showMessageDialog(null, "Venta realizada con éxito.");
        }
    }

    private Usuario seleccionarUsuario() {
        String[] nombresUsuarios = usuarios.stream().map(Usuario::getNombre).toArray(String[]::new);
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un usuario:",
                "Usuarios", JOptionPane.PLAIN_MESSAGE, null, nombresUsuarios, nombresUsuarios[0]);
        return usuarios.stream().filter(u -> u.getNombre().equals(seleccion)).findFirst().orElse(null);
    }

    private Producto seleccionarProducto() {
        String[] nombresProductos = productos.stream().map(Producto::getNombre).toArray(String[]::new);
        String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un producto (Cancelar para finalizar):",
                "Productos", JOptionPane.PLAIN_MESSAGE, null, nombresProductos, nombresProductos[0]);
        return productos.stream().filter(p -> p.getNombre().equals(seleccion)).findFirst().orElse(null);
    }

    public void listarVentas() {
        StringBuilder lista = new StringBuilder("Lista de Ventas:\n");
        for (Venta venta : ventas) {
            lista.append("Usuario: ").append(venta.getUsuario().getNombre())
                    .append(" - Fecha: ").append(venta.getFecha()).append("\n");
            for (Producto producto : venta.getProductos()) {
                lista.append("  ").append(producto.getNombre())
                        .append(" - Cantidad: ").append(producto.getCantidad()).append("\n");
            }
            lista.append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
}