import React, { useState, useEffect } from 'react';
import { useUsers } from "../../contexts/UsersContext";
import { Box, Button, Typography } from '@mui/material';
import { CarMinimalist } from '../../components/CarMinimalist/CarMinimalist';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import HabitFormModal from '../../components/HabitFormModal/HabitFormModal';
import Carousel from 'react-material-ui-carousel';
import CardHabitTemplate from '../../components/CardHabitTemplate';

import { habitsTemplates } from '../../utils/utils';

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
    mb: 4,
    mt: 2,
    gap: '2em'
  },
  carouselContainer: {
    width: '100%',    
    maxWidth: '500px', // Ajusta según sea necesario
    margin: '2em auto',    
  },
  carouselItem: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',    
  }
};

export const Home = () => {
  const { users, habits, habitLogs, isLoading } = useUsers(); 
  const [user, setUser] = useState({});
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentHabits, setCurrentHabits] =useState(habits.filter(habit=>habit.archived==false))

  const handleOpenModal = () => {    
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  }; 

  const habitsOptionsCard = [
    {id:0, name: "Ver más", onClick: null},
    {id:1, name: "Agregar a favoritos", onClick: null},
    {id:2, name: "Editar", onClick: null} , 
    {id:3, name: "Archivar", onClick: null} ,
  ];

  useEffect(() => {      
    if (!isLoading) {
      setUser(users[0]);
      setCurrentHabits(habits.filter(habit=>habit.archived==false));
    }    
  }, [isLoading, habits]);

  if (isLoading){
    return(
      <div>Cargando ...</div>
    )
  }

  return (
    <div>   
      <Button sx={{ p: "15px 0px 16px 15px", position: "fixed", bottom: "70px", right: "30px", zIndex: "100"  }}
        variant='contained' 
        size="small"
        startIcon={<AddOutlinedIcon />}
        onClick={handleOpenModal}>        
      </Button>
      <HabitFormModal open={isModalOpen} handleClose={handleCloseModal} />

      <Box component='header' sx={styles.headerContainer}>   
        <Typography variant="h5" color='primary' component="h1" sx={{ fontWeight: "600"}}>
          {`Bienvenido, ${user.name}`}
        </Typography>
        <Typography  variant="h6"  component="h2"  sx={{ fontWeight: "600"}}>
          Actividades de hoy
        </Typography>
      </Box>

      <Box sx={styles.carouselContainer}>
        <Carousel
          autoPlay={false}
          animation="slide"          
          navButtonsAlwaysVisible={false}
          indicators={true}          
        >
          {currentHabits.map((habit, index) => (
            <Box key={`habit_${index}`} sx={styles.carouselItem}>
              <CarMinimalist habit={habit} tasks={habitLogs.find(log=>log.id==habit.id).log} options={habitsOptionsCard} />
            </Box>
          ))}
        </Carousel>
      </Box>

      <Box sx={styles.header}>
        <Typography  variant="h6"  component="h2"  sx={{ fontWeight: "600"}}>
            Hábitos que te pueden interesar
        </Typography>
      </Box>

      <Box sx={styles.carouselContainer}>
        <Carousel
          autoPlay={false}
          animation="slide"
          navButtonsAlwaysVisible={false}
          indicators={true}
        >
          {habitsTemplates.map((habit, index) => (
            <Box key={`habit_${index}`} sx={styles.carouselItem}>
              <CardHabitTemplate habit={habit} options={habitsOptionsCard} />
            </Box>
          ))}
        </Carousel>
      </Box>
       
    </div>
  );
};

