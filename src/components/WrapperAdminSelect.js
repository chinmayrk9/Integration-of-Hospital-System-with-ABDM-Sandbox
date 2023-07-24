import React from 'react'
import { useLocation } from 'react-router-dom'
import AdminSelect from './AdminSelect'

function WrapperAdminSelect() {
    const location =  useLocation()
  return (
    <AdminSelect data={location} />
  )
}

export default WrapperAdminSelect
