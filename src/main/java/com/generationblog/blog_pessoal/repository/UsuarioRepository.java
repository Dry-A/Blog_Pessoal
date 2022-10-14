package com.generationblog.blog_pessoal.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generationblog.blog_pessoal.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	// dentro de jparepository vai o usuario da classe model e o tipo do id
	//extends = usa e cria os metodos do jpa
		//o jpa traz os metodos padrao como grt, pos, put e delete
		
	public Optional<Usuario> findByUsuario(String usuario);//findbyUsuariocriou agora
	//variavel usuario email em model(string é o tipo 
	//findBy já é predeterminado
	//Find = SELECT/ By = Where / Usuario = coluna usuário da tabela

}

