


import React, { useEffect, useState } from 'react';
import { findAll, addToFavorites, removeFromFavorites, createOrder } from '../services/ApiServices';
import '../styles/Items.css';
import ItemCard from './ItemCard';

const Items = () => {
  const [items, setItems] = useState([]);
  const [favorites, setFavorites] = useState([]);
  const [error, setError] = useState('');



  useEffect(() => {
    const fetchItems = async () => {
      try {
        const response = await findAll();
        setItems(response.data);

     
        const savedFavorites = JSON.parse(localStorage.getItem("favoriteItemIds")) || [];
        setFavorites(savedFavorites);
      } catch (err) {
        console.error("Error fetching items:", err);
        setError("Could not load items.");
      }
    };

    fetchItems();
  }, []);










  const handleFavoriteToggle = (itemId) => {
    setFavorites((prevFavorites) => {
      let updatedFavorites;
      if (prevFavorites.includes(itemId)) {
        updatedFavorites = prevFavorites.filter((id) => id !== itemId);
      } else {
        updatedFavorites = [...prevFavorites, itemId];
      }

      // Save to localStorage
      localStorage.setItem("favoriteItemIds", JSON.stringify(updatedFavorites));

      return updatedFavorites;
    });
  };











  // const handleBuy = async (order) => {
  //   try {
  //     await createOrder(order);
  //     alert("Item ordered successfully!");
  //   } catch (err) {
  //     console.error("Order failed:", err);
  //     alert("Failed to create order.");
  //   }
  // };

  const handleBuy = async (order, itemId) => {
  try {
    await createOrder(order);

    // Update stock locally
    setItems(prevItems =>
      prevItems.map(item =>
        item.id === itemId ? { ...item, stock: item.stock - 1 } : item
      )
    );

    alert("Item ordered successfully!");
  } catch (err) {
    console.error("Order failed:", err);
    alert("Failed to create order.");
  }
};









  return (
    <div>
      <h2>ITEMS LIST</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {items.length === 0 ? (
        <p>No items available.</p>
      ) : (
        <div className="grid">
          {items.map((item) => (
            <ItemCard
              key={item.id}
              item={item}
              isFavorite={favorites.includes(item.id)}
              onFavoriteToggle={handleFavoriteToggle}
              onBuy={handleBuy}
            />



          ))}
        </div>
      )}
    </div>
  );
};

export default Items;




























