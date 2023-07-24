import React from 'react'
import { useLocation } from 'react-router-dom'
import ReceptionistDelete from './ReceptionistDelete'

function WrapperReceptionistDelete() {
    const location = useLocation()
  return (
    <ReceptionistDelete data = {location} />
  )
}

export default WrapperReceptionistDelete
