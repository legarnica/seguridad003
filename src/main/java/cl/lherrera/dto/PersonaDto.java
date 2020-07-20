package cl.lherrera.dto;

public class PersonaDto {
    private Integer id;
    private String nombre;
    private String correo;

    public PersonaDto(Integer id, String nombre, String correo) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public PersonaDto() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "PersonaDto [id=" + id + ", nombre=" + nombre + ", correo="
                + correo + "]";
    }

}
