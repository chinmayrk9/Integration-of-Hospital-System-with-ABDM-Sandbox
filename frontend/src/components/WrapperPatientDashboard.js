import React from 'react'
import { useLocation } from 'react-router-dom'
import PatientDashboard from './PatientDashboard'

function WrapperPatientDashboard() {
    const location = useLocation()
  return (
    <PatientDashboard data = {location} />
  )
}

export default WrapperPatientDashboard
