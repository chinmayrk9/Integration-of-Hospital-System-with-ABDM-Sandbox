import React from 'react'
import { useLocation } from 'react-router-dom'
import PatientLogin from './PatientLogin'

function WrapperPatientLogin() {
    const location = useLocation()
  return (
    <PatientLogin data = {location} />
  )
}

export default WrapperPatientLogin
