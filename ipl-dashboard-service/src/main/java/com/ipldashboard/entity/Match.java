package com.ipldashboard.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Match {
	@Id
	private long id;

	private String season;
	private String city;
	private LocalDate date;
	private String team1;
	private String team2;
	private String tossWinner;
	private String tossDecision;
	private String result;
	private String winner;
	private int winByRuns;
	private int winByWickets;
	private String playerOfMatch;
	private String venue;
	private String umpire1;
	private String umpire2;
}