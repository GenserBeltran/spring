package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model) {
        var personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        model.addAttribute("personas", personas);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) { //Spring busca un objeto de tipo persona si no lo encuentra crea el objeto de tipo persona de ese modo no es necesario usar el new Persona() ya se hace automatico
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(Persona persona) { //Spring busca un objeto de tipo persona si no lo encuentra crea el objeto de tipo persona de ese modo no es necesario usar el new Persona() ya se hace automatico
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) { //Spring busca un objeto de tipo persona si no lo encuentra crea el objeto de tipo persona de ese modo no es necesario usar el new Persona() ya se hace automatico
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
      @GetMapping("/eliminar")
    public String eliminar(Persona persona, Model model) { //Spring busca un objeto de tipo persona si no lo encuentra crea el objeto de tipo persona de ese modo no es necesario usar el new Persona() ya se hace automatico
        personaService.eliminar(persona);
        return "redirect:/";
    }
    
}
