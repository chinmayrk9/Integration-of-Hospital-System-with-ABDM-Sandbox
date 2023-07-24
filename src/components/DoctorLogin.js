import React, { Component } from 'react'
import axios from 'axios'
import { Navigate } from 'react-router-dom'
import base_url from './Url'

export class DoctorLogin extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: '',
            password: '',
            flag: false,
            objectOfPatientList: {},
            type: 'doctor',
            TOKEN: null,
            doctorName: '',
            doctorHosId: ''
        }
    }

    changeHandler = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    submitHandler = (event) => {
        event.preventDefault()
        axios
            .post(`${base_url}/login`, {
                id: this.state.id,
                password: this.state.password
            })
            .then(res => {
                if (res.status >= 200 && res.status <= 299 && res.data.role === 'ROLE_doctor') {
                    this.setState({
                        TOKEN: res.data.token,
                        doctorName: res.data.name,
                        doctorHosId: res.data.hosId
                    })
                    axios.post(`${base_url}/doctor/patient-list`, {
                        doctorId: this.state.doctorHosId
                    }, {
                        headers: {
                            Authorization: `Bearer ${this.state.TOKEN}`
                        }
                    })
                        .then(res => {
                            this.setState({
                                objectOfPatientList: res.data,
                                flag: true
                            })
                        })
                        .catch(e => {
                            console.log(e)
                        })

                }
                else alert('Invalid Credentials')
            })
            .catch(err => {
                console.log(err)
                alert('Server Down')
            })
    }

    render() {
        console.log(this.state.TOKEN)
        const { id, password, flag } = this.state

        if (flag) return <Navigate to='/search' state={{
            objectOfPatientList: this.state.objectOfPatientList,
            TOKEN: this.state.TOKEN,
            doctorName: this.state.doctorName,
            doctorId: this.state.doctorHosId
        }} />
        else
            return (
                <div>
                    <div className='loginblock'>

                        <div className='loginheading'>
                            <h1>DOCTOR LOGIN</h1>
                        </div>

                        <form className='loginmenu' onSubmit={this.submitHandler}>

                            <div className='loginusername'>
                                <label htmlFor="id">id:</label>
                                <input type="text" id="id" name="id" value={id} onChange={this.changeHandler} />
                            </div>

                            <div className='loginpassword'>
                                <label htmlFor="password">Password:</label>
                                <input type="password" id="password" name="password" value={password} onChange={this.changeHandler} />
                            </div>

                            <button type='submit' className='loginbutton'>Submit</button>
                        </form>
                    </div>
                </div>
            )
    }
}
export default DoctorLogin