import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'

class AdminSelect extends Component { 

    constructor(props) {
      super(props)
    
      this.state = {
         goToReceptionistAdd: false,
         goToReceptionistDelete: false,
         goToDoctorAdd: false,
         goToDoctorDelete: false,
         TOKEN: this.props.data?.state?.TOKEN || null
      }
    }
    
    receptionistAddHandler = e => {
        this.setState({
            goToReceptionistAdd: true
        })
    }

    receptionistDeleteHandler = e => {
        this.setState({
            goToReceptionistDelete: true
        })
    }

    doctorAddHandler = e => {
        this.setState({
            goToDoctorAdd: true
        })
    }

    doctorDeleteHandler = e => {
        this.setState({
            goToDoctorDelete: true
        })
    }
    
    render() {
        console.log(this.state.TOKEN)
        const {goToDoctorAdd, goToDoctorDelete, goToReceptionistAdd, goToReceptionistDelete} = this.state

        if(goToDoctorAdd) return <Navigate to = '/doctoradd' state = {{TOKEN : this.state.TOKEN}}/>
        else if(goToDoctorDelete) return <Navigate to = '/doctordelete' state = {{TOKEN : this.state.TOKEN}}/>
        else if(goToReceptionistAdd) return <Navigate to = '/receptionistadd' state = {{TOKEN : this.state.TOKEN}}/>
        else if(goToReceptionistDelete) return <Navigate to = '/receptionistdelete' state = {{TOKEN : this.state.TOKEN}}/>
        else
        return (
            <div className='homeHeroImage'>
                <div className='homeblock'>
                    <div className='homeheading'>
                        <h1>What Role you want to Add / Delete?</h1>
                    </div>

                    <div className='homemenu'>

                            <div className='homeicon'>
                                <svg height='120px' width='120px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><path d="M96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM504 312V248H440c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V136c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H552v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z" /></svg>
                                <p>Receptionist</p>
                                <button type='submit' onClick={this.receptionistAddHandler}>ADD</button>
                                <button type='submit' onClick={this.receptionistDeleteHandler}>DELETE</button>
                            </div>

                            <div className='homeicon'>
                                <svg height='110px' width='110px' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-96 55.2C54 332.9 0 401.3 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7c0-81-54-149.4-128-171.1V362c27.6 7.1 48 32.2 48 62v40c0 8.8-7.2 16-16 16H336c-8.8 0-16-7.2-16-16s7.2-16 16-16V424c0-17.7-14.3-32-32-32s-32 14.3-32 32v24c8.8 0 16 7.2 16 16s-7.2 16-16 16H256c-8.8 0-16-7.2-16-16V424c0-29.8 20.4-54.9 48-62V304.9c-6-.6-12.1-.9-18.3-.9H178.3c-6.2 0-12.3 .3-18.3 .9v65.4c23.1 6.9 40 28.3 40 53.7c0 30.9-25.1 56-56 56s-56-25.1-56-56c0-25.4 16.9-46.8 40-53.7V311.2zM144 448a24 24 0 1 0 0-48 24 24 0 1 0 0 48z" /></svg>
                                <p>Doctor</p>
                                <button type='submit' onClick={this.doctorAddHandler}>ADD</button>
                                <button type='submit' onClick={this.doctorDeleteHandler}>DELETE</button>
                            </div>
                    </div>
                </div>
            </div>
        )
    }
}
export default AdminSelect
