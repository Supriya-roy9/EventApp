import { createTheme } from "@mui/material";
import { green, red } from "@mui/material/colors";


const theme=createTheme({
    palette:{
        primary: {
            main: '#000',
            contrastText:'#63E6BE',
            
        },
        secondary:{
            main: '#FFF'
        },
        background:{
            default: '#63E6BE',
        },
        success: {
            main: green['800']
        },
        error: {
            main: red.A400,
        }
    }
})

export default theme;