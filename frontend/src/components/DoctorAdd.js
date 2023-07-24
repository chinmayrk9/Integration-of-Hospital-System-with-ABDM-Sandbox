import axios from 'axios'
import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'

class DoctorAdd extends Component {
    constructor(props) {
        super(props)

        this.state = {
            password: '',
            name: '',
            gender: '',
            yearOfBirth: '',
            abha_id: '',
            address: '',
            mobile: '',
            role: 'doctor',
            speciality: '',
            email_id: '',
            country: '',
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

    doctorDetailsSubmit = e => {
        e.preventDefault()
        axios.post('http://localhost:8080/admin/savdoc', {
            password: this.state.password,
            name: this.state.name,
            gender: this.state.gender,
            yearofBirth: this.state.yearOfBirth,
            abha_id: this.state.abha_id,
            address: this.state.address,
            mobile: this.state.mobile,
            role: 'doctor',
            speciality: this.state.speciality,
            email_Id: this.state.email_id,
            country: this.state.country
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
        .then(res => {
            if(res.status >= 200 && res.status <= 299) {
                alert(`Doctor Id generated Successfully with username - ${res.data.id}`)
                this.setState({
                    goToAdminSelect : true
                })      
            }
            else {
                alert('Token expired or Server Down')
            }
        })
    }

    render() {
        const { name, gender, yearOfBirth, abha_id, address, mobile, speciality, email_id, country, password, goToAdminSelect } = this.state

        if(goToAdminSelect) return <Navigate to = '/adminselect' state = { {TOKEN : this.state.TOKEN } } />
        else
        return (
            <div className='registrationBlock'>
                <div className='registrationHeading'>
                    <h1>DOCTOR REGISTRATION FORM</h1>
                </div>

                <div className='registrationNew'>

                    <form className='patientDetailsForm' onSubmit={this.doctorDetailsSubmit}>

                        <label htmlFor='name'>NAME:</label>
                        <input type='text' id='name' name='name' placeholder="Enter Doctor's Name" value={name} onChange={this.changeHandler} />

                        <label htmlFor="gender">GENDER:</label>
                        <select name='gender' id='gender' value={gender} onChange={this.genderChangeHandler}>
                            <option value='Male'>Male</option>
                            <option value='Female'>Female</option>
                            <option value='Other'>Other</option>
                        </select>

                        <label htmlFor='abha_id'>ABHA ID:</label>
                        <input type='text' id='abha_id' name='abha_id' placeholder="Enter Doctor's abha Id" value={abha_id} onChange={this.changeHandler} />

                        <label htmlFor='yearOfBirth'>YEAR OF BIRTH:</label>
                        <input type='text' id='yearOfBirth' name='yearOfBirth' placeholder="Enter Doctor's Year of Birth" value={yearOfBirth} onChange={this.changeHandler} />

                        <label htmlFor='line'>ADDRESS:</label>
                        <input type='text' id='address' name='address' placeholder="Enter Doctor's address" value={address} onChange={this.changeHandler} />

                        <label htmlFor='mobile'>MOBILE NO:</label>
                        <input type='text' id='mobile' name='mobile' placeholder="Enter Doctor's Mobile No" value={mobile} onChange={this.changeHandler} />

                        <label htmlFor='email_id'>EMAIL ID:</label>
                        <input type='text' id='email_id' name='email_id' placeholder="Enter Doctor's email address" value={email_id} onChange={this.changeHandler} />

                        <label htmlFor='country'>COUNTRY:</label>
                        <input type='text' id='country' name='country' placeholder="Enter Doctor's Country of Residence" value={country} onChange={this.changeHandler} />

                        <label htmlFor='speciality'>SPECIALITY:</label>
                        <input type='text' id='speciality' name='speciality' placeholder="Enter Doctor's speciality" value={speciality} onChange={this.changeHandler} />

                        <label htmlFor='password'>PASSWORD:</label>
                        <input type='text' id='password' name='password' placeholder="Enter password for Doctor" value={password} onChange={this.changeHandler} />

                        <button type='submit'>Save</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default DoctorAdd
