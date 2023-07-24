import React from 'react'
import { useLocation } from 'react-router-dom'
import ReceptionistAdd from './ReceptionistAdd'

function WrapperReceptionistAdd() {
    const location = useLocation()
  return (
    <ReceptionistAdd data = {location} />
  )
}

export default WrapperReceptionistAdd
