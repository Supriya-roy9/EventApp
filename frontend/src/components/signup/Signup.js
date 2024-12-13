import {
    Button,
    Card,
    TextField,
    Typography,
    RadioGroup,
    FormControl,
    FormControlLabel,
    Radio,
  } from "@mui/material";
  import { Form, Formik } from "formik";
  import React from "react";
  import axios from "axios";
 
  
  function Signup() {
   
    const handleSignUpSubmit = async (values) => {
      try {
        const credentials = {
          username: values.username,
          name: values.name,
          email: values.email,
          phone: values.phone,
          gender: values.gender,
          password: values.password,
        };
  
        const response = await axios.post(
          "http://localhost:8081/customer/register",
          credentials,
          {
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
  
        alert("Signup successful!");
       
        window.location.href = "/login"; 
      } catch (error) {
        console.error("SignUp error:", error.response?.data || error.message);
        alert("Signup failed. Please try again.");
      }
    };
  
    return (
      <div className="signUpContainer">
        <Card className="signUpCard">
          <Formik
            initialValues={{
              username: "",
              name: "",
              email: "",
              phone: "",
              gender: "",
              password: "",
            }}
            onSubmit={handleSignUpSubmit}
          >
            {({ values, handleChange }) => (
              <Form className="signUpForm">
                <TextField
                  id="username"
                  sx={{ padding: "8px", margin: "5px" }}
                  required
                  type="text"
                  label="Username"
                  name="username"
                  value={values.username}
                  onChange={handleChange}
                />
  
                <TextField
                  id="name"
                  sx={{ padding: "8px", margin: "5px" }}
                  required
                  type="text"
                  label="Name"
                  name="name"
                  value={values.name}
                  onChange={handleChange}
                />
  
                <TextField
                  id="email"
                  sx={{ padding: "8px", margin: "5px" }}
                  required
                  type="email"
                  label="Email"
                  name="email"
                  value={values.email}
                  onChange={handleChange}
                />
  
                <TextField
                  id="phone"
                  sx={{ padding: "8px", margin: "5px" }}
                  required
                  type="text"
                  label="Phone"
                  name="phone"
                  value={values.phone}
                  onChange={handleChange}
                />
  
                <FormControl required sx={{ padding: "8px", margin: "5px" }}>
                  <Typography variant="body1">Gender</Typography>
                  <RadioGroup
                    className="gender"
                    name="gender"
                    row
                    value={values.gender}
                    onChange={handleChange}
                  >
                    <FormControlLabel
                      value="male"
                      control={<Radio />}
                      label="Male"
                    />
                    <FormControlLabel
                      value="female"
                      control={<Radio />}
                      label="Female"
                    />
                  </RadioGroup>
                </FormControl>
  
                <TextField
                  id="password"
                  sx={{ padding: "10px", margin: "25px" }}
                  required
                  type="password"
                  label="Password"
                  name="password"
                  value={values.password}
                  onChange={handleChange}
                />
  
                <Button className="signupbutton" variant="contained" type="submit">
                  SIGNUP
                </Button>
  
                <a className="loginAnchor" href="/login">
                  LOGIN
                </a>
              </Form>
            )}
          </Formik>
        </Card>
      </div>
    );
  }
  
  export default Signup;
  