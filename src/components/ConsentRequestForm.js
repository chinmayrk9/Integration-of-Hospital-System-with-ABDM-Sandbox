import axios from 'axios'
import React, { Component } from 'react'
import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'
import base_url from './Url'
import {Navigate} from "react-router-dom";

class ConsentRequestForm extends Component {

  constructor(props) {
    super(props)

    this.state = {
      patientIdentifier: '',
      purposeOfRequest: '',

      HealthInfoFrom: null,
      HealthInfoTo: null,
      consentExpiry: null,

      isDRchecked: false,
      isDSchecked: false,
      isPchecked: false,
      isIRchecked: false,
      isOPCchecked: false,
      isHDRchecked: false,
      isWRchecked: false,

      TOKEN: this.props.data?.state?.TOKEN || null,

      goToPatientConsentTable: false,

      patientConsentTab: []
    }
  }

  drHandler = e => {
    this.setState({
      isDRchecked: e.target.checked
    })
  }

  dsHandler = e => {
    this.setState({
      isDSchecked: e.target.checked
    })
  }

  pHandler = e => {
    this.setState({
      isPchecked: e.target.checked
    })
  }

  irHandler = e => {
    this.setState({
      isIRchecked: e.target.checked
    })
  }

  opcHandler = e => {
    this.setState({
      isOPCchecked: e.target.checked
    })
  }

  hdrHandler = e => {
    this.setState({
      isHDRchecked: e.target.checked
    })
  }

  wrHandler = e => {
    this.setState({
      isWRchecked: e.target.checked
    })
  }

  changeHandler = e => {
    this.setState({
      [e.target.name]: e.target.value
    })
  }

  handleHealthInfoFrom = (date) => {
    this.setState({
      HealthInfoFrom: date
    })
  }

  handleHealthInfoTo = (date) => {
    this.setState({
      HealthInfoTo: date
    })
  }

  handleConsentExpiry = (date) => {
    this.setState({
      consentExpiry: date
    })
  }

  patientConsentDetailsSubmit = e => {
    e.preventDefault()
    axios.post(`${base_url}/doctor/init-consent`, {
      requestId: '',
      timestamp: '',
      consent: {
        purpose: {
          text: 'string',
          code: 'CAREMGT'
        },
        patient: {
          id: this.props.data?.state?.patientAbhaId
        },
        hiu: {
          id: ''
        },
        requester: {
          name: 'adwait',
          identifier: {
            type: 'REGNO',
            value: this.props.data.state.doctorId,
            system: 'https://www.mciindia.org'
          }
        },
        hiTypes: ['OPConsultation'],
        permission: {
          accessMode: 'VIEW',
          dateRange: {
            from: this.state.HealthInfoFrom.toISOString(),
            to: this.state.HealthInfoTo.toISOString()
          },
          dataEraseAt: this.state.consentExpiry.toISOString(),
          frequency: {
            unit: 'HOUR',
            value: 1,
            repeats: 0
          }
        }
      }
    }, {
      headers: {
        Authorization: `Bearer ${this.state.TOKEN}`
      }
    })
        .then(res => {
          if(res.status >= 200 && res <= 299) {
            alert('Consent request Successfully sent! Wait for Medical Records to get fetched')
            axios.post(`${base_url}`/doctor/patient-consentlist, {
              abhaId: this.props.data.state.patientAbhaId
            }, {
              headers: {
                Authorization: `Bearer ${this.state.TOKEN}`
              }
            })
                .then(res => {
                  this.setState({
                    patientConsentTab: res.data,
                    goToPatientConsentTable: true
                  })
                })
          }
        })
  }

  render() {

    const { patientIdentifier, purposeOfRequest, HealthInfoFrom, HealthInfoTo, consentExpiry, isDRchecked, isDSchecked, isPchecked, isIRchecked, isOPCchecked, isHDRchecked, isWRchecked } = this.state
  if(this.state.goToPatientConsentTable) return <Navigate to='/patientconsenttable' state={this.state} />

    return (
      <div className='registrationBlock'>
        <div className='registrationHeading'>
          <h1>CONSENT REQUEST FORM</h1>
        </div>

        <div className='registrationNew'>
          <h4>All the field are mandatory</h4>

          <form className='patientDetailsForm' onSubmit={this.patientConsentDetailsSubmit}>

            <h4 htmlFor='patientIdentifier'>PATIENT IDENTIFIER:</h4>
            <input type='text' id='patientIdentifier' name='patientIdentifier' placeholder="Enter patient's abha Id" value={patientIdentifier} onChange={this.changeHandler} />

            <h4 htmlFor='purposeOfRequest'>PURPOSE OF REQUEST::</h4>
            <input type='text' id='purposeOfRequest' name='purposeOfRequest' placeholder="Enter purpose of request" value={purposeOfRequest} onChange={this.changeHandler} />


            <h4>Health Info from</h4>
            <DatePicker selected={HealthInfoFrom} onChange={this.handleHealthInfoFrom} placeholderText="Select a date" />

            <h4>Health Info to</h4>
            <DatePicker selected={HealthInfoTo} onChange={this.handleHealthInfoTo} placeholderText="Select a date" />

            <div className='checkboxSelectorDiv'>
              <h4>Diagnostic Reports</h4>
              <input type='checkbox' name='isDRchecked' value='Diagnostic Reports' checked={isDRchecked} onChange={this.drHandler} />
            </div>

            <div className='checkboxSelectorDiv'>
              <h4>Discharge Summary</h4>
              <input type='checkbox' name='isDSchecked' value='Discharge Summary' checked={isDSchecked} onChange={this.dsHandler} />
            </div>

            <div className='checkboxSelectorDiv'>
              <h4>prescription</h4>
              <input type='checkbox' name='isPchecked' value='prescription' checked={isPchecked} onChange={this.pHandler} />
            </div>

            <div className='checkboxSelectorDiv'>
              <h4>Immunization Record</h4>
              <input type='checkbox' name='isIRchecked' value='Immunization Record' checked={isIRchecked} onChange={this.irHandler} />
            </div>

            <div className='checkboxSelectorDiv'>
              <h4>OP Consulation</h4>
              <input type='checkbox' name='isOPCchecked' value='OP Consulation' checked={isOPCchecked} onChange={this.opcHandler} />
            </div>

            <div className='checkboxSelectorDiv'>
              <h4>Health Document Record</h4>
              <input type='checkbox' name='isHDRchecked' value='Health Document Record' checked={isHDRchecked} onChange={this.hdrHandler} />
            </div>

            <div className='checkboxSelectorDiv'>
              <h4>Wellness Record</h4>
              <input type='checkbox' name='isWRchecked' value='Wellness Record' checked={isWRchecked} onChange={this.wrHandler} />
            </div>

            <h4>Consent Expiry</h4>
            <DatePicker selected={consentExpiry} onChange={this.handleConsentExpiry} placeholderText="Select a date" />

            <button type='submit'>Save</button>
          </form>
        </div>
      </div>
    )
  }
}

export default ConsentRequestForm
