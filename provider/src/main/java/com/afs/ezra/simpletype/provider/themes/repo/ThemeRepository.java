package com.afs.ezra.simpletype.provider.themes.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afs.ezra.simpletype.provider.themes.model.ThemeImpl;

public interface ThemeRepository extends JpaRepository<ThemeImpl, Long> {
	
	public Optional<ThemeImpl> findThemeByName(String name);
	
	public List<ThemeImpl> findAll();
	
	public void deleteThemeByName(String name);

}
