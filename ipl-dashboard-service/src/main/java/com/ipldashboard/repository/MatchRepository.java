package com.ipldashboard.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipldashboard.entity.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
	public List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

	@Query ("select m from Match m where (m.team1=:teamName or m.team2=:teamName) and season=:year order by date desc")
	public List<Match> findTeamMatchesBetweenDates(String teamName, String year);
}