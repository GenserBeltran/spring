package mx.com.gm.dao;

import mx.com.gm.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    
    Usuario finByUsername(String username); //Filtra un objeto filtrado por el username
}
