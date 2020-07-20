package cl.lherrera.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.lherrera.dto.PersonaDto;

@Controller
@RequestMapping("admin")
public class AdminController {
    
    @GetMapping
    public String admin(ModelMap modelo) {
        // capturo el nombre de usuario
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String name = auth.getName();
        modelo.addAttribute("username", name);

        return "admin/index";
    }
    
    @PostMapping
    public String persona(
        HttpSession session,
        @ModelAttribute PersonaDto persona
    ) {
        List<PersonaDto> personas = new ArrayList<>();
        
        // si es que existen personas en sesión, estas se cargan
        // antes que se cargue lo que llegó por el formulario
        if(session.getAttribute("personas") != null) {
            @SuppressWarnings("unchecked")
            List<PersonaDto> personasEnSession = (List<PersonaDto>) session.getAttribute("personas");
            personas.addAll(personasEnSession);
        }
        
        personas.add(persona);
        
        session.setAttribute("personas", personas);
        
        return "redirect:home";
        
    }
}
