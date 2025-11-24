package model;

public class Libros {
    private String isbn;
    private String titulo;
    private Long autor_id;
    private int año_publicacion;
    private int cantidad_total;
    private int cantidad_disponible;

    public Libros(String titulo, Long autor_id, int año_publicacion, int cantidad_total, int cantidad_disponible) {
        this.titulo = titulo;
        this.autor_id = autor_id;
        this.año_publicacion = año_publicacion;
        this.cantidad_total = cantidad_total;
        this.cantidad_disponible = cantidad_disponible;
    }

    
    public Libros(String isbn, String titulo, Long autor_id, int año_publicacion, int cantidad_total, int cantidad_disponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor_id = autor_id;
        this.año_publicacion = año_publicacion;
        this.cantidad_total = cantidad_total;
        this.cantidad_disponible = cantidad_disponible;
    }



    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getAutor_id() {
        return autor_id;
    }

    public int getAño_publicacion() {
        return año_publicacion;
    }

    public int getCantidad_total() {
        return cantidad_total;
    }

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor_id(Long autor_id) {
        this.autor_id = autor_id;
    }

    public void setAño_publicacion(int año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    public void setCantidad_total(int cantidad_total) {
        this.cantidad_total = cantidad_total;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

}