import React from 'react'
import { useLocation } from 'react-router-dom'
import DoctorAssignment from './DoctorAssignment'

function WrapperAssignment() {
    const location = useLocation()
  return (
    <DoctorAssignment data = {location}/>
  )
}

export default WrapperAssignment
