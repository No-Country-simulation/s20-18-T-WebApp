import React from "react";
import { Box, Typography } from "@mui/material";

const dayColors = {
  completed: "#4caf50", // Verde
  pending: "#f44336", // Rojo
  setted: "#ddd",
  default: "none", // Gris
};

  // Calcula la semana actual comenzando desde el lunes
  const getWeekDays = () => {
    const today = new Date();
    const currentDay = today.getDay(); // 0 para domingo, 6 para sábado
    const monday = new Date(today);
    monday.setDate(today.getDate() - (currentDay === 0 ? 6 : currentDay - 1));

    return Array.from({ length: 7 }, (_, i) => {
      const date = new Date(monday);
      date.setDate(monday.getDate() + i);
      return date;
    });
  };

  const weekDays = getWeekDays();

const CalendarRowWeekDays = ({ tasks, daysWeekSet }) => {
  return (
    <Box sx={{ display: "flex", justifyContent: "space-between" }}>
      {weekDays.map((day, index) => {
        const dayOfWeek = day.toLocaleDateString("es-ES", { weekday: "short" });
        const dayNumber = day.toLocaleDateString("es-ES", { day: "numeric" });
        const currentIsSetted = daysWeekSet.some((d) => d === index);

        // Determina el color según el estado de la tarea
        const taskStatus = tasks[day.toISOString().split("T")[0]]; // Formato ISO: YYYY-MM-DD
        let backgroundColor = dayColors.default;
        if (currentIsSetted) {
          backgroundColor = dayColors[taskStatus] || dayColors.setted;
        }

        const today = new Date();
        const isToday =
          day.getDate() === today.getDate() &&
          day.getMonth() === today.getMonth() &&
          day.getFullYear() === today.getFullYear();

        return (
          <Box
            textAlign="center"
            key={index}
            sx={{ display: "flex", flexDirection: "column", gap: "8px" }}
          >
            <Typography
              sx={{
                fontFamily: "Outfit, Arial, sans-serif",
                textTransform: "capitalize",
                color: "#aaa",
              }}
            >
              {dayOfWeek}
            </Typography>
            <Box
              sx={{
                flex: 1,
                backgroundColor,
                border: isToday ? "3px solid #222" : undefined,
                borderRadius: "12px",
                padding: { xs: "4px 0.5em 2px 0.5em", md: "0.7em 1em 0.5em 1em" },
                textAlign: "center",
                color: "#222",
              }}
            >
              <Typography sx={{ fontSize: "1.4em" }}>{dayNumber}</Typography>
            </Box>
          </Box>
        );
      })}
    </Box>
  );
};

export default CalendarRowWeekDays;
