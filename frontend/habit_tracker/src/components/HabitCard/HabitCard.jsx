import React from 'react';
import { Link } from 'react-router-dom';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import LocalFireDepartmentIcon from '@mui/icons-material/LocalFireDepartment';
import ShowChartOutlinedIcon from '@mui/icons-material/ShowChartOutlined';
import Divider from '@mui/material/Divider';

const HabitCard = ({habit}) => {
  const {
    icon,
    title,
    streak,
    porcCompletetion,
    unitName,
    unitsQty,
    date,
    to    
  } = habit
  
  return (
    <Card
      component={Link} // Usamos Link de react-router-dom
      to={to} // La URL se pasa como prop
      sx={{
        borderRadius: '1rem',
        width: {sx: "345", md: "560px"},
        boxShadow: 1,
        textDecoration: 'none', // Quitar subrayado del enlace
        color: 'inherit', // Heredar el color del texto
        cursor: 'pointer', // Indicar que es clickeable
        '&:hover': {
          boxShadow: 3, // Un ligero efecto al pasar el ratón        
        },
        backgroundColor: "#FCFCFC"
      }}
    >
      <CardContent>
        <Box display="flex" alignItems="center" justifyContent="space-between" mb={2}>
          <Box display="flex" alignItems="center">
            <Box
              sx={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                borderRadius: '5px',
                width: 30,
                height: 30,
                bgcolor: 'success.light',
                color: 'success.contrastText',
                mr: 1,
              }}
            >
              {icon}
            </Box>
            <Typography variant="subtitle1" sx={{ fontWeight: '600', fontSize: '1.3em' }}>
              {title}
            </Typography>
          </Box>
          <IconButton aria-label="opciones">
            <MoreVertIcon />
          </IconButton>
        </Box>

        <Box display="flex" justifyContent="space-around" alignItems="center" gap="16px">
          <Box display="flex" flexDirection="column" alignItems="center">
            <Box display="flex" alignItems="center">
              <LocalFireDepartmentIcon sx={{ color: '#FFB620', fontSize: '2.5em' }} />
              <Typography variant="h6" sx={{fontWeight: "600"}}>{streak}</Typography>
            </Box>
          </Box>
          <Divider orientation="vertical" variant="middle" flexItem />
          <Box display="flex" flexDirection="column" alignItems="center">
            <Box sx={{ display: 'flex', alignItems: 'center' }}>
              <ShowChartOutlinedIcon sx={{ color: '#FFB620', fontSize: '2.5em' }} />
              <Typography variant="h6" sx={{fontWeight: "600"}} >{porcCompletetion}</Typography>
            </Box>
          </Box>
          <Divider orientation="vertical" variant="middle" flexItem />
          <Box display="flex" flexDirection="column" alignItems="center">
            <Box sx={{ display: 'flex', alignItems: 'center', gap:"8px"}}>              
              {React.cloneElement(icon, { sx: { color: '#FFB620', fontSize: '2.5em' } })}
              <Typography variant="h6"  sx={{fontWeight: "600"}}>{`${unitsQty} ${unitName}`}</Typography>
            </Box>
          </Box>
        </Box>
      </CardContent>
    </Card>
  );
};

export default HabitCard;