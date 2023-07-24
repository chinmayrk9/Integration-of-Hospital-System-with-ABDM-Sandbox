import React from 'react'
import {useLocation} from "react-router-dom";
import PatientMedicalData from "./PatientMedicalData";

function WrapperPatientMedicalData() {
    const location = useLocation()
    return (
        <PatientMedicalData data = {location} />
    )
}

export default WrapperPatientMedicalData