import javax.swing.JOptionPane;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class Reportes {
    public static void generarReporteVentas(List<Venta> ventas) {
        double totalVentas = 0;
        Map<String, Integer> productosVendidos = new HashMap<>();

        for (Venta venta : ventas) {
            for (Producto producto : venta.getProductos()) {
                totalVentas += producto.getPrecio() * producto.getCantidad();
                productosVendidos.merge(producto.getNombre(), producto.getCantidad(), Integer::sum);
            }
        }

        StringBuilder reporte = new StringBuilder("Reporte de Ventas:\n\n");
        reporte.append("Total de ventas: $").append(String.format("%.2f", totalVentas)).append("\n\n");
        reporte.append("Productos vendidos:\n");
        for (Map.Entry<String, Integer> entry : productosVendidos.entrySet()) {
            reporte.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte de Ventas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void generarReporteCompras(List<Compra> compras) {
        double totalCompras = 0;
        Map<String, Integer> productosComprados = new HashMap<>();

        for (Compra compra : compras) {
            for (Producto producto : compra.getProductos()) {
                totalCompras += producto.getPrecio() * producto.getCantidad();
                productosComprados.merge(producto.getNombre(), producto.getCantidad(), Integer::sum);
            }
        }

        StringBuilder reporte = new StringBuilder("Reporte de Compras:\n\n");
        reporte.append("Total de compras: $").append(String.format("%.2f", totalCompras)).append("\n\n");
        reporte.append("Productos comprados:\n");
        for (Map.Entry<String, Integer> entry : productosComprados.entrySet()) {
            reporte.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte de Compras", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void generarReporteInventario(List<Producto> productos) {
        double valorTotal = 0;
        StringBuilder reporte = new StringBuilder("Reporte de Inventario:\n\n");

        for (Producto producto : productos) {
            double valorProducto = producto.getPrecio() * producto.getCantidad();
            valorTotal += valorProducto;
            reporte.append(producto.getNombre())
                    .append(" - Cantidad: ").append(producto.getCantidad())
                    .append(" - Precio unitario: $").append(String.format("%.2f", producto.getPrecio()))
                    .append(" - Valor total: $").append(String.format("%.2f", valorProducto))
                    .append("\n");
        }

        reporte.append("\nValor total del inventario: $").append(String.format("%.2f", valorTotal));

        JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte de Inventario", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void generarReporteUsuarios(List<Usuario> usuarios) {
        StringBuilder reporte = new StringBuilder("Reporte de Usuarios:\n\n");

        for (Usuario usuario : usuarios) {
            reporte.append("Nombre: ").append(usuario.getNombre()).append("\n");
        }

        reporte.append("\nTotal de usuarios: ").append(usuarios.size());

        JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte de Usuarios", JOptionPane.INFORMATION_MESSAGE);
    }
}