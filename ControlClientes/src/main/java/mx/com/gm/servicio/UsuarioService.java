package mx.com.gm.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.UsuarioDao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.finByUsername(username);
        
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol : usuario.getRoles()) { //Get roles se crear automatico con la anotacion @data recordar que para cada atributo genera en automatico los  set y get
            roles.add(new SimpleGrantedAuthority(rol.getNombre())); //ESTA CLASE SIMPLE REQUIERE DEL rol qeu se esta recuperando y asi con cada uno de los roles asignados al usuario
        }
        
        return new User(usuario.getUsername(), usuario.getPassword(), roles); //Se recuperael nombre, el password y los roles
    }
    
}
