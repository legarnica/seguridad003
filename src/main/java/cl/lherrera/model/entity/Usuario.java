package cl.lherrera.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Usuario {
    private String correo;
    private String contrasenia;
    private Rol rol;
}
