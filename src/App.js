


import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Registration from './pages/Registration';
import Login from './pages/Login';
import Profile from './pages/Profile';
import Items from './components/Items';
import FavoriteItems from './pages/FavoriteItems';
import Order from './pages/Order';
import NotFound from './pages/NotFound';
import Navbar from './components/Navbar';
import { useEffect, useState } from 'react';
import { fetchCurrentUser } from './services/ApiServices';
import UserContext from './contexts/UserContext';
  import { FavoriteProvider } from "./contexts/FavoriteContext"; 
import { OrderProvider } from './contexts/OrderContext';

  
 




function App() {
  const [currentUser, setCurrentUser] = useState(null);
  const [isRequestToGetCurrentUserDone, setIsRequestToGetCurrentUserDone] = useState(false);

  const updateCurrentUserContext = (user) => {
    console.log("current user: ", user);
    setCurrentUser(user);
  }

  const getCurrentUserForContext = async () => {
    try {
      if (localStorage.getItem("token")) {
        const { data } = await fetchCurrentUser();
        updateCurrentUserContext(data);
      }
    } catch (err) {
      console.error("Error fetching current user: ", err);
    }
    setIsRequestToGetCurrentUserDone(true);
  }

  useEffect(() => {
    getCurrentUserForContext();
  }, []);


  // return (
  //   <UserContext.Provider value={{ currentUser, updateCurrentUserContext, isRequestToGetCurrentUserDone }}>
  //     <Router>
  //       <Navbar />
  //       <Routes>
  //         <Route path='/' element={<Home />} />
  //         <Route path='/register' element={<Registration />} />
  //         <Route path='/login' element={<Login />} />
  //         <Route path='/profile' element={<Profile />} />
  //         <Route path='/items' element={<Items />} />
  //         <Route path="/favorites" element={<FavoriteItems />} />
          
  //         <Route path='*' element={<NotFound />} />
  //       </Routes>
  //     </Router>
  //   </UserContext.Provider>


  // );





return (
  <UserContext.Provider value={{ currentUser, updateCurrentUserContext, isRequestToGetCurrentUserDone }}>
    <OrderProvider>
    <FavoriteProvider>
      <Router>
        <Navbar />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/register' element={<Registration />} />
          <Route path='/login' element={<Login />} />
          <Route path='/profile' element={<Profile />} />
          <Route path='/items' element={<Items />} />
          <Route path="/favorites" element={<FavoriteItems />} />
          <Route path="/order" element={<Order />} />
          <Route path='*' element={<NotFound />} />
        </Routes>
      </Router>
    </FavoriteProvider>
    </OrderProvider>
  </UserContext.Provider>
);






}



export default App;
