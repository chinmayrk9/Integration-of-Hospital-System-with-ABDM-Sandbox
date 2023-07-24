import React from 'react'
import { useLocation } from 'react-router-dom'
import AbhaVerification from './AbhaVerification'

function WrapperAbhaVerification() {
    const location = useLocation()
  return (
    <AbhaVerification data = {location} />
  )
}

export default WrapperAbhaVerification
