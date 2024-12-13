// TicketControls.js - A separate component to handle ticket count logic

import React from 'react';
import styles from '../dashboard/styles/dashboardStyles';

const TicketControls = ({ event, ticketCounts, handleTicketCountChange }) => {
  return (
    <div style={styles.ticketControls}>
      <button
        onClick={() => handleTicketCountChange(event.id, -1)}
        style={styles.ticketButton}
      >
        -
      </button>
      <span style={styles.ticketCount}>{ticketCounts[event.id]}</span>
      <button
        onClick={() => handleTicketCountChange(event.id, 1)}
        style={styles.ticketButton}
      >
        +
      </button>
    </div>
  );
};

export default TicketControls;
