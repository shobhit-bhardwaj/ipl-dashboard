import './TeamTile.scss';

import { Link } from 'react-router-dom';

import React from 'react';

export const TeamTile = ({ teamName }) => {

	const teamLink = `/teams/${teamName}`;

	return (
		<div className="TeamTile">
			<div>
				<Link to={teamLink}><h3>{teamName}</h3></Link>
			</div>
		</div>
	);
}
