// api.js - A separate module to handle API requests related to events

export const fetchEvents = async (API_URL) => {
    try {
      const response = await fetch(API_URL);
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error fetching events:', error);
      throw error;
    }
  };
  
  export const deleteEvent = async (id, API_URL) => {
    try {
      await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    } catch (error) {
      console.error('Error deleting event:', error);
      throw error;
    }
  };
  
  export const addEvent = async (newEvent, API_URL) => {
    try {
      const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newEvent),
      });
      return await response.json();
    } catch (error) {
      console.error('Error adding event:', error);
      throw error;
    }
  };
  