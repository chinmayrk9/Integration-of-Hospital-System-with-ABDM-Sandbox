import React, { Component } from 'react'
import axios from 'axios'
import { Navigate } from 'react-router-dom'
import base_url from './Url'

class ReceptionistAdd extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: '',
            gender: '',
            yearOfBirth: '',
            address: '',
            mobile: '',
            role: 'receptionist',
            email_id: '',
            password: '',
            goToAdminSelect: false,

            TOKEN: this.props.data?.state?.TOKEN || null
        }
    }

    changeHandler = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    genderChangeHandler = (event) => {
        this.setState({
            gender: event.target.value
        })
    }

    receptionistDetailsSubmit = e => {
        e.preventDefault()
        axios.post(`${base_url}/admin/savrec`, {
            name: this.state.name,
            gender: this.state.gender,
            yearofBirth: this.state.yearOfBirth,
            address: this.state.address,
            mobile: this.state.mobile,
            role: 'receptionist',
            email_Id: this.state.email_id,
            password: this.state.password,
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
        .then(res => {
            if(res.status >= 200 && res.status <= 299) {
                alert('Receptionist Sucessfully Added')
                this.setState({
                    goToAdminSelect : true
                })
            }
            else {
                alert('Invalid token or Server Down')
            }
        })
    }
    render() {
        const { name, gender, yearOfBirth, address, mobile, email_id, password, goToAdminSelect } = this.state
        if(goToAdminSelect) return <Navigate to = '/adminselect' state = { {TOKEN : this.state.TOKEN } }/>
        else
        return (
            <div className='registrationBlock'>
                <div className='registrationHeading'>
                    <h1>RECEPTIONIST REGISTRATION FORM</h1>
                </div>

                <div className='registrationNew'>

                    <form className='patientDetailsForm' onSubmit={this.receptionistDetailsSubmit}>

                        <label htmlFor='name'>NAME:</label>
                        <input type='text' id='name' name='name' placeholder="Enter Receptionist's Name" value={name} onChange={this.changeHandler} />

                        <label htmlFor="gender">GENDER:</label>
                        <select name='gender' id='gender' value={gender} onChange={this.genderChangeHandler}>
                            <option value='Male'>Male</option>
                            <option value='Female'>Female</option>
                            <option value='Other'>Other</option>
                        </select>

                        <label htmlFor='yearOfBirth'>YEAR OF BIRTH:</label>
                        <input type='text' id='yearOfBirth' name='yearOfBirth' placeholder="Enter Receptionist's Year of Birth" value={yearOfBirth} onChange={this.changeHandler} />

                        <label htmlFor='line'>ADDRESS:</label>
                        <input type='text' id='address' name='address' placeholder="Enter Receptionist's address" value={address} onChange={this.changeHandler} />

                        <label htmlFor='mobile'>MOBILE NO:</label>
                        <input type='text' id='mobile' name='mobile' placeholder="Enter Receptionist's Mobile No" value={mobile} onChange={this.changeHandler} />

                        <label htmlFor='email_id'>EMAIL ID:</label>
                        <input type='text' id='email_id' name='email_id' placeholder="Enter Receptionist's email address" value={email_id} onChange={this.changeHandler} />

                        <label htmlFor='password'>PASSWORD:</label>
                        <input type='text' id='password' name='password' placeholder="Enter Receptionist's password" value={password} onChange={this.changeHandler} />

                        <button type='submit'>Save</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default ReceptionistAdd
