import React, { Component } from 'react'
import axios from 'axios'
import { Navigate } from 'react-router-dom'
import base_url from './Url'

export class AdminLogin extends Component {
  constructor(props) {
    super(props)
 
    this.state = {
      id: '',
      password: '',
      goToAdminDashboard: false,
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
        if (res.status >= 200 && res.status <= 299 && res.data.role === 'ROLE_admin') {
          this.setState({
            goToAdminDashboard: true,
            TOKEN: res.data.token
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
    const { id, password, goToAdminDashboard } = this.state

    if(goToAdminDashboard) return <Navigate to='/adminselect' state={{
      TOKEN: this.state.TOKEN
    }}/>
    else
    return (
      <div>
        <div className='loginblock'>

          <div className='loginheading'>
            <h1>ADMIN LOGIN</h1>
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
export const ADMINTOKEN = AdminLogin.TOKEN
export default AdminLogin


