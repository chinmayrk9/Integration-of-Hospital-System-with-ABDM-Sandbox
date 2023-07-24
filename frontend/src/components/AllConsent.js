import React, { Component } from 'react'
import ConsentTable from "./ConsentTable";
import {NavLink} from "react-router-dom";

class AllConsent extends Component {
    render() {
        return (
            <div>
                <NavLink to='/'>
                    <div className='homeicon'>
                        <p>Go To Home</p>
                    </div>

                </NavLink>
                <ConsentTable tab = {this.props.data.state} />
            </div>


        )
    }
}

export default AllConsent
