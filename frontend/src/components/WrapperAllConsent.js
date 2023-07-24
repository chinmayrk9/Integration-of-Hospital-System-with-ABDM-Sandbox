import React from 'react'
import {useLocation} from "react-router-dom";
import ConsentTable from "./ConsentTable";
import AllConsent from "./AllConsent";

function WrapperAllConsent() {
    const location = useLocation()
    return (
        <AllConsent data = {location} />
    )
}

export default WrapperAllConsent
