import { render } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';

import Register from './Register';

let wrapper;

beforeEach(() => {
  wrapper = render(
    <MemoryRouter>
      <Register />
    </MemoryRouter>
  );
});

test('renders each field properly', () => {
  const { getByPlaceholderText, container } = wrapper;

  const createAccountButton = container.querySelector('input[type="submit"]');

  expect(getByPlaceholderText('Enter your full name')).toBeTruthy();
  expect(getByPlaceholderText('Enter your email')).toBeTruthy();
  expect(getByPlaceholderText('Enter your password')).toBeTruthy();
  expect(createAccountButton).toBeTruthy();
});

test('should have link to register', () => {
  const { getByText } = wrapper;

  expect(getByText('Already have an account?')).toBeTruthy();
});
