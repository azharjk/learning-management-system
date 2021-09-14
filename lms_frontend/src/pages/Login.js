import './Login.css';
import dummyLogoImage from '../logo-dummy.png';

export default function Login() {
  return (
    <div className="login-centered">
      <div className="login-container login-border-curve">
        <div className="login-lhs login-content-sized">
          <h3>Learning Management System</h3>
          <p>View full source code on <a href="https://github.com">Github</a></p>
          <div className="login-lhs-logo">
            <img src={dummyLogoImage} alt="Logo" />
          </div>
        </div>
        <div className="login-rhs login-content-sized">
          <form>
            <div className="login-rhs-input-field login-rhs-input-spacing">
              <label htmlFor="email">Email</label>
              <input className="login-border-curve" type="text" name="email" placeholder="Enter your email" />
            </div>
            <div className="login-rhs-input-field login-rhs-input-spacing">
              <label htmlFor="password">Password</label>
              <input className="login-border-curve" type="password" name="password" placeholder="Enter your password" />
            </div>
            <input className="login-rhs-submit-field" type="submit" value="Log in" />
          </form>
          <p>Does not have account? <a href="#">here</a></p>
        </div>
      </div>
    </div>
  );
}
