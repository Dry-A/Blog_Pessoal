package com.generationblog.blog_pessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;
import javax.validation.constraints.Null;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.generationblog.blog_pessoal.model.Usuario;
import com.generationblog.blog_pessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	// instancio a interface de usuario

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		// primeiro usuario é o ususario da model, o segundo é objeto
		/*
		 * só reforçando conceitos: Objeto da classe é a classe instanciada com todos os
		 * atributos passados, ou seja, o usuário com seu email, senha, nome etc...
		 */

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuário já existe", null);

		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			if (buscaUsuario.isPresent()) {
				if (buscaUsuario.get().getId() != usuario.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario já existe", null);

			}
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			return Optional.of(usuarioRepository.save(usuario));

		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado", null);

	}

	private String criptografarSenha(String senha) {// metodo de criptografas senha
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);// senhaecnoder - fica a senha criptografada
		// encode(senha) - essa senha vai ser criptograda pelo encoder
		return senhaEncoder;
	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {// comparar senha digitada e senha
																				// criptografada
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	private String gerarBasicToken(String email, String password) {// gerar token
		String estrutura = email + ":" + password;
		byte[] estruturaBase64 = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(estruturaBase64);
	}
}
