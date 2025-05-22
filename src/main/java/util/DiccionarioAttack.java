package util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DiccionarioAttack {

    public static String bytesToHex(byte[] bytes) {
        StringBuilder resultado = new StringBuilder();
        for (byte b : bytes) {
            resultado.append(String.format("%02x", b));
        }
        return resultado.toString();
    }

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] resultado = md.digest(input.getBytes());
        return bytesToHex(resultado);
    }

    public static String ataqueDiccionario(String hashObjetivo, String rutaDiccionario) {
        int intentos = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaDiccionario))) {
            String palabra;
            while ((palabra = br.readLine()) != null) {
                intentos++;
                String hashCalculado = sha1(palabra.trim());
                System.out.println("Intento " + intentos + ": " + palabra + " => " + hashCalculado);
                if (hashCalculado.equalsIgnoreCase(hashObjetivo)) {
                    return "✅ Contraseña encontrada: '" + palabra + "' en " + intentos + " intentos.";
                }
            }
            return "❌ Contraseña no encontrada en el diccionario.";
        } catch (IOException | NoSuchAlgorithmException e) {
            return "Error: " + e.getMessage();
        }
    }
}
