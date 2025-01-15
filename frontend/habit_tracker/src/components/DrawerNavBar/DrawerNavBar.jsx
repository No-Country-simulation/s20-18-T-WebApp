import {
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Toolbar
} from '@mui/material';
import HomeIcon from '@mui/icons-material/Home';
import PlaylistAddCheckIcon from '@mui/icons-material/PlaylistAddCheck';
import PermIdentityIcon from '@mui/icons-material/PermIdentity';
import TuneIcon from '@mui/icons-material/Tune';

import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom'; // If you are using React Router

const DrawerNavBar = () => {
  const [selectedIndex, setSelectedIndex] = useState(0); // Initialize with the default selected index
  const location = useLocation(); // If using React Router

  const navBarItems = [
    { id: 0, name: 'Inicio', icon: <HomeIcon />, href: '/' },
    { id: 1, name: 'Habitos', icon: <PlaylistAddCheckIcon />, href: '/habits' },
    { id: 2, name: 'Perfil', icon: <PermIdentityIcon />, href: '/profile' },
    { id: 3, name: 'Preferencias', icon: <TuneIcon />, href: '/settings' },
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

  return (
    <div>
      <Toolbar />
      <List>
        {navBarItems.map((navItem, index) => (
          <ListItem key={navItem.name} disablePadding>
            <ListItemButton
              href={navItem.href}
              selected={selectedIndex === index} // Conditionally apply the 'selected' prop
              onClick={() => handleListItemClick(index)} // Update selectedIndex on click
              sx={{
                '&.Mui-selected': { // Style for the selected state
                  backgroundColor: (theme) => theme.palette.action.selected, // Use a theme-aware color
                  // You can add more styles here for the selected state
                },
              }}
            >
              <ListItemIcon sx={{ color: selectedIndex === index ? 'primary.main' : 'inherit' }}>
                {navItem.icon}
              </ListItemIcon>
              <ListItemText primary={navItem.name} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
    </div>
  );
}

export default DrawerNavBar;