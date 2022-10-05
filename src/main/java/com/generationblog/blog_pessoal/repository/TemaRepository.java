package com.generationblog.blog_pessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generationblog.blog_pessoal.model.Tema;

public interface TemaRepository  extends JpaRepository<Tema, Long> {
	
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);

}
