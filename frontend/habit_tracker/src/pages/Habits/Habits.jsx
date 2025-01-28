import React from 'react'

import { Box, Typography } from '@mui/material';
import {CalendarWeekly} from '../../components/CalendarWeekly/CalendarWeekly';
import SearchBarWithFilters from '../../components/SearchBarWithFilters/SearchBarWithFilters';
import { useUsers } from "../../contexts/UsersContext";

const sampleTasks = {
    "2025-01-13": "completed",
    "2025-01-14": "pending",
    "2025-01-15": "completed",    
    "2025-01-20": "completed",
    // Agrega más fechas según sea necesario
  };
  
  
  const Habits = () => {

    const styles = {
      cardContainer: {    
        display: 'flex',
        flexDirection: { xs: 'column', md: 'row' },
        alignItems: 'center',
        justifyContent: 'center',
        flexWrap: 'wrap',
        mb: 2,
        gap: '2em'
      }  
    }

  const { habits, isLoading } = useUsers(); 

  if (isLoading) {
    return(
      <div>Cargando ...</div>
    )
  }

  console.log('habits')

  return (
    <>      
      <Typography variant='h4' component="h1" sx={{fontWeight: "600", margin: "16px 0px"}}>Hábitos</Typography>   
      <SearchBarWithFilters />
      <Box sx={styles.cardContainer}>
      {habits.map((habit, index) => (        
        <CalendarWeekly 
        habit={habit}        
        tasks={sampleTasks}
        key={index}
      />
      ))}        
      </Box>
    </>
  )
}
export default Habits;