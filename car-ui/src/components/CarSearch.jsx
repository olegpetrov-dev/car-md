import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { carService } from '../services/api';
import defaultCarImage from '../assets/default-car.png';
import './CarSearch.css';

const CarSearch = () => {
  const navigate = useNavigate();
  const [makes, setMakes] = useState([]);
  const [models, setModels] = useState([]);
  const [searchParams, setSearchParams] = useState({
    make: '',
    model: '',
    minPrice: 0,
    maxPrice: 1000000,
    minYear: 1900,
    maxYear: new Date().getFullYear(),
    minMileage: 0,
    maxMileage: 500000,
    transmission: '',
    minEngine: '',
    maxEngine: '',
    drive: '',
    body: '',
    minPlaces: 1,
    maxPlaces: 10,
    page: 1,
    pageSize: 12,
    sortBy: '+price'
  });
  const [searchResults, setSearchResults] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadMakes();
  }, []);

  useEffect(() => {
    if (searchParams.make) {
      loadModels(searchParams.make);
    }
  }, [searchParams.make]);

  useEffect(() => {
    if (searchParams.page !== undefined) {
      handleSearch();
    }
  }, [searchParams.page]);

  const loadMakes = async () => {
    try {
      const makesData = await carService.getAllMakes();
      setMakes(makesData);
    } catch (error) {
      console.error('Error loading makes:', error);
    }
  };

  const loadModels = async (make) => {
    try {
      const modelsData = await carService.getModelsForMake(make);
      setModels(modelsData);
    } catch (error) {
      console.error('Error loading models:', error);
    }
  };

  const handleSearch = async () => {
    setLoading(true);
    try {
      const results = await carService.searchCars(searchParams);
      setSearchResults(results);
    } catch (error) {
      console.error('Error searching cars:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (field) => (event) => {
    setSearchParams((prev) => ({
      ...prev,
      [field]: event.target.value,
    }));
  };

  const handleNumberChange = (field) => (event) => {
    const value = event.target.value === '' ? '' : Number(event.target.value);
    setSearchParams((prev) => ({
      ...prev,
      [field]: value,
    }));
  };

  const handlePageChange = (event) => {
    setSearchParams((prev) => ({
      ...prev,
      page: parseInt(event.target.value),
    }));
  };

  const handleViewDetails = (carId) => {
    navigate(`/cars/${carId}`);
  };

  return (
    <div className="car-search">
      <div className="search-filters">
        <h2>Search Filters</h2>
        
        <div className="filter-group">
          <label htmlFor="make">Make</label>
          <select
            id="make"
            value={searchParams.make}
            onChange={handleInputChange('make')}
          >
            <option value="">Select Make</option>
            {makes.map((make) => (
              <option key={make} value={make}>
                {make}
              </option>
            ))}
          </select>
        </div>

        <div className="filter-group">
          <label htmlFor="model">Model</label>
          <select
            id="model"
            value={searchParams.model}
            onChange={handleInputChange('model')}
            disabled={!searchParams.make}
          >
            <option value="">Select Model</option>
            {models.map((model) => (
              <option key={model} value={model}>
                {model}
              </option>
            ))}
          </select>
        </div>

        <div className="filter-group">
          <label htmlFor="transmission">Transmission</label>
          <select
            id="transmission"
            value={searchParams.transmission}
            onChange={handleInputChange('transmission')}
          >
            <option value="">All</option>
            <option value="MANUAL">Manual</option>
            <option value="AUTOMATIC">Automatic</option>
          </select>
        </div>

        <div className="filter-group">
          <label htmlFor="drive">Drive Type</label>
          <select
            id="drive"
            value={searchParams.drive}
            onChange={handleInputChange('drive')}
          >
            <option value="">All</option>
            <option value="FWD">Front Wheel Drive</option>
            <option value="RWD">Rear Wheel Drive</option>
            <option value="AWD">All Wheel Drive</option>
            <option value="4WD">Four Wheel Drive</option>
          </select>
        </div>

        <div className="filter-group">
          <label htmlFor="body">Body Type</label>
          <select
            id="body"
            value={searchParams.body}
            onChange={handleInputChange('body')}
          >
            <option value="">All</option>
            <option value="SEDAN">Sedan</option>
            <option value="SUV">SUV</option>
            <option value="COUPE">Coupe</option>
            <option value="HATCHBACK">Hatchback</option>
            <option value="WAGON">Wagon</option>
            <option value="VAN">Van</option>
            <option value="PICKUP">Pickup</option>
          </select>
        </div>

        <div className="filter-group">
          <label>Price Range</label>
          <div className="range-inputs">
            <div className="input-group">
              <label htmlFor="minPrice">Min</label>
              <input
                type="number"
                id="minPrice"
                min="0"
                step="1000"
                value={searchParams.minPrice}
                onChange={handleNumberChange('minPrice')}
                placeholder="Min price"
              />
            </div>
            <div className="input-group">
              <label htmlFor="maxPrice">Max</label>
              <input
                type="number"
                id="maxPrice"
                min="0"
                step="1000"
                value={searchParams.maxPrice}
                onChange={handleNumberChange('maxPrice')}
                placeholder="Max price"
              />
            </div>
          </div>
        </div>

        <div className="filter-group">
          <label>Year Range</label>
          <div className="range-inputs">
            <div className="input-group">
              <label htmlFor="minYear">Min</label>
              <input
                type="number"
                id="minYear"
                min="1900"
                max={new Date().getFullYear()}
                value={searchParams.minYear}
                onChange={handleNumberChange('minYear')}
                placeholder="Min year"
              />
            </div>
            <div className="input-group">
              <label htmlFor="maxYear">Max</label>
              <input
                type="number"
                id="maxYear"
                min="1900"
                max={new Date().getFullYear()}
                value={searchParams.maxYear}
                onChange={handleNumberChange('maxYear')}
                placeholder="Max year"
              />
            </div>
          </div>
        </div>

        <div className="filter-group">
          <label>Mileage Range</label>
          <div className="range-inputs">
            <div className="input-group">
              <label htmlFor="minMileage">Min</label>
              <input
                type="number"
                id="minMileage"
                min="0"
                step="1000"
                value={searchParams.minMileage}
                onChange={handleNumberChange('minMileage')}
                placeholder="Min mileage"
              />
            </div>
            <div className="input-group">
              <label htmlFor="maxMileage">Max</label>
              <input
                type="number"
                id="maxMileage"
                min="0"
                step="1000"
                value={searchParams.maxMileage}
                onChange={handleNumberChange('maxMileage')}
                placeholder="Max mileage"
              />
            </div>
          </div>
        </div>

        <div className="filter-group">
          <label>Seats Range</label>
          <div className="range-inputs">
            <div className="input-group">
              <label htmlFor="minPlaces">Min</label>
              <input
                type="number"
                id="minPlaces"
                min="1"
                max="10"
                value={searchParams.minPlaces}
                onChange={handleNumberChange('minPlaces')}
                placeholder="Min seats"
              />
            </div>
            <div className="input-group">
              <label htmlFor="maxPlaces">Max</label>
              <input
                type="number"
                id="maxPlaces"
                min="1"
                max="10"
                value={searchParams.maxPlaces}
                onChange={handleNumberChange('maxPlaces')}
                placeholder="Max seats"
              />
            </div>
          </div>
        </div>

        <div className="filter-group">
          <label>Engine Range (cc)</label>
          <div className="range-inputs">
            <div className="input-group">
              <label htmlFor="minEngine">Min</label>
              <input
                type="text"
                id="minEngine"
                value={searchParams.minEngine}
                onChange={handleInputChange('minEngine')}
                placeholder="e.g. 1600"
              />
            </div>
            <div className="input-group">
              <label htmlFor="maxEngine">Max</label>
              <input
                type="text"
                id="maxEngine"
                value={searchParams.maxEngine}
                onChange={handleInputChange('maxEngine')}
                placeholder="e.g. 3000"
              />
            </div>
          </div>
        </div>

        <button
          className="search-button"
          onClick={handleSearch}
          disabled={loading}
        >
          {loading ? 'Searching...' : 'Search'}
        </button>
      </div>

      <div className="search-results">
        {searchResults?.content.map((car) => (
          <div key={car.id} className="car-card">
            <img
              src={car.photos && car.photos.length > 0 ? car.photos[0] : defaultCarImage}
              alt={`${car.make} ${car.model}`}
              className="car-image"
              onError={(e) => {
                e.target.onerror = null;
                e.target.src = defaultCarImage;
              }}
            />
            <div className="car-info">
              <h3>{car.make} {car.model}</h3>
              <p>Year: {car.year}</p>
              <p>Mileage: {car.mileage.toLocaleString()} km</p>
              <p className="car-price">${car.price.toLocaleString()}</p>
              <button 
                className="view-details-button"
                onClick={() => handleViewDetails(car.id)}
              >
                View Details
              </button>
            </div>
          </div>
        ))}

        {searchResults && (
          <div className="pagination">
            <button
              onClick={() => handlePageChange({ target: { value: searchResults.page - 1 } })}
              disabled={searchResults.page === 1}
            >
              Previous
            </button>
            <span>Page {searchResults.page} of {searchResults.totalPages}</span>
            <button
              onClick={() => handlePageChange({ target: { value: searchResults.page + 1 } })}
              disabled={searchResults.page === searchResults.totalPages}
            >
              Next
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default CarSearch; 