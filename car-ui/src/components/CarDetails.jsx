import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { carService } from '../services/api';
import defaultCarImage from '../assets/default-car.png';
import './CarDetails.css';

const CarDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [car, setCar] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedImage, setSelectedImage] = useState(null);

  useEffect(() => {
    const fetchCarDetails = async () => {
      try {
        const data = await carService.getCarById(id);
        setCar(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchCarDetails();
  }, [id]);

  const handleBack = () => {
    navigate(-1);
  };

  const handleImageClick = (imageUrl) => {
    setSelectedImage(imageUrl);
  };

  const handleCloseModal = () => {
    setSelectedImage(null);
  };

  if (loading) return <div className="loading">Loading...</div>;
  if (error) return <div className="error">{error}</div>;
  if (!car) return <div className="error">Car not found</div>;

  return (
    <div className="car-details">
      <button className="back-button" onClick={handleBack}>
        ← Back to Search
      </button>
      
      <div className="car-details-content">
        <div className="car-info">
          <h1>{car.make} {car.model}</h1>
          <p className="price">${car.price.toLocaleString()}</p>
          
          <div className="info-grid">
            <div className="info-item">
              <h3>Year</h3>
              <p>{car.year}</p>
            </div>
            <div className="info-item">
              <h3>Mileage</h3>
              <p>{car.mileage.toLocaleString()} km</p>
            </div>
            <div className="info-item">
              <h3>Engine</h3>
              <p>{car.engine}</p>
            </div>
            <div className="info-item">
              <h3>Transmission</h3>
              <p>{car.transmission}</p>
            </div>
            <div className="info-item">
              <h3>Fuel Type</h3>
              <p>{car.fuel}</p>
            </div>
            <div className="info-item">
              <h3>Color</h3>
              <p>{car.color}</p>
            </div>
          </div>
        </div>

        <div className="car-images">
          {car.photos && car.photos.length > 0 ? (
            car.photos.map((photo, index) => (
              <div key={index} className="car-image-container" onClick={() => handleImageClick(photo)}>
                <img 
                  src={photo} 
                  alt={`${car.make} ${car.model} - Photo ${index + 1}`}
                  className="car-image"
                />
              </div>
            ))
          ) : (
            <div className="car-image-container">
              <img 
                src={defaultCarImage} 
                alt="Default car" 
                className="car-image"
              />
            </div>
          )}
        </div>

        {selectedImage && (
          <div className="image-modal" onClick={handleCloseModal}>
            <div className="modal-content">
              <img src={selectedImage} alt="Enlarged car photo" />
              <button className="close-modal" onClick={handleCloseModal}>×</button>
            </div>
          </div>
        )}

        <div className="description">
          <h2>Description</h2>
          <p>{car.description}</p>
        </div>

        <div className="location">
          <h2>Location</h2>
          <p>{car.locationAddress}</p>
        </div>

        <div className="manager">
          <h2>Contact Information</h2>
          <p>Name: {car.manager.name}</p>
          <p>Phone: {car.manager.phone}</p>
        </div>
      </div>
    </div>
  );
};

export default CarDetails; 