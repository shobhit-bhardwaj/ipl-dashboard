import './TeamPage.scss';

import { React, useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { PieChart } from 'react-minimal-pie-chart';

import { MatchDetails } from '../components/MatchDetails'
import { MatchInfo } from '../components/MatchInfo'

export const TeamPage = () => {

  const [team, setTeam] = useState({matches: []});
  const { teamName } = useParams();

  useEffect(
    () => {
      const fetchTeams = async() => {

        const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}`);
        const data = await response.json();
        setTeam(data);
      };

      fetchTeams();
    }, [teamName]
  );

  const moreLink = `/teams/${teamName}/matches/2017`;

  if(!team || !team.teamName) {
    return <h3>Team Not Found</h3>;
  }

  return (
	<div className="TeamPage">
		<div className="team-name-section">
			<h1 className="team-name">{team.teamName}</h1>
		</div>
		<div className="win-loss-section">
			Win / Loss
			<PieChart
				data={[
					{title: team.totalMatches-team.totalWinnings, value: team.totalMatches-team.totalWinnings, color: '#e15454'},
					{title: team.totalWinnings, value: team.totalWinnings, color: '#54e1a3'}
				]}
			/>
		</div>
		<div className="match-detail-section">
			<p>Latest Matches</p>
			<MatchDetails match={team.matches[0]} teamName={team.teamName} />
		</div>
		{team.matches.slice(1).map(match => <MatchInfo key={match.id} match={match} teamName={team.teamName} />)}
		<div className="more-link">
			<Link to={moreLink}>More</Link>
		</div>
	</div>
  );
}
