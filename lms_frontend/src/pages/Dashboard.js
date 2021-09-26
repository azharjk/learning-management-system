// TODO: create dashboard ui and test
import { useLocation } from 'react-router-dom';

export default function Dashboard() {
  const location = useLocation();
  let user;

  try {
    user = location.state.user;
  }
  catch {
    // pass
  }

  return (
    <div>
      <h1>Fullname: { user.fullname } </h1>
      <p>Email: { user.email } </p>
    </div>
  );
}
