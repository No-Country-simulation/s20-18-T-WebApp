import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";

import { Box, Button, Typography } from '@mui/material';
import { CalendarWeekly } from '../../components/CalendarWeekly/CalendarWeekly';
import SearchBarWithFilters from '../../components/SearchBarWithFilters/SearchBarWithFilters';
import { useUsers } from "../../contexts/UsersContext";
import HabitFormModal from '../../components/HabitFormModal/HabitFormModal';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { getCurrentDate } from '../../utils/utils';

// Función para convertir un string de formato "dd-MM-yyyy" a un objeto Date
const parseDate = (dateStr) => {
  const [day, month, year] = dateStr.split('-');
  return new Date(year, month - 1, day);
};

export const Habits = () => {
  const navigate = useNavigate();
  const styles = {
    cardContainer: {
      display: 'flex',
      flexDirection: { xs: 'column', md: 'row' },
      alignItems: 'center',
      justifyContent: 'center',
      flexWrap: 'wrap',
      mb: "2em",
      mt: "2em",
      gap: '2em'
    }
  };

  const { habits, habitLogs, updateHabit, updateHabitLogs, isLoading } = useUsers();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentHabits, setCurrentHabits] = useState(habits.filter(habit => habit.archived === false));
  const [currentHabitsFiltered, setCurrentHabitsFiltered] = useState([]);

  const [searchText, setSearchText] = useState('');
  const [orderBy, setOrderBy] = useState('');

  const handleOpenModal = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const handleClickCard = (e, habit) => {
    e.preventDefault();
    navigate(`/viewhabit/${habit.id}`);
  };

  const handleArchive = (e, habit) => {
    e.preventDefault();
    updateHabit(habit.id, { archived: true });
    navigate('/habits');
  };

  // newLogStatus = "completed" or "pending"
  const handleAddLogEntry = (habitIdToUpdate) => {
    console.log('adding habit log');
    const newLogDate = getCurrentDate();
    const newLogStatus = "completed";
    updateHabitLogs(habitIdToUpdate, newLogDate, newLogStatus);
  };

  const handleSearchTextChange = (event) => {
    const currentSearchText = event.target.value;
    setSearchText(currentSearchText);
    setCurrentHabitsFiltered(
      currentHabits.filter(habit =>
        habit.title.toUpperCase().includes(currentSearchText.toUpperCase())
      )
    );
  };

  const handleOrderByChange = (event) => {
    setOrderBy(event.target.value);
    console.log("Ordenar por:", event.target.value);

    const orderedHabits = event.target.value === 'oldest'
      ? [...currentHabitsFiltered].sort((a, b) => parseDate(b.date) - parseDate(a.date))
      : [...currentHabitsFiltered].sort((a, b) => parseDate(a.date) - parseDate(b.date));

    console.log(orderedHabits);
    setCurrentHabitsFiltered(orderedHabits);
  };

  const habitsOptionsCard = [
    { id: 0, name: "Ver más", onClick: handleClickCard },
    // {id:1, name: "Agregar a favoritos", onClick: ()=>{console.log('sin función todavía')}},
    { id: 2, name: "Editar", onClick: () => { console.log('sin función todavía') } },
    { id: 3, name: "Archivar", onClick: handleArchive },
  ];

  useEffect(() => {
    const filteredHabits = habits.filter(habit => habit.archived === false);
    setCurrentHabits(filteredHabits);
    // Inicializamos currentHabitsFiltered con todos los hábitos no archivados
    setCurrentHabitsFiltered(filteredHabits);
  }, [habits]);

  if (isLoading) {
    return (
      <div>Cargando ...</div>
    );
  }

  return (
    <>
      <Button
        sx={{ p: "15px 0px 16px 15px", position: "fixed", bottom: "70px", right: "30px" }}
        variant='contained'
        size="small"
        startIcon={<AddOutlinedIcon />}
        onClick={handleOpenModal}>
      </Button>
      <HabitFormModal open={isModalOpen} handleClose={handleCloseModal} />

      <Typography variant='h4' component="h1" sx={{ fontWeight: "600", margin: "16px 0px" }}>
        Hábitos
      </Typography>

      <SearchBarWithFilters
        searchText={searchText}
        onChangeSearchText={handleSearchTextChange}
        orderBy={orderBy}
        onChangeOrderBy={handleOrderByChange}
      />

      <Box sx={styles.cardContainer}>
        {currentHabitsFiltered.map((habit, index) => (
          <CalendarWeekly
            habit={habit}
            tasks={habitLogs.find(log => log.id === habit.id)?.log || null}
            onComplete={handleAddLogEntry}
            options={habitsOptionsCard}
            key={index}
          />
        ))}
      </Box>
    </>
  );
};
