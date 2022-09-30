package com.generationblog.blog_pessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generationblog.blog_pessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> { 

	
}
