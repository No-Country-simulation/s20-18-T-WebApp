import React, {useState, useEffect} from 'react'

import {  
  Avatar,
  Typography,
  Box,  
} from '@mui/material';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import LinearProgress from '@mui/material/LinearProgress';

import { useUsers } from "../../contexts/UsersContext";

export const Profile = () => {
  const { users, isLoading } = useUsers();
  const [user, setUser] = useState({}) 
  const [progress, setProgress] = useState(0);

  const baseUrl = import.meta.env.VITE_BASE_URL;

  const mapLevelName = [
    'Principiante', 'Intermedio', 'Profesional', 'Experto'
  ]

   useEffect(() => {      
      if (!isLoading) {
        setUser(users[0]);
        setProgress(50);
      }    
    }, [isLoading]);

  const cardStyles = {
    borderRadius: '1rem',
    width: {sx: "345", md: "560px"},
    boxShadow: 1,
    textDecoration: 'none', // Quitar subrayado del enlace
    color: 'inherit', // Heredar el color del texto
    cursor: 'pointer', // Indicar que es clickeable
    '&:hover': {
      boxShadow: 3, // Un ligero efecto al pasar el ratón        
    },
    backgroundColor: "#FCFCFC",
    border: "1px solid #DDD",
    marginTop: "2em"
  }

  return (
    <div>      
      <Card sx={cardStyles}>
        <CardContent>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <Avatar sx={{ mr: 2 }}>          
              <img src={`${baseUrl}/images/${user.avatar}`} alt={user.name}
                width="100%" height="auto"
              />
            </Avatar>
            <Box sx={{ width: '100%', display: "flex", flexDirection: "column", rowGap: "6px" }}>
              <Typography fontWeight="bold">{user.name}</Typography>
              <Typography variant="body2" color="text.secondary">
                {mapLevelName[user.level]}
              </Typography>
              <Box sx={{ width: '100%' }}>
                <Typography variant="body2" color="text.secondary">
                  125/250XP
                </Typography>
                <LinearProgress variant="determinate" value={progress} 
                  sx={{height: "10px", borderRadius: "10px", backgroundColor: "#BBB"}}/>
              </Box>
            </Box>
          </Box>
        </CardContent>
      </Card>
      <Card sx={cardStyles}>
        <CardContent>
        <Typography fontWeight="bold" variant="h5">Logros</Typography>
        <img src={`${baseUrl}/images/AchievmentsBadges.png`} alt='medallas de los logros' title="logros que podria obtener el usuario"
                width="100%" height="auto"
              />
        </CardContent>
      </Card>
    </div>
  )
}