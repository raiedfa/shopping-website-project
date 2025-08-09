import { useOrder } from "../contexts/OrderContext";
import React from "react";
import "../styles/Order.css";
import { useNavigate } from "react-router-dom";


const Order = () => {
  const { cartItems, changeQuantity, removeFromCart, clearCart } = useOrder();
  const navigate = useNavigate();
  const totalPrice = cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);


  const handleSubmitOrder = () => {
    // Send cartItems to backend via POST (Axios or fetch)
  };

  return (
    <div className="cart-page center">
      <h2>Your Order</h2>
      {cartItems.length === 0 ? (
        <p>No items in your cart</p>
      ) : (
        cartItems.map((item) => (
          <div key={item.id}>
            <h3>{item.title}</h3>
            <img src={item.image} alt={item.title} className="item-image" />

            <p>${item.price}</p>
            <p>Quantity: {item.quantity}</p>
            <button onClick={() => changeQuantity(item.id, -1)}>-</button>
            <button onClick={() => changeQuantity(item.id, 1)}>+</button>
            <button onClick={() => removeFromCart(item.id)}>Remove</button>
          </div>
        ))
      )}
      {cartItems.length > 0 && (
        <>
        <h3>Total: ${totalPrice.toFixed(2)}</h3>
          <button onClick={handleSubmitOrder}>Submit Order</button>
          <button onClick={clearCart}>Clear Cart</button>
        </>
      )}
      <button className="return-home-button" onClick={() => navigate("/")}>Return home</button>
    </div>
  );
};

export default Order;
