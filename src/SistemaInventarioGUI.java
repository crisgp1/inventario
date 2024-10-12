import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SistemaInventarioGUI extends JFrame {
    private JPanel cardPanel;
    private CardLayout cl;
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private List<Venta> ventas;
    private List<Compra> compras;

    public SistemaInventarioGUI() {
        usuarios = new ArrayList<>();
        productos = new ArrayList<>();
        ventas = new ArrayList<>();
        compras = new ArrayList<>();

        setTitle("Sistema de Inventario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel de menú lateral
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(52, 73, 94));
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));

        addButton(menuPanel, "Usuarios", e -> cl.show(cardPanel, "Usuarios"));
        addButton(menuPanel, "Productos", e -> cl.show(cardPanel, "Productos"));
        addButton(menuPanel, "Ventas", e -> cl.show(cardPanel, "Ventas"));
        addButton(menuPanel, "Compras", e -> cl.show(cardPanel, "Compras"));
        addButton(menuPanel, "Reportes", e -> cl.show(cardPanel, "Reportes"));

        // Panel de contenido con CardLayout
        cardPanel = new JPanel();
        cl = new CardLayout();
        cardPanel.setLayout(cl);

        cardPanel.add(createUsuariosPanel(), "Usuarios");
        cardPanel.add(createProductosPanel(), "Productos");
        cardPanel.add(createVentasPanel(), "Ventas");
        cardPanel.add(createComprasPanel(), "Compras");
        cardPanel.add(createReportesPanel(), "Reportes");

        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 128, 185));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(listener);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(button);
    }

    private JPanel createUsuariosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addActionButton(buttonPanel, "Agregar Usuario", e -> agregarUsuario());
        addActionButton(buttonPanel, "Listar Usuarios", e -> listarUsuarios());
        addActionButton(buttonPanel, "Eliminar Usuario", e -> eliminarUsuario());

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createProductosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addActionButton(buttonPanel, "Agregar Producto", e -> agregarProducto());
        addActionButton(buttonPanel, "Listar Productos", e -> listarProductos());
        addActionButton(buttonPanel, "Actualizar Stock", e -> actualizarStock());

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createVentasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addActionButton(buttonPanel, "Realizar Venta", e -> realizarVenta());
        addActionButton(buttonPanel, "Listar Ventas", e -> listarVentas());

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createComprasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addActionButton(buttonPanel, "Realizar Compra", e -> realizarCompra());
        addActionButton(buttonPanel, "Listar Compras", e -> listarCompras());

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createReportesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addActionButton(buttonPanel, "Reporte de Ventas", e -> generarReporteVentas());
        addActionButton(buttonPanel, "Reporte de Compras", e -> generarReporteCompras());
        addActionButton(buttonPanel, "Reporte de Inventario", e -> generarReporteInventario());
        addActionButton(buttonPanel, "Reporte de Usuarios", e -> generarReporteUsuarios());

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private void addActionButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(listener);
        panel.add(button);
    }

// Implementación de los métodos de acción

    private void agregarUsuario() {
        JTextField nombreField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Nombre:", nombreField,
                "Contraseña:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nombre = nombreField.getText();
            String password = new String(passwordField.getPassword());
            if (!nombre.isEmpty() && !password.isEmpty()) {
                usuarios.add(new Usuario(nombre, password));
                JOptionPane.showMessageDialog(this, "Usuario agregado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            }
        }
    }

    private void listarUsuarios() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
        } else {
            StringBuilder lista = new StringBuilder("Lista de Usuarios:\n\n");
            for (Usuario usuario : usuarios) {
                lista.append(usuario.getNombre()).append("\n");
            }
            JTextArea textArea = new JTextArea(lista.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(300, 200));
            JOptionPane.showMessageDialog(this, scrollPane, "Usuarios Registrados", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void eliminarUsuario() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay usuarios para eliminar.");
            return;
        }

        String[] nombres = usuarios.stream().map(Usuario::getNombre).toArray(String[]::new);
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione el usuario a eliminar:",
                "Eliminar Usuario", JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);

        if (seleccion != null) {
            usuarios.removeIf(u -> u.getNombre().equals(seleccion));
            JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
        }
    }

    private void agregarProducto() {
        JTextField nombreField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField cantidadField = new JTextField();
        Object[] message = {
                "Nombre:", nombreField,
                "Precio:", precioField,
                "Cantidad:", cantidadField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText();
                double precio = Double.parseDouble(precioField.getText());
                int cantidad = Integer.parseInt(cantidadField.getText());

                if (!nombre.isEmpty() && precio > 0 && cantidad >= 0) {
                    productos.add(new Producto(nombre, precio, cantidad));
                    JOptionPane.showMessageDialog(this, "Producto agregado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese datos válidos.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos para precio y cantidad.");
            }
        }
    }

    private void listarProductos() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos registrados.");
        } else {
            StringBuilder lista = new StringBuilder("Lista de Productos:\n\n");
            for (Producto producto : productos) {
                lista.append(producto.getNombre())
                        .append(" - Precio: $").append(String.format("%.2f", producto.getPrecio()))
                        .append(" - Cantidad: ").append(producto.getCantidad()).append("\n");
            }
            JTextArea textArea = new JTextArea(lista.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(300, 200));
            JOptionPane.showMessageDialog(this, scrollPane, "Productos Registrados", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void actualizarStock() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para actualizar.");
            return;
        }

        String[] nombres = productos.stream().map(Producto::getNombre).toArray(String[]::new);
        String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione el producto a actualizar:",
                "Actualizar Stock", JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);

        if (seleccion != null) {
            String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la nueva cantidad:");
            try {
                int nuevaCantidad = Integer.parseInt(cantidadStr);
                if (nuevaCantidad >= 0) {
                    for (Producto producto : productos) {
                        if (producto.getNombre().equals(seleccion)) {
                            producto.setCantidad(nuevaCantidad);
                            JOptionPane.showMessageDialog(this, "Stock actualizado con éxito.");
                            return;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad válida (mayor o igual a cero).");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
            }
        }
    }

    private void realizarVenta() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos disponibles para vender.");
            return;
        }

        List<Producto> productosVendidos = new ArrayList<>();
        while (true) {
            String[] nombresProductos = productos.stream().map(Producto::getNombre).toArray(String[]::new);
            String seleccion = (String) JOptionPane.showInputDialog(this, "Seleccione un producto para vender (Cancelar para finalizar):",
                    "Realizar Venta", JOptionPane.QUESTION_MESSAGE, null, nombresProductos, nombresProductos[0]);

            if (seleccion == null) break;

            Producto productoSeleccionado = productos.stream()
                    .filter(p -> p.getNombre().equals(seleccion))
                    .findFirst().orElse(null);

            if (productoSeleccionado != null) {
                String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad a vender:");
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad > 0 && cantidad <= productoSeleccionado.getCantidad()) {
                        productoSeleccionado.setCantidad(productoSeleccionado.getCantidad() - cantidad);
                        productosVendidos.add(new Producto(productoSeleccionado.getNombre(), productoSeleccionado.getPrecio(), cantidad));
                    } else {
                        JOptionPane.showMessageDialog(this, "Cantidad no válida o insuficiente stock.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
                }
            }
        }

        if (!productosVendidos.isEmpty()) {
            ventas.add(new Venta(new Usuario("Cliente", ""), productosVendidos));
            JOptionPane.showMessageDialog(this, "Venta realizada con éxito.");
        }
    }

    private void listarVentas() {
        if (ventas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ventas registradas.");
        } else {
            StringBuilder lista = new StringBuilder("Lista de Ventas:\n\n");
            for (int i = 0; i < ventas.size(); i++) {
                Venta venta = ventas.get(i);
                lista.append("Venta ").append(i+1).append(":\n");
                for (Producto producto : venta.getProductos()) {
                    lista.append("  ").append(producto.getNombre())
                            .append(" - Cantidad: ").append(producto.getCantidad())
                            .append(" - Precio: $").append(String.format("%.2f", producto.getPrecio())).append("\n");
                }
                lista.append("\n");
            }
            JTextArea textArea = new JTextArea(lista.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Ventas Registradas", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void realizarCompra() {
        List<Producto> productosComprados = new ArrayList<>();
        while (true) {
            String nombreProducto = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto a comprar (o cancele para finalizar):");
            if (nombreProducto == null) break;

            String precioStr = JOptionPane.showInputDialog(this, "Ingrese el precio de compra:");
            String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad comprada:");

            try {
                double precio = Double.parseDouble(precioStr);
                int cantidad = Integer.parseInt(cantidadStr);

                if (precio > 0 && cantidad > 0) {
                    Producto productoExistente = productos.stream()
                            .filter(p -> p.getNombre().equals(nombreProducto))
                            .findFirst().orElse(null);

                    if (productoExistente != null) {
                        productoExistente.setCantidad(productoExistente.getCantidad() + cantidad);
                    } else {
                        productos.add(new Producto(nombreProducto, precio, cantidad));
                    }

                    productosComprados.add(new Producto(nombreProducto, precio, cantidad));
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese valores positivos.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos.");
            }
        }

        if (!productosComprados.isEmpty()) {
            compras.add(new Compra(new Usuario("Proveedor", ""), productosComprados));
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
        }
    }

    private void listarCompras() {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay compras registradas.");
        } else {
            StringBuilder lista = new StringBuilder("Lista de Compras:\n\n");
            for (int i = 0; i < compras.size(); i++) {
                Compra compra = compras.get(i);
                lista.append("Compra ").append(i+1).append(":\n");
                for (Producto producto : compra.getProductos()) {
                    lista.append("  ").append(producto.getNombre())
                            .append(" - Cantidad: ").append(producto.getCantidad())
                            .append(" - Precio: $").append(String.format("%.2f", producto.getPrecio())).append("\n");
                }
                lista.append("\n");
            }
            JTextArea textArea = new JTextArea(lista.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Compras Registradas", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void generarReporteVentas() {
        if (ventas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ventas para generar un reporte.");
        } else {
            double totalVentas = 0;
            StringBuilder reporte = new StringBuilder("Reporte de Ventas:\n\n");
            for (int i = 0; i < ventas.size(); i++) {
                Venta venta = ventas.get(i);
                reporte.append("Venta ").append(i+1).append(":\n");
                double totalVenta = 0;
                for (Producto producto : venta.getProductos()) {
                    double subtotal = producto.getPrecio() * producto.getCantidad();
                    totalVenta += subtotal;
                    reporte.append("  ").append(producto.getNombre())
                            .append(" - Cantidad: ").append(producto.getCantidad())
                            .append(" - Subtotal: $").append(String.format("%.2f", subtotal)).append("\n");
                }
                reporte.append("Total de la venta: $").append(String.format("%.2f", totalVenta)).append("\n\n");
                totalVentas += totalVenta;
            }
            reporte.append("Total de todas las ventas: $").append(String.format("%.2f", totalVentas));

            JTextArea textArea = new JTextArea(reporte.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Ventas", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // Continuación de los métodos de acción

    private void generarReporteCompras() {
        if (compras.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay compras para generar un reporte.");
        } else {
            double totalCompras = 0;
            StringBuilder reporte = new StringBuilder("Reporte de Compras:\n\n");
            for (int i = 0; i < compras.size(); i++) {
                Compra compra = compras.get(i);
                reporte.append("Compra ").append(i+1).append(":\n");
                double totalCompra = 0;
                for (Producto producto : compra.getProductos()) {
                    double subtotal = producto.getPrecio() * producto.getCantidad();
                    totalCompra += subtotal;
                    reporte.append("  ").append(producto.getNombre())
                            .append(" - Cantidad: ").append(producto.getCantidad())
                            .append(" - Subtotal: $").append(String.format("%.2f", subtotal)).append("\n");
                }
                reporte.append("Total de la compra: $").append(String.format("%.2f", totalCompra)).append("\n\n");
                totalCompras += totalCompra;
            }
            reporte.append("Total de todas las compras: $").append(String.format("%.2f", totalCompras));

            JTextArea textArea = new JTextArea(reporte.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Compras", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void generarReporteInventario() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos en el inventario.");
        } else {
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

            JTextArea textArea = new JTextArea(reporte.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Inventario", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void generarReporteUsuarios() {
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
        } else {
            StringBuilder reporte = new StringBuilder("Reporte de Usuarios:\n\n");
            for (Usuario usuario : usuarios) {
                reporte.append("Nombre: ").append(usuario.getNombre()).append("\n");
            }
            reporte.append("\nTotal de usuarios: ").append(usuarios.size());

            JTextArea textArea = new JTextArea(reporte.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            scrollPane.setPreferredSize(new Dimension(300, 200));
            JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Usuarios", JOptionPane.PLAIN_MESSAGE);
        }
    }

// Clases de modelo (estas deberían estar en archivos separados en un proyecto real)

    class Usuario {
        private String nombre;
        private String password;

        public Usuario(String nombre, String password) {
            this.nombre = nombre;
            this.password = password;
        }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    class Producto {
        private String nombre;
        private double precio;
        private int cantidad;

        public Producto(String nombre, double precio, int cantidad) {
            this.nombre = nombre;
            this.precio = precio;
            this.cantidad = cantidad;
        }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public double getPrecio() { return precio; }
        public void setPrecio(double precio) { this.precio = precio; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }

    class Venta {
        private Usuario usuario;
        private List<Producto> productos;
        private Date fecha;

        public Venta(Usuario usuario, List<Producto> productos) {
            this.usuario = usuario;
            this.productos = productos;
            this.fecha = new Date();
        }

        public Usuario getUsuario() { return usuario; }
        public List<Producto> getProductos() { return productos; }
        public Date getFecha() { return fecha; }
    }

    class Compra {
        private Usuario usuario;
        private List<Producto> productos;
        private Date fecha;

        public Compra(Usuario usuario, List<Producto> productos) {
            this.usuario = usuario;
            this.productos = productos;
            this.fecha = new Date();
        }

        public Usuario getUsuario() { return usuario; }
        public List<Producto> getProductos() { return productos; }
        public Date getFecha() { return fecha; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaInventarioGUI sistema = new SistemaInventarioGUI();
            sistema.setVisible(true);
        });
    }
}