import { Button, Card, TextField } from "@mui/material";
import axios from "axios";
import { Field } from "formik";
import { Form, Formik } from "formik";
import React from "react";

function Login() {
  
  const handleLoginSubmit = async (values) => {
    try {
      const credentials = {
        username: values.username,
        password: values.password,
      };

      const response = await axios.post(
        "http://localhost:8081/login",
        credentials,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      const token = response.data.jwtToken;
      if (token) {
        localStorage.setItem("authToken", token);
        window.location.href = "/dashboard"; 
      } else {
        alert("Login failed. Please try again.");
      }
    } catch (error) {
      console.error("Login error:", error.response?.data || error.message);
      alert("Invalid username or password");
    }
  };

  return (
    <div className="loginContainer">
      <Card className="loginCard">
        <Formik initialValues={{ username: "", password: "" }} onSubmit={handleLoginSubmit}>
          <Form className="loginForm">
            <Field
              as={TextField}
              sx={{ padding: "8px", margin: "5px" }}
              required
              type="text"
              label="Username"
              name="username"
              id="username" // Add an id for accessibility
            />
            <Field
              as={TextField}
              sx={{ padding: "10px", margin: "25px" }}
              required
              type="password"
              label="Password"
              name="password"
              id="password" // Add an id for accessibility
            />
            <Button className="loginButton" variant="contained" type="submit">
              LOGIN
            </Button>
            <a className="signUpAnchor" href="/signup">
              Signup
            </a>
          </Form>
        </Formik>
      </Card>
    </div>
  );
}

export default Login;
