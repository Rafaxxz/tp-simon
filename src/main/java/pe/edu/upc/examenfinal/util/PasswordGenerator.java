package pe.edu.upc.examenfinal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Clase utilitaria para generar contraseñas encriptadas con BCrypt
 * Ejecutar el main para generar los hashes de las contraseñas
 */
public class PasswordGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        System.out.println("=== CONTRASEÑAS ENCRIPTADAS PARA ConnectED ===\n");
        
        // Generar contraseñas para usuarios
        String[] usuarios = {"admin123", "user123", "profesor123", "estudiante123"};
        String[] nombres = {"admin", "user", "profesor1", "estudiante1"};
        
        for (int i = 0; i < usuarios.length; i++) {
            String passwordEncriptada = encoder.encode(usuarios[i]);
            System.out.println("Usuario: " + nombres[i]);
            System.out.println("Password original: " + usuarios[i]);
            System.out.println("Password encriptada: " + passwordEncriptada);
            System.out.println("---");
        }
        
        System.out.println("\n=== Script SQL para insertar usuarios ===\n");
        System.out.println("INSERT INTO users (username, password, enabled) VALUES");
        for (int i = 0; i < usuarios.length; i++) {
            String passwordEncriptada = encoder.encode(usuarios[i]);
            System.out.print("('" + nombres[i] + "', '" + passwordEncriptada + "', true)");
            if (i < usuarios.length - 1) {
                System.out.println(",");
            } else {
                System.out.println(";");
            }
        }
    }
}
