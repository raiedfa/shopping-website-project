import React, { createContext, useContext, useEffect, useState } from "react";
import {
  getFavorites,
  addToFavorites,
  removeFromFavorites,
} from "../services/ApiServices";

const FavoriteContext = createContext();

export const FavoriteProvider = ({ children }) => {
  const [favorites, setFavorites] = useState([]);

  const fetchFavorites = async () => {
    try {
      const res = await getFavorites(); 
      setFavorites(res.data);
    } catch (err) {
      console.error("Failed to fetch favorites", err);
    }
  };

  useEffect(() => {
    fetchFavorites();
  }, []);

  const isFavorite = (itemId) =>
    favorites.some((item) => item.id === itemId);

  const addToFavorites = async (item) => {
    try {
      await addToFavorites(item.id);
      setFavorites((prev) => [...prev, item]);
    } catch (err) {
      console.error("Failed to add favorite", err);
    }
  };

  const removeFromFavorites = async (itemId) => {
    try {
      await removeFromFavorites(itemId);
      setFavorites((prev) => prev.filter((item) => item.id !== itemId));
    } catch (err) {
      console.error("Failed to remove favorite", err);
    }
  };

  return (
    <FavoriteContext.Provider
      value={{
        favorites,
        isFavorite,
        addToFavorites,
        removeFromFavorites,
        fetchFavorites,
      }}
    >
      {children}
    </FavoriteContext.Provider>
  );
};

export const useFavorites = () => useContext(FavoriteContext);
