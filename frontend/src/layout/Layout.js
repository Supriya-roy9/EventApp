import React from 'react'
import Header from '../header/Header'
import { Container } from '@mui/material'
import RootRouter from '../components/router/RootRouter'
import { Box } from '@mui/system'


function Layout() {

  return (
    <div>
      <Box>
        <Header />
        <Container className='container'>
          <RootRouter/>
        </Container>
        </Box>
    </div>
  )
}

export default Layout

