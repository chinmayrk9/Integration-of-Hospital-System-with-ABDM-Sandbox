import React from 'react'
import { useLocation } from 'react-router-dom'
import DoctorAdd from './DoctorAdd'

function WrapperDoctorAdd() {
    const location = useLocation()
  return (
    <DoctorAdd data = {location} />
  )
}

export default WrapperDoctorAdd
