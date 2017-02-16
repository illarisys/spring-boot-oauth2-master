package lcb.com.pe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcb.com.pe.domain.Usuario;
import lcb.com.pe.repository.UserRepository;

@Service("usuarioService")
public class UsuarioService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean getCrudByUsuario(String username, String tabla){		
		Usuario u = userRepository.findByUsernameCaseInsensitive(username);
		return true;
	}

}
