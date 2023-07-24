import React, { useMemo } from 'react'
import { useTable, useSortBy, useGlobalFilter, usePagination } from 'react-table'
import { COLUMNS } from './Columns'
import MOCK_DATA from './MOCK_DATA.json'
import GlobalFilter from './GlobalFilter'
import base_url from "./Url"
import axios from "axios";
import {useState} from "react";
import {Navigate} from "react-router-dom";

const ConsentTable = (props) => {

    const[patientConsentTab, setPatientConsentTab] = useState([])
    const[goToPatientMedicalData, setGoToPatientMedicalData] = useState(false)

    const columns = useMemo(() => COLUMNS, [])
    const data = useMemo(() => props.tab.patientConsentTab, [])

    const { getTableProps, getTableBodyProps, headerGroups, page, nextPage, previousPage, canNextPage, canPreviousPage, pageOptions, prepareRow, state, setGlobalFilter } = useTable({
        columns,
        data
    }, useGlobalFilter, useSortBy, usePagination)

    const sort = <svg height='20px' width='20px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path d="M137.4 41.4c12.5-12.5 32.8-12.5 45.3 0l128 128c9.2 9.2 11.9 22.9 6.9 34.9s-16.6 19.8-29.6 19.8H32c-12.9 0-24.6-7.8-29.6-19.8s-2.2-25.7 6.9-34.9l128-128zm0 429.3l-128-128c-9.2-9.2-11.9-22.9-6.9-34.9s16.6-19.8 29.6-19.8H288c12.9 0 24.6 7.8 29.6 19.8s2.2 25.7-6.9 34.9l-128 128c-12.5 12.5-32.8 12.5-45.3 0z" /></svg>
    const sortUp = <svg height='20px' width='20px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path d="M182.6 41.4c-12.5-12.5-32.8-12.5-45.3 0l-128 128c-9.2 9.2-11.9 22.9-6.9 34.9s16.6 19.8 29.6 19.8H288c12.9 0 24.6-7.8 29.6-19.8s2.2-25.7-6.9-34.9l-128-128z" /></svg>
    const sortDown = <svg height='20px' width='20px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path d="M182.6 470.6c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-9.2-9.2-11.9-22.9-6.9-34.9s16.6-19.8 29.6-19.8H288c12.9 0 24.6 7.8 29.6 19.8s2.2 25.7-6.9 34.9l-128 128z" /></svg>

    const { globalFilter, pageIndex } = state

    const handleRowClick = () => {
        setGoToPatientMedicalData(true)
    }

    if(goToPatientMedicalData) return <Navigate to='/testOnData' />
    else
    return (
        <>
            <GlobalFilter filter={globalFilter} setFilter={setGlobalFilter} />
            <table {...getTableProps()}>
                <thead>
                    {
                        headerGroups.map(headerGroups => (
                            <tr {...headerGroups.getHeaderGroupProps}>
                                {
                                    headerGroups.headers.map(column => (
                                        <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                                            {
                                                column.render('Header')
                                            }
                                            <span>
                                                {column.isSorted ? (column.isSortedDesc ? sortDown : sortUp) : sort}
                                            </span>
                                        </th>
                                    ))
                                }
                            </tr>
                        ))
                    }
                </thead>
                <tbody {...getTableBodyProps()}>
                    {
                        page.map(row => {
                            prepareRow(row)
                            return (
                                <tr {...row.getRowProps()} >
                                    {
                                        row.cells.map(cell => {
                                            return <td {...cell.getCellProps()}>
                                                {
                                                    cell.render('Cell')
                                                }
                                            </td>
                                        })
                                    }

                                </tr>
                            )
                        })
                    }
                </tbody>
            </table>

            <div id = 'paginationButtonDiv'>
                    {/* Page{' '} */}
                    <strong>
                        {pageIndex + 1} of {pageOptions.length}
                    </strong>{' '}
                <button onClick={() => previousPage()} disabled={!canPreviousPage} >Previous</button>
                <button onClick={() => nextPage()} disabled={!canNextPage}>Next</button>
                <button id='sbutton' onClick={handleRowClick} >get data</button>
            </div>
        </>
    )
}

export default ConsentTable
