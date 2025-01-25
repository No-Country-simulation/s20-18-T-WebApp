import React, {useState} from "react";
import { Card, CardContent, Typography, Box, IconButton } from "@mui/material";
import MoreVertIcon from '@mui/icons-material/MoreVert';
import LocalFireDepartmentIcon from '@mui/icons-material/LocalFireDepartment';
import ShowChartOutlinedIcon from '@mui/icons-material/ShowChartOutlined';
import Divider from '@mui/material/Divider';
import {Button} from "@mui/material";

import DayOfWeekChip from './DayOfWeekChip';

// Colores para los días según su estado
const dayColors = {
  completed: "#4caf50", // Verde
  pending: "#f44336", // Rojo
  setted: "#ddd",
  default: "none", // Gris
};


export const CalendarWeekly = ({ habit, tasks }) => {
  const [ showCompleteButton, setShowCompleteButton] = useState(true);
    const {
        icon,
        title,
        streak,
        porcCompletetion,        
        date,
        to,
        color,
        daysWeekSet    
      } = habit

      const cardStyles = {
        borderRadius: '1rem',
        width: {xs: "100%", md: "460px"},
        paddingTop: "0.2em",
        paddingLeft: "1em",
        paddingRight: "1em",
        boxShadow: 1,
        textDecoration: 'none', // Quitar subrayado del enlace
        color: 'inherit', // Heredar el color del texto
        cursor: 'pointer', // Indicar que es clickeable
        '&:hover': {
          boxShadow: 3, // Un ligero efecto al pasar el ratón        
        },
        backgroundColor: "#FCFCFC"
      }

      const cardTitleIcon = {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: '5px',
        width: 30,
        height: 30,
        bgcolor: color,
        color: '#FCFCFC',
        mr: 1,
      }      

      const cardTitleText = { fontWeight: '600', fontSize: '1.3em', lineHeight: "1em" };

      const cardContent = {
        display: "flex",
        flexDirection: "column",
        rowGap: "16px",
        padding: {xs: '0px', md: "16px"}
      }


  // Calcula la semana actual comenzando desde el lunes
  const getWeekDays = () => {
    const today = new Date();
    const currentDay = today.getDay(); // 0 para domingo, 6 para sábado
    const monday = new Date(today);
    monday.setDate(today.getDate() - (currentDay === 0 ? 6 : currentDay - 1));

    return Array.from({ length: 7 }, (_, i) => {
      const date = new Date(monday);
      date.setDate(monday.getDate() + i);
      return date;
    });
  };

  const weekDays = getWeekDays();

  const handleClickComplete = () => {
    //console.log('click en complete');
    setShowCompleteButton(false);
  }

  return (
    <Card sx={cardStyles}>
      <CardContent sx={cardContent}>

        {/* First Row */}
        <Box display="flex" alignItems="center" justifyContent="space-between" >
          <Box display="flex" alignItems="center">
            <Box sx={cardTitleIcon} >
              {icon}
            </Box>
            <Typography variant="subtitle1" sx={cardTitleText}>
              {title}
            </Typography>
          </Box>
          <IconButton aria-label="opciones">
            <MoreVertIcon />
          </IconButton>
        </Box>

        {/* Second Row */}
        <Box sx={{ display: "flex",justifyContent: "space-between" }} >
          {weekDays.map((day, index) => {
            const dayOfWeek = day.toLocaleDateString("es-ES", { weekday: "short" }); 
            const dayNumber = day.toLocaleDateString("es-ES", { day: "numeric" }); 
            const currentIsSetted = daysWeekSet.some(d => d == index)

            // Determina el color según el estado de la tarea
            const taskStatus = tasks[day.toISOString().split("T")[0]]; // Formato ISO: YYYY-MM-DD
            let backgroundColor = dayColors.default;
            if (currentIsSetted){
              backgroundColor = dayColors[taskStatus] ||  dayColors.setted ;
            }            
            const today = new Date();
            const isToday = day.getDate() === today.getDate() &&
                day.getMonth() === today.getMonth() &&
                day.getFullYear() === today.getFullYear();

            return (
                <Box textAlign="center" key={index} sx={{display: "flex", flexDirection: "column", gap: "8px"}}>
                    <Typography sx={{ fontFamily: 'Outfit, Arial, sans-serif', textTransform: "capitalize", color:"#aaa"}}>{dayOfWeek}</Typography>
                    <Box
                        key={index}
                        sx={{
                        flex: 1,
                        backgroundColor,
                        border: (isToday && { border: '3px solid #222' }),
                        borderRadius: "12px",
                        padding: {xs: "4px 0.5em 2px 0.5em", md: "0.7em 1em 0.5em 1em"},
                        textAlign: "center",
                        color: "#222",
                        }}
                    >                
                        <Typography sx={{fontSize: "1.4em"}}>{dayNumber}</Typography>
                    </Box>
                </Box>
            );
          })}
        </Box>
        <Divider orientation="horizontal" variant="middle" flexItem />

        {/* Third row */}
        <Box display="flex" flexDirection="row" justifyContent="space-between" alignItems="center"  width="100%" >       
          
          {
          (showCompleteButton) ?           
            <Button variant="contained" sx={{width: "100%", textTransform: 'capitalize'}} 
              onClick={handleClickComplete}>Completar</Button>          
          : 
          <>
            <DayOfWeekChip daysWeekSet={habit.daysWeekSet} color={color} />          
            <Box display="flex" flexDirection="row" alignItems="center"  >  
              <Box display="flex" flexDirection="row" alignItems="center">            
                  <LocalFireDepartmentIcon sx={{ color: '#FFB620', fontSize: '2em' }} />
                  <Typography variant="h6" sx={{fontWeight: "600"}}>{streak}</Typography>            
              </Box>          
              <Box display="flex" flexDirection="row" alignItems="center">           
                  <ShowChartOutlinedIcon sx={{ color: '#FFB620', fontSize: '2em' }} />
                  <Typography variant="h6" sx={{fontWeight: "600"}} >{porcCompletetion}</Typography>            
              </Box>
            </Box>
          </>          
          }
        </Box>
      </CardContent>
    </Card>
  );
};

// Datos de ejemplo para las tareas



