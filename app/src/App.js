import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

const App = () => {
const axiosBody = {
  username:"sofiamail",
  userpassword:1234
}

  const [groups, setGroups] = useState([]);
  const [loading, setLoading] = useState(false);
  
axios({
         method:'post',
         url:'/login',
         params:{
                username: "sofiamail",
                password: "1234"
            },
         config: { headers: {'Content-Type': 'application/x-www-form-urlencoded'}}
        });

  if (loading) {
    return <p>Loading...</p>;
  }

return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <form>
          <input type="text" name="email" 
            
           />
          <input type="password" name="password" 
            />
          
          <button type="submit">Enviar</button>
        </form>
      </div>
    );
}

export default App;
