package com.ipldashboard.data.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.ipldashboard.data.model.MatchInput;
import com.ipldashboard.data.processor.MatchDataProcessor;
import com.ipldashboard.entity.Match;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	private static final String[] FIELDS = { "id", "season", "city", "date", "team1", "team2", "toss_winner",
			"toss_decision", "result", "dl_applied", "winner", "win_by_runs", "win_by_wickets",
			"player_of_match", "venue", "umpire1", "umpire2", "umpire3" };

	@Bean
	public FlatFileItemReader<MatchInput> reader() {
	  return new FlatFileItemReaderBuilder<MatchInput>()
	    .name("matchDataReader")
	    .resource(new ClassPathResource("match-data.csv"))
	    .delimited()
	    .names(FIELDS)
	    .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {{
	      setTargetType(MatchInput.class);
	    }})
	    .build();
	}

	@Bean
	public MatchDataProcessor processor() {
	  return new MatchDataProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<Match>()
	    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO Match (id, season, city, date, team1, team2, toss_winner, toss_decision, result, winner, win_by_runs, win_by_wickets, player_of_match, venue, umpire1, umpire2) VALUES (:id, :season, :city, :date, :team1, :team2, :tossWinner, :tossDecision, :result, :winner, :winByRuns, :winByWickets, :playerOfMatch, :venue, :umpire1, :umpire2)")
	    .dataSource(dataSource)
	    .build();
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step) {
	  return jobBuilderFactory.get("importMatchDataJob")
	    .incrementer(new RunIdIncrementer())
	    .listener(listener)
	    .flow(step)
	    .end()
	    .build();
	}

	@Bean
	public Step step(JdbcBatchItemWriter<Match> writer) {
	  return stepBuilderFactory.get("step")
	    .<MatchInput, Match> chunk(10)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer)
	    .build();
	}
}