// import React, { useContext } from 'react'
// import '../styles/Home.css'
// import UserContext from '../contexts/UserContext'
// import Items from '../components/Items'

// const Home = () => {
//     const { currentUser, isRequestToGetCurrentUserDone } = useContext(UserContext);
//     return (
//         <div className='home'>
//             {(isRequestToGetCurrentUserDone && !currentUser) &&

//                 <div>
//                     <h2>Welcome to RAIED STORE</h2>
//                     <p>Sign in to start shopping</p>


                    
//                 </div>

//             }
//             {currentUser &&
//                 <div>

//                     <h2>Welcome , {currentUser.first_name} {currentUser.last_name}</h2>
//                     <p>Continue Shopping</p>

//                     <Items />
//                 </div>
//             }
//         </div>
//     )
// }

// export default Home




import React, { useContext } from 'react';
import '../styles/Home.css';
import UserContext from '../contexts/UserContext';
import Items from '../components/Items';

const Home = () => {
  const { currentUser, isRequestToGetCurrentUserDone } = useContext(UserContext);

  return (
    <div className='home'>
      {isRequestToGetCurrentUserDone && !currentUser && (
        <div>
          <h2>Welcome to RAIED STORE</h2>
          <p className='sign-in-prompt'>"Join us by signing in to explore and shop the best products!"</p>
        </div>
      )}

      {currentUser && (
        <div>
          <h2>Welcome, {currentUser.first_name} {currentUser.last_name}</h2>
          <p>Continue Shopping</p>
        </div>
      )}

      {/* Always show items, whether logged in or not */}
      <Items />
    </div>
  );
};

export default Home;

