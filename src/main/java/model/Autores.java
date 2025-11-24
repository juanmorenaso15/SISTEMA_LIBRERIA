package model;

public class Autores {
    private Long id;
    private String nombre;
    private String nacionalidad;
    /*constructor para crear un autor */
    public Autores(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
    /*Constructor para atrae autores */
    public Autores(Long id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getNacionalidad(){
        return nacionalidad;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setNacionalidad(String nacionalidad){
        this.nacionalidad=nacionalidad;
    }


}