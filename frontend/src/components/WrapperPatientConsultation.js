import React from 'react'
import { useLocation } from 'react-router-dom'
import PatientConsultation from './PatientConsultation'

function WrapperPatientConsultation() {
    const location = useLocation()
  return (
    <PatientConsultation data={location} />
  )
}

export default WrapperPatientConsultation
