package com.ipldashboard.data.processor;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.ipldashboard.data.model.MatchInput;
import com.ipldashboard.entity.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	@Override
	public Match process(MatchInput matchInput) throws Exception {
		Match match = new Match();
		match.setId(Long.parseLong(matchInput.getId()));
		match.setSeason(matchInput.getSeason());
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setTeam1(matchInput.getTeam1());
		match.setTeam2(matchInput.getTeam2());
		match.setTossWinner(matchInput.getToss_winner());
		match.setTossDecision(matchInput.getToss_decision());
		match.setResult(matchInput.getResult());
		match.setWinner(matchInput.getWinner());
		match.setWinByRuns(Integer.parseInt(matchInput.getWin_by_runs()));
		match.setWinByWickets(Integer.parseInt(matchInput.getWin_by_wickets()));
		match.setPlayerOfMatch(matchInput.getPlayer_of_match());
		match.setVenue(matchInput.getVenue());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());

		return match;
	}
}