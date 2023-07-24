import React, { Component } from 'react'
import ConsentTable from "./ConsentTable";
import base_url from "./Url";
import {Navigate} from "react-router-dom";

class PatientConsentTable extends Component {
    constructor(props) {
        super(props)

        this.state = {
            goToConsentRequestForm: false
        }
    }

    clickHandler = e => {
        this.setState({
            goToConsentRequestForm: true
        })
    }

    render() {
        if(this.state.goToConsentRequestForm) return <Navigate to='/consent' state={this.props.data.state} />
        else
        return (
            <div>
                <button onClick={this.clickHandler} >New Consent Request</button>
                <ConsentTable tab = {this.props.data.state}/>

            </div>
        )
    }
}

export default PatientConsentTable