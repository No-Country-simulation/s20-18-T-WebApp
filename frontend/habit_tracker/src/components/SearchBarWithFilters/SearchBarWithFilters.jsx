import React, { useState } from 'react';
import { Box, TextField, InputAdornment, IconButton, Select, MenuItem, FormControl, InputLabel, Stack } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';

const SearchBarWithFilters = () => {
  
  const [searchText, setSearchText] = useState('');
  const [orderBy, setOrderBy] = useState('');

  const handleSearchTextChange = (event) => {
    setSearchText(event.target.value);
    console.log("Texto de búsqueda:", event.target.value);
  };

  const handleOrderByChange = (event) => {
    setOrderBy(event.target.value);
    console.log("Ordenar por:", event.target.value);
  };

  return (
    <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2} alignItems="center">
      {/* Campo de búsqueda */}
  <Box sx={{ position: 'relative', width: '100%' }}>
    <TextField
      fullWidth
      placeholder="Buscar..."
      variant="outlined"
      value={searchText}
      onChange={handleSearchTextChange}
      sx={{
        backgroundColor: '#f0f0f0',
        '& .MuiOutlinedInput-root': {
          fontSize: '20px',
          borderRadius: 2,
          paddingLeft: '40px', // Espacio para la lupa
          paddingTop: "0px",
          paddingBotton: "0px",
          '& fieldset': {
            borderColor: '#ccc',
          },
          '&:hover fieldset': {
            borderColor: '#999',
          },
          '&.Mui-focused fieldset': {
            borderColor: '#666',
          },
        },
      }}
    />
    <SearchIcon
      sx={{
        position: 'absolute',
        top: '50%',
        left: '10px',
        transform: 'translateY(-50%)',
        color: '#888',
      }}
    />
  </Box>

      {/* Filtro de ordenamiento */}
      <FormControl fullWidth>
        <InputLabel id="order-by-label">Ordenar por</InputLabel>
        <Select
          labelId="order-by-label"
          id="order-by-select"
          value={orderBy}
          label="Ordenar por"
          onChange={handleOrderByChange}
        >
          <MenuItem value="recent">Más recientes</MenuItem>
          <MenuItem value="oldest">Más antiguas</MenuItem>          
        </Select>
      </FormControl>
    </Stack>

  );
}

export default SearchBarWithFilters;