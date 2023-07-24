import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
import axios from 'axios'
import base_url from './Url'

class PatientSearch extends Component {

  constructor(props) {
    super(props)

    this.state = {
      patientSelected: false,

      patientInfo: [],

      TOKEN: this.props.data?.state?.TOKEN || null,

      doctorName: this.props.data?.state?.doctorName || '',
      doctorId: this.props.data?.state?.doctorId || '',
      
      patientId: '',
      patientName: '',
      PatientDateOfBirth: '',
      PatientGender: '',
      patientAbhaId: ''
    }
  }

  patientClickHandler = (patientId, patientName, PatientDateOfBirth, PatientGender, patientAbhaId) => {

    axios.post(`${base_url}/doctor/getRecord`, {
      patientId: patientId
    }, {
      headers: {
        Authorization: `Bearer ${this.state.TOKEN}`
      }
    })
      .then(res => {
        if (res.status >= 200 && res.status <= 299) {
          this.setState({
            patientId: patientId,
            patientName: patientName,
            PatientGender: PatientGender,
            PatientDateOfBirth: PatientDateOfBirth,
            patientAbhaId: patientAbhaId,

            patientInfo: res.data,
            patientSelected: true
          }, () => {
            console.log(this.state)
          })
        }
      })
  }

  render() {

    const patientDescription = this.props.data?.state?.objectOfPatientList?.patientList.map((patient,index) => (
      <div key={index} className='patientSearchIcon' onClick={() => this.patientClickHandler(patient.patientId, patient.patientName, patient.dateOfBirth, patient.gender, patient.abhaId)}>
        <svg height='60px' width='60px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z" /></svg>
        <p>{patient.patientName}</p>
        <p>{patient.patientId}</p>
      </div>
    )) || (<h4>Nothing to Show</h4>)

    if (this.state.patientSelected) return <Navigate to='/dashboard' state={this.state} />
    else
      return (
        <div className='patientSearchBlock'>

          <div className='patientSearchHeading'>
            <h1>List Of Active Patients</h1>
          </div>

          <form className='patientSearchDiv'>
            <label htmlFor='patientid'>Patient ID:</label>
            <input type='text' id='patientid' name='patientid' placeholder='Enter Patient ID'></input>
            <button type='submit'>Search</button>
          </form>

          <div className='patientSearchMenu'>
            {patientDescription}
          </div>
        </div>
      )
  }
}

export default PatientSearch
