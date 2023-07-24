import React from 'react'
import { NavLink } from 'react-router-dom'

function Navbar() {
  return (
    <nav className='navbar'>
      <NavLink to='/' >HOME</NavLink>
      <NavLink to='adminlogin'> ADMIN LOGIN</NavLink>
      <p className='navbartitle'>HOUSE OF HEALTH</p>
    </nav>
  )
}

export default Navbar
