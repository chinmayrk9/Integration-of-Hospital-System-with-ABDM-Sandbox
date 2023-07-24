import React, { Component } from 'react'
import axios from 'axios'
import { Navigate } from 'react-router-dom'
import base_url from './Url'

export class ReceptionistLogin extends Component {

    constructor(props) {
      super(props)
    
      this.state = {
        id: '',
        password: '',
        goToAbha: false,
        type: 'receptionist',
        TOKEN: null
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
                if(res.status >= 200 && res.status <= 299 && res.data.role === 'ROLE_receptionist') {
                    this.setState({
                        TOKEN: res.data.token,
                        goToAbha: true
                    })
                }
                else alert('Invalid Token or Credentials')
            })
            .catch(err => {
                alert('Server Down')
                console.log(err)
            })
    }

    render() {
        const {id, password, goToAbha} = this.state

        if(goToAbha) return <Navigate to='/abha' state={{TOKEN : this.state.TOKEN}}/>
        else
        return (
            <div>
                <div className='loginblock'>

                    <div className='loginheading'>
                        <h1>RECEPTIONIST LOGIN</h1>
                    </div>
  
                    <form className='loginmenu' onSubmit = {this.submitHandler}>

                        <div className='loginusername'>
                            <label htmlFor="id">id:</label>
                            <input type="text" id="id" name="id" value={id} onChange={this.changeHandler} placeholder='Enter your Username'/>
                        </div>

                        <div className='loginpassword'>
                            <label htmlFor="password">Password:</label>
                            <input type="password" id="password" name="password" value={password} onChange={this.changeHandler} placeholder='Enter your Password'/>
                        </div>

                        <button type='submit' className='loginbutton'>Submit</button>
                    </form>
                </div>
            </div>
        )
    }
}
export default ReceptionistLogin