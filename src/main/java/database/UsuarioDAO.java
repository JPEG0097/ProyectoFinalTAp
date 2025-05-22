package database;

import java.sql.*;

public class UsuarioDAO {

    public boolean registrarUsuario(String nombreUsuario, String contrasenaSha1) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena_sha1) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenaSha1);
            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validarUsuario(String nombreUsuario, String contrasenaSha1) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena_sha1 = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenaSha1);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
