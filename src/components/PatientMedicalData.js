import React, { Component } from 'react'
import ConsentTable from "./ConsentTable";
export class PatientMedicalData extends Component {
    render() {
        return (
            <ConsentTable tab = {this.props.data.state} />
        )
    }
}

export default PatientMedicalData