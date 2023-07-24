import axios from 'axios'
import React, { Component } from 'react'
import base_url from './Url'
import { Navigate } from 'react-router-dom'

class testOnData extends Component {
    constructor(props) {
        super(props)

        this.state = {
            pulse: '70',
            bloodPressure: '120/80',
            complains: 'headache',
            diagnosis: 'cold',
            prescription: 'zady',
            instruction: 'No oily food',
            dosage: '3 Times',
            symptoms: 'high fewer',
            timings: '1-1-1',

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


                        </form>
                    </div>
                </div>
            )
    }
}

export default testOnData
