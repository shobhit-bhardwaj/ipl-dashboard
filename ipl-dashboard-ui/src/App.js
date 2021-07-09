import './App.scss';

//import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { HashRouter as Router, Route, Switch } from 'react-router-dom';
import { MatchPage } from './pages/MatchPage';
import { TeamPage } from './pages/TeamPage';
import { HomePage } from './pages/HomePage';


function App() {
	return (
		<div className="App">
			<Router>
				<Switch>
					<Route path="/teams/:teamName/matches/:year">
						<MatchPage />
					</Route>
					<Route path="/teams/:teamName">
						<TeamPage />
					</Route>
					<Route path="/">
						<HomePage />
					</Route>
				</Switch>
			</Router>
		</div>
	);
}

export default App;
