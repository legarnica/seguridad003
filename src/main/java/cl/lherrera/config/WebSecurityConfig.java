package cl.lherrera.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * este método que será desechado más adelante
     * se encarga de crear usuarios por defecto
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        String adminUsername = "admin@mail.cl";
        String adminPassword = passwordEncoder().encode("1234");
        String adminRole = "ADMIN";

        String userUsername = "user@mail.cl";
        String userPassword = passwordEncoder().encode("1234");
        String userRole = "USER";
        
        auth.inMemoryAuthentication()
        .withUser(adminUsername).password(adminPassword).roles(adminRole)
        .and()
        .withUser(userUsername).password(userPassword).roles(userRole);
        
    }
    
    
    /**
     * Acá irá la configuración principal
     * Las reglas serán impuestas en este
     * método.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // csrf - desactivamos por seguridad
        http.csrf().disable()
        // configurar los request autorizados
        .authorizeRequests()
        //acceso a direcctorios
        .antMatchers("/auth/**").permitAll()
        .antMatchers("/home/**").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/login").permitAll()
        // permite el uso de los recursos estáticos
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        // para todo el resto de peticiones, permite, si está logeado
        .anyRequest().authenticated()
        // indicar el login personalizado
        .and().formLogin().loginPage("/login")
        // indicar en caso de fallo, donde ir y con qué
        .failureUrl("/login?error=true")
        // indicar campos name en los imput
        // de usuario y contraseña
        .usernameParameter("email").passwordParameter("password")
        // indicamos la url de éxito
        .defaultSuccessUrl("/")
        // manejamos el recurso no permitido
        .and().exceptionHandling().accessDeniedPage("/recurso-prohibido");

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
