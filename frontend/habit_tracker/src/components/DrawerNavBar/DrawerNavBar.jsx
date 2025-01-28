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
  Button
} from '@mui/material';

import DashboardOutlinedIcon from '@mui/icons-material/DashboardOutlined';
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';

import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom'; 
import HabitFormModal from '../HabitFormModal/HabitFormModal';
import { useUsers } from "../../contexts/UsersContext";

const mapLevelName = [
  'Principiante', 'Intermedio', 'Profesional', 'Experto'
]

const DrawerNavBar = () => {
  const [selectedIndex, setSelectedIndex] = useState(0); 
  const location = useLocation(); 
  const { users, isLoading } = useUsers(); 
  const [user, setUser] = useState({})
  
  useEffect(() => {
    //console.log(users);
    if (!isLoading) {
      setUser(users[0]);
    }    
  }, [isLoading]);
  

  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpenModal = () => {    
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const navBarItems = [
    { id: 0, name: 'Inicio', icon: <DashboardOutlinedIcon />, href: '/' },
    { id: 1, name: 'Habitos', icon: <FavoriteBorderOutlinedIcon />, href: '/habits' },
    { id: 2, name: 'Cerrar sesión', icon: <LogoutOutlinedIcon />, href: '/logout' },    
  ];

  const handleListItemClick = (index) => {
    setSelectedIndex(index);
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
      <Box sx={{ display: 'flex', alignItems: 'center', p: 2 }}>
        <Avatar sx={{ mr: 2 }}>          
          <img src={`./images/${user.avatar}`} alt={user.name}
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
      <Divider />
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
                <ListItemIcon sx={{ color: selectedIndex === index ? 'primary.main' : 'inherit' }}>
                  {navItem.icon}
                </ListItemIcon>
                <ListItemText primary={navItem.name} />
              </ListItemButton>
            </ListItem>
          </React.Fragment>
        ))}
      </List>
      <Button sx={{width: "70%", ml: "50px", mb: "30px"}}
        variant='contained' 
        startIcon={<AddOutlinedIcon />}
        onClick={handleOpenModal}>
        Crear hábito
      </Button>
      <HabitFormModal open={isModalOpen} handleClose={handleCloseModal} />
    </div>
  );
}

export default DrawerNavBar;