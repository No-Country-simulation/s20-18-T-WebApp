import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter  } from 'react-router-dom';
import { ThemeProvider, createTheme } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";

//import './index.css'
import App from './App.jsx'

const theme = createTheme({
  palette: {
    mode: "light", // Cambia a "dark" para tema oscuro
    primary: {
      main: "#1976d2", // Azul por defecto
    },
    secondary: {
      main: "#f50057", // Rosa
    },
  },
  typography: {
    fontFamily: 'Outfit, sans-serif', // Reemplaza con tu fuente deseada font-family: "Outfit", serif;
  },
});

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <BrowserRouter >
        <App />
      </BrowserRouter >
    </ThemeProvider> 
  </StrictMode>,
)
