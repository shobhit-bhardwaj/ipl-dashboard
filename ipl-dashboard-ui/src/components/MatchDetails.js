import './MatchDetails.scss';

import React from 'react';
import { Link } from 'react-router-dom';

export const MatchDetails = ({ match, teamName }) => {
  if(!match)
    return null;

  const isMatchWinner = teamName === match.winner;
  const anotherTeamName = match.team1 === teamName ? match.team2 : match.team1;
  const teamRoute = `/teams/${anotherTeamName}`;

  return (
	<div className={isMatchWinner ? 'MatchDetails won-card' : 'MatchDetails lost-card'}>
		<div>
			<h2>vs <Link to={teamRoute}>{anotherTeamName}</Link></h2>
			<h3 className="match-date">Date {match.date}</h3>
			<h3 className="match-venue">Venue {match.venue}, {match.city}</h3>
			<h3 className="match-result">Winner {match.winner}</h3>
		</div>
		<div className="additional-details">
			<h3>Toss Winner</h3>
			<p>{match.tossWinner}</p>
			<h3>Umpires</h3>
			<p>{match.umpire1}, {match.umpire2}</p>
			<h3>Man of the Match</h3>
			<p>{match.playerOfMatch}</p>
		</div>
	</div>
  );
}
