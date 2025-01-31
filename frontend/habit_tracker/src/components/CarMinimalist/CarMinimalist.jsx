import React, {useState} from "react";

import { Card, CardContent, Typography, Box, Button, IconButton, Menu, MenuItem  } from "@mui/material";

import MoreVertIcon from '@mui/icons-material/MoreVert';
import LocalFireDepartmentIcon from '@mui/icons-material/LocalFireDepartment';
import ShowChartOutlinedIcon from '@mui/icons-material/ShowChartOutlined';
import Divider from '@mui/material/Divider';

import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import MenuBookOutlinedIcon from '@mui/icons-material/MenuBookOutlined';
import FlatwareOutlinedIcon from '@mui/icons-material/FlatwareOutlined';
import AttachFileOutlinedIcon from '@mui/icons-material/AttachFileOutlined';

// import CalendarRowWeekDays from "../CalendarRowWeekDays/CalendarRowWeekDays";
import DayOfWeekChip from './DayOfWeekChip';
import { useNavigate } from "react-router-dom";

const iconMap = {
  DirectionsRunIcon: <DirectionsRunIcon />,
  MenuBookOutlinedIcon: <MenuBookOutlinedIcon />,
  FlatwareOutlinedIcon: <FlatwareOutlinedIcon />,
  AttachFileOutlinedIcon: <AttachFileOutlinedIcon />
};

const defaultOptions = [
  {id:0, name: "Ver mas"},
  {id:1, name: "Editar"}
]

const canCompleteToday = (days) => {
  const dayNumber = (new Date().getDay() + 6) % 7;
  const can = days.includes(dayNumber);

  //console.log(can);
  return can;
}

export const CarMinimalist = ({ habit, tasks, options = defaultOptions }) => {
  const navigate = useNavigate();
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
  const [ showCompleteButton, setShowCompleteButton] = useState(canCompleteToday(habit.daysWeekSet));

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
        padding: {xs: '16px 0px', md: "16px"}
      }     


 

  const handleClickComplete = (e) => {
    //console.log('click en complete');
    e.stopPropagation();
    setShowCompleteButton(false);
  }

  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleMoreClick = (event) => {
    event.stopPropagation();
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const handleOptionClick = (e,option) => {
    e.stopPropagation();
    console.log(`Option selected: ${option}`);
    setAnchorEl(null); // Cierra el menú después de seleccionar
  };

  const handleClickCard = () => {
    console.log(`Click en la card, ver habit con id ${habit.id}`);
    navigate(`/viewhabit/${habit.id}`);
  }

  
  //console.log(canCompleteToday(habit.daysWeekSet))

  return (
    <Card sx={cardStyles} onClick={handleClickCard}>
      <CardContent sx={cardContent}>

        {/* First Row */}
        <Box display="flex" alignItems="center" justifyContent="space-between" >
          <Box display="flex" alignItems="center">
            <Box sx={cardTitleIcon} >
              {iconMap[icon]}
            </Box>
            <Typography variant="subtitle1" sx={cardTitleText}>
              {title}
            </Typography>
          </Box>
          <IconButton aria-label="opciones" onClick={handleMoreClick}>
            <MoreVertIcon />
          </IconButton>
          {/* Menu Component */}
          <Menu
            anchorEl={anchorEl}
            open={open}
            onClose={handleMenuClose}
            anchorOrigin={{
              vertical: "bottom",
              horizontal: "right",
            }}
            transformOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
          >
            {options.map((option, index) => (
              <MenuItem key={index}
                onClick={(e) => handleOptionClick(e, option.id)}>
                {option.name}
              </MenuItem>
            ))}
          </Menu>
        </Box>

        {/* Second Row */}
        <Box sx={{ display: "flex",justifyContent: "space-between" }} >          
          {/* <CalendarRowWeekDays  tasks={tasks} daysWeekSet={daysWeekSet} />           */}
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



