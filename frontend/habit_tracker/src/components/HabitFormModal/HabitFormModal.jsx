import React, { useState, useEffect } from 'react';
import {
  Modal,
  Box,
  Typography,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  FormControlLabel,
  RadioGroup,
  Radio,
  Button,
} from '@mui/material';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '90%',
    maxWidth: 800,
    bgcolor: 'background.paper',
    boxShadow: 24,
    p: 3,
    borderRadius: 2,
    display: 'flex',
    flexDirection: 'column',
    gap: 2,
    maxHeight: '90vh', // Limita la altura máxima al 90% de la altura de la pantalla
    overflowY: 'auto',  // Permite el scroll vertical cuando el contenido se desborda
    '@media (minWidth: 600px)': {
      p: 4,
    },
  };

  import { categories, iconMap } from '../../utils/utils';
  import { useUsers } from '../../contexts/UsersContext';



const HabitFormModal = ({ open, handleClose }) => {
  const [categoryId, setCategoryId] = useState(0);
  const [title, setTitle] = useState('');
  const [hasGoal, setHasGoal] = useState(false);
  const [goalUnit, setGoalUnit] = useState('');
  const [goalValue, setGoalValue] = useState('');
  const [hasDuration, setHasDuration] = useState(false);
  const [endDate, setEndDate] = useState('');
  const [selectedDays, setSelectedDays] = useState([]);
  const [currentUnits, setCurrentUnits] = useState([]);
  const {addHabit} = useUsers();

  const handleDayClick = (day, index) => {
    if (selectedDays.includes(index)) { // Check for index in selectedDays
      setSelectedDays(selectedDays.filter((d) => d !== index)); // Remove index
    } else {
      setSelectedDays([...selectedDays, index]); // Add index
    }
  };

  const handleCategory = (e) => {
    const catId = e.target.value;
    setCategoryId(catId);
    const currentCategory = categories.find(cat=>cat.id==catId);
    setCurrentUnits(currentCategory.units);
    setTitle(currentCategory.name);
  }

  const handleSubmit = (event) => {
    event.preventDefault();


    let isValid = true;
    const errors = {};

    if (categoryId == null) {
      errors.categoryId = 'La categoría es requerida.';
      isValid = false;
    }

    if (title.trim() == '') {
      errors.title = 'El título es requerido.';
      isValid = false;
    }

    if (selectedDays.length === 0) {
      errors.selectedDays = 'Debes seleccionar al menos un día.';
      isValid = false;
    }

    if (!isValid) {
      console.log('Errores de validación:', errors);
      return
    }

    addHabit({
      archived: false,
      title,
      streak: 0,
      porcCompletetion: 0,
      hasGoal,
      goalUnit,
      goalValue,
      hasDuration,
      endDate,
      categoryId,
      daysWeekSet: selectedDays, 
    })

    handleClose();
  };

  useEffect(() => {
    setCategoryId(0);
    setTitle(categories[0].name);
    setGoalUnit('');
    setGoalValue('');
    setHasDuration(false);
    setEndDate('');
    setSelectedDays([]);
    setCurrentUnits(categories[0].units);


  }, [open]);

  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="habit-form-modal-title"
      aria-describedby="habit-form-modal-description"
    >
      <Box sx={style}>
        <Typography
          id="habit-form-modal-title"
          variant="h6"
          component="h2"
          mb={1}
        >
          Crear un nuevo hábito
        </Typography>
        <form
          onSubmit={handleSubmit}
          style={{ display: "flex", flexDirection: "column", gap: 16 }}
        >
          <FormControl fullWidth>
            <InputLabel id="categoria-label">Categoría</InputLabel>
            <Select
              labelId="categoria-label"
              id="categoria"
              value={categoryId}
              label="Categoría"
              onChange={handleCategory}
            >
              <MenuItem value="">
                <em>Seleccionar categoría</em>
              </MenuItem>
              {categories.map((cat) => (
                <MenuItem value={cat.id} key={`cat_${cat.id}`} sx={{}}>
                  <Box sx={{display: "flex", flexDirection: "row", alignItems: "center", columnGap: 1, color: cat.color}}>
                    {iconMap[cat.icon]}
                    {cat.name}
                  </Box>
                </MenuItem>
              ))}
            </Select>
          </FormControl>

          <TextField
            fullWidth
            id="titulo"
            label="Ingresa un título..."
            variant="outlined"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          <div
            style={{
              display: "flex",
              flexDirection: "column",
              gap: 16,
              "@media (minWidth: 600px)": { flexDirection: "row", gap: 16 },
            }}
          >
            <FormControl fullWidth>
              <Typography variant="subtitle2">
                ¿Este hábito tiene metas? (Opcional)
              </Typography>
              <FormControl component="fieldset">
                <RadioGroup
                  aria-label="tiene-metas"
                  name="tiene-metas"
                  value={hasGoal}
                  onChange={(e) => setHasGoal(e.target.value)}
                  row
                >
                  <FormControlLabel
                    value="true"
                    control={<Radio />}
                    label="Si"
                  />
                  <FormControlLabel
                    value="false"
                    control={<Radio />}
                    label="No"
                  />
                </RadioGroup>
              </FormControl>

              {hasGoal === "true" && (
                <Box sx={{width: "100%", display: "flex", flexDirection: "column", rowGap: "1em", padding: "10px 0px"}}>

                  <FormControl fullWidth>
                  <InputLabel id="unidad-meta-label">Unidad de tu meta</InputLabel>
                  <Select
                    sx={{width: "100%"}}
                    id="unidad-meta"
                    value={goalUnit}
                    label="Unidad de tu meta (Opcional)"
                    onChange={(e) => setGoalUnit(e.target.value)}
                  >
                    <MenuItem value="">
                      <em>Seleccionar unidades</em>
                    </MenuItem>
                    {currentUnits.map((unit, index) => (
                      <MenuItem value={unit} key={`unit_index_${index}`}>
                        {unit}
                      </MenuItem>
                    ))}
                  </Select>
                  </FormControl>
                  <TextField
                    fullWidth
                    id="valor-meta"
                    label="Valor de tu meta (Opcional)"
                    variant="outlined"
                    value={goalValue}
                    onChange={(e) => setGoalValue(e.target.value)}
                  />
                </Box>
              )}
            </FormControl>
          </div>

          <Typography variant="subtitle2">
            ¿Este hábito tiene duración? (Opcional)
          </Typography>
          <FormControl component="fieldset">
            <RadioGroup
              aria-label="tiene-duracion"
              name="tiene-duracion"
              value={hasDuration}
              onChange={(e) => setHasDuration(e.target.value)}
              row
            >
              <FormControlLabel value="true" control={<Radio />} label="Si" />
              <FormControlLabel value="false" control={<Radio />} label="No" />
            </RadioGroup>
          </FormControl>

          {hasDuration === "true" && (
            <TextField
              fullWidth
              id="fecha-finalizacion"
              label="Fecha de finalizacion"
              type="date"
              variant="outlined"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
              InputLabelProps={{
                shrink: true,
              }}
            />
          )}

          <Typography variant="subtitle2">
            Días para realizar tu habito
          </Typography>
          <div style={{ display: "flex", flexWrap: "wrap", gap: 8 }}>
            {[
              "Lunes",
              "Martes",
              "Miércoles",
              "Jueves",
              "Viernes",
              "Sábado",
              "Domingo",
            ].map((day, index) => (
              <Button
                key={day}
                variant={selectedDays.includes(index) ? "contained" : "outlined"} // Check for index in selectedDays
                onClick={() => handleDayClick(day, index)} // Pass index to handleDayClick
                color="primary"
                sx={{ flex: "1 0 auto", minWidth: "auto" }}
              >
                {day}
              </Button>
            ))}
          </div>

          <Box mt={2} display="flex" justifyContent="flex-end" gap={1}>
            <Button onClick={handleClose}>Cancelar</Button>
            <Button type="submit" variant="contained" color="primary">
              Crear habito
            </Button>
          </Box>
        </form>
      </Box>
    </Modal>
  );
};

export default HabitFormModal;