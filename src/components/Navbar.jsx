import React, { useContext, useState } from 'react'
import '../styles/Navbar.css';
import UserContext from '../contexts/UserContext';
import { removeAuthHeaders } from '../services/ApiServices';
import { useNavigate } from 'react-router-dom';
import CustomLink from './CustomLink';
import { Link } from 'react-router-dom';

const Navbar = () => {
    const { currentUser, updateCurrentUserContext, isRequestToGetCurrentUserDone } = useContext(UserContext);
    const navigate = useNavigate();
    const [searchTerm, setSearchTerm] = useState('');

    const logout = () => {
        setTimeout(() => {
            removeAuthHeaders();
            updateCurrentUserContext(null);
            navigate("/");

        }, 1000);


    }

    const handleSearch = (e) => {
        e.preventDefault();
        navigate(`/search?query=${searchTerm}`);
        setSearchTerm('');
    };


    return (
        <div className='navbar'>
            <CustomLink to={"/"}>RAIED STORE</CustomLink>
            <div className="navbar-links">
                {(isRequestToGetCurrentUserDone && !currentUser) && <CustomLink to={"/login"}>Login</CustomLink>}
                {currentUser &&
                    <div >
                        

                            <form onSubmit={handleSearch} className="flex ">
                                <input 
                                    type="text"
                                    value={searchTerm}
                                    placeholder="Search items..."
                                    onChange={(e) => setSearchTerm(e.target.value)}
                                    className="border px-2 py-1 rounded"
                                />
                                <button type="submit" className="bg-blue-500 text-white px-2 py-1 rounded">
                                    🔍
                                </button>
                            </form>
                        










                        <CustomLink to={"/profile"}>Profile</CustomLink>

                        <Link to="/favorites">🤍 </Link>

                        {/* <Link to="/orders" className="nav-link">🛒</Link> */}
                       <Link to="/order">🛒 </Link>


                        <button onClick={logout}>Logout</button>


                    </div>


                }
            </div>
        </div>
    )
}

export default Navbar