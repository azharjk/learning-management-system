import { render } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';

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
