package com.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipldashboard.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
	public Team findByTeamName(String teamName);
}