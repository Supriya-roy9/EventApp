import { render, screen } from "@testing-library/react";
import Signup from "./Signup";


describe("Basic rendering", () => {
  it("should render signUp component", () => {
    render(
       
          <Signup />
        
        
      );
  

    const userNameField = screen.getByLabelText(/username/i);
    expect(userNameField).toBeInTheDocument();

    const emailField = screen.getByLabelText(/Email/i);
    expect(emailField).toBeInTheDocument();

    const phoneField = screen.getByLabelText(/Phone/i);
    expect(phoneField).toBeInTheDocument();

    const passwordField = screen.getByLabelText(/password/i);
    expect(passwordField).toBeInTheDocument();
  });

  it("Should have empty fields in the inputs initially", () => {
    render(<Signup />);

    const userNameField = screen.getByLabelText(/username/i);
    expect(userNameField).toHaveValue("");

    const email = screen.getByLabelText(/email/i);
    expect(email).toHaveValue("");

    const phone = screen.getByLabelText(/phone/i);
    expect(phone).toHaveValue("");

    const passwordField = screen.getByLabelText(/password/i);
    expect(passwordField).toHaveValue("");
  });
});
