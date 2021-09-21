import { useState } from 'react';
import { Link } from 'react-router-dom';

import './Login.css';
import dummyLogoImage from '../logo-dummy.png';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [formError, setFormError] = useState(false);

  const handleSubmitForm = (e) => {
    e.preventDefault();
    if (!(email && password)) {
      setFormError(true);
      return;
    }
  }

  return (
    <div className="login-centered">
      <div className="login-container login-border-curve">
        <div className="login-lhs login-content-sized">
          <h3>Learning Management System</h3>
          <p>View full source code on <a href="https://github.com/azharjk/learning-management-system">Github</a></p>
          <div className="login-lhs-logo">
            <img src={dummyLogoImage} alt="Logo" />
          </div>
        </div>
        <div className="login-rhs login-content-sized">
          <form onSubmit={handleSubmitForm}>
            <div className="login-rhs-input-field login-rhs-input-spacing">
              <label htmlFor="email">Email</label>
              <input onChange={(e) => setEmail(e.target.value)} className="login-border-curve" type="email" name="email" placeholder="Enter your email" />
            </div>
            <div className="login-rhs-input-field login-rhs-input-spacing">
              <label htmlFor="password">Password</label>
              <input onChange={(e) => setPassword(e.target.value)} className="login-border-curve" type="password" name="password" placeholder="Enter your password" />
            </div>
            <input className="login-rhs-submit-field" type="submit" value="Log in" />
          </form>
          { formError && <p className="login-form-error">There' s some form error, please check your email or password</p> }
          <p>Does not have account? <Link to="/register">here</Link></p>
        </div>
      </div>
    </div>
  );
}
