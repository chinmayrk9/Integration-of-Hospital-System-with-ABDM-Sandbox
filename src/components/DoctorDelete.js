import React, { Component } from 'react'
import base_url from './Url'
import axios from 'axios'
import { Navigate } from 'react-router-dom'

class DoctorDelete extends Component {
    constructor(props) {
        super(props)

        this.state = {
            goToAdminSelect: false,
            doctorId: '',
            TOKEN: this.props.data?.state?.TOKEN || ''
        }
    }

    changeHandler = e => {
        this.setState({
            doctorId: e.target.value
        })
    }

    submitHandler = e => {
        e.preventDefault()
        axios.post(`${base_url}/admin/deletedoc`, {
            userName: this.state.doctorId
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
            .then(res => {
                if (res.status >= 200 && res.status <= 299) {
                    alert(`Doctor with ID - ${this.state.doctorId} deleted` )
                    this.setState({
                        goToAdminSelect: true
                    })
                }
                else(
                    alert('Either token expired or server sent bad response')
                )
            })

    }
    render() {
        if (this.state.goToAdminSelect) return <Navigate to='/adminselect' state = { {TOKEN : this.state.TOKEN } }/>
        else
            return (
                <div className='patientSearchBlock'>

                    <div className='patientSearchHeading'>
                        <h1>DOCTOR DELETE PAGE</h1>
                    </div>

                    <form className='patientSearchDiv' onSubmit={this.submitHandler}>
                        <label htmlFor='doctorId'>Doctor ID:</label>
                        <input type='text' id='doctorId' name='doctorId' placeholder='Enter Doctor ID' value={this.state.doctorId} onChange={this.changeHandler} ></input>
                        <button type='submit'>Delete</button>
                    </form>
                </div>
            )
    }
}
export default DoctorDelete
