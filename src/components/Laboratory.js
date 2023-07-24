import React, { Component } from 'react'
import axios from 'axios'
import { Navigate } from 'react-router-dom' 

class Registration extends Component {
  constructor(props) {
    super(props)

    this.state = {
      name: '',
      goToAssignment: false
    }
  }

  changeHandler = (event) => {
    this.setState({
      [event.target.name]: event.target.value
    })
  }
 
  patientDetailsSubmit = e => {
    e.preventDefault()
    axios.post('http://localhost:8080/save-patient', {
        name: this.state.name
    })
    .then(r => {
      if(r.status >= 200 && r.status <= 299) {
        this.setState({
          goToAssignment: true
        })
      }
      else console.log('error encountered at registration component')
    })
  }

  render() {
    var { name, goToAssignment} = this.state;
    var pname = this.props.data?.state?.name || name


    if(goToAssignment) return <Navigate to='/assignment' state={this.state}/>
    return (
      <div className='registrationBlock'>
        <div className='registrationHeading'>
          <h1>PATIENT REGISTRATION FORM</h1>
        </div>

        <div className='registrationNew'>
          <h4>Patient Demographic and other details</h4>

          <form className='patientDetailsSubmit'>
            <label htmlFor='name'>NAME:</label>
            <input type='text' id='name' name='name' placeholder="Enter Patient's Name" value={name = pname? pname : name} onChange={this.changeHandler} />
            
            <button type='submit'>Save</button>
          </form>
        </div>
      </div>
    )
  }
}

export default Registration
