import React from 'react'
import { useLocation } from 'react-router-dom'
import DoctorDelete from './DoctorDelete'

function WrapperDoctorDelete() {
    const location = useLocation()
  return (
    <DoctorDelete data = {location} />
  )
}

export default WrapperDoctorDelete
