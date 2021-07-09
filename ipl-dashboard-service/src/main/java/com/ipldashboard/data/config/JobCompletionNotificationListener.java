package com.ipldashboard.data.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ipldashboard.entity.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private EntityManager entityManager;

	@Autowired
	public JobCompletionNotificationListener(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

			entityManager.createQuery("SELECT m.team1, m.team2 FROM Match m", Object[].class)
				.getResultList()
				.forEach(data -> System.out.println(((String) data[0]) + " - " + ((String) data[0]) + " Inserted in the DB."));

			Map<String, Team> teamMap = new HashMap<>();

			entityManager.createQuery("SELECT m.team1, count(*) FROM Match m GROUP BY m.team1", Object[].class)
				.getResultList()
				.stream()
				.map(data -> new Team((String) data[0], (long) data[1]))
				.forEach(team -> teamMap.put(team.getTeamName(), team));

			entityManager.createQuery("SELECT m.team2, count(*) FROM Match m GROUP BY m.team2", Object[].class)
				.getResultList()
				.forEach(data -> {
					Team team = teamMap.get(((String) data[0]));
					team.setTotalMatches(team.getTotalMatches() + ((long) data[1]));
				});

			entityManager.createQuery("SELECT m.winner, count(*) FROM Match m GROUP BY m.winner", Object[].class)
				.getResultList()
				.forEach(data -> {
					Team team = teamMap.get(((String) data[0]));
					if(team != null)
						team.setTotalWinnings(((long) data[1]));
				});

			teamMap.values()
				.stream()
				.forEach(team -> {
					System.out.println(team);
					entityManager.persist(team);
				});
		}
	}
}