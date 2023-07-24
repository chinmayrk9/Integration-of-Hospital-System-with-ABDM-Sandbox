import React, { Component } from 'react'
import base_url from './Url'
import axios from 'axios'
import { Navigate } from 'react-router-dom'

class ReceptionistDelete extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
        goToAdminSelect: false,
        receptionistId: '',
        TOKEN: this.props.data?.state?.TOKEN || ''
      }
    }
    
    changeHandler = e => {
        this.setState({
            receptionistId: e.target.value,
        })
    }

    submitHandler = e => {
        e.preventDefault()
        axios.post(`${base_url}/admin/deleterec`, {
            username: this.state.receptionistId
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
        .then(res => {
            if(res.status >= 200 && res.status <= 299) {
                alert(`${res.data}`)
                this.setState({
                    goToAdminSelect: true
                })
            }
            else {
                alert('Token Expired or Server Down')
            }
        })

    }
    render() {
        if(this.state.goToAdminSelect) return <Navigate to='/adminselect' state = { {TOKEN : this.state.TOKEN } }/>
        else
        return (
            <div className='patientSearchBlock'>

                <div className='patientSearchHeading'>
                    <h1>RECEPTIONIST DELETE PAGE</h1>
                </div>

                <form className='patientSearchDiv' onSubmit={this.submitHandler}>
                    <label htmlFor='receptionistId'>Receptionist ID:</label>
                    <input type='text' id='receptionistId' name='receptionistId' value={this.state.receptionistId} placeholder='Enter Receptionist ID' onChange={this.changeHandler} ></input>
                    <button type='submit'>Delete</button>
                </form>
            </div>
        )
    }
}

export default ReceptionistDelete
