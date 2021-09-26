import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import AuthenticatedRoute from './components/AuthenticatedRoute';

function App() {
  return (
    <Router>
      <div>
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/register">
            <Register />
          </Route>
          <AuthenticatedRoute path="/">
            <Dashboard />
          </AuthenticatedRoute>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
