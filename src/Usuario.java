import javax.swing.JOptionPane;
import java.util.List;
import java.util.Optional;

class Usuario {
    private String nombre;
    private String password;
    private String rol;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.rol = "usuario"; // rol por defecto
    }

    public Usuario(String nombre, String password, String rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}

class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean existeUsuario(String nombre) {
        return usuarios.stream().anyMatch(u -> u.getNombre().equals(nombre));
    }

    public boolean agregarUsuario(String nombre, String password, String rol) {
        if (existeUsuario(nombre)) {
            JOptionPane.showMessageDialog(null, "Error: El usuario ya existe.");
            return false;
        }
        
        if (rol != null && !rol.trim().isEmpty()) {
            usuarios.add(new Usuario(nombre, password, rol));
        } else {
            usuarios.add(new Usuario(nombre, password));
        }
        JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.");
        return true;
    }

    public Optional<Usuario> leerUsuario(String nombre) {
        return usuarios.stream()
                      .filter(u -> u.getNombre().equals(nombre))
                      .findFirst();
    }

    public void listarUsuarios() {
        StringBuilder lista = new StringBuilder("Lista de Usuarios:\n");
        for (Usuario usuario : usuarios) {
            lista.append("Nombre: ").append(usuario.getNombre())
                .append(", Rol: ").append(usuario.getRol()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    public boolean actualizarNombre(String nombreActual, String nuevoNombre) {
        if (existeUsuario(nuevoNombre)) {
            JOptionPane.showMessageDialog(null, "Error: El nuevo nombre ya está en uso.");
            return false;
        }
        
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreActual)) {
                usuario.setNombre(nuevoNombre);
                JOptionPane.showMessageDialog(null, "Nombre actualizado con éxito.");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado.");
        return false;
    }

    public boolean actualizarPassword(String nombre, String nuevaPassword) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuario.setPassword(nuevaPassword);
                JOptionPane.showMessageDialog(null, "Contraseña actualizada con éxito.");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado.");
        return false;
    }

    public boolean actualizarRol(String nombre, String nuevoRol) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuario.setRol(nuevoRol);
                JOptionPane.showMessageDialog(null, "Rol actualizado con éxito.");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado.");
        return false;
    }

    public boolean eliminarUsuario(String nombre) {
        if (!existeUsuario(nombre)) {
            JOptionPane.showMessageDialog(null, "Error: Usuario no encontrado.");
            return false;
        }
        usuarios.removeIf(u -> u.getNombre().equals(nombre));
        JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.");
        return true;
    }
}