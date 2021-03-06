package com.afs.ezra.simpletype.provider.leaderboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity
@Table(name="LEADERBOARD")
@Getter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardPlaceImpl implements LeaderboardPlace{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Double speed;
	
	@Column(nullable = false)
	private Double accuracy;

}
