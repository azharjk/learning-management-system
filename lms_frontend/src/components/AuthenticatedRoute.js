import { Redirect, Route, useLocation } from "react-router-dom";

export default function AuthenticatedRoute({ children, ...rest }) {
  const location = useLocation();
  let isAuthenticated = false;

  try {
    isAuthenticated = location.state.isAuthenticated;
  }
  catch {
    // pass
  }

  return (
    <Route {...rest} render={() => (isAuthenticated ? ( children ) : (<Redirect to={{ pathname: '/login' }} />)) }>
    </Route>
  );
}
