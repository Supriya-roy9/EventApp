import Login from "./Login"
import { render, screen } from "@testing-library/react";

describe("Basic rendering", () => {
    it("should render login component", () => {
      render(
            <Login />
        );
      const userNameField = screen.getByLabelText(/username/i);
      expect(userNameField).toBeInTheDocument();
  
      const passwordField = screen.getByLabelText(/password/i);
      expect(passwordField).toBeInTheDocument();
    });
  
    it("Should have empty fields in the inputs initially", () => {
      render(<Login />);
  
      const userNameField = screen.getByLabelText(/username/i);
      expect(userNameField).toHaveValue("");
  
      const passwordField = screen.getByLabelText(/password/i);
      expect(passwordField).toHaveValue("");
    });
  });
  