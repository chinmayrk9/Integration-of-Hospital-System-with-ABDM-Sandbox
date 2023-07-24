import axios from 'axios'
import React, { Component } from 'react'
import base_url from './Url'
import { Navigate } from 'react-router-dom'

class PatientConsultation extends Component {
    constructor(props) {
        super(props)

        this.state = {
            pulse: '',
            bloodPressure: '',
            complains: '',
            diagnosis: '',
            prescription: '',
            instruction: '',
            dosage: '',
            symptoms: '',
            timings: '',

            goToPatientSearch: false,

            TOKEN: this.props.data?.state?.TOKEN || null,

            doctorName: this.props.data?.state?.doctorName || '',
            doctorId: this.props.data?.state?.doctorId || '',

            patientId: this.props.data?.state?.patientId || '',
            patientName: this.props.data?.state?.patientName || '',
            patientDateOfBirth: this.props.data?.state?.patientDateOfBirth || '',
            patientGender: this.props.data?.state?.patientGender || '',
            patientAbhaId: this.props.data?.state?.patientAbhaId || '',

            objectOfPatientList: {}
        }
    }

    changeHandler = e => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    consultationSubmitHandler = e => {
        e.preventDefault()
        axios.post(`${base_url}/doctor/care-context`, {
            medicine: this.state.prescription,
            dosage: this.state.dosage,
            instruction: this.state.instruction,
            pattern: this.state.complains,
            symptoms: this.state.symptoms,
            timings: this.state.timings,
            bloodPressure: this.state.bloodPressure,
            diagnosis: this.state.diagnosis,
            pulse: this.state.pulse,

            patientId: this.state.patientId,
            doctorId: this.state.doctorId,

            requestId: '',
            id: this.state.patientAbhaId,
            timestamp: '',
            link: {
                accessToken: '',
                patient: {
                    referenceNumber: this.state.patientId,
                    display: "Your care context is added successfully",
                    careContexts: [
                        {
                            referenceNumber: '',
                            display: ''
                        }
                    ]
                }
            }
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
            .then(res => {
                if (res.status >= 200 && res.status <= 299) {
                    alert('Care Context has been added successfully and Consultation is saved')
                    this.setState({
                        goToPatientSearch: false,

                    })
                }
            })
    }

    render() {

        const { pulse, bloodPressure, complains, diagnosis, prescription, dosage, instruction, symptoms, timings, goToPatientSearch } = this.state

        if (goToPatientSearch) return <Navigate to='/search' state={{
            objectOfPatientList: this.state.objectOfPatientList,
            TOKEN: this.state.TOKEN,
            doctorName: this.state.doctorName,
            doctorId: this.state.doctorId
        }} />
        else
        return (
            <div>
                <div className='topbar'>
                    <svg height='30px' width='30px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" /></svg>
                    <h4>{this.props.data?.state?.patientId.name || ''}</h4>
                    <h4>Patient Id - {this.props.data?.state?.patientId || ''}</h4>
                    <h4>Age - {23 - (this.props.data?.state?.patientDateOfBirth || '')}</h4>
                    <h4>{this.props.data?.state?.patientGender || ''}</h4>
                </div>

                <div className='patientConsultationDiv'>
                    <h4>Patient Consultation Details</h4>
                    <form className='patientConsultationForm' onSubmit={this.consultationSubmitHandler}>

                        <label htmlFor='pulse'>Pulse:</label>
                        <input type='text' id='pulse' name='pulse' placeholder="Enter Patient's Pulse" value={pulse} onChange={this.changeHandler} ></input>

                        <label htmlFor='bloodPressure'>Blood Pressure:</label>
                        <input type='text' id='bloodPressure' name='bloodPressure' placeholder="Enter Patient's blood pressure" value={bloodPressure} onChange={this.changeHandler}></input>

                        <label htmlFor='complains'>Complains:</label>
                        <input type='text' id='complains' name='complains' placeholder="Enter Patient's complains" value={complains} onChange={this.changeHandler}></input>

                        <label htmlFor='diagnosis'>Diagnosis:</label>
                        <input type='text' id='diagnosis' name='diagnosis' placeholder="Enter Patient's diagnosis" value={diagnosis} onChange={this.changeHandler}></input>

                        <label htmlFor='prescription'>Prescription:</label>
                        <input type='text' id='prescription' name='prescription' placeholder="Enter Patient's prescription" value={prescription} onChange={this.changeHandler}></input>

                        <label htmlFor='dosage'>Dosage:</label>
                        <input type='text' id='dosage' name='dosage' placeholder="Enter Patient's medicine dosage" value={dosage} onChange={this.changeHandler}></input>

                        <label htmlFor='instruction'>Instruction:</label>
                        <input type='text' id='instruction' name='instruction' placeholder="Enter Patient's medicine related instruction" value={instruction} onChange={this.changeHandler}></input>

                        <label htmlFor='symptoms'>Symptoms:</label>
                        <input type='text' id='symptoms' name='symptoms' placeholder="Enter Patient's symptoms" value={symptoms} onChange={this.changeHandler}></input>

                        <label htmlFor='timings'>Timings:</label>
                        <input type='text' id='timings' name='timings' placeholder="Enter Patient's medicine timings" value={timings} onChange={this.changeHandler}></input>

                        <button type='submit'>Save and Exit Patient</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default PatientConsultation


// axios.post(`${base_url}/doctor/delete-patient`, {
//     doctorId: this.state.doctorId,
//     patientId: this.state.patientId
// }, {
//     headers: {
//         Authorization: `Bearer ${this.state.TOKEN}`
//     }
// })
//     .then(res => {
//         if(res.status >= 200 && res.status <= 299) {
//             axios.post(`${base_url}/doctor/patient-list`, {
//                 doctorId: this.state.doctorId
//             }, {
//                 headers: {
//                     Authorization: `Bearer ${this.state.TOKEN}`
//                 }
//             })
//                 .then(res => {
//                     if(res.status >= 200 && res.status <= 299) {
//                         this.setState({
//                             objectOfPatientList: res.data,
//                             goToPatientSearch: true
//                         })
//                     }
//                 })
//         }
//     })