import React, { Component } from 'react'
import axios from 'axios'
import { Navigate } from 'react-router-dom'
import base_url from './Url'

class PatientLogin extends Component {
    constructor(props) {
        super(props)

        this.state = {
            mobileNumber: '',
            patientInfo: {},
            goToAssignment: false,
            TOKEN: this.props.data?.state?.TOKEN || ''
        }
    }
    changeHandler = (e) => {
        this.setState({
            mobileNumber: e.target.value
        })
    }

    patientLoginHandler = (e) => {
        e.preventDefault()
        axios.post(`${base_url}/receptionist/get-demographic`, {
            mobileNumber: this.state.mobileNumber
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
            .then(res => {
                if (res.status >= 200 && res.status <= 299) {
                    this.setState({
                        patientInfo: res.data,
                        goToAssignment: true
                    })
                }

            })
            .catch(e => {
                console.log(e)
            })
    }
    render() {
        if (this.state.goToAssignment) return <Navigate to='/assignment' state={{
            patientInfo: this.state.patientInfo,
            TOKEN: this.state.TOKEN
        }} />
        return (
            <div className='registrationBlock'>

                <div className='registrationHeading'>
                    <h1>PATIENT LOGIN</h1>
                </div>

                <div className='registrationAbhaVerificationMenu'>

                    <h4>Patient Mobile Verification</h4>

                    <form className='abhaVerificationForm' onSubmit={this.patientLoginHandler}>
                        <label htmlFor='mobileNumber'>Mobile No:</label>
                        <input type='text' id='mobileNumber' name='mobileNumber' placeholder="Enter Patient's Mobile No" value={this.state.mobileNumber} onChange={this.changeHandler} />
                        <button type='submit'>Submit</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default PatientLogin
