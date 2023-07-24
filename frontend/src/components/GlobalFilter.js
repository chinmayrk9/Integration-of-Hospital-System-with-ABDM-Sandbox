import React from 'react'

const GlobalFilter = ({ filter, setFilter }) => {
  return (
    <div id='tableSearchDiv'>
      <h4>Search: {' '} </h4>
      <input id='filterTableInput' value={filter || ''} onChange={e => setFilter(e.target.value)} />
    </div>
  )
}
export default GlobalFilter
