import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class TestUsuario {
    public static void main(String[] args) {
        // Inicializar el gestor con una lista de usuarios para pruebas
        List<Usuario> usuarios = new ArrayList<>();
        GestorUsuarios gestor = new GestorUsuarios(usuarios);
        
        System.out.println("Iniciando pruebas de Usuario...\n");
        
        // 1. Prueba de crear usuario existente
        System.out.println("1. Prueba de crear usuario existente:");
        System.out.println("- Creando usuario inicial:");
        boolean resultadoCreacion1 = gestor.agregarUsuario("testUser", "password123", "usuario");
        System.out.println("Creación de usuario inicial exitosa: " + resultadoCreacion1);
        
        System.out.println("- Intentando crear usuario con mismo nombre:");
        boolean resultadoCreacion2 = gestor.agregarUsuario("testUser", "otraPassword", "usuario");
        System.out.println("Creación de usuario duplicado fallida (esperado): " + !resultadoCreacion2);
        
        // 2. Prueba de leer usuario no existente
        System.out.println("\n2. Prueba de leer usuario no existente:");
        Optional<Usuario> usuarioNoExistente = gestor.leerUsuario("usuarioInexistente");
        System.out.println("Usuario no encontrado (esperado): " + !usuarioNoExistente.isPresent());
        
        // 3. Pruebas de actualización
        System.out.println("\n3. Pruebas de actualización:");
        
        // 3.1 Actualización de nombre
        System.out.println("\n3.1 Actualización de nombre:");
        System.out.println("- Caso exitoso:");
        boolean resultadoNombre = gestor.actualizarNombre("testUser", "nuevoNombre");
        System.out.println("Actualización de nombre exitosa: " + resultadoNombre);
        
        // Verificar que el nombre se actualizó correctamente
        Optional<Usuario> usuarioActualizado = gestor.leerUsuario("nuevoNombre");
        System.out.println("Verificación de actualización de nombre: " + 
                          (usuarioActualizado.isPresent() && 
                           usuarioActualizado.get().getNombre().equals("nuevoNombre")));
        
        // 3.2 Actualización de contraseña
        System.out.println("\n3.2 Actualización de contraseña:");
        System.out.println("- Caso exitoso:");
        boolean resultadoPassword = gestor.actualizarPassword("nuevoNombre", "newPassword123");
        System.out.println("Actualización de contraseña exitosa: " + resultadoPassword);
        
        // 3.3 Actualización de rol
        System.out.println("\n3.3 Actualización de rol:");
        System.out.println("- Caso exitoso:");
        boolean resultadoRol = gestor.actualizarRol("nuevoNombre", "admin");
        System.out.println("Actualización de rol exitosa: " + resultadoRol);
        
        // 4. Pruebas de actualización de usuario no existente
        System.out.println("\n4. Pruebas de actualización de usuario no existente:");
        
        System.out.println("- Intentando actualizar nombre de usuario no existente:");
        boolean resultadoNombreFallido = gestor.actualizarNombre("usuarioInexistente", "otroNombre");
        System.out.println("Actualización de nombre fallida (esperado): " + !resultadoNombreFallido);
        
        System.out.println("\n- Intentando actualizar contraseña de usuario no existente:");
        boolean resultadoPasswordFallido = gestor.actualizarPassword("usuarioInexistente", "otraPassword");
        System.out.println("Actualización de contraseña fallida (esperado): " + !resultadoPasswordFallido);
        
        System.out.println("\n- Intentando actualizar rol de usuario no existente:");
        boolean resultadoRolFallido = gestor.actualizarRol("usuarioInexistente", "otroRol");
        System.out.println("Actualización de rol fallida (esperado): " + !resultadoRolFallido);
        
        // 5. Prueba de eliminación de usuario no existente
        System.out.println("\n5. Prueba de eliminación de usuario no existente:");
        boolean resultadoEliminacion = gestor.eliminarUsuario("usuarioInexistente");
        System.out.println("Eliminación de usuario inexistente fallida (esperado): " + !resultadoEliminacion);
        
        System.out.println("\nPruebas completadas.");
    }
}