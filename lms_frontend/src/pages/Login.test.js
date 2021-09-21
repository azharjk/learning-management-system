import { render } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import userEvent from '@testing-library/user-event';

import Login from './Login';

let wrapper;

beforeEach(() => {
  wrapper = render(
    <MemoryRouter>
      <Login />
    </MemoryRouter>
  );
});

test('renders each field properly', () => {
  const { getByPlaceholderText, container } = wrapper;

  const logInButton = container.querySelector('input[type=submit]');

  expect(getByPlaceholderText('Enter your email')).toBeTruthy();
  expect(getByPlaceholderText('Enter your password')).toBeTruthy();
  expect(logInButton).toBeTruthy();
});

test('should have link to register', () => {
  const { getByText } = wrapper;

  expect(getByText('Does not have account?')).toBeTruthy();
});

test('should give error msg if user made invalid input', () => {
  const { getByPlaceholderText, getByText, container } = wrapper;
  const emailField = getByPlaceholderText('Enter your email');
  const submitBtn = container.querySelector('input[type=submit]');

  userEvent.type(emailField, 'a@a');
  userEvent.click(submitBtn);

  expect(getByText('There\' s some form error, please check your email or password')).toBeTruthy();
});
