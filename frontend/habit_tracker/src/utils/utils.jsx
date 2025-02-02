import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import SelfImprovementIcon from '@mui/icons-material/SelfImprovement';
import SchoolIcon from '@mui/icons-material/School';
import BlockIcon from '@mui/icons-material/Block';
import LocalAtmIcon from '@mui/icons-material/LocalAtm';
import GroupsIcon from '@mui/icons-material/Groups';
import CategoryIcon from '@mui/icons-material/Category';

import meditacionMindfulnessImage from '../assets/01.meditation.png'; 
import runningImage from '../assets/02.running.png';
import readBookImage from '../assets/03.readBook.png';
import payTaxes from '../assets/04.tasks.png';


export const iconMap = {
    DirectionsRunIcon: <DirectionsRunIcon />,//actividad fisica
    SelfImprovementIcon: <SelfImprovementIcon />, //vida saludable
    BlockIcon: <BlockIcon />, //malos habitos
    SchoolIcon: <SchoolIcon />,// aprendizaje estudio
    LocalAtmIcon: <LocalAtmIcon />,//finanzas
    GroupsIcon: <GroupsIcon />,//vida social
    CategoryIcon: <CategoryIcon />, //otros
  };


export  const categories = [
    {id: 0, name: "Actividad Física", units: ['Metros', 'Kilómetros', 'Minutos', 'Veces'], icon: 'DirectionsRunIcon', color: '#49BE9F'}, 
    {id: 1, name: "Malos Hábitos", units: ['Minutos', 'Horas', 'Veces'], icon: 'BlockIcon', color: '#a92c38'},    
    {id: 2, name: "Aprendizaje/Estudio", units: ['Minutos', 'Horas', 'Páginas', 'Capítulos', 'Veces'], icon: 'SchoolIcon', color: '#7E71E1'},    
    {id: 3, name: "Finanzas", units: ['Minutos', 'Horas', 'Veces'], icon: 'LocalAtmIcon', color: '#136959'},    
    {id: 4, name: "Actividades Sociales", units: ['Minutos', 'Horas', 'Veces'], icon: 'GroupsIcon', color: '#EA7F89'},    
    {id: 5, name: "Vida saludable", units: ['Metros', 'Kilómetros', 'Horas', 'Minutos', 'Páginas', 'Capítulos', 'Veces'], icon: 'SelfImprovementIcon', color: '#2232ab'},
    {id: 6, name: "Otro hábito", units: ['Metros', 'Kilómetros', 'Horas', 'Minutos', 'Páginas', 'Capítulos', 'Veces'], icon: 'CategoryIcon', color: '#b74a06'},
    ];

   
    
  export  const habitsTemplates = [
      {
        habitImage: meditacionMindfulnessImage,
        habitName: "Meditación y Mindfulness",
        habitDescription: "Dedica unos minutos cada día para centrarte en el presente y calmar tu mente. Los ejercicios de respiración te ayudarán a reducir el estrés.",
        habitFrequency: "Diario",
        habitDuration: "30 minutos",
      },
      {
        habitImage: runningImage, 
        habitName: "Salir a correr",
        habitDescription: "Dedica unos minutos cada dia para correr y mejorar tus capacidades respiratorias.",
        habitFrequency: "Diario", 
        habitDuration: "1 hora", 
      },
      {
        habitImage: readBookImage, 
        habitName: "Leer un Libro",
        habitDescription: "Dedica tiempo a la lectura para expandir tu conocimiento, reducir el estrés y disfrutar de nuevas historias y perspectivas.",
        habitFrequency: "Lun, Mie, Vie", 
        habitDuration: "2 Horas", 
      },
      {
        habitImage: payTaxes, 
        habitName: "Pagar los Impuestos del Mes",
        habitDescription: "Organiza tus finanzas y cumple con tus obligaciones fiscales mensuales para evitar problemas y mantener tus cuentas en orden.",
        habitFrequency: "Mensual",
        habitDuration: "1 Hora", 
      },
    ];