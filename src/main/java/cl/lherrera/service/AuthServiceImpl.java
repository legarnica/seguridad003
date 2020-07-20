package cl.lherrera.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.lherrera.config.EncoderUtils;
import cl.lherrera.model.entity.Rol;
import cl.lherrera.model.entity.Usuario;

@Service
public class AuthServiceImpl  implements UserDetailsService {
    
//    // simulación de usuario
//    private static final Usuario USUARIO = new Usuario(
//        "admin@mail.cl", 
//        EncoderUtils.passwordEncoder().encode("1234"), 
//        Rol.ROLE_ADMIN
//    );

    private static final Usuario USUARIO = new Usuario(
            "admin@mail.cl", 
            EncoderUtils.passwordEncoder().encode("1234"), 
            Rol.ROLE_USER
            ); 

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = USUARIO;
        User usuarioSistema = null;
        
        if(usuario != null) {
            String rol = usuario.getRol().toString();
            String username_ = usuario.getCorreo();
            String password_ = usuario.getContrasenia();
            
            // agregamos el rol a la lista de roles
            List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority(rol));
            usuarioSistema = new User(username_, password_, roles);
        }else {
            // TODO...
        }
        
        return usuarioSistema;
    }

}
























