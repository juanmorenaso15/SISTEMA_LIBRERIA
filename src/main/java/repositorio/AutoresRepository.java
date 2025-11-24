package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Autores;

public class AutoresRepository {
    public void insertarAutor(Autores autor) {
        String sql = "INSERT INTO autores (NOMBRE, NACIONALIDAD) VALUES (?,?)";

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, autor.getNombre());
            preparedStatement.setString(2, autor.getNacionalidad());
            //para ejecutar la consulta
            preparedStatement.executeUpdate();

            System.out.println("Autor insertado Correctamente");

        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e);
            e.printStackTrace();
        }
    }

    public List < Autores > ListarAutores() {
        String sql = "SELECT ID, NOMBRE, NACIONALIDAD FROM autores";
        List < Autores > autores = new ArrayList < > ();

        try (Connection connection = Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                autores.add(new Autores(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("nacionalidad")
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return autores;
    }


    //Actualizar 

    public void ActualizarAutores(Autores autores) {
        String sql = "UPDATE autores SET NOMBRE = ?, NACIONALIDAD = ? WHERE ID = ?";

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, autores.getNombre());
            preparedStatement.setString(2, autores.getNacionalidad());
            preparedStatement.setLong(3, autores.getId());

            int filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Autor actualizado correctamente");
            } else {
                System.out.println("No se encontró el Autor con ID: " + autores.getId());

            }

        } catch (Exception e) {
        System.out.println("Ocurrió un error inesperado: " + e);
        e.printStackTrace();        
    }
    }

    //eliminar

    public void eliminarAutor(Long id) {
    String sql = "DELETE FROM AUTORES WHERE ID = ?";
    
    try (Connection connection = Conexion.getConnection()) {
        PreparedStatement eliminar = connection.prepareStatement(sql);
        eliminar.setLong(1, id);
        eliminar.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al eliminar: " + e);
    }
}
}