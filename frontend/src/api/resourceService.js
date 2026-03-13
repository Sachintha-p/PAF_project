import api from './axios';

export const resourceService = {
  getAll: async (params) => {
    const response = await api.get('/resources', { params });
    return response.data;
  },
  getById: async (id) => {
    const response = await api.get(`/resources/${id}`);
    return response.data;
  },
  create: async (data) => {
    const response = await api.post('/resources', data);
    return response.data;
  },
  update: async (id, data) => {
    const response = await api.put(`/resources/${id}`, data);
    return response.data;
  },
  delete: async (id) => {
    const response = await api.delete(`/resources/${id}`);
    return response.data;
  }
};
