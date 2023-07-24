import React from 'react'
import { useLocation } from 'react-router-dom'
import ConsentRequestForm from './ConsentRequestForm'

function WrapperConsentRequestForm() {
    const location = useLocation()
  return (
    <ConsentRequestForm data={location} />
  )
}

export default WrapperConsentRequestForm
