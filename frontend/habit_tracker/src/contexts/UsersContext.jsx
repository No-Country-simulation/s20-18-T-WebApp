import React, { createContext, useContext, useEffect, useState } from "react";
import axios from "axios";

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [users, setUsers] = useState([]);
  const [habits, setHabits] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const fetchUsers = async () => {
    try {
      const response = await axios.get("./data/users.json");      
      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching users:", error);
    } 
  };

  const fetchHabits = async () => {
    try {
      const response = await axios.get("./data/habitList.json");      
      //console.log(response)
      setHabits(response.data);
    } catch (error) {
      console.error("Error fetching habitList:", error);
    } 
  };
  const loadData = async () => {
    await Promise.all([fetchUsers(),fetchHabits()])
    setIsLoading(false);
  }

  useEffect(() => {   
    //console.log('from usercontext')
    loadData();

  }, []);

  

  return (
    <UserContext.Provider value={{ users, habits, isLoading }}>
      {children}
    </UserContext.Provider>
  );
};

// Hook personalizado
export const useUsers = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error("useUsers debe usarse dentro de un UserProvider");
  }
  return context;
};
