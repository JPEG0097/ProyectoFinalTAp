package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PruebaConsulta {
    public static void main(String[] args) {
        Connection conexion = ConexionBD.getInstancia().getConexion();

        if (conexion != null) {
            try {
                Statement stmt = conexion.createStatement();
                String sql = "SELECT id_usuario, username, correo FROM usuario";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int id = rs.getInt("id_usuario");
                    String user = rs.getString("username");
                    String correo = rs.getString("correo");
                    System.out.println("ID: " + id + ", Usuario: " + user + ", Correo: " + correo);
                }
                rs.close();
                stmt.close();
                conexion.close();  // Opcional, dependiendo de si quieres cerrar aquí
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay conexión a la base de datos");
        }
    }
}

