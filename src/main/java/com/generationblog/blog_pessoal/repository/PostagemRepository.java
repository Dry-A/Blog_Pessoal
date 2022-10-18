package com.generationblog.blog_pessoal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.generationblog.blog_pessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> { 

	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("título") String titulo );

}
