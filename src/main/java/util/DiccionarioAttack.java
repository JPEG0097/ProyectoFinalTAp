package util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DiccionarioAttack {
    // Ruta fija configurada (ajusta esta ruta a tu sistema)
    private static final String RUTA_DICCIONARIO = "C:\\Users\\52417\\Downloads\\clavesTAP.txt";

    // Método para obtener la ruta configurada
    public static String getRutaDiccionario() {
        return RUTA_DICCIONARIO;
    }

    // Verifica si el archivo existe y es legible
    public static boolean archivoExiste() {
        File archivo = new File(RUTA_DICCIONARIO);
        return archivo.exists() && archivo.canRead();
    }

    // Conversión de bytes a hexadecimal
    public static String bytesToHex(byte[] bytes) {
        StringBuilder resultado = new StringBuilder();
        for (byte b : bytes) {
            resultado.append(String.format("%02x", b));
        }
        return resultado.toString();
    }

    // Cálculo de hash SHA-1
    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] resultado = md.digest(input.getBytes());
        return bytesToHex(resultado);
    }

    // Método principal de ataque con ruta fija
    public static String ataqueDiccionario(String hashObjetivo) {
        // Verificación inicial del archivo
        if (!archivoExiste()) {
            return "❌ Error: No se puede acceder al archivo de diccionario\n" +
                    "Ubicación: " + RUTA_DICCIONARIO;
        }

        int intentos = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_DICCIONARIO))) {
            String palabra;
            while ((palabra = br.readLine()) != null) {
                intentos++;
                String hashCalculado = sha1(palabra.trim());

                // Mostrar progreso cada 1000 intentos
                if (intentos % 1000 == 0) {
                    System.out.println("Procesando intento #" + intentos);
                }

                if (hashCalculado.equalsIgnoreCase(hashObjetivo)) {
                    return "✅ Contraseña encontrada: '" + palabra + "'\n" +
                            "Intentos realizados: " + intentos;
                }
            }
            return "❌ Contraseña no encontrada después de " + intentos + " intentos";
        } catch (IOException | NoSuchAlgorithmException e) {
            return "❌ Error durante el ataque: " + e.getMessage();
        }
    }
}