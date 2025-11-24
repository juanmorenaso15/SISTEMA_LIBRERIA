package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Libros;

public class LibrosRepository {

    public void insertarLibros(Libros libro) {
        String sql = "INSERT INTO libros (isbn, titulo, autor_id, año_publicacion, cantidad_total, cantidad_disponible) VALUES (?,?,?,?,?,?)";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getTitulo());
            preparedStatement.setLong(3, libro.getAutor_id());
            preparedStatement.setInt(4, libro.getAño_publicacion());
            preparedStatement.setInt(5, libro.getCantidad_total());
            preparedStatement.setInt(6, libro.getCantidad_disponible());

            preparedStatement.executeUpdate();

            System.out.println("Libro insertado correctamente");

        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e);
            e.printStackTrace();
        }

    }

    public List < Libros > ListarLibros() {
        String sql = "SELECT isbn, titulo, autor_id, año_publicacion, cantidad_total, cantidad_disponible FROM libros";
        List < Libros > libros = new ArrayList < > ();

        try (Connection connection = Conexion.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                libros.add(new Libros(
                    resultSet.getString("isbn"),
                    resultSet.getString("titulo"),
                    resultSet.getLong("autor_id"),
                    resultSet.getInt("año_publicacion"),
                    resultSet.getInt("cantidad_total"),
                    resultSet.getInt("cantidad_disponible")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;

    }

    public void mostrarLibro(Libros libro) {
        System.out.println("ISBN: " + libro.getIsbn() +
            " - Título: " + libro.getTitulo() +
            " - Autor ID: " + libro.getAutor_id() +
            " - Año: " + libro.getAño_publicacion() +
            " - Total: " + libro.getCantidad_total() +
            " - Disponible: " + libro.getCantidad_disponible());
    }

    public Libros obtenerLibroPorIsbn(String isbn) {
        String sql = "SELECT isbn, titulo, autor_id, año_publicacion, cantidad_total, cantidad_disponible FROM libros WHERE isbn = ?";

        

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Libros(
                    resultSet.getString("isbn"),
                    resultSet.getString("titulo"),
                    resultSet.getLong("autor_id"),
                    resultSet.getInt("año_publicacion"),
                    resultSet.getInt("cantidad_total"),
                    resultSet.getInt("cantidad_disponible")
                );
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e);
            e.printStackTrace();
        }
        return null ;
    }

    public void ActualizarLibros(Libros libros) {
        String sql = "UPDATE libros SET TITULO = ?, AUTOR_ID = ?, AÑO_PUBLICACION = ?, CANTIDAD_TOTAL = ?, CANTIDAD_DISPONIBLE = ? WHERE ISBN = ?";

        try (Connection connection = Conexion.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, libros.getTitulo());
            preparedStatement.setLong(2, libros.getAutor_id());
            preparedStatement.setInt(3, libros.getAño_publicacion());
            preparedStatement.setInt(4, libros.getCantidad_total());
            preparedStatement.setInt(5, libros.getCantidad_disponible());
            preparedStatement.setString(6, libros.getIsbn());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e);
            e.printStackTrace();
        }
    }


    public void eliminarLibro(String isbn) {
        String sql = "DELETE FROM libros WHERE isbn = ?";

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement eliminar = connection.prepareStatement(sql);
            eliminar.setString(1, isbn);
            eliminar.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e);
        }
    }
}