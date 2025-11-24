package model;

public class Prestamos {

    private Long id;
    private String libro_isbn;
    private int socio_id;
    private java.sql.Date fecha_prestamo;
    private java.sql.Date fecha_devolucion_prevista;
    private java.sql.Date fecha_devolucion_real;
    private EstadoPrestamo estado;

    public enum EstadoPrestamo {
        PRESTADO,
        DEVUELTO
    }

    public Prestamos(String libro_isbn, int socio_id, java.sql.Date fecha_prestamo, java.sql.Date fecha_devolucion_prevista, java.sql.Date fecha_devolucion_real, EstadoPrestamo estado) {
        this.libro_isbn = libro_isbn;
        this.socio_id = socio_id;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion_prevista = fecha_devolucion_prevista;
        this.fecha_devolucion_real = fecha_devolucion_real;
        this.estado = estado;
    }

    public Prestamos(long id, String libro_isbn, int socio_id, java.sql.Date fecha_prestamo, java.sql.Date fecha_devolucion_prevista, java.sql.Date fecha_devolucion_real, EstadoPrestamo estado) {
        this.id = id;
        this.libro_isbn = libro_isbn;
        this.socio_id = socio_id;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion_prevista = fecha_devolucion_prevista;
        this.fecha_devolucion_real = fecha_devolucion_real;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }
    public String getLibro_isbn() {
        return libro_isbn;
    }
    public int getSocio_id() {
        return socio_id;
    }

    public java.util.Date getFecha_prestamo() {
        return fecha_prestamo;
    }
    public java.util.Date getFecha_devolucion_prevista() {
        return fecha_devolucion_prevista;
    }
    public java.util.Date getFecha_devolucion_real() {
        return fecha_devolucion_real;
    }
    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setLibro_isbn(String libro_isbn) {
        this.libro_isbn = libro_isbn;
    }
    public void setSocio_id(int socio_id) {
        this.socio_id = socio_id;
    }

    public void setFecha_prestamo(java.sql.Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }
    public void setFecha_devolucion_prevista(java.sql.Date fecha_devolucion_prevista) {
        this.fecha_devolucion_prevista = fecha_devolucion_prevista;
    }
    public void setFecha_devolucion_real(java.sql.Date fecha_devolucion_real) {
        this.fecha_devolucion_real = fecha_devolucion_real;
    }
    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

}