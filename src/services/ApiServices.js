import axios from "axios";

const BASE_URL = "http://localhost:9000";

const setAuthHeaders = (token) => {
    console.log("token: " + token);
    localStorage.setItem("token", token);
}

// const getAuthHeaders = () => {
//     const token = localStorage.getItem("token");
//     return {
//         Authorization: `Bearer ${token}`
//     };
// }

// ApiServices.js

export const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return {
    Authorization: `Bearer ${token}`,
  };
};


export const removeAuthHeaders = () => {
    localStorage.removeItem("token");
}

export const register = (user) => {
    return axios.post(`${BASE_URL}/users/register`, user);
}

export const login = async (credentials) => {
    const { data } = await axios.post(`${BASE_URL}/authenticate`, credentials);
    const jwtToken = data.jwt;
    setAuthHeaders(jwtToken);
    return data;
}

export const fetchCurrentUser = () => {
    return axios.get(`${BASE_URL}/users`, { headers: getAuthHeaders() });
}

export const updateCurrentUser = (updatedCurrentUser) => {
    return axios.put(`${BASE_URL}/users`, updatedCurrentUser, { headers: getAuthHeaders() });
}

export const deleteCurrentUser = () => {
    return axios.delete(`${BASE_URL}/users`, { headers: getAuthHeaders() });
}

    export const createItem = (Item) => {
        return axios.post(`${BASE_URL}/items`, Item, { headers: getAuthHeaders() });
    }

    

    export const findAll = () => {
  return axios.get(`${BASE_URL}/items`);
};







    export const searchByTitle = (title) => {
        return axios.get(`${BASE_URL}/items/search?title=${title}`, { headers: getAuthHeaders() });
    }

    export const addToFavorites = (itemId) => {
        return axios.post(`${BASE_URL}/favorites/${itemId}`,  { headers: getAuthHeaders() });
    }

    export const getFavorites = () => {
        return axios.get(`${BASE_URL}/favorites`, { headers: getAuthHeaders() });
    }

    export const removeFromFavorites = (itemId) => {
        return axios.delete(`${BASE_URL}/favorites/${itemId}`, { headers: getAuthHeaders() });
    }

    export const toggleFavoriteItem = async (itemId, isFavorite) => {
  if (isFavorite) {
    return axios.delete(`${BASE_URL}/favorites/${itemId}`, { headers: getAuthHeaders() });
  } else {
    return axios.post(`${BASE_URL}/favorites/${itemId}`, {}, { headers: getAuthHeaders() });
  }
};

   

    export const createOrder = (order) => {
        return axios.post(`${BASE_URL}/orders`, order, { headers: getAuthHeaders() });
    }

    export const findById = (id) => {
        return axios.get(`${BASE_URL}/orders/${id}`, { headers: getAuthHeaders() });
    }

    export const getOrderByUserName = () => {
        return axios.get(`${BASE_URL}/orders/user-order/{username}`, { headers: getAuthHeaders() });
    }

   

    export const addOrderItems =(orderItems) => {
        return axios.post(`${BASE_URL}/orderItems`, orderItems, { headers: getAuthHeaders() });
    } 

    // export const updateOrderItemQuantity = (orderItem) => {
    //     return axios.put(`${BASE_URL}/orderItems`, orderItem, { headers: getAuthHeaders() });
    // }

    

    export const decreaseItemStock = (Item) => {
        return axios.put(`${BASE_URL}/items/{itemId}/decrease-stock`, Item, { headers: getAuthHeaders() });
    }

    export const increaseItemStock = (Item) => {
        return axios.post(`${BASE_URL}/Items/cancel`, Item, { headers: getAuthHeaders() });
    }

    export const getAllOrderItems = () => {
        return axios.get(`${BASE_URL}/order_items`, { headers: getAuthHeaders() });
    }

    export const getOrderItemsByOrderId = (orderId) => {
        return axios.get(`${BASE_URL}/order_items/${orderId}`, { headers: getAuthHeaders() });
    }

    export const deleteOrderItem = () => {
        return axios.delete(`${BASE_URL}/order_items`, { headers: getAuthHeaders() });
    }

    export const getUserOrders = () => {
        return axios.get(`${BASE_URL}/orders`, { headers: getAuthHeaders() });
    }






    
