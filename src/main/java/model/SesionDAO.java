package model;

import database.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class SesionDAO {

    public void registrarSesion(int usuarioId) {
        String sql = "INSERT INTO sesion (id_usuario, fecha_inicio) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}