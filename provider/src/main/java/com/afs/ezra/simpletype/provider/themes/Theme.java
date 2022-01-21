package com.afs.ezra.simpletype.provider.themes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Theme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private Integer backgroundMain;
	@Column(nullable = false)
	private Integer backgroundSecondary;
	@Column(nullable = false)
	private Integer accent;
	@Column(nullable = false)
	private Integer accentLight;
	@Column(nullable = false)
	private Integer neutral;
	@Column(nullable = false)
	private Integer notTyped;
	@Column(nullable = false)
	private Integer error;
	@Column(nullable = false)
	private Integer correct;

}
