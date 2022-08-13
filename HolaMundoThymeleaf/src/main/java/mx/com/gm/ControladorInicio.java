package mx.com.gm;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Value("${index.saludo}")
    private String saludo;

    @GetMapping("/")
    public String inicio(Model model) {
        var mensaje = "HolaMundo con thymeleaf";
        var persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setEmail("juanp@gamil.com");
        persona.setTelefono("1231547");

        var persona2 = new Persona();
        persona2.setNombre("Karla");
        persona2.setApellido("Patricio");
        persona2.setEmail("karpa@gamil.com");
        persona2.setTelefono("1231547");

//        var personas = new ArrayList();
//        personas.add(persona);
//        personas.add(persona2);

//        Puedo usar otra anotacion parala list
        var personas = Arrays.asList(persona, persona2);

//var personas = new ArrayList();
        

        log.info("Ejecutando el controlador de tipo Controlador MVC");
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
//        model.addAttribute("persona", persona);
        model.addAttribute("personas", personas);
        return "index";

    }
}
