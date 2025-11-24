package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.Conexion;
import model.Prestamos;

public class PrestamosRepository {

    //Realizar préstamo de un libro (controlando disponibilidad)

    public void realizarPrestamo(Prestamos prestamos) {
        String sql = "INSERT INTO prestamos (libro_isbn, socio_id, fecha_prestamo, fecha_devolucion_prevista, estado) VALUES (?,?,?,?,?)";
        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, prestamos.getLibro_isbn());
            preparedStatement.setInt(2, prestamos.getSocio_id());
            preparedStatement.setDate(3, new java.sql.Date(prestamos.getFecha_prestamo().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(prestamos.getFecha_devolucion_prevista().getTime()));
            preparedStatement.setString(5, Prestamos.EstadoPrestamo.PRESTADO.name());

            preparedStatement.executeUpdate();

            System.out.println("Préstamo realizado correctamente");

        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e);
            e.printStackTrace();
        }
    }

    // Devolver un libro

    public void devolverPrestamo(long prestamoId, java.util.Date fechaDevolucionReal) {
        String sql = "UPDATE prestamos SET fecha_devolucion_real = ?, estado = ? WHERE id = ?";

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(fechaDevolucionReal.getTime()));
            preparedStatement.setString(2, Prestamos.EstadoPrestamo.DEVUELTO.name());
            preparedStatement.setLong(3, prestamoId);

            preparedStatement.executeUpdate();

            System.out.println("Préstamo devuelto correctamente");

        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado: " + e);
            e.printStackTrace();
        }
    }

    // Listar préstamos activos

    public java.util.List < Prestamos > listarPrestamosActivos() {
        String sql = "SELECT id, libro_isbn, socio_id, fecha_prestamo, fecha_devolucion_prevista, fecha_devolucion_real, estado FROM prestamos WHERE estado = ?";
        java.util.List < Prestamos > prestamos = new java.util.ArrayList < > ();

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Prestamos.EstadoPrestamo.PRESTADO.name());
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                prestamos.add(new Prestamos(
                    resultSet.getLong("id"),
                    resultSet.getString("libro_isbn"),
                    resultSet.getInt("socio_id"),
                    resultSet.getDate("fecha_prestamo"),
                    resultSet.getDate("fecha_devolucion_prevista"),
                    resultSet.getDate("fecha_devolucion_real"),
                    Prestamos.EstadoPrestamo.valueOf(resultSet.getString("estado"))
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return prestamos;
    }

    // Listar préstamos de un socio específico

    public java.util.List < Prestamos > listarPrestamosPorSocio(int socioId) {
        String sql = "SELECT id, libro_isbn, socio_id, libro_isbn, fecha_prestamo, fecha_devolucion_prevista, fecha_devolucion_real, estado FROM prestamos WHERE socio_id = ?";
        java.util.List < Prestamos > prestamos = new java.util.ArrayList < > ();

        try (Connection connection = Conexion.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, socioId);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                prestamos.add(new Prestamos(
                    resultSet.getLong("id"),
                    resultSet.getString("libro_isbn"),
                    resultSet.getInt("socio_id"),

                    resultSet.getDate("fecha_prestamo"),
                    resultSet.getDate("fecha_devolucion_prevista"),
                    resultSet.getDate("fecha_devolucion_real"),
                    Prestamos.EstadoPrestamo.valueOf(resultSet.getString("estado"))
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prestamos;
    }


    //Ver libros prestados actualmente y quién los tiene
    public void mostrarPrestamo(Prestamos prestamo) {
        System.out.println("ID Préstamo: " + prestamo.getId() +
            " - ISBN: " + prestamo.getLibro_isbn() +
            " - ID Socio: " + prestamo.getSocio_id() +
            " - Fecha Préstamo: " + prestamo.getFecha_prestamo() +
            " - Fecha Devolución Prevista: " + prestamo.getFecha_devolucion_prevista() +
            " - Fecha Devolución Real: " + prestamo.getFecha_devolucion_real() +
            " - Estado: " + prestamo.getEstado());
    }



}