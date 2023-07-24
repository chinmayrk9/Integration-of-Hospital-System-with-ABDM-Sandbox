import React from 'react'
import { useLocation } from 'react-router-dom'
import Registration from './Registration'

function WrapperRegistration() {
    const location = useLocation()
  return (
    <Registration data={location}/>
  )
}

export default WrapperRegistration
