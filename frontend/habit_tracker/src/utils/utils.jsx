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
    {id: 0, name: "Actividad Física", units: ['Metros', 'Kilómetros', 'Minutos', 'Veces'], icon: 'DirectionsRunIcon', color: '#32CD32'}, 
    {id: 1, name: "Malos Hábitos", units: ['Minutos', 'Horas', 'Veces'], icon: 'BlockIcon', color: '#F0294A'},    
    {id: 2, name: "Aprendizaje/Estudio", units: ['Minutos', 'Horas', 'Páginas', 'Capítulos', 'Veces'], icon: 'SchoolIcon', color: '#2A71D0'},    
    {id: 3, name: "Finanzas", units: ['Minutos', 'Horas', 'Veces'], icon: 'LocalAtmIcon', color: '#2E8B57'},    
    {id: 4, name: "Actividades Sociales", units: ['Minutos', 'Horas', 'Veces'], icon: 'GroupsIcon', color: '#F4A261'},    
    {id: 5, name: "Vida saludable", units: ['Metros', 'Kilómetros', 'Horas', 'Minutos', 'Páginas', 'Capítulos', 'Veces'], icon: 'SelfImprovementIcon', color: '#FFD166'},
    {id: 6, name: "Otro hábito", units: ['Metros', 'Kilómetros', 'Horas', 'Minutos', 'Páginas', 'Capítulos', 'Veces'], icon: 'CategoryIcon', color: '#A0A0A0'},
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

    export const canCompleteToday = (habit, tasks) => {
      // Obtener la fecha actual en formato YYYY-MM-DD (local)
      const today = new Date();
      const todayString = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;
    
      // Verificar si ya existe una tarea para hoy
      const hasTaskForToday = tasks?.some(task => task.date === todayString);
    
      // Si existe una tarea para hoy (en cualquier estado), retornar false
      if (hasTaskForToday) return false;
    
      // Verificar si el hábito está programado para hoy
      const dayNumber = (today.getDay() + 6) % 7; // Lunes = 0, Domingo = 6
      return habit.daysWeekSet.includes(dayNumber);
    };

    export const getCurrentDate = () => {
      const today = new Date();
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, '0'); // Meses van de 0-11
      const day = String(today.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    };