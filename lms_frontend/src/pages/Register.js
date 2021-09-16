import { Link } from 'react-router-dom';

import './Register.css';

export default function Register() {
  return (
    <div className="reg-centered">
      <div className="reg-container reg-border-curve">
        <form>
          <div className="reg-input-field reg-spacing">
            <label htmlFor="name">Name</label>
            <input type="text" name="name" placeholder="Enter your full name" />
          </div>
          <div className="reg-input-field reg-spacing">
            <label htmlFor="email">Email</label>
            <input type="email" name="email" placeholder="Enter your email" />
          </div>
          <div className="reg-input-field reg-spacing">
            <label htmlFor="password">Password</label>
            <input type="password" name="password" placeholder="Enter your password" />
          </div>
          <input className="reg-submit-field" type="submit" value="Create Account" />
          <p>Already have an account? <Link to="/login">here</Link></p>
        </form>
      </div>
    </div>
  );
}
