import React, { createContext, useContext, useEffect, useState } from "react";
import axios from "axios";

const UserContext = createContext();

const HABITS_STORAGE_KEY = 'habitListData'; // Key to store habits in localStorage
const HABIT_LOGS_STORAGE_KEY = 'habitLogsData'; // Key to store habitLogs in localStorage

export const UserProvider = ({ children }) => {
  const [users, setUsers] = useState([]);
  const [habits, setHabits] = useState([]);
  const [habitLogs, setHabitLogs] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const fetchUsers = async () => {
    try {
      const response = await axios.get("./data/users.json");
      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching users:", error);
    }
  };

  const fetchInitialHabits = async () => {
    try {
      const response = await axios.get("./data/habitList.json");
      return response.data; // Return the fetched data, don't set state yet
    } catch (error) {
      console.error("Error fetching habitList:", error);
      return []; // Return empty array in case of error
    }
  };

  const fetchHabitsLogs = async () => {
    try {
      const response = await axios.get("./data/habitLogs.json");
      return response.data; // Return the fetched data, don't set state yet
    } catch (error) {
      console.error("Error fetching habitLogs:", error);
      return []; // Return empty array in case of error
    }
  };

  const loadData = async () => {
    await fetchUsers(); // Fetch users in parallel

    // Load habits from localStorage or fetch from JSON
    const storedHabits = localStorage.getItem(HABITS_STORAGE_KEY);
    let initialHabits = []; // Initialize the variable

    if (storedHabits) {
      initialHabits = JSON.parse(storedHabits);
      setHabits(initialHabits);
    } else {
      initialHabits = await fetchInitialHabits();
      setHabits(initialHabits);
      localStorage.setItem(HABITS_STORAGE_KEY, JSON.stringify(initialHabits));
    }

    // Load habitLogs from localStorage or fetch from JSON
    const storedHabitLogs = localStorage.getItem(HABIT_LOGS_STORAGE_KEY);
    let initialHabitLogs = []; // Initialize the variable

    if (storedHabitLogs) {
      initialHabitLogs = JSON.parse(storedHabitLogs);
      setHabitLogs(initialHabitLogs);
    } else {
      initialHabitLogs = await fetchHabitsLogs();
      setHabitLogs(initialHabitLogs);
      localStorage.setItem(HABIT_LOGS_STORAGE_KEY, JSON.stringify(initialHabitLogs));
    }

    setIsLoading(false); // Set loading to false after fetching all initial data
  };

  useEffect(() => {
    loadData();
  }, []);

  // Function to add a new habit
  const addHabit = (newHabitData) => {
    const newHabit = {
      id: habits.length, // Simple ID generation
      date: new Date().toISOString().split('T')[0], // Add creationDate in YYYY-MM-DD format
      ...newHabitData,
    };
    const updatedHabits = [...habits, newHabit];
    setHabits(updatedHabits);
    // Update localStorage with the new habit list
    localStorage.setItem(HABITS_STORAGE_KEY, JSON.stringify(updatedHabits));
  };

  const updateHabit = (habitIdToUpdate, updates) => {
    const updatedHabitsArray = habits.map(habit => {
      if (habit.id === habitIdToUpdate) {
        // If the habit's id matches the id to update,
        // merge the 'updates' object with the existing habit object.
        return {
          ...habit, // Keep all existing properties of the habit
          ...updates, // Override with properties from 'updates' object
        };
      }
      // Otherwise, keep the existing habit object unchanged.
      return habit;
    });
    setHabits(updatedHabitsArray);
    // Update localStorage with the updated habit list
    localStorage.setItem(HABITS_STORAGE_KEY, JSON.stringify(updatedHabitsArray));
  };

   // Function to delete a habit
   const deleteHabit = (habitIdToDelete) => {
    const updatedHabits = habits.filter(habit => habit.id !== habitIdToDelete);
    setHabits(updatedHabits);
    // Update localStorage with the updated habit list
    localStorage.setItem(HABITS_STORAGE_KEY, JSON.stringify(updatedHabits));
  };

  const updateHabitLogs = (habitId, newDate, newStatus) => {
    const updatedHabitLogs = habitLogs.map(habitLog => {
        if (habitLog.id === habitId) {
            // Found the habit log to update
            const updatedLog = [...habitLog.log, { date: newDate, status: newStatus }];
            return { ...habitLog, log: updatedLog }; // Update the log array
        }
        return habitLog; // Keep other habit logs unchanged
    });

    setHabitLogs(updatedHabitLogs);
    localStorage.setItem(HABIT_LOGS_STORAGE_KEY, JSON.stringify(updatedHabitLogs));
};

  // Function to update habits in localStorage whenever habits state changes (alternative approach - useEffect)
  // useEffect(() => {
  //   if (!isLoading) { // Prevent saving during initial load, only on updates
  //     localStorage.setItem(HABITS_STORAGE_KEY, JSON.stringify(habits));
  //   }
  // }, [habits, isLoading]); // Dependency on habits and isLoading

  return (
    <UserContext.Provider value={{ users, habits, habitLogs, isLoading, addHabit, updateHabit, deleteHabit, updateHabitLogs }}>
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