import React, { useState } from "react";
import { AppBar, Box, Drawer, Toolbar, Typography, IconButton } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import DrawerNavBar from "../components/DrawerNavBar/DrawerNavBar";
import haruLogo from '../assets/HaruLogo.png';

const drawerWidth = 300;

const Layout = ({ children }) => {
  const [mobileOpen, setMobileOpen] = useState(false);

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  return (
    <Box sx={{ display: "flex" }}>
      {/* AppBar for mobile */}
      <AppBar
        position="fixed"
        sx={{
          display: { sm: "block", md: "none" },
          top: "auto",
          top: 0,
          backgroundColor: "#fff", 
          width: { sm: `calc(100% - ${0}px)` },
        }}
      >
        <Toolbar sx={{ justifyContent: "space-between" }}>
          <Typography variant="h6" noWrap component="div">
          <Box
            component="img"
            src={haruLogo}
            alt="Haru logo"
            sx={{ width: 100, height: "auto" }}
          />
          </Typography>
          <IconButton color="inherit" aria-label="open drawer" onClick={handleDrawerToggle}>
            <MenuIcon sx={{color: "#222"}}/>
          </IconButton>
        </Toolbar>
      </AppBar>

      {/* Drawer for desktop */}
      <Drawer
        variant="permanent"
        sx={{
          display: { xs: "none", md: "block" },
          "& .MuiDrawer-paper": { width: drawerWidth, boxSizing: "border-box" },
        }}
        open
      >
        <DrawerNavBar />
      </Drawer>

      {/* Drawer for mobile */}
      <Drawer
        anchor="bottom"
        open={mobileOpen}
        onClose={handleDrawerToggle}
        sx={{
          display: { sm: "block", md: "none" },
          "& .MuiDrawer-paper": { height: "auto", minHeight: "50vh" },
        }}
      >
        <DrawerNavBar />
      </Drawer>

      {/* Main content */}
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          ml: { md: `${drawerWidth}px` },
          p: { xs: 2, md: 4 },
          mb: "100px",
          mt: "70px",
        }}
      >
        {children}
      </Box>
    </Box>
  );
};

export default Layout;
