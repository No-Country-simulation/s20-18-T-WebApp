import React from 'react'

import { Box } from '@mui/material';
import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import MenuBookOutlinedIcon from '@mui/icons-material/MenuBookOutlined';
import FlatwareOutlinedIcon from '@mui/icons-material/FlatwareOutlined';
import AttachFileOutlinedIcon from '@mui/icons-material/AttachFileOutlined';
import {CalendarWeekly} from '../../components/CalendarWeekly/CalendarWeekly';
import SearchBarWithFilters from '../../components/SearchBarWithFilters/SearchBarWithFilters';



const habitsList = [
  {
    icon: <DirectionsRunIcon />,
    title: 'Salir a correr',
    streak: 2,  
    porcCompletetion: '100%',
    unitName: 'Kms',
    unitsQty: 3.2,
    date: '15-01-2024',
    to: '/habits',
    color: '#49BE9F',
    daysWeekSet: [0,2,4]
  },
  {
    icon: <MenuBookOutlinedIcon/>,
    title: "Leer 'El quijote de la mancha'",
    streak: 1,
    porcCompletetion: '33%',
    unitName: 'Mins',
    unitsQty: 90,
    date: '15-01-2024',
    to: '/habits',
    color: '#7E71E1',
    daysWeekSet: [1, 2, 3, 5]
  },
  {
    icon: <FlatwareOutlinedIcon />,
    title: 'Tomar dos litros de agua por dia',
    streak: 1,  
    porcCompletetion: '66%',
    unitName: 'litros',
    unitsQty: 2,
    date: '16-01-2024',
    to: '/habits',
    color: '#EA7F89',
    daysWeekSet: [0, 1, 2, 3, 4, 5, 6]
  },
  {
    icon: <AttachFileOutlinedIcon />,
    title: 'Pagar los impuestos',
    streak: 0,  
    porcCompletetion: '0%',
    unitName: '',
    unitsQty: 0,
    date: '16-01-2024',
    to: '/habits',
    color: '#7E71E1',
    daysWeekSet: [4]
  },
]
const sampleTasks = {
    "2025-01-13": "completed",
    "2025-01-14": "pending",
    "2025-01-15": "completed",    
    // Agrega más fechas según sea necesario
  };

export const Habits = () => {
  return (
    <div>
      <h2>Hábitos</h2>      
      <SearchBarWithFilters />
      <Box display="flex"
        flexDirection= {{xs: "column", md: "row"}}
        alignItems="center" 
        justifyContent="center" 
        flexWrap="wrap"
        mb={2} gap="2em">
      {habitsList.map((habit, index) => (
        // <HabitCard habit={habit} key={index}/>
        <CalendarWeekly 
        habit={habit}        
        tasks={sampleTasks}
        key={index}
      />
      ))}        
      </Box>
    </div>
  )
}