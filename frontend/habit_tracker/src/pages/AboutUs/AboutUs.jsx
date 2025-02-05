import React from 'react';


import {  
  Avatar,
  Typography,
  Box,
  Button,  
} from '@mui/material';
import OpenInNewIcon from '@mui/icons-material/OpenInNew';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import { Link } from 'react-router-dom';



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

const baseUrl = import.meta.env.VITE_BASE_URL;

const integrantes = [
  {
    nombre: "Sara Alcantara",
    rol: "Product manager",
    imagen: "SaraAlcantara.png",
    enlace: "",
  },
  {
    nombre: "Santiago Garcia",
    rol: "UX/UI",
    imagen: "SantiagoGarcia.png",
    enlace: "",
  },
  {
    nombre: "Anadela Hernandez",
    rol: "UX/UI",
    imagen: "AnadelaHernandez.png",
    enlace: "",
  },
  {
    nombre: "Cesar Ruscica",
    rol: "Frontend",
    imagen: "CesarRuscica.png",
    enlace: "https://impulsainternet.com/ruscica",
  },
  {
    nombre: "Jeniree Suarez",
    rol: "QA Tester",
    imagen: "JenireeSuarez.png",
    enlace: "",
  },
]

export const AboutUs = () => {
  return (
    <div>
      <h2>Sobre nosotos</h2>
      <p>Quienes somos los que hicimos esta aplicacion. </p>
      <div>      
      {integrantes.map((integrante, index) => (
        <Card sx={cardStyles} key={index}>
        <CardContent>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <Avatar sx={{ mr: 4, width:"70px", height: "70px" }}>          
              <img src={`${baseUrl}/images/integrantes/${integrante.imagen}`} alt={`imagen de ${integrante.nombre}`}
                width="100%" height="auto"
              />
            </Avatar>
            <Box sx={{ width: '100%', display: "flex", flexDirection: "column", rowGap: "6px" }}>
              <Typography fontWeight="bold">
                {integrante.nombre}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {integrante.rol}
              </Typography>   

              {
              (integrante.enlace !== '') &&
              <Button
              component={Link}  // Usa el componente Link de react-router-dom
              to={integrante.enlace} // Pasa la URL al atributo to
              target="_blank"    // Abre el enlace en una nueva pestaña
              rel="noopener noreferrer" // Agrega por seguridad cuando usas target="_blank"
              variant="outlined"
              endIcon={<OpenInNewIcon />}
              >
              {integrante.enlace}
            </Button>
            }

            </Box>
          </Box>  
        </CardContent>
      </Card>
      ))}
    </div>  
  </div>
  )
}