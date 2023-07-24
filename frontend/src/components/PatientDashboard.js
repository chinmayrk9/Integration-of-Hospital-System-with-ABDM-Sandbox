import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
import base_url from "./Url";
import axios from "axios";

class PatientDashboard extends Component {

    constructor(props) {
        super(props)

        this.state = {
            goToConsultation: false,
            goToConsentRequestForm: false,

            TOKEN: this.props.data.state.TOKEN || null,
            doctorName: this.props.data?.state?.doctorName || '',
            doctorId: this.props.data?.state?.doctorId || '',

            patientId: this.props.data?.state?.patientId || '',
            patientName: this.props.data?.state?.patientName || '',
            patientDateOfBirth: this.props.data?.state?.patientDateOfBirth || '',
            patientGender: this.props.data?.state?.patientGender || '',
            patientAbhaId: this.props.data?.state?.patientAbhaId || '',

            patientInfo: this.props.data.state.patientInfo,

            patientConsentTab: []
        }
    }

    consultationClickHandler = e => {
        this.setState({
            goToConsultation: true
        })
    }

    consentRequestHandler = e => {

        e.preventDefault()

        axios.post(`${base_url}/doctor/patient-consentlist`, {
            abhaId: this.props.data.state.patientAbhaId
        }, {
            headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
            }
        })
            .then(res => {
                this.setState({
                    patientConsentTab: res.data,
                    goToConsentRequestForm: true
                })
            })
    }

    render() {

        // const visitsList = this.props.data?.state?.patientInfo?.visits.map((visit, index) => <li key={index}> {visit.date} </li>) || <h4>Nothing to Show </h4>
        // const vitalsList = this.props.data?.state?.patientInfo.map((entry, index) => <li key={index}> {entry.bloodPressure} AND {entry.pulse} </li>) || <h4>Nothing to Show </h4>
        // const treatmentsList = this.props.data?.state?.patientInfo?.treatment.map((treatment, index) => <li key={index}> {treatment.value} </li>) || <h4>Nothing to Show </h4>
        // const diagnosisList = this.props.data?.state?.patientInfo?.diagnosis.map((diagnosis, index) => <li key={index}> {diagnosis.value} </li>) || <h4>Nothing to Show </h4>
        // const labResultsList = this.props.data?.state?.patientInfo?.labResult.map((labResult, index) => <li key={index}> {labResult.value} </li>) || <h4>Nothing to Show </h4>

        const prescriptions = this.props.data?.state?.patientInfo.map((entry, index) => <li key={index}> {entry.medicine}  </li>) || <h4>Nothing to Show </h4>
        const dosages = this.props.data?.state?.patientInfo.map((entry, index) => <li key={index}> {entry.dosage}  </li>) || <h4>Nothing to Show </h4>
        const timings = this.props.data?.state?.patientInfo.map((entry, index) => <li key={index}> {entry.timings}  </li>) || <h4>Nothing to Show </h4>
        const diagnosis = this.props.data?.state?.patientInfo.map((entry, index) => <li key={index}> {entry.diagnosis}  </li>) || <h4>Nothing to Show </h4>
        const pulses = this.props.data?.state?.patientInfo.map((entry, index) => <li key={index}> {entry.pulse}  </li>) || <h4>Nothing to Show </h4>
        const bps = this.props.data?.state?.patientInfo.map((entry, index) => <li key={index}> {entry.bloodPressure}  </li>) || <h4>Nothing to Show </h4>

        if (this.state.goToConsultation) return <Navigate to='/consultation' state={this.state} />

        else if (this.state.goToConsentRequestForm) return <Navigate to='/patientconsenttable' state={this.state} />
        else
            return (
                <div>
                    <div className='topbar'>
                        <svg height='30px' width='30px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" /></svg>
                        <h4>{this.props.data?.state?.patientName}</h4>
                        <h4>{this.props.data?.state?.patientGender}</h4>
                        <h4>{2023 - this.props.data?.state?.patientDateOfBirth}</h4>
                        <h4>{this.props.data?.state?.patientId}</h4>

                        <form id='patientDashboardForm' >
                            <button type='submit' onClick={this.consultationClickHandler} >Consultation</button>
                            <button type='submit' onClick={this.consentRequestHandler}>Consent Request</button>
                        </form>

                    </div>

                    <div className='prescription'>
                        <div className='prescriptionHeading'>
                            <h4>Prescriptions</h4>
                            <div>
                                <ul>
                                    <li>{prescriptions}</li>
                                </ul>
                            </div>

                        </div>
                    </div>

                    <div className='dosage'>
                        <div className='dosageHeading'>
                            <h4>Dosage</h4>
                            <div>
                                <ul>
                                    <li>{dosages}</li>
                                </ul>
                            </div>

                        </div>
                    </div>

                    <div className='timings'>
                        <div className='timingsHeading'>
                            <h4>timings</h4>
                            <div>
                                <ul>

                                    <li>{timings}</li>
                                </ul>
                            </div>


                        </div>
                    </div>

                    <div className='diagnosis'>
                        <div className='diagnosisHeading'>
                            <h4>diagnosis</h4>
                            <h4>
                                <ul>
                                    <li>{diagnosis}</li>
                                </ul>
                            </h4>

                        </div>
                    </div>

                    <div className='pulses'>
                        <div className='pulsesHeading'>
                            <h4>pulses</h4>
                            <div>
                                <ul>

                                    <li>{pulses}</li>
                                </ul>
                            </div>

                        </div>
                    </div>

                    <div className='bps'>
                        <div className='bpsHeading'>
                            <h4>Blood Pressure</h4>
                            <div>
                                <ul>

                                    <li>{bps}</li>
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
            )
    }
}

export default PatientDashboard
