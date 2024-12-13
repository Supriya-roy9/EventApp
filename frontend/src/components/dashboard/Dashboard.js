import React, { useState, useEffect } from 'react';
import styles from './styles/dashboardStyles';
import { fetchEvents, deleteEvent, addEvent } from '../../utils/api';
import EventCard from '../events/EventCard';
import EventForm from '../events/EventForm';

const API_URL = "https://your-backend-api.com/api/events";

function Dashboard() {
  const [events, setEvents] = useState([]);
  const [ticketCounts, setTicketCounts] = useState({});
  const [showAddEventForm, setShowAddEventForm] = useState(false);
  const [newEvent, setNewEvent] = useState({
    name: '',
    location: '',
    date: '',
    capacity: '',
    price: '',
  });

  useEffect(() => {
    fetchEventsData();
  }, []);

  const fetchEventsData = async () => {
    try {
      const data = await fetchEvents(API_URL);
      setEvents(data);
      setTicketCounts(
        data.reduce((acc, event) => {
          acc[event.id] = 0; // Initialize ticket counts
          return acc;
        }, {})
      );
    } catch (error) {
      console.error('Error fetching events:', error);
    }
  };

  const handleTicketCountChange = (id, increment) => {
    setTicketCounts((prev) => {
      const newCount = prev[id] + increment;
      return { ...prev, [id]: Math.max(0, newCount) }; // Ensure count doesn't go below 0
    });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewEvent((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleAddEvent = async (e) => {
    e.preventDefault();
    try {
      const addedEvent = await addEvent(newEvent, API_URL);
      setEvents((prevEvents) => [...prevEvents, addedEvent]);
      setShowAddEventForm(false);
      setNewEvent({ name: '', location: '', date: '', capacity: '', price: '' });
    } catch (error) {
      console.error('Error adding event:', error);
    }
  };

  const handleDeleteEvent = async (id) => {
    try {
      await deleteEvent(id, API_URL);
      setEvents(events.filter((event) => event.id !== id));
    } catch (error) {
      console.error('Error deleting event:', error);
    }
  };

  return (
    <div style={styles.container}>
      <h1 style={styles.header}>Movie Events Dashboard</h1>

      <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '20px' }}>
        <button onClick={() => setShowAddEventForm(true)} style={styles.button}>Add Event</button>
        <button onClick={() => alert('Logging out...')} style={{ ...styles.button, ...styles.buttonLogout }}>Logout</button>
      </div>

      {showAddEventForm && (
        <EventForm
          newEvent={newEvent}
          handleInputChange={handleInputChange}
          handleAddEvent={handleAddEvent}
          handleCancelAddEvent={() => setShowAddEventForm(false)}
        />
      )}

      <div style={styles.eventsContainer}>
        {events.map((event) => (
          <EventCard
            key={event.id}
            event={event}
            ticketCounts={ticketCounts}
            handleTicketCountChange={handleTicketCountChange}
            handleDeleteEvent={handleDeleteEvent}
          />
        ))}
      </div>
    </div>
  );
}

export default Dashboard;

