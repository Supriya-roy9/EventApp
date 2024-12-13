import { makeStyles } from "@mui/styles";
import theme from "../../Theme";

export default makeStyles(()=>({
    appBar:{
       backgroundColor: theme.palette.secondary.main,
       borderBottom: '0.5px solid #63E6BE'
    },
    appName:{
        animation: '$moveText 1.0s forwards',
        opacity: 1
    },
    logoutBtn:{
        backgroundColor: theme.palette.error.main
    },
    headerIcon:{
      padding: '0.75rem'
    },
    appLink:{
        color: theme.palette.primary.contrastText,
        textDecoration: 'none',
        display: 'flex',
        justifyContent: 'flex-start',
        flexGrow: 1,
        alignItems: 'center'
    },
    '@keyframes moveText':{
        '0%':{
            bottom: '-0.2em',
            opacity: 0
        },
        '33%':{
            bottom: '-0.2em',
            opacity: 1
        },
        '66%':{
            bottom: '-0.2em',
            opacity: 0
        },
       

    }
}));
