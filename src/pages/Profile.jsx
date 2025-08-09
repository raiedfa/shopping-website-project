// import React, { useContext, useState, useEffect } from 'react'
// import '../styles/Profile.css'
// import UserContext from '../contexts/UserContext'
// import { useNavigate } from 'react-router-dom'
// import { updateCurrentUser } from '../services/ApiServices'



// const Profile = () => {
//   const { currentUser, updateCurrentUserContext, isRequestToGetCurrentUserDone } = useContext(UserContext);
//   const navigate = useNavigate();

//   const [formData, setFormData] = useState(null);
//   const [isEditing, setIsEditing] = useState(false);
//   const [isDeletedAccount, setIsDeletedAccount] = useState(false);
//   const [errors, setErrors] = useState({});
//   const [errorFromServer, setErrorFromServer] = useState("");
//   const [isFormValid, setIsFormValid] = useState(true);

//   useEffect(() => {
//     if (currentUser) {
//       setFormData(currentUser);
//     }
//   }, [currentUser]);



//   const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//   const phoneRegex = /^[0-9]*$/; // change: regex for phone validation (is optional & only numbers)

//   const validateField = (name, value) => {
//     let error = "";
//     if (!value.trim() && ["first_name", "last_name", "email", "username", "password"].includes(name)) {
//       error = `${name.replace("_", " ")} is required`;

//     } else if (name == "email" && !emailRegex.test(value)) {
//       error = "Please enter a valid email address."
//     } else if (name == "phone" && value.trim() && !phoneRegex.test(value)) {
//       error = "Please enter a valid phone number."
//     }
//     setErrors({
//       ...errors,
//       [name]: error
//     })
//   }

//   useEffect(() => {
//     if (formData) {
//       const { first_name, last_name, email } = formData;
//       setIsFormValid(
//         Boolean(first_name && last_name && email) &&
//         Object.values(errors).every(err => !err)
//       )
//     }
//   }, [errors]);

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setFormData({
//       ...formData,
//       [name]: value
//     })
//     validateField(name, value);
//   }

//   const handleSubmit = async(e) => {
//     e.preventDefault();
//     if (!isFormValid) return;
    

//     try{
//         const {data} = await updateCurrentUser(formData);
//         updateCurrentUserContext(data);
//         setIsEditing(false);
//     }catch(err){
//       console.log(err);
//       if (err.status == 400 || err.status == 500) {
//         setErrorFromServer(err.response.data);
//       }
//       if (err.code == "ERR_NETWORK") {
//         setErrorFromServer("Network error. Please try again later.");
//       }
//       setTimeout(() => {
//         setErrorFromServer("");
//       }, 3000);


//     }
//   }



//   return (
//     <div className='profile'>
//       {currentUser &&
//         <div>
//           {(formData && !isDeletedAccount) &&
//             <div>
//               <h2 className='center'> Your Profile</h2>
//               <form className='profile-form' onSubmit={handleSubmit} >
//                 <div className='form-group'>
//                   <label htmlFor="first_name" className='form-label'>First Name</label>
//                   <input type="text" name="first_name" value={formData.first_name}
//                     className={`form-input ${errors.first_name ? "input-error" : ""}`}
//                     onChange={handleChange}
//                     disabled={!isEditing}
//                   />
//                   {errors.first_name && <p className='error-text'>{errors.first_name}</p>}
//                 </div>

//                 <div className='form-group'>
//                   <label htmlFor="last_name" className='form-label'>Last Name</label>
//                   <input type="text" name="last_name" value={formData.last_name}
//                     className={`form-input ${errors.last_name ? "input-error" : ""}`}
//                     onChange={handleChange}
//                     disabled={!isEditing}
//                   />
//                   {errors.last_name && <p className='error-text'>{errors.last_name}</p>}
//                 </div>

//                 <div className='form-group'>
//                   <label htmlFor="email" className='form-label'>Email</label>
//                   <input type="email" name="email" value={formData.email}
//                     className={`form-input ${errors.email ? "input-error" : ""}`}
//                     onChange={handleChange}
//                     disabled={!isEditing}
//                   />
//                   {errors.email && <p className='error-text'>{errors.email}</p>}
//                 </div>

//                 <div className='form-group'>
//                   <label htmlFor="phone" className='form-label'>Phone</label>
//                   <input type="tel" name="phone" value={formData.phone}
//                     className={`form-input ${errors.phone ? "input-error" : ""}`}
//                     onChange={handleChange}
//                     disabled={!isEditing}
//                   />
//                   {errors.phone && <p className='error-text'>{errors.phone}</p>}
//                 </div>

//                 <div className='form-group'>
//                   <label htmlFor="address" className='form-label'>Address</label>
//                   <input type="text" name="address" value={formData.addrees}
//                     className={`form-input ${errors.addrees ? "input-error" : ""}`}
//                     onChange={handleChange}
//                     disabled={!isEditing}
//                   />
//                   {errors.addrees && <p className='error-text'>{errors.addrees}</p>}
//                 </div>

//                 {errorFromServer && <p className='error-text'>{errorFromServer}</p>}

//                 {!isEditing && <button type='button' className='edit-btn' onClick={() => setIsEditing(true)}>Edit Profile</button>}
//                 {isEditing && <button type='submit' className='save-btn' disabled={!isFormValid}>Save</button>}
//               </form>
//             </div>
//           }
//           {!isDeletedAccount ?
//            <button className='delete-btn' >Delete your account</button>
//             :
//             <h3>Your account has been deleted</h3>
//           }

//         </div>
//       }
      
//       {(!isRequestToGetCurrentUserDone && !currentUser) &&
//         <div style={{ textAlign: "center" }}>
//           <h2>Unauthorized Access...</h2>
//           <h3>You need to login to access this page.</h3>
//           <button className='login-button' onClick={() => navigate("/login")}>Login</button>
//         </div>
//       }


//     </div>
//   )
// }




// export default Profile


import React, { useContext, useEffect, useState } from 'react'
import '../styles/Profile.css'
import UserContext from '../contexts/UserContext'
import { useNavigate } from 'react-router-dom';
import { deleteCurrentUser , removeAuthHeaders , updateCurrentUser } from '../services/ApiServices';


const Profile = () => {
  const { currentUser, updateCurrentUserContext, isRequestToGetCurrentUserDone } = useContext(UserContext);
  const navigate = useNavigate();

  const [formData, setFormData] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isDeletedAccount, setIsDeletedAccount] = useState(false);
  const [errors, setErrors] = useState({});
  const [errorFromServer, setErrorFromServer] = useState("");
  const [isFormValid, setIsFormValid] = useState(true);

  useEffect(() => {
    if (currentUser) {
      setFormData(currentUser);
    }
  }, [currentUser]);

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const phoneRegex = /^[0-9]*$/; // change: regex for phone validation (is optional & only numbers)

  const validateField = (name, value) => {
    let error = "";
    if (!value.trim() && ["first_name", "last_name", "email"].includes(name)) {
      error = `${name.replace("_", " ")} is required`;
    } else if (name == "email" && !emailRegex.test(value)) {
      error = "Please enter a valid email address."
    } else if (name == "phone" && value.trim() && !phoneRegex.test(value)) {
      error = "Please enter a valid phone number."
    }
    setErrors({
      ...errors,
      [name]: error
    })
  }

  useEffect(() => {
    if (formData) {
      const { first_name, last_name, email } = formData;
      setIsFormValid(
        Boolean(first_name && last_name && email) &&
        Object.values(errors).every(err => !err)
      )
    }
  }, [errors]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    })
    validateField(name, value);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!isFormValid) return;

    try {
      const { data } = await updateCurrentUser(formData);
      updateCurrentUserContext(data);
      setIsEditing(false);
    } catch (err) {
      console.log(err);
      if (err.status == 400 || err.status == 500) {
        setErrorFromServer(err.response.data);
      }
      if (err.code == "ERR_NETWORK") {
        setErrorFromServer("Network error. Please try again later.");
      }
      setTimeout(() => {
        setErrorFromServer("");
      }, 3000);
    }
  }

  const handleDeleteAccount = async () => {
    const confirmDelete = window.confirm("Are you sure you want to delete your account? This action cannot be undone.");
    if (confirmDelete) {
      try {
        await deleteCurrentUser();
        setIsDeletedAccount(true);
        setTimeout(() => {
          removeAuthHeaders();
          updateCurrentUserContext(null);
          navigate("/");
        }, 3000);
      } catch (err) {
        console.log(err);
        if (err.status == 400 || err.status == 500) {
          setErrorFromServer(err.response.data);
        }
        if (err.code == "ERR_NETWORK") {
          setErrorFromServer("Network error. Please try again later.");
        }
        setTimeout(() => {
          setErrorFromServer("");
        }, 3000);
      }
    }
  }

  return (
    <div className='profile'>
      {currentUser &&
        <div>
          {(formData && !isDeletedAccount) &&
            <div>
              <h2 className='center'>Your Profile</h2>
              <form className='profile-form' onSubmit={handleSubmit}>
                <div className='form-group'>
                  <label htmlFor="first_name" className='form-label'>First Name</label>
                  <input type="text" name='first_name' value={formData.first_name}
                    className={`form-input ${errors.first_name ? "input-error" : ""}`}
                    onChange={handleChange}
                    disabled={!isEditing}
                  />
                  {errors.first_name && <p className='error-text'>{errors.first_name}</p>}
                </div>
                <div className='form-group'>
                  <label htmlFor="last_name" className='form-label'>Last Name</label>
                  <input type="text" name='last_name' value={formData.last_name}
                    className={`form-input ${errors.last_name ? "input-error" : ""}`}
                    onChange={handleChange}
                    disabled={!isEditing}
                  />
                  {errors.last_name && <p className='error-text'>{errors.last_name}</p>}
                </div>
                <div className='form-group'>
                  <label htmlFor="email" className='form-label'>Email</label>
                  <input type="email" name='email' value={formData.email}
                    className={`form-input ${errors.email ? "input-error" : ""}`}
                    onChange={handleChange}
                    disabled={!isEditing}
                  />
                  {errors.email && <p className='error-text'>{errors.email}</p>}
                </div>
                <div className='form-group'>
                  <label htmlFor="phone" className='form-label'>Phone</label>
                  <input type="tel" name='phone' value={formData.phone}
                    className={`form-input ${errors.phone ? "input-error" : ""}`}
                    onChange={handleChange}
                    disabled={!isEditing}
                  />
                  {errors.phone && <p className='error-text'>{errors.phone}</p>}
                </div>
                <div className='form-group'>
                  <label htmlFor="address" className='form-label'>Address</label>
                  <input type="text" name='address' value={formData.address}
                    className={`form-input ${errors.address ? "input-error" : ""}`}
                    onChange={handleChange}
                    disabled={!isEditing}
                  />
                  {errors.address && <p className='error-text'>{errors.address}</p>}
                </div>

                {errorFromServer && <p className='error-text'>{errorFromServer}</p>}

                {!isEditing && <button type='button' className='edit-btn' onClick={() => setIsEditing(true)}>Edit</button>}
                {isEditing && <button type='submit' className='save-btn' disabled={!isFormValid}>Save</button>}
              </form>
            </div>
          }
          {!isDeletedAccount ?
            <button className='delete-btn' onClick={handleDeleteAccount}>Delete Your Account</button>
            :
            <h3>Your account has been deleted</h3>
          }
        </div>
      }
      {(isRequestToGetCurrentUserDone && !currentUser && !isDeletedAccount) &&
        <div className='center'>
          <h2>Unauthorized Access</h2>
          <h3>You need to login to access this page.</h3>
          <button className='login-btn' onClick={() => navigate("/login")}>Login</button>
        </div>
      }
    </div>
  )
}

export default Profile