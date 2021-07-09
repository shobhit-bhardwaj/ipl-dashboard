import './MatchInfo.scss';

import React from 'react';
import { Link } from 'react-router-dom';

export const MatchInfo = ({ match, teamName }) => {
	const isMatchWinner = teamName === match.winner;
	const anotherTeamName = match.team1 === teamName ? match.team2 : match.team1;
	const teamRoute = `/teams/${anotherTeamName}`;

	return (
		<div className={isMatchWinner ? 'MatchInfo won-card' : 'MatchInfo lost-card'}>
			<div>
				<h3>vs <Link to={teamRoute}>{anotherTeamName}</Link></h3>
				<h4 className="match-result">Winner {match.winner}</h4>
			</div>
		</div>
	);
}
