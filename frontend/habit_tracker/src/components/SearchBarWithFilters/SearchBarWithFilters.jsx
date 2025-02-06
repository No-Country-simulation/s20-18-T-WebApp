import { Box, TextField, Select, MenuItem, FormControl, InputLabel, Stack } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';

const SearchBarWithFilters = ({searchText, onChangeSearchText, orderBy, onChangeOrderBy}) => {

  return (
    <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2} alignItems="center">
      {/* Campo de búsqueda */}
      <Box sx={{ position: 'relative', width: '100%' }}>
        <TextField
          fullWidth
          placeholder="Buscar..."
          variant="outlined"
          value={searchText}
          onChange={onChangeSearchText}
          sx={{
            backgroundColor: '#f0f0f0',
            '& .MuiOutlinedInput-root': {
              fontSize: '16px', // Reducir tamaño de fuente
              borderRadius: 2,
              paddingLeft: '32px', // Reducir espacio para la lupa
              paddingTop: '8px',  // Ajustar padding vertical
              paddingBottom: '8px',
              '& input': {
                padding: '6px 14px', // Ajustar padding del input
              },
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
            left: '8px',
            transform: 'translateY(-50%)',
            color: '#888',
            fontSize: '20px' // Reducir tamaño de la lupa
          }}
        />
      </Box>

      {/* Filtro de ordenamiento */}
      <FormControl fullWidth sx={{ '& .MuiInputBase-root': { fontSize: '14px' } }}>
        <InputLabel id="order-by-label" sx={{ fontSize: '16px' }}>Ordenar por</InputLabel>
        <Select
          labelId="order-by-label"
          id="order-by-select"
          value={orderBy}
          label="Ordenar por"
          onChange={onChangeOrderBy}
          size="small" // Usar tamaño pequeño
          sx={{
            '& .MuiSelect-select': {
              padding: '14px 14px', // Ajustar padding del select
              fontSize: '16px'
            }
          }}
        >
          <MenuItem value="recent" sx={{ fontSize: '14px' }}>Más recientes</MenuItem>
          <MenuItem value="oldest" sx={{ fontSize: '14px' }}>Más antiguas</MenuItem>          
        </Select>
      </FormControl>
    </Stack>
  );
}

export default SearchBarWithFilters;