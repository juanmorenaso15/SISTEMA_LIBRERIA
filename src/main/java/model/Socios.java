package model;

public class Socios {
    private int id;
    private String nombre;
    private String apellido;
    private String DNI;
    private String telefono;

    public Socios(String nombre, String apellido, String DNI, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
    }

    public Socios(int id, String nombre, String apellido, String DNI, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getDNI() {
        return DNI;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}