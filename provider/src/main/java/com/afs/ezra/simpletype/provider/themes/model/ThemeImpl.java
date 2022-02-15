package com.afs.ezra.simpletype.provider.themes.model;

import java.awt.Color;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "THEME")
@Getter
@Setter
@NoArgsConstructor
public class ThemeImpl implements Theme{
	
	public ThemeImpl(Theme theme) {
		BeanUtils.copyProperties(theme, this);
	}
	
	public static ThemeImpl convert(Theme theme) {
		if(theme == null) {
			return null;
		}
		if(theme instanceof ThemeImpl) {
			return (ThemeImpl) theme;
		}
		return new ThemeImpl(theme);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private Color backgroundMain;
	@Column(nullable = false)
	private Color backgroundSecondary;
	@Column(nullable = false)
	private Color accent;
	@Column(nullable = false)
	private Color accentLight;
	@Column(nullable = false)
	private Color neutral;
	@Column(nullable = false)
	private Color notTyped;
	@Column(nullable = false)
	private Color error;
	@Column(nullable = false)
	private Color correct;
}
