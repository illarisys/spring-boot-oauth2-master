package lcb.com.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lcb.com.pe.service.UsuarioService;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

	@Autowired
	UsuarioService usuarioService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {    	
        return "Hello User! ";
    }

}
