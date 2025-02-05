import React from "react";
import { Box, Typography } from "@mui/material";

const dayColors = {
    completed: "#4caf50", // Verde
    pending: "#f44336", // Rojo
    setted: "#ddd",      // Gris para fechas futuras con tarea
    default: "none",     // Sin color
};

const getWeekDays = () => {
    const today = new Date();
    const currentDay = today.getDay(); // 0 (domingo) a 6 (sábado)
    const monday = new Date(today);
    monday.setDate(today.getDate() - (currentDay === 0 ? 6 : currentDay - 1));

    return Array.from({ length: 7 }, (_, i) => {
        const date = new Date(monday);
        date.setDate(monday.getDate() + i);
        return date;
    });
};

const CalendarRowWeekDays = ({ tasks }) => {
    const weekDays = getWeekDays();
    const today = new Date();
    const todayString = `${today.getFullYear()}-${String(today.getMonth() + 1).padStart(2, '0')}-${String(today.getDate()).padStart(2, '0')}`;

    return (
        <Box sx={{ display: "flex", justifyContent: "space-between", width: "100%", gap: "6px" }}>
            {weekDays.map((day, index) => {
                const dayOfWeek = day.toLocaleDateString("es-ES", { weekday: "short" });
                const dayNumber = day.getDate();
                
                // Formato YYYY-MM-DD en hora local
                const dayString = `${day.getFullYear()}-${String(day.getMonth() + 1).padStart(2, '0')}-${String(day.getDate()).padStart(2, '0')}`;
                const taskForDay = tasks.find(task => task.date === dayString);

                // Determinar color
                let backgroundColor = dayColors.default;
                if (taskForDay) {
                    if (dayString > todayString) {
                        backgroundColor = dayColors.setted; // Fecha futura
                    } else {
                        backgroundColor = dayColors[taskForDay.status] || dayColors.setted;
                    }
                }

                // Verificar si es hoy
                const isToday = dayString === todayString;

                return (
                    <Box textAlign="center" key={index} sx={{ display: "flex", flexDirection: "column", minWidth: "36px", gap: "6px" }}>
                        <Typography sx={{ fontFamily: "Outfit, Arial, sans-serif", textTransform: "capitalize", color: "#aaa" }}>
                            {dayOfWeek}
                        </Typography>
                        <Box
                            sx={{
                                flex: 1,
                                backgroundColor,
                                border: isToday ? "3px solid #222" : undefined,
                                borderRadius: "12px",
                                padding: "4px 8px 0px 8px",
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