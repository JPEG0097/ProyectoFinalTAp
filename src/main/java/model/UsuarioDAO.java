package model;

import database.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean validarUsuario(String username, String password) {
        String sql = "SELECT password_sha1 FROM usuario WHERE username = ?";
        try (Connection conn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashBD = rs.getString("password_sha1");
                String hashInput = SHA1Util.sha1(password);
                return hashBD.equalsIgnoreCase(hashInput);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}