package com.ipldashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ipldashboard.entity.Match;
import com.ipldashboard.entity.Team;
import com.ipldashboard.repository.MatchRepository;
import com.ipldashboard.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MatchRepository matchRepository;
	
	public Iterable<Team> findAllTeams() {
		return teamRepository.findAll();
	}

	public Team findTeamInfo(String teamName) {
		Pageable pageable = PageRequest.of(0, 4);
		List<Match> matches = matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);

		Team team = teamRepository.findByTeamName(teamName);
		team.setMatches(matches);

		return team;
	}

	public List<Match> findTeamMatchesBetweenDates(String teamName, String year) {
		List<Match> matches = matchRepository.findTeamMatchesBetweenDates(teamName, year);
		return matches;
	}
}