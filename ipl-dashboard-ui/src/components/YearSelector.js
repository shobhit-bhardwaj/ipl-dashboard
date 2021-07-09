import './YearSelector.scss';

import React from 'react';
import { Link } from 'react-router-dom';

export const YearSelector = ({ teamName }) => {

	const years = [2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010, 2009, 2008];

	return (
		<div className="YearSelector">
			<div>
				<h4 className="year-selector-header">Select Year</h4>
				<ol>
					{ years.map(year => (
						<Link key={year} to={`/teams/${teamName}/matches/${year}`}><li>{year}</li></Link>
					))}
				</ol>
			</div>
		</div>
	);
}
