package com.ipldashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipldashboard.entity.Match;
import com.ipldashboard.entity.Team;
import com.ipldashboard.service.TeamService;

@CrossOrigin
@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping("/all")
	public Iterable<Team> findAllTeams() {
		return teamService.findAllTeams();
	}

	@GetMapping("/{teamName}")
	public Team findTeam(@PathVariable String teamName) {
		return teamService.findTeamInfo(teamName);
	}

	@GetMapping("/{teamName}/matches")
	public List<Match> findTeamMatchesBetweenDates(@PathVariable String teamName, @RequestParam String year) {
		return teamService.findTeamMatchesBetweenDates(teamName, year);
	}
}