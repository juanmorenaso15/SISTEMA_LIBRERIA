package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import db.Conexion;
import model.Socios;

public class SociosRepository {

    public void insertarSocio(Socios socio) {
        String sql = "INSERT INTO socios (nombre, apellido, DNI, telefono) VALUES (?,?,?,?)";

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, socio.getNombre());
            preparedStatement.setString(2, socio.getApellido());
            preparedStatement.setString(3, socio.getDNI());
            preparedStatement.setString(4, socio.getTelefono());
            preparedStatement.executeUpdate();
            System.out.println("Socio insertado correctamente");
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e);
            e.printStackTrace();
        }

    }

    public List < Socios > ListarSocios() {
        String sql = "SELECT id, nombre, apellido, DNI, telefono FROM socios";
        List < Socios > socios = new java.util.ArrayList < > ();

        try (Connection connection = Conexion.getConnection()) {

            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                socios.add(new Socios(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("DNI"),
                    resultSet.getString("telefono")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return socios;
    }

    public Socios obtenerSocioPorId(int id) {
        String sql = "SELECT id, nombre, apellido, DNI, telefono FROM socios WHERE id = ?";
        Socios socio = null;

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                socio = new Socios(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("DNI"),
                    resultSet.getString("telefono")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return socio;
    
    }   

    public void ActualizarSocios(Socios socio) {
        String sql = "UPDATE socios SET nombre = ?, apellido = ?, DNI = ?, telefono = ? WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, socio.getNombre());
            preparedStatement.setString(2, socio.getApellido());
            preparedStatement.setString(3, socio.getDNI());
            preparedStatement.setString(4, socio.getTelefono());
            preparedStatement.setInt(5, socio.getId());

            preparedStatement.executeUpdate();

            System.out.println("Socio actualizado correctamente");

        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e);
            e.printStackTrace();
        }
    }

    public void eliminarSocio(int id) {
        String sql = "DELETE FROM socios WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int filasEliminadas = preparedStatement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Socio eliminado correctamente");
            } else {
                System.out.println("No se encontró el socio con ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e);
            e.printStackTrace();
        }
    }
}