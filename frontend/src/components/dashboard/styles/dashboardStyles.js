// dashboardStyles.js - Styles for the dashboard components

const styles = {
  container: {
    padding: '20px',
  },
  header: {
    fontSize: '24px',
    marginBottom: '20px',
  },
  button: {
    padding: '10px 20px',
    cursor: 'pointer',
    border: 'none',
    backgroundColor: '#4CAF50',
    color: 'white',
    borderRadius: '5px',
  },
  buttonRed: {
    backgroundColor: '#f44336',
  },
  buttonLogout: {
    backgroundColor: '#2196F3',
  },
  eventsContainer: {
    display: 'flex',
    flexWrap: 'wrap',
    gap: '20px',
  },
  eventCard: {
    border: '1px solid #ddd',
    borderRadius: '5px',
    padding: '15px',
    width: '250px',
  },
  eventTitle: {
    fontSize: '20px',
    fontWeight: 'bold',
  },
  eventDate: {
    fontSize: '16px',
  },
  eventLocation: {
    fontSize: '14px',
  },
  ticketControls: {
    display: 'flex',
    alignItems: 'center',
    gap: '10px',
    marginTop: '10px',
  },
  ticketButton: {
    padding: '5px 10px',
    fontSize: '16px',
  },
  ticketCount: {
    fontSize: '18px',
  },
  priceDisplay: {
    marginTop: '10px',
    fontSize: '16px',
  },
};

export default styles;
