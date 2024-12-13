import { CssBaseline, TextField } from '@mui/material';
import './App.css';
import Layout from './layout/Layout';
import Theme from './Theme';
import { ThemeProvider } from '@mui/system';

function App() {
  return (
    <>
    <ThemeProvider theme={Theme}>
      <CssBaseline />
      <Layout />
    </ThemeProvider>
  
    </>
    
    
  );
}

export default App;
