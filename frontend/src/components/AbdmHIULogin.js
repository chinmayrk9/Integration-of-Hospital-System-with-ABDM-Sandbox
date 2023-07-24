import React, { Component } from 'react'
import axios from "axios";
import base_url from "./Url";
import {Navigate} from "react-router-dom";

export class AbdmHIULogin extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: '',
            password: '',
            flag: false,
            TOKEN: null,
            doctorName: '',
            doctorHosId: '',
            patientConsentTab: []
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
                    axios.post(`${base_url}/login/all-consentlist`, {
                    }, )
                        .then(res => {
                            this.setState({
                                patientConsentTab: res.data,
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
        if(this.state.flag) return <Navigate to='/allconsent' state={this.state.patientConsentTab} />
        else
        return (
            <div>
                <div className='loginblock'>

                    <div className='loginheading'>
                        <h1>ABDM HIU DOCTOR LOGIN</h1>
                    </div>

                    <form className='loginmenu' onSubmit={this.submitHandler}>

                        <div className='loginusername'>
                            <label htmlFor="id">id:</label>
                            <input type="text" id="id" name="id" value={this.state.id} onChange={this.changeHandler} placeholder='Enter your Id' />
                        </div>

                        <div className='loginpassword'>
                            <label htmlFor="password">Password:</label>
                            <input type="password" id="password" name="password" value={this.state.password.password} onChange={this.changeHandler} placeholder='Enter your password'/>
                        </div>

                        <button type='submit' className='loginbutton'>Submit</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default AbdmHIULogin
