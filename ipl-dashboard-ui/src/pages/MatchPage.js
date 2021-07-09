import './MatchPage.scss';

import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom'

import { YearSelector } from '../components/YearSelector'
import { MatchDetails } from '../components/MatchDetails'

export const MatchPage = () => {

  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();

  useEffect(
    () => {
      const fetchMatches = async() => {

        const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}/matches?year=${year}`);
        const data = await response.json();
        setMatches(data);
      };

      fetchMatches();
    }, [teamName, year]
  );

  return (
    <div className="MatchPage">
		<div className="year-selector">
			<YearSelector teamName={teamName} />
		</div>
		<div>
	      <h2 className="match-page-header">{teamName} Matches in {year}</h2>
	      {
	        matches.map(match => <MatchDetails key={match.id} match={match} teamName={teamName} />)
	      }
		</div>
    </div>
  );
}
