import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from '../login/Login';
import Signup from '../signup/Signup';
import Dashboard from '../dashboard/Dashboard';
import ProtectedRoute from './ProtectedRoute'; // Import the ProtectedRoute component

const RootRouter = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/login" element={<Login />} />
        <Route exact path="/signup" element={<Signup />} />
        <Route
          exact
          path="/dashboard"
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
};

export default RootRouter;
