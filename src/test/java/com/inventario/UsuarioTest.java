package com.inventario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Pruebas de GestorUsuarios")
class UsuarioTest {
    private GestorUsuarios gestorUsuarios;
    private List<Usuario> usuarios;

    @BeforeEach
    void setUp() {
        usuarios = new ArrayList<>();
        gestorUsuarios = new GestorUsuarios(usuarios);
    }

    @Test
    @DisplayName("Prueba: Crear usuario existente")
    void testCrearUsuarioExistente() {
        // Primer intento de crear usuario
        boolean resultado1 = gestorUsuarios.agregarUsuario("usuario1", "pass123", "usuario");
        assertTrue(resultado1, "El primer usuario debería crearse exitosamente");

        // Intento de crear usuario con el mismo nombre
        boolean resultado2 = gestorUsuarios.agregarUsuario("usuario1", "otraPass", "usuario");
        assertFalse(resultado2, "No debería permitir crear un usuario con nombre duplicado");
    }

    @Test
    @DisplayName("Prueba: Leer usuario no existente")
    void testLeerUsuarioNoExistente() {
        Optional<Usuario> resultado = gestorUsuarios.leerUsuario("noExiste");
        assertTrue(resultado.isEmpty(), "Debería retornar Optional vacío para usuario no existente");
    }

    @Test
    @DisplayName("Prueba: Actualizar nombre de usuario")
    void testActualizarNombre() {
        // Crear usuario inicial
        gestorUsuarios.agregarUsuario("usuario1", "pass123", "usuario");

        // Actualizar nombre
        boolean resultado = gestorUsuarios.actualizarNombre("usuario1", "nuevoNombre");
        assertTrue(resultado, "La actualización del nombre debería ser exitosa");

        // Verificar que el nombre se actualizó
        Optional<Usuario> usuarioActualizado = gestorUsuarios.leerUsuario("nuevoNombre");
        assertTrue(usuarioActualizado.isPresent(), "El usuario debería existir con el nuevo nombre");
        assertEquals("nuevoNombre", usuarioActualizado.get().getNombre());
    }

    @Test
    @DisplayName("Prueba: Actualizar contraseña de usuario")
    void testActualizarPassword() {
        // Crear usuario inicial
        gestorUsuarios.agregarUsuario("usuario1", "pass123", "usuario");

        // Actualizar contraseña
        boolean resultado = gestorUsuarios.actualizarPassword("usuario1", "nuevaPass");
        assertTrue(resultado, "La actualización de la contraseña debería ser exitosa");

        // Verificar que la contraseña se actualizó
        Optional<Usuario> usuario = gestorUsuarios.leerUsuario("usuario1");
        assertTrue(usuario.isPresent(), "El usuario debería existir");
        assertEquals("nuevaPass", usuario.get().getPassword());
    }

    @Test
    @DisplayName("Prueba: Actualizar rol de usuario")
    void testActualizarRol() {
        // Crear usuario inicial
        gestorUsuarios.agregarUsuario("usuario1", "pass123", "usuario");

        // Actualizar rol
        boolean resultado = gestorUsuarios.actualizarRol("usuario1", "admin");
        assertTrue(resultado, "La actualización del rol debería ser exitosa");

        // Verificar que el rol se actualizó
        Optional<Usuario> usuario = gestorUsuarios.leerUsuario("usuario1");
        assertTrue(usuario.isPresent(), "El usuario debería existir");
        assertEquals("admin", usuario.get().getRol());
    }

    @Test
    @DisplayName("Prueba: Actualizar usuario no existente")
    void testActualizarUsuarioNoExistente() {
        // Intentar actualizar nombre
        boolean resultadoNombre = gestorUsuarios.actualizarNombre("noExiste", "nuevoNombre");
        assertFalse(resultadoNombre, "No debería actualizar el nombre de un usuario no existente");

        // Intentar actualizar contraseña
        boolean resultadoPassword = gestorUsuarios.actualizarPassword("noExiste", "nuevaPass");
        assertFalse(resultadoPassword, "No debería actualizar la contraseña de un usuario no existente");

        // Intentar actualizar rol
        boolean resultadoRol = gestorUsuarios.actualizarRol("noExiste", "nuevoRol");
        assertFalse(resultadoRol, "No debería actualizar el rol de un usuario no existente");
    }

    @Test
    @DisplayName("Prueba: Verificar usuario existente")
    void testExisteUsuario() {
        // Crear usuario inicial
        gestorUsuarios.agregarUsuario("usuario1", "pass123", "usuario");

        // Verificar que existe
        assertTrue(gestorUsuarios.existeUsuario("usuario1"), "El usuario debería existir");
        assertFalse(gestorUsuarios.existeUsuario("noExiste"), "El usuario no debería existir");
    }
}