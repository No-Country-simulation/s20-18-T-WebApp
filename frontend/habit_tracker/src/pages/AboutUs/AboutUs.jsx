import React from 'react';
import {Link} from 'react-router-dom';

import {  
  Avatar,
  Typography,
  Box   
} from '@mui/material';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';

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
}

const baseUrl = import.meta.env.VITE_BASE_URL;

const integrantes = [
  {
    nombre: "Sara Alcantara",
    rol: "Product manager",
    imagen: "SaraAlcantara.png",
    enlaces: ['https://www.linkedin.com/in/sara-e-alcantara-r']
  },
  {
    nombre: "Santiago Garcia",
    rol: "UX/UI",
    imagen: "SantiagoGarcia.png",
    enlaces: ['https://www.linkedin.com/in/santiagogarciaa',
              'https://www.behance.net/santiagogarciaa'
    ]
  },
  {
    nombre: "Anadela Hernandez",
    rol: "UX/UI",
    imagen: "AnadelaHernandez.png",
    enlaces: ['https://www.linkedin.com/in/anadelahernandez',
              'https://www.behance.net/anadelahernndez2'
    ]
  },
  {
    nombre: "Cesar Ruscica",
    rol: "Frontend",
    imagen: "CesarRuscica.png",
    enlaces: ["https://impulsainternet.com/ruscica"],
  },
  {
    nombre: "Jeniree Suarez",
    rol: "QA Tester",
    imagen: "JenireeSuarez.png",
    enlaces: ["https://www.linkedin.com/in/jenireesuarez/"]
  },
]

const styles = {
  cardContainer: {
    display: "flex", 
    flexDirection: "row",
    flexWrap: 'wrap',
    gap: "1.5em"}
}

export const AboutUs = () => {
  
  return (
    <div >
      <h2>Sobre nosotros</h2>
      <p>Quienes somos los que hicimos esta aplicación. Por orden alfabético.</p>
      <div style={styles.cardContainer}>      
      {integrantes.map((integrante, index) => (
        <Card sx={cardStyles} key={index}>
        <CardContent>
          <Box sx={{ display: 'flex',  alignItems: 'center', mb: "8px" }}>
            <Avatar sx={{ mr: 4, width:"70px", height: "70px" }}>          
              <img src={`${baseUrl}/images/integrantes/${integrante.imagen}`} alt={`imagen de ${integrante.nombre}`}
                width="100%" height="auto"
              />
            </Avatar>
            <Box sx={{ width: '100%', display: "flex", flexDirection: "column", rowGap: "6px" }}>
              <Typography fontWeight="bold" fontSize='20px'>
                {integrante.nombre}
              </Typography>
              <Typography variant="body2" color="text.secondary">
                {integrante.rol}
              </Typography>   
            </Box>
          </Box>  
          <Box sx={{ width: '100%', display: "flex", flexDirection: "column", rowGap: "18px" }}>
          {
          (integrante.enlaces.length > 0) ?
          integrante.enlaces.map((enlace, index)=> (
        <Link             
            key={index}              
            to={enlace} 
            target="_blank"    
            rel="noopener noreferrer"                        
            >
            {/* <OpenInNewIcon /> */}
            <Typography variant='body1' 
              sx={{
                wordBreak: 'break-all',  
                backgroundColor: "#dce6fd",
                color: '#2232ab',
                padding: "10px",
                borderRadius: "10px",
                textAlign: "center"                
                }}>
              {enlace}
            </Typography>
        </Link>))
        : null  // Use null instead of empty string
        }
        </Box>
        </CardContent>
      </Card>
      ))}
    </div>  
  </div>
  )
}