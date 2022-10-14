package com.generationblog.blog_pessoal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generationblog.blog_pessoal.model.Usuario;
import com.generationblog.blog_pessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
		return service.cadastrarUsuario(usuario).map(resp -> ResponseEntity.status
				(HttpStatus.CREATED).body(resp)).orElse(ResponseEntity.status
						(HttpStatus.BAD_REQUEST).build());
	}
}
