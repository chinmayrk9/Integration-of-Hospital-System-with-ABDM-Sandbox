import axios from 'axios'
import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
import base_url from './Url'

class DoctorAssignment extends Component {
    constructor(props) {
        super(props)

        this.state = {
            doctorId: '',
            goToHome: false,
            TOKEN: this.props.data?.state?.TOKEN || null,
            listOfDoctor: []
        }
    }

    changeHandler = e => {
        this.setState({
            doctorId: e.target.value
        })
    }

    submitHandler = e => {
        e.preventDefault()
        axios.post(`${base_url}/receptionist/add-patient`, {
            patientId: this.props.data?.state?.patientInfo?.patientId || this.props.data?.state?.patientId,
            patientName: this.props.data?.state?.patientInfo?.name || this.props.data?.state?.name,
            gender: this.props.data?.state?.patientInfo?.gender || this.props.data?.state?.gender,
            dateOfBirth: this.props.data?.state?.patientInfo?.yearOfBirth || this.props.data?.state?.yearOfBirth,
            doctorId: this.state.doctorId,
            abhaId: this.props.data?.state?.patientInfo?.abhaId || this.props.data?.state?.abhaId
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
            .then(r => {
                if (r.status >= 200 && r.status <= 299) {
                    alert(`Patient ${
                        this.props.data?.state?.patientInfo?.name || this.props.data?.state?.name
                    } has been assigned to Dr. with Id -${
                        this.state.doctorId
                    }`)
                    this.setState({
                        goToHome: true
                    })
                }
                else console.log('error encountered at add-patient call')
            })
    }

    componentDidMount() {
        axios.get(`${base_url}/receptionist/doclist`, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
        .then(res => {
            if(res.status >= 200 && res.status <= 299) {
                this.setState({
                    listOfDoctor: res.data
                }, () => {
                    console.log(this.state.listOfDoctor)
                })
            }
        })
    }

    render() {
            const doctors = this.state.listOfDoctor.map(doctor => (<option key={doctor.hos_id} value={doctor.hos_id} >{`${doctor.name} (${doctor.hos_id})`}</option>))

        if (this.state.goToHome) return <Navigate to='/' />
        else
            return (
                <div>
                    <div className='topbar'>
                        <svg height='30px' width='30px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" /></svg>
                        <h4>{this.props.data?.state?.patientInfo?.name || this.props.data?.state?.name}</h4>
                        <h4>{this.props.data?.state?.patientInfo?.gender || this.props.data?.state?.gender}</h4>
                        <h4>{2023 - (this.props.data?.state?.patientInfo?.yearOfBirth || this.props.data?.state?.yearOfBirth)} Years</h4>
                        <h4>patient id - {this.props.data?.state?.patientInfo?.patientId || this.props.data?.state?.patientId}</h4>
                        <h4>abha id - {this.props.data?.state?.patientInfo?.abhaId || this.props.data?.state?.abhaId}</h4>
                    </div>

                    <div className='doctorAssignmentHeading'>
                        <h1>Doctor Assignment</h1>
                    </div>

                    <div className='doctorAssignmentDiv'>
                        <form className='doctorAssignmentForm' onSubmit={this.submitHandler}>

                            <label htmlFor="doctorId">Assign a Doctor:</label>
                            <select name='doctorId' id='doctorId' value={this.state.doctorId} onChange={this.changeHandler}>
                               {doctors}
                            </select>

                            <button type='submit'>Assign</button>
                        </form>
                    </div>
                </div>
            )
    }
}

export default DoctorAssignment
