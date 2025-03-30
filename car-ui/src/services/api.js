import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: API_BASE_URL,
});

export const carService = {
  searchCars: async (searchParams) => {
    const params = Object.entries(searchParams).reduce((acc, [key, value]) => {
      if (value === '' || value === null || value === undefined) {
        return acc;
      }

      acc[key] = value;
      return acc;
    }, {});
    
    const response = await api.get('/cars', { params });
    return response.data;
  },

  getCarById: async (id) => {
    const response = await api.get(`/cars/${id}`);
    return response.data;
  },

  getAllMakes: async () => {
    const response = await api.get('/makes');
    return response.data;
  },

  getModelsForMake: async (make) => {
    const response = await api.get('/models', { params: { make } });
    return response.data;
  },
}; 