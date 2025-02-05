import React, {useState, useEffect} from 'react'
import { useNavigate } from "react-router-dom";

import { Box, Button, Typography } from '@mui/material';
import {CalendarWeekly} from '../../components/CalendarWeekly/CalendarWeekly';
import SearchBarWithFilters from '../../components/SearchBarWithFilters/SearchBarWithFilters';
import { useUsers } from "../../contexts/UsersContext";
import HabitFormModal from '../../components/HabitFormModal/HabitFormModal';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';


const sampleTasks = {
    "2025-01-13": "completed",
    "2025-01-14": "pending",
    "2025-01-15": "completed",    
    "2025-01-20": "completed",
    "2025-02-03": "completed",
    // "2025-02-04": "pending",
    "2025-02-05": "completed",
    "2025-02-06": "pending",
    //"2025-02-07": "completed",
    //"2025-02-08": "pending",
    //"2025-02-09": "completed",
    
    // Agrega más fechas según sea necesario
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
    }

  const { habits, habitLogs, updateHabit, updateHabitLogs,  isLoading } = useUsers(); 

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentHabits, setCurrentHabits] =useState(habits.filter(habit=>habit.archived==false))
    
      const handleOpenModal = () => {    
        setIsModalOpen(true);
      };
      
      const handleCloseModal = () => {
        setIsModalOpen(false);
      };

      const handleClickCard = (e,habit) => {    
        e.preventDefault();
        navigate(`/viewhabit/${habit.id}`);
      }
      
      const handleArchive = (e, habit) => {
        e.preventDefault();   
        //console.log('archivar desde habits');
        updateHabit(habit.id, {archived: true});
        navigate('/habits');
      };

      //newLogStatus = "completed" or "pending"
        const handleAddLogEntry = (habitIdToUpdate) => {
          console.log('adding habbit log');          
          const newLogDate = "2025-02-04";      
          const newLogStatus="completed";
          updateHabitLogs(habitIdToUpdate, newLogDate, newLogStatus);
        };
      
      const habitsOptionsCard = [
        {id:0, name: "Ver más", onClick: handleClickCard},
        // {id:1, name: "Agregar a favoritos", onClick: ()=>{console.log('sin funcion todavia')}},
        {id:2, name: "Editar", onClick: ()=>{console.log('sin funcion todavia')}} , 
        {id:3, name: "Archivar", onClick: handleArchive} ,
      ];

      useEffect(() => {
        setCurrentHabits(habits.filter(habit=>habit.archived==false));
      }, [habits]);
      
      if (isLoading) {
    return(
      <div>Cargando ...</div>
    )
  }

  //console.log(habitLogs)

  return (
    <>      
    <Button sx={{ p: "15px 0px 16px 15px", position: "fixed", bottom: "70px", right: "30px"  }}
        variant='contained' 
        size="small"
        startIcon={<AddOutlinedIcon />}
        onClick={handleOpenModal}>        
      </Button>
      <HabitFormModal open={isModalOpen} handleClose={handleCloseModal} />

      <Typography variant='h4' component="h1" sx={{fontWeight: "600", margin: "16px 0px"}}>Hábitos</Typography>   
      <SearchBarWithFilters />
      <Box sx={styles.cardContainer}>
      {currentHabits.map((habit, index) => (        
        <CalendarWeekly 
          habit={habit}        
          tasks={habitLogs.find(log=>log.id==habit.id).log}   
          onComplete={handleAddLogEntry}     
          options={habitsOptionsCard}
          key={index}
      />
      ))}        
      </Box>
    </>
  )
}
