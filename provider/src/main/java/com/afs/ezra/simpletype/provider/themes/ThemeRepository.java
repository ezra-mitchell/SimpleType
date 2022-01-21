package com.afs.ezra.simpletype.provider.themes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
	
	public Theme findThemeByName(String name);
	
	public List<Theme> findAll();
	
	public void deleteThemeByName(String name);

}
