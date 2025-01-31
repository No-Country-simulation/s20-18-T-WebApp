import {
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,  
  Divider,
  Avatar,
  Typography,
  Box,  
} from '@mui/material';

import DashboardOutlinedIcon from '@mui/icons-material/DashboardOutlined';
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
import SystemUpdateAltIcon from '@mui/icons-material/SystemUpdateAlt';


import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom'; 

import { useUsers } from "../../contexts/UsersContext";

//import haruLogo from `${process.env.BASEURL}/images/HaruLogo.png`;

const mapLevelName = [
  'Principiante', 'Intermedio', 'Profesional', 'Experto'
]

const DrawerNavBar = () => {
  const [selectedIndex, setSelectedIndex] = useState(0); 
  const location = useLocation(); 
  const { users, isLoading } = useUsers(); 
  const [user, setUser] = useState({})

  const baseUrl = import.meta.env.VITE_BASE_URL;
  
  
  useEffect(() => {
    //console.log(users);
    if (!isLoading) {
      setUser(users[0]);
    }    
  }, [isLoading]);

  const navBarItems = [
    { id: 0, name: '', icon: '', href: '/profile' },  
    { id: 1, name: 'Inicio', icon: <DashboardOutlinedIcon />, href: '/' },
    { id: 2, name: 'Habitos', icon: <FavoriteBorderOutlinedIcon />, href: '/habits' },
    { id: 3, name: 'Archivados', icon: <SystemUpdateAltIcon />, href: '/Archives' },
    { id: 4, name: 'Cerrar sesión', icon: <LogoutOutlinedIcon />, href: '/logout' },    
  ];

  const handleListItemClick = (index) => {
    setSelectedIndex(index);
    console.log(index)
  };

  useEffect(() => {
     const path = location.pathname;
     const activeItemIndex = navBarItems.findIndex(item => item.href === path);
     if (activeItemIndex !== -1) {
       setSelectedIndex(activeItemIndex);
     }
   }, [location]);

if (isLoading) {
  return(
    <div>Cargando...
    </div>
  )
}

  return (
    <div >      
       <Box
          component="img"
          src={`${baseUrl}/images/HaruLogo.png`}
          alt="Haru logo"
          sx={{ width: 140, height: "auto", pl: "0.8em", pt: "1em" }}
        />         
      <List>      

        {navBarItems.map((navItem, index) => (
          <React.Fragment key={navItem.name}>
            {index === navBarItems.length - 1 && <Divider sx={{mt: "2em"}}/>}
            <ListItem key={navItem.name} disablePadding>
              <ListItemButton
                href={navItem.href}
                selected={selectedIndex === index} // Conditionally apply the 'selected' prop
                onClick={() => handleListItemClick(index)} // Update selectedIndex on click
                sx={{
                  '&.Mui-selected': { // Style for the selected state
                    backgroundColor: (theme) => theme.palette.action.selected, // Use a theme-aware color
                  },
                }}
              >
                {(navItem.id !== 0) ?  
                <>
                  <ListItemIcon sx={{ color: selectedIndex === index ? 'primary.main' : 'inherit' }}>
                    {navItem.icon}
                  </ListItemIcon>
                  <ListItemText primary={navItem.name} />
                </> :
                  <Box sx={{ display: 'flex', alignItems: 'center' }}>
                  <Avatar sx={{ mr: 2 }}>          
                    <img src={`${baseUrl}/images/${user.avatar}`} alt={user.name}
                      width="100%" height="auto"
                    />
                  </Avatar>
                  <div>
                    <Typography fontWeight="bold">{user.name}</Typography>
                    <Typography variant="body2" color="text.secondary">
                      {mapLevelName[user.level]}
                    </Typography>
                  </div>
                </Box>
              }

              </ListItemButton>
            </ListItem>
          </React.Fragment>
        ))}
      </List>
      {/*
       <Button sx={{width: "70%", ml: "50px", mb: "30px"}}
        variant='contained' 
        startIcon={<AddOutlinedIcon />}
        onClick={handleOpenModal}>
        Crear hábito
      </Button> 
      <HabitFormModal open={isModalOpen} handleClose={handleCloseModal} />*/}
    </div>
  );
}

export default DrawerNavBar;