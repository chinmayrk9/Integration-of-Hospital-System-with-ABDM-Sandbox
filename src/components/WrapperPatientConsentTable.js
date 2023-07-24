import React from 'react'
import { useLocation } from 'react-router-dom'
import PatientConsentTable from './PatientConsentTable'

function WrapperPatientConsentTable() {
    const location = useLocation()
    return (
        <PatientConsentTable data = {location}/>
    )
}

export default WrapperPatientConsentTable