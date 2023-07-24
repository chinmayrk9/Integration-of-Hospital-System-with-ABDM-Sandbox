import React, { Component } from 'react'
import axios from 'axios'
import { Navigate } from 'react-router-dom' 
import base_url from './Url'

class Registration extends Component {
  constructor(props) {
    super(props)

    this.state = {
      name: this.props.data?.state?.patientDemographic?.name,
      gender: this.props.data?.state?.patientDemographic?.gender,
      yearOfBirth: this.props.data?.state?.patientDemographic?.yearOfBirth,
      monthOfBirth: '',
      dayOfBirth: '',
      line: null,
      district: '',
      state: '',
      pincode: '',
      weight: '',
      mobileNumber: '',
      country: 'India',
      emailId: '',
      bloodGroup: '',
      goToAssignment: false,
      patientId: '',
      address: '',
      abhaId : '',

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

  patientDetailsSubmit = e => {
    e.preventDefault()
    axios.post(`${base_url}/receptionist/save-patient`, {
      abhaId: this.props.data?.state?.patientDemographic?.abhaId,
      mobileNumber: this.state.mobileNumber,
      healthNumber: this.props.data?.state?.patientDemographic?.healthNumber,
      patientId: this.props.data?.state?.patientDemographic?.patientId,
      bloodGroup: this.state.bloodGroup,
      weight: this.state.weight,
      emailId: this.state.emailId,
      country: this.state.country,
      name: this.props.data?.state?.patientDemographic?.name,
      gender: this.props.data?.state?.patientDemographic?.gender,
      yearOfBirth: this.props.data?.state?.patientDemographic?.yearOfBirth,
      monthOfBirth: this.state.monthOfBirth,
      dayOfBirth: this.state.dayOfBirth,
      line: this.state.line,
      district: this.state.district,
      state: this.state.state,
      pincode: this.state.pincode,
    }, {
      headers: {
          Authorization: `Bearer ${this.state.TOKEN}`
      }
  })
    .then(r => {
      if(r.status >= 200 && r.status <= 299) {
        this.setState({
          patientId: this.props.data?.state?.patientDemographic?.patientId,
          abhaId: this.props.data?.state?.patientDemographic?.abhaId,
          goToAssignment: true
        }, () => {
          console.log(this.state)
        })
      }
      else console.log('error encountered at registration component')
    })
  }

  render() {
    var { name, gender, yearOfBirth, monthOfBirth, dayOfBirth, address, district, state, pincode, weight, mobileNumber, country, emailId, bloodGroup, goToAssignment} = this.state;
    
    var pmonth = this.props.data?.state?.patientDemographic?.monthOfBirth || monthOfBirth
    var pname = this.props.data?.state?.patientDemographic?.name || name
    var pgender = this.props.data?.state?.patientDemographic?.gender || gender
    var pyear = this.props.data?.state?.patientDemographic?.yearOfBirth || yearOfBirth
    var pdate = this.props.data?.state?.patientDemographic?.dayOfBirth || dayOfBirth
    var pdistrict = this.props.data?.state?.patientDemographic?.district || district
    var pstate = this.props.data?.state?.patientDemographic?.state || state
    var ppincode = this.props.data?.state?.patientDemographic?.pincode || pincode
    var pmobile = this.props.data?.state?.patientDemographic?.mobileNumber || mobileNumber

    if(goToAssignment) return <Navigate to='/assignment' state={this.state}/>
    else
    return (
      <div className='registrationBlock'>
        <div className='registrationHeading'>
          <h1>PATIENT REGISTRATION FORM</h1>
        </div>

        <div className='registrationNew'>
          <h4>Patient Demographic and other details</h4>

          <form className='patientDetailsForm' onSubmit={this.patientDetailsSubmit}>
            <label htmlFor='name'>NAME:</label>
            <input type='text' id='name' name='name' placeholder="Enter Patient's Name" value={name = pname? pname : name} />

            <label htmlFor="gender">GENDER:</label>
            <select name='gender' id='gender' value={gender = pgender? pgender : gender} >
              <option value='M'>Male</option>
              <option value='F'>Female</option>
              <option value='O'>Other</option>
            </select>

            <label htmlFor='yearOfBirth'>YEAR:</label>
            <input type='text' id='yearOfBirth' name='yearOfBirth' placeholder="Enter Patient's Year of Birth" value={yearOfBirth = pyear ? pyear : yearOfBirth}  />
            <label htmlFor='monthOfBirth'>MONTH:</label>
            <input type='text' id='monthOfBirth' name='monthOfBirth' placeholder="Enter Patient's Month of Birth" value={monthOfBirth = pmonth ? pmonth : monthOfBirth} />
            <label htmlFor='dayOfBirth'>DATE:</label>
            <input type='text' id='dayOfBirth' name='dayOfBirth' placeholder="Enter Patient's Date of Birth" value={dayOfBirth = pdate ? pdate : dayOfBirth}  />

            <label htmlFor='line'>ADDRESS:</label>
            <input type='text' id='address' name='address' placeholder="Enter Patient's address" value={address} onChange={this.changeHandler} />

            <label htmlFor='district'>DISTRICT:</label>
            <input type='text' id='district' name='district' placeholder="Enter Patient's District of Residence" value={district = pdistrict ? pdistrict : district} />

            <label htmlFor='state'>STATE:</label>
            <input type='text' id='state' name='state' placeholder="Enter Patient's State of Residence" value={state = pstate ? pstate : state} />

            <label htmlFor='pincode'>PINCODE:</label>
            <input type='text' id='pincode' name='pincode' placeholder="Enter Patient's Pincode of Residence" value={pincode = ppincode ? ppincode : pincode} onChange={this.changeHandler} />

            <label htmlFor='weight'>WEIGHT(kg):</label>
            <input type='text' id='weight' name='weight' placeholder="Enter Patient's Weight in Kg" value={weight} onChange={this.changeHandler} />

            <label htmlFor='mobileNumber'>MOBILE NO:</label>
            <input type='text' id='mobileNumber' name='mobileNumber' placeholder="Enter Patient's Mobile No" value={mobileNumber = pmobile ? pmobile : mobileNumber} />

            <label htmlFor='emailId'>EMAIL:</label>
            <input type='text' id='emailId' name='emailId' placeholder="Enter Patient's email address" value={emailId} onChange={this.changeHandler} />

            <label htmlFor='country'>COUNTRY:</label>
            <input type='text' id='country' name='country' placeholder="Enter Patient's Country of Residence" value={country} />

            <label htmlFor='bloodGroup'>BLOOD GROUP:</label>
            <input type='text' id='bloodGroup' name='bloodGroup' placeholder="Enter Patient's Blood Group" value={bloodGroup} onChange={this.changeHandler} />
            
            <button type='submit'>Save</button>
          </form>
        </div>
      </div>
    )
  }
}

export default Registration

