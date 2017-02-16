package com.rd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rd.domain.Usuario;
import com.rd.repository.UserRepository;

@Service("usuarioService")
public class UsuarioService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean getCrudByUsuario(String username, String tabla){		
		Usuario u = userRepository.findByUsernameCaseInsensitive(username);
		return true;
	}

}
