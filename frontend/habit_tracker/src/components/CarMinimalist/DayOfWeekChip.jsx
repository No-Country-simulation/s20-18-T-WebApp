import React from "react";
import { Chip } from "@mui/material";

function DayOfWeekChip({ daysWeekSet, color }) {
  if (!daysWeekSet || daysWeekSet.length === 0) {
    return null; // O podrías retornar un Chip con un mensaje diferente
  }

  const dayNames = ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'];
  const selectedDays = daysWeekSet.map(dayIndex => dayNames[dayIndex]).join(', ');

  const allDaysSelected = daysWeekSet.length === 7;

  return (
    <Chip
      label={allDaysSelected ? "Todos los dias" : selectedDays}
      sx={{ borderRadius: "8px", backgroundColor: color, color: "#eee" }}
    />
  );
}

export default DayOfWeekChip;