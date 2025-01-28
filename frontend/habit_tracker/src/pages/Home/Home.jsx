import React, { useState, useEffect } from 'react'
import { useUsers } from "../../contexts/UsersContext";
import { Box, Typography } from '@mui/material';
import { CarMinimalist } from '../../components/CarMinimalist/CarMinimalist';

const styles = {
  headerContainer: {
    display: "flex",
    flexDirection: "column",
    gap: "1em",
  },
  cardContainer: {    
    display: 'flex',
    flexDirection: { xs: 'column', md: 'row' },
    alignItems: 'center',
    justifyContent: 'center',
    flexWrap: 'wrap',
    mb: 2,
    mt: 2,
    gap: '2em'
  }  
}

const Home = () => {
  const { users, habits, isLoading } = useUsers(); 
  const [user, setUser] = useState({});


   useEffect(() => {      
      if (!isLoading) {
        setUser(users[0]);
      }    
    }, [isLoading]);

    if (isLoading){
      return(
        <div>Cargando ...</div>
      )
    }
    //console.log(habits)
    
  return (
    
    <div>   
      <Box component='header' sx={styles.headerContainer}>   
        <Typography variant="h5" color='primary' component="h1" sx={{ fontWeight: "600"}}>
          {`Bienvenido, ${user.name}`}
        </Typography>
        <Typography  variant="h6"  component="h2"  sx={{ fontWeight: "600"}}>
          Actividades de hoy
        </Typography>
      </Box>
      <Box sx={styles.cardContainer}>
        {habits.map((habit, index)=>
          <CarMinimalist habit={habit} key={`habit_${index}`}/> 
        )}
      </Box>
      <Box sx={styles.header}>
        <Typography  variant="h6"  component="h2"  sx={{ fontWeight: "600"}}>
            Hábitos que te pueden interesar
        </Typography>
      </Box>
    </div>
  )
}
export default Home;
