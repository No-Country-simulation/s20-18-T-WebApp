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

const iconMap = {
  DirectionsRunIcon: <DirectionsRunIcon />,
  MenuBookOutlinedIcon: <MenuBookOutlinedIcon />,
  FlatwareOutlinedIcon: <FlatwareOutlinedIcon />,
  AttachFileOutlinedIcon: <AttachFileOutlinedIcon />
};

import DayOfWeekChip from './DayOfWeekChip';

export const CarMinimalist = ({ habit, tasks }) => {
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
    console.log('Click en la card')
  }

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
            <MenuItem onClick={(e) => handleOptionClick(e,"Ver mas")}>
              Ver mas
            </MenuItem>
            <MenuItem onClick={(e) => handleOptionClick(e,"Editar")}>
              Editar
            </MenuItem>
            <MenuItem onClick={(e) => handleOptionClick(e,"Archivar")}>
              Archivar
            </MenuItem>
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



