import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button'; // Changed from IconButton to Button
import AddIcon from '@mui/icons-material/Add';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import Box from '@mui/material/Box';

// Importa la imagen que me pasaste
//import meditacionMindfulnessImage from '../assets/01.meditation.png'; // Asegúrate de que la ruta sea correcta


const CardHabitTemplate = ({habit}) => {
    const {habitImage, habitName, habitDescription, habitFrequency, habitDuration} = habit;
  return (
    <Card
      sx={{
        maxWidth: 360, // Ancho máximo de la card en mobile
        height: "470px",
        borderRadius: '16px', // Bordes redondeados como en la imagen
        boxShadow: '0px 4px 12px rgba(0, 0, 0, 0.08)', // Sombra suave
        backgroundColor: '#F9F9F9', // Color de fondo similar al de la imagen (opcional)
        display: 'flex',
        flexDirection: 'column', // Ensure content stacks vertically
      }}
    >
      <CardMedia
        component="img"
        sx={{
          height: 160, // Altura de la imagen, puedes ajustarla
          width: '100%', // Ancho al 100% del contenedor
          objectFit: 'cover', // Para que la imagen cubra el espacio sin deformarse
          maxWidth: '360px' // Ancho máximo de la imagen en mobile
        }}
        image={habitImage}
        alt={habitName}
      />
      <CardContent sx={{ padding: '24px', display: 'flex', flexDirection: 'column', justifyContent: 'space-between', height: '100%' }}> {/* Added flex column and space-between to CardContent */}
        <Box> {/* Box to group title and description */}
            <Typography gutterBottom variant="h5" component="div" sx={{ fontWeight: 'bold', color: '#333' }}>
            {habitName}
            </Typography>
            <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
            {habitDescription}
            </Typography>

            <Box sx={{ display: 'flex', alignItems: 'center', mb: 1 }}> {/* Replaced Grid with Box for Frequency */}
                <CalendarMonthIcon color="action" sx={{ mr: 1 }} />
                <Typography variant="body2" color="text.secondary" sx={{ mr: 2 }}>
                Frecuencia: {habitFrequency}
                </Typography>
            </Box>
            <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}> {/* Replaced Grid with Box for Duration */}
                <AccessTimeIcon color="action" sx={{ mr: 1 }} />
                <Typography variant="body2" color="text.secondary">
                Duración: {habitDuration}
                </Typography>
            </Box>
        </Box>


        <Box sx={{ width: '100%' }}> {/* Box to contain the button and take full width */}
            <Button
                fullWidth // Make the button full width
                variant="contained" // Use contained style for background color
                aria-label="add habit"
                sx={{
                backgroundColor: '#8E7AF7', // Color morado del botón
                color: 'white',
                borderRadius: '10px', // Bordes redondeados del botón
                padding: '12px', // Adjust padding for better visual
                '&:hover': {
                    backgroundColor: '#705DF0', // Un tono más oscuro al pasar el mouse
                },
                }}
                startIcon={<AddIcon />} // Add icon to the button
            >               
            </Button>
        </Box>
      </CardContent>
    </Card>
  );
};

export default CardHabitTemplate;