// EventForm.js - A component for adding a new event

import React from 'react';
import styles from '../dashboard/styles/dashboardStyles';

const EventForm = ({ newEvent, handleInputChange, handleAddEvent, handleCancelAddEvent }) => {
  return (
    <form onSubmit={handleAddEvent} style={{ marginBottom: '20px' }}>
      <div style={{ marginBottom: '10px' }}>
        <label>Name: </label>
        <input
          type="text"
          name="name"
          value={newEvent.name}
          onChange={handleInputChange}
          required
        />
      </div>
      <div style={{ marginBottom: '10px' }}>
        <label>Location: </label>
        <input
          type="text"
          name="location"
          value={newEvent.location}
          onChange={handleInputChange}
          required
        />
      </div>
      <div style={{ marginBottom: '10px' }}>
        <label>Date: </label>
        <input
          type="date"
          name="date"
          value={newEvent.date}
          onChange={handleInputChange}
          required
        />
      </div>
      <div style={{ marginBottom: '10px' }}>
        <label>Capacity: </label>
        <input
          type="number"
          name="capacity"
          value={newEvent.capacity}
          onChange={handleInputChange}
          required
        />
      </div>
      <div style={{ marginBottom: '10px' }}>
        <label>Price: </label>
        <input
          type="number"
          name="price"
          value={newEvent.price}
          onChange={handleInputChange}
          required
        />
      </div>
      <div>
        <button type="submit" style={styles.button}>Add Event</button>
        <button type="button" onClick={handleCancelAddEvent} style={{ ...styles.button, ...styles.buttonRed }}>
          Cancel
        </button>
      </div>
    </form>
  );
};

export default EventForm;
