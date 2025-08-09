

import { useFavorites } from "../contexts/FavoriteContext";
import React from "react";
import "../styles/Favorite.css";
import { FaHeart, FaRegHeart } from "react-icons/fa";
import { useOrder } from "../contexts/OrderContext";


const ItemCard = ({ item }) => {
  const { isFavorite, addToFavorites, removeFromFavorites } = useFavorites();
   const { addToCart } = useOrder();

  const toggleFavorite = () => {
    if (isFavorite(item.id)) {
      removeFromFavorites(item.id);
    } else {
      addToFavorites(item);
    }
  };

  const handleAddToCart = async (item) => {
  try {
    await addToCart(item); // your API/service call to add item to cart
    alert("Item added successfully!"); // success message
  } catch (err) {
    console.error("Add to cart failed:", err);
    alert("Failed to add item to cart.");
  }
};


  return (
    <div className="card">
      <img src={item.image} alt={item.title} className="card-img" />
      <div className="card-body">
        <h3>{item.title}</h3>
        <p className="price">${item.price}</p>
        <p className="stock">In stock: {item.stock}</p>
        <button className="heart-button" onClick={toggleFavorite}>
          {isFavorite(item.id) ? (
            <FaHeart color="red" />
          ) : (
            <FaRegHeart />
          )}
        </button>
        <button className="add-to-cart" onClick={() => handleAddToCart(item)}>add to cart🛒</button>
      </div>
    </div>
  );
};

export default ItemCard;






































