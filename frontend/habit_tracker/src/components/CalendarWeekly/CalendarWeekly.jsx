import React from "react";
import { Card, CardContent, Typography, Box, IconButton } from "@mui/material";
import MoreVertIcon from '@mui/icons-material/MoreVert';
import LocalFireDepartmentIcon from '@mui/icons-material/LocalFireDepartment';
import ShowChartOutlinedIcon from '@mui/icons-material/ShowChartOutlined';
import Divider from '@mui/material/Divider';
import Chip from '@mui/material/Chip';

// Colores para los días según su estado
const dayColors = {
  completed: "#4caf50", // Verde
  pending: "#f44336", // Rojo
  default: "#e0e0e0", // Gris
};

export const CalendarWeekly = ({ habit, tasks }) => {
    const {
        icon,
        title,
        streak,
        porcCompletetion,
        unitName,
        unitsQty,
        date,
        to    
      } = habit
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

  return (
    <Card sx={{
        borderRadius: '1rem',
        width: {sx: "345", md: "460px"},
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
      }}>
      <CardContent >
        <Box display="flex" alignItems="center" justifyContent="space-between" mb={1}>
          <Box display="flex" alignItems="center">
            <Box
              sx={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                borderRadius: '5px',
                width: 30,
                height: 30,
                bgcolor: 'success.light',
                color: 'success.contrastText',
                mr: 1,
              }}
            >
              {icon}
            </Box>
            <Typography variant="subtitle1" sx={{ fontWeight: '600', fontSize: '1.3em', fontFamily: 'Outfit, Arial, sans-serif' }}>
              {title}
            </Typography>
          </Box>
          <IconButton aria-label="opciones">
            <MoreVertIcon />
          </IconButton>
        </Box>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            gap: 1, 
            mb: 2
          }}
        >
          {weekDays.map((day, index) => {
            const dayOfWeek = day.toLocaleDateString("es-ES", {
                weekday: "short",
              }); // Ejemplo: "lun."
              const dayNumber = day.toLocaleDateString("es-ES", {
                day: "numeric",
              }); // Ejemplo: "15"

            // Determina el color según el estado de la tarea
            const taskStatus = tasks[day.toISOString().split("T")[0]]; // Formato ISO: YYYY-MM-DD
            const backgroundColor =
              dayColors[taskStatus] || dayColors.default;

            return (
                <Box textAlign="center" key={index} sx={{display: "flex", flexDirection: "column", gap: "8px"}}>
                    <Typography sx={{ fontFamily: 'Outfit, Arial, sans-serif', textTransform: "capitalize", color:"#aaa"}}>{dayOfWeek}</Typography>
                    <Box
                        key={index}
                        sx={{
                        flex: 1,
                        backgroundColor,
                        borderRadius: "16px",
                        padding: "10px 14px",
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
        <Box display="flex" flexDirection="row" justifyContent="space-between" alignItems="center" gap="16px" width="100%" mt="16px">
        
          <Chip label="Todos los dias"  sx={{borderRadius: "8px", backgrounColor:"#DCE6FD", color: "#6494F6"}}/>
          <Box display="flex" flexDirection="row" alignItems="center" width="100px" gap="12px">  
            <Box display="flex" flexDirection="row" alignItems="center">            
                <LocalFireDepartmentIcon sx={{ color: '#FFB620', fontSize: '2em' }} />
                <Typography variant="h6" sx={{fontWeight: "600"}}>{streak}</Typography>            
            </Box>          
            <Box display="flex" flexDirection="row" alignItems="center">           
                <ShowChartOutlinedIcon sx={{ color: '#FFB620', fontSize: '2em' }} />
                <Typography variant="h6" sx={{fontWeight: "600"}} >{porcCompletetion}</Typography>            
            </Box>
          </Box>
        </Box>
      </CardContent>
    </Card>
  );
};

// Datos de ejemplo para las tareas



