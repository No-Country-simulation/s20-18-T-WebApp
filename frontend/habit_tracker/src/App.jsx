import { useState } from 'react'
import { Routes, Route } from 'react-router-dom'

import {
  AppBar,
  Box,  
  Drawer,  
  Toolbar,
  Typography,
  IconButton,
} from '@mui/material';
import '@fontsource/outfit';

import MenuIcon from '@mui/icons-material/Menu';
import DrawerNavBar from './components/DrawerNavBar/DrawerNavBar';


//import './App.css'
import { Footer, Header } from './components/index';
import { Home, Habits, Profile, Settings } from './pages/index';
const drawerWidth = 240;

function App() {
  const [mobileOpen, setMobileOpen] = useState(false);

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };
  return (
    <Box sx={{ display: 'flex' }}>
      
      {/* AppBar for mobile */}
      <AppBar
        position="fixed"
        sx={{
          display: { sm: 'block', md: 'none' },
          top: 'auto', // Override default top positioning
          bottom: 0,    // Stick to the bottom
          width: { sm: `calc(100% - ${0}px)` },
        }}
      >
                <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Typography variant="h6" noWrap component="div">
            My App
          </Typography>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerToggle}
          >
            <MenuIcon />
          </IconButton>
        </Toolbar>
      </AppBar>

      {/* Drawer for desktop (left) */}
      <Drawer
        variant="permanent"
        sx={{
          display: { xs: 'none', md: 'block' },
          position: 'static', // Changed to static
          '& .MuiDrawer-paper': { width: drawerWidth, boxSizing: 'border-box' },
        }}
        open
      >
       <DrawerNavBar/>
      </Drawer>

      {/* Drawer for mobile (bottom) */}
      <Drawer
        anchor="bottom"
        open={mobileOpen}
        onClose={handleDrawerToggle}
        sx={{
          display: { sm: 'block', md: 'none' },
          '& .MuiDrawer-paper': { height: 'auto', minHeight: '50vh' }, // Adjust height as needed
        }}
      >
       <DrawerNavBar/>
      </Drawer>

      {/* Main content */}
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          ml: { md: `${drawerWidth}px` }, // Add margin-left to shift content away from static drawer
          position: "static",
          width: { sm: '100%', md: `calc(100% - ${drawerWidth}px)` },
          p: {xs: 2, md:4},
        }}
      >
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/habits' element={<Habits />} />
          <Route path='/profile' element={<Profile />} />
          <Route path='/settings' element={<Settings />} />
        </Routes>  

        
      </Box>
    </Box>
  )
}
export default App
