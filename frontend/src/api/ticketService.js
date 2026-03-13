import api from './axios';

export const ticketService = {
  getAll: async () => {
    const response = await api.get('/tickets');
    return response.data;
  },
  getById: async (id) => {
    const response = await api.get(`/tickets/${id}`);
    return response.data;
  },
  create: async (data) => {
    const response = await api.post('/tickets', data);
    return response.data;
  },
  assign: async (id, technicianId) => {
    const response = await api.put(`/tickets/${id}/assign`, null, { params: { technicianId } });
    return response.data;
  },
  updateStatus: async (id, data) => {
    const response = await api.put(`/tickets/${id}/status`, data);
    return response.data;
  },
  delete: async (id) => {
    const response = await api.delete(`/tickets/${id}`);
    return response.data;
  },
  addComment: async (id, data) => {
    const response = await api.post(`/tickets/${id}/comments`, data);
    return response.data;
  },
  updateComment: async (id, commentId, data) => {
    const response = await api.put(`/tickets/${id}/comments/${commentId}`, data);
    return response.data;
  },
  deleteComment: async (id, commentId) => {
    const response = await api.delete(`/tickets/${id}/comments/${commentId}`);
    return response.data;
  },
  uploadAttachments: async (id, files) => {
    const formData = new FormData();
    files.forEach(file => formData.append('files', file));
    const response = await api.post(`/tickets/${id}/attachments`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    });
    return response.data;
  }
};
