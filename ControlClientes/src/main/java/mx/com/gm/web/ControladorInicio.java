package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login: " + user);
        model.addAttribute("personas", personas);
        var saldoTotal = 0D;
        for (var p : personas) {
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) { //Spring busca un objeto de tipo persona si no lo encuentra crea el objeto de tipo persona de ese modo no es necesario usar el new Persona() ya se hace automatico
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated Persona persona, BindingResult errors) { //Spring busca un objeto de tipo persona si no lo encuentra crea el objeto de tipo persona de ese modo no es necesario usar el new Persona() ya se hace automatico
        if (errors.hasErrors()) {
            return "modificar";
        }
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
