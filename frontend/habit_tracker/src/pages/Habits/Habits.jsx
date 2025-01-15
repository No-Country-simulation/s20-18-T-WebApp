import React from 'react'
import HabitCard from '../../components/HabitCard/HabitCard';
import { Box } from '@mui/material';
import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import MenuBookOutlinedIcon from '@mui/icons-material/MenuBookOutlined';

const habitsList = [
  {
    icon: <DirectionsRunIcon />,
    title: 'Salir a correr',
    streak: 2,
    porcCompletetion: '12%',
    unitName: 'Kms',
    unitsQty: 3.2,
    date: '15-01-2024',
    to: '/habits'
  },
  {
    icon: <MenuBookOutlinedIcon/>,
    title: 'Leer mas',
    streak: 4,
    porcCompletetion: '32%',
    unitName: 'Mins',
    unitsQty: 90,
    date: '15-01-2024',
    to: '/habits'
  },
]

export const Habits = () => {
  return (
    <div>
      <h2>Habits</h2>
      <p>En esta pagina podras ver todos los habitos que actualmente estas siguiendo.</p>
      <Box display="flex"
        flexDirection= {{xs: "column", md: "row"}}
        alignItems="center" 
        justifyContent="center" 
        mb={2} gap="2em">
      {habitsList.map((habit, index) => (
        <HabitCard
          key={index} // Importante para el rendimiento de React al renderizar listas
          icon={habit.icon}
          title={habit.title}
          streak={habit.streak}
          porcCompletetion={habit.porcCompletetion}
          unitName={habit.unitName}
          unitsQty={habit.unitsQty}
          date={habit.date}
          to={habit.to} // Generación dinámica de la URL
        />
      ))}        
      </Box>
    </div>
  )
}