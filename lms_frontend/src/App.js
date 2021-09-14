import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import Login from './pages/Login';

function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/">
            <h1>/ page</h1>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
