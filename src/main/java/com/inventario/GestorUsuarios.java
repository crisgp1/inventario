package com.inventario;

import java.util.List;
import java.util.Optional;

public class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean existeUsuario(String nombre) {
        return usuarios.stream().anyMatch(u -> u.getNombre().equals(nombre));
    }

    public boolean agregarUsuario(String nombre, String password, String rol) {
        if (existeUsuario(nombre)) {
            return false;
        }
        
        if (rol != null && !rol.trim().isEmpty()) {
            usuarios.add(new Usuario(nombre, password, rol));
        } else {
            usuarios.add(new Usuario(nombre, password));
        }
        return true;
    }

    public Optional<Usuario> leerUsuario(String nombre) {
        return usuarios.stream()
                      .filter(u -> u.getNombre().equals(nombre))
                      .findFirst();
    }

    public boolean actualizarNombre(String nombreActual, String nuevoNombre) {
        if (existeUsuario(nuevoNombre)) {
            return false;
        }
        
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreActual)) {
                usuario.setNombre(nuevoNombre);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarPassword(String nombre, String nuevaPassword) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuario.setPassword(nuevaPassword);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarRol(String nombre, String nuevoRol) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                usuario.setRol(nuevoRol);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarUsuario(String nombre) {
        if (!existeUsuario(nombre)) {
            return false;
        }
        usuarios.removeIf(u -> u.getNombre().equals(nombre));
        return true;
    }
}