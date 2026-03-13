import api from './axios';

export const authService = {
  getCurrentUser: async () => {
    const response = await api.get('/users/me');
    return response.data;
  },
  getAllUsers: async () => {
    const response = await api.get('/users');
    return response.data;
  },
  changeRole: async (id, role) => {
    const response = await api.put(`/users/${id}/role`, null, { params: { role } });
    return response.data;
  }
};
