import javax.swing.JOptionPane;
import java.util.List;

class Usuario {
    private String nombre;
    private String password;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void agregarUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña del usuario:");
        usuarios.add(new Usuario(nombre, password));
        JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.");
    }

    public void listarUsuarios() {
        StringBuilder lista = new StringBuilder("Lista de Usuarios:\n");
        for (Usuario usuario : usuarios) {
            lista.append(usuario.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    public void eliminarUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario a eliminar:");
        usuarios.removeIf(u -> u.getNombre().equals(nombre));
        JOptionPane.showMessageDialog(null, "Usuario eliminado si existía.");
    }
}