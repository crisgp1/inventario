import javax.swing.JOptionPane;
import java.util.List;

class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}

class GestorProductos {
    private List<Producto> productos;

    public GestorProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto:"));
        productos.add(new Producto(nombre, precio, cantidad));
        JOptionPane.showMessageDialog(null, "Producto agregado con éxito.");
    }

    public void listarProductos() {
        StringBuilder lista = new StringBuilder("Lista de Productos:\n");
        for (Producto producto : productos) {
            lista.append(producto.getNombre()).append(" - Precio: ").append(producto.getPrecio())
                    .append(" - Cantidad: ").append(producto.getCantidad()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    public void actualizarStock() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a actualizar:");
        int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad:"));
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                producto.setCantidad(nuevaCantidad);
                JOptionPane.showMessageDialog(null, "Stock actualizado.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
    }
}