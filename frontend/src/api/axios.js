import axios from 'axios';

const api = axios.create({
  // This uses the URL from your .env file
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
  withCredentials: true, // IMPORTANT: Allows cookies to be sent
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;