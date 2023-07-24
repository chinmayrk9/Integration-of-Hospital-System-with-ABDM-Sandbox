import React from 'react'
import { useLocation } from 'react-router-dom'
import PatientSearch from './PatientSearch'

function WrapperPatientSearch() {
    const location = useLocation()
  return (
    <PatientSearch data = {location}/>
  )
}

export default WrapperPatientSearch
