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
    // Agrega más fechas según sea necesario
  };
  
  
  export const Archives = () => {

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

  const { habits, updateHabit, deleteHabit, isLoading } = useUsers(); 

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentHabits, setCurrentHabits] =useState(habits.filter(habit=>habit.archived==true))
    
      const handleOpenModal = () => {    
        setIsModalOpen(true);
      };
    
      const handleCloseModal = () => {
        setIsModalOpen(false);
      };

      const handleUnArchive = (e, habit) => {
        e.preventDefault();   
        //console.log('archivar desde habits');
        updateHabit(habit.id, {archived: false});
        navigate('/archives');
      }
      const handleDelete = (e, habit) => {
        //console.log(`Eliminando el habito ${habit.title}`);
        deleteHabit(habit.id);
        navigate('/archives');
      }

  const archivesOptionsCard = [
    {id:0, name: "Desarchivar", onClick: handleUnArchive},
    {id:1, name: "Eliminar", onClick: handleDelete}  
  ];

    useEffect(() => {
      setCurrentHabits(habits.filter(habit=>habit.archived==true));
    }, [habits]);

  if (isLoading) {
    return(
      <div>Cargando ...</div>
    )
  }  

  return (
    <>      
    {/* <Button sx={{ p: "15px 0px 16px 15px", position: "fixed", bottom: "70px", right: "30px"  }}
        variant='contained' 
        size="small"
        startIcon={<AddOutlinedIcon />}
        onClick={handleOpenModal}>        
      </Button> */}
      <HabitFormModal open={isModalOpen} handleClose={handleCloseModal} />

      <Typography variant='h4' component="h1" sx={{fontWeight: "600", margin: "16px 0px"}}>Hábitos archivados</Typography>   
      <SearchBarWithFilters />
      <Box sx={styles.cardContainer}>
      {currentHabits.map((habit, index) => (        
        <CalendarWeekly 
        habit={habit}        
        tasks={sampleTasks}
        options={archivesOptionsCard}
        key={index}
      />
      ))}        
      </Box>
    </>
  )
}
