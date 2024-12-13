// EventCard.js - A component to display an individual event

import React from 'react';
import TicketControls from './TicketControls';
import styles from '../dashboard/styles/dashboardStyles';

const EventCard = ({ event, ticketCounts, handleTicketCountChange, handleDeleteEvent }) => {
  return (
    <div key={event.id} style={styles.eventCard}>
      <h3 style={styles.eventTitle}>{event.name}</h3>
      <p style={styles.eventDate}>Date: {event.date}</p>
      <p style={styles.eventLocation}>Location: {event.location}</p>

      <TicketControls 
        event={event} 
        ticketCounts={ticketCounts} 
        handleTicketCountChange={handleTicketCountChange}
      />

      <div style={styles.priceDisplay}>
        Total Price: ${ticketCounts[event.id] * event.price}
      </div>

      {/* Delete Event Button (outside the event box) */}
      <button
        onClick={() => handleDeleteEvent(event.id)}
        style={{ ...styles.button, ...styles.buttonRed }}
      >
        Delete Event
      </button>
    </div>
  );
};

export default EventCard;
