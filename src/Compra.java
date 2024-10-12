import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

class Compra {
    private Usuario usuario;
    private List<Producto> productos;
    private Date fecha;

    public Compra(Usuario usuario, List<Producto> productos) {
        this.usuario = usuario;
        this.productos = productos;
        this.fecha = new Date();
    }

    // Getters
    public Usuario getUsuario() { return usuario; }
    public List<Producto> getProductos() { return productos; }
    public Date getFecha() { return fecha; }
}

class GestorCompras {
    private List<Compra> compras;
    private List<Usuario> usuarios;
    private List<Producto> productos;

    public GestorCompras(List<Compra> compras, List<Usuario> usuarios, List<Producto> productos) {
        this.compras = compras;
        this.usuarios = usuarios;
        this.productos = productos;
    }

    public void realizarCompra() {
        Usuario usuario = seleccionarUsuario();
        if (usuario == null) return;

        List<Producto> productosComprados = new ArrayList<>();
        while (true) {
            Producto producto = seleccionarProducto();
            if (producto == null) break;

            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a comprar:"));
            producto.setCantidad(producto.getCantidad() + cantidad);
            productosComprados.add(new Producto(producto.getNombre(), producto.getPrecio(), cantidad));
        }

        if (!productosComprados.isEmpty()) {
            compras.add(new Compra(usuario, productosComprados));
            JOptionPane.showMessageDialog(null, "Compra realizada con éxito.");
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

    public void listarCompras() {
        StringBuilder lista = new StringBuilder("Lista de Compras:\n");
        for (Compra compra : compras) {
            lista.append("Usuario: ").append(compra.getUsuario().getNombre())
                    .append(" - Fecha: ").append(compra.getFecha()).append("\n");
            for (Producto producto : compra.getProductos()) {
                lista.append("  ").append(producto.getNombre())
                        .append(" - Cantidad: ").append(producto.getCantidad()).append("\n");
            }
            lista.append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
}