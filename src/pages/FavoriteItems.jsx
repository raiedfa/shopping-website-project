// import React, { useEffect, useState } from "react";
// import { getFavorites } from "../services/ApiServices";
// import ItemCard from "../components/ItemCard";
// import "../styles/Favorite.css";
// import { useNavigate } from "react-router-dom";

// const FavoriteItems = () => {
//   const [favorites, setFavorites] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const navigate = useNavigate();

//   // useEffect(() => {
//   //   const fetchFavorites = async () => {
//   //     try {
//   //       const res = await getFavorites();
//   //       setFavorites(res.data);
//   //     } catch (err) {
//   //       console.error("Error fetching favorite items:", err);
//   //     } finally {
//   //       setLoading(false);
//   //     }
//   //   };

//   //   fetchFavorites();
//   // }, []);


//  useEffect(() => {
//   const fetchFavorites = async () => {
//     try {
//       // Get all items
//       const res = await getFavorites(); // Or replace with findAll()
//       const allItems = res.data;

//       // Get favorite item IDs from localStorage
//       const favoriteIds = JSON.parse(localStorage.getItem("favoriteItemIds")) || [];

//       // Filter only favorite items
//       const filteredFavorites = allItems.filter(item => favoriteIds.includes(item.id));

//       setFavorites(filteredFavorites);
//     } catch (err) {
//       console.error("Error fetching favorite items:", err);
//     } finally {
//       setLoading(false);
//     }
//   };

//   fetchFavorites();
// }, []);



//   return (
//     <div className="p-8 center" >
//       <h1 className="text-3xl font-bold mb-6">My Favorite Items </h1>
      

//       {loading ? (
//         <p>Loading...</p>
//       ) : favorites.length === 0 ? (
//         <p className="bold">No favorite items yet.</p>
//       ) : (
//         <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
//           {favorites.map(item => (
//             <ItemCard key={item.id} item={item}  isFavorite={true}
//               />
//           ))}
//         </div>
//       )}
//       <button className="return-home-button" onClick={() => navigate("/")}>
//         ⬅ Return Home
//       </button>
//     </div>
//   );
// };

// export default FavoriteItems;






















// import React, { useEffect, useState } from "react";
// import { getFavorites, removeFromFavorites } from "../services/ApiServices";
// import ItemCard from "../components/ItemCard";
// import "../styles/Favorite.css";
// import { useNavigate } from "react-router-dom";

// const FavoriteItems = () => {
//   const [favorites, setFavorites] = useState([]);
//   const [loading, setLoading] = useState(true);
//   const navigate = useNavigate();

//   useEffect(() => {
//     fetchFavorites();
//   }, []);

//   const fetchFavorites = async () => {
//     try {
//       const res = await getFavorites();
//       setFavorites(res.data);
//     } catch (err) {
//       console.error("Error fetching favorite items:", err);
//     } finally {
//       setLoading(false);
//     }
//   };

//   const handleFavoriteToggle = async (itemId) => {
//     try {
//       await removeFromFavorites(itemId); // Call API to remove
//       setFavorites(prev => prev.filter(item => item.id !== itemId)); // Update local list
//     } catch (err) {
//       console.error("Error removing item from favorites:", err);
//     }
//   };

//   return (
//     <div className="p-8 center">
//       <h1 className="text-3xl font-bold mb-6">My Favorite Items</h1>

//       {loading ? (
//         <p>Loading...</p>
//       ) : favorites.length === 0 ? (
//         <p className="bold">No favorite items yet.</p>
//       ) : (
//         <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
//           {favorites.map((item) => (
//             <ItemCard
//               key={item.id}
//               item={item}
//               isFavorite={true}
//               onFavoriteToggle={() => handleFavoriteToggle(item.id)}
//             />
//           ))}
//         </div>
//       )}

//       <button className="return-home-button" onClick={() => navigate("/")}>
//         ⬅ Return Home
//       </button>
//     </div>
//   );
// };

// export default FavoriteItems;




import React from "react";
import { useFavorites } from "../contexts/FavoriteContext";
import ItemCard from "../components/ItemCard";
import "../styles/Favorite.css";
import { useNavigate } from "react-router-dom";

const FavoriteItems = () => {
  const { favorites } = useFavorites();
  const navigate = useNavigate();

  return (
    <div className="container">
      <h2 className="center">My Favorites</h2>
      {favorites.length === 0 ? (
        <p className="center">No favorites yet.</p>
      ) : (
        favorites.map((item) => <ItemCard  key={item.id} item={item} />)
      )}
      <button className="return-home-button" onClick={() => navigate("/")}>Return home</button>
    </div>
    
  );
};

export default FavoriteItems;
















































