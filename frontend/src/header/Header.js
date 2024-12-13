import { AppBar, Button, Toolbar, Typography } from '@mui/material'
import React from 'react'
import styles from './styles/headerStyles';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faClapperboard } from '@fortawesome/free-solid-svg-icons';

function Header() {
  const classes=styles(); 
  return (
    <div aria-label='header'>
        <AppBar >
          <Toolbar className={classes.appBar}>

            <a href="/" className={classes.appLink}>
              <FontAwesomeIcon icon={faClapperboard} className={classes.headerIcon} ></FontAwesomeIcon>
              <Typography variant='h6' noWrap className={classes.appName} sx={{fontFamily: 'Chakra Petch',fontWeight :800}}>My Cinemas</Typography>
            </a>
          </Toolbar>
        </AppBar>
        
    </div>
  )
}

export default Header;