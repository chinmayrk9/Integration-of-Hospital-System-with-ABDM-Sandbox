import './App.css';
import { Routes, Route } from 'react-router-dom'
import Home from './components/Home'
import Navbar from './components/Navbar';
import WrapperRegistration from './components/WrapperRegistration';
import WrapperAssignment from './components/WrapperAssignment';
import WrapperPatientSearch from './components/WrapperPatientSearch';
import WrapperPatientConsultation from './components/WrapperPatientConsultation';
import WrapperPatientDashboard from './components/WrapperPatientDashboard';
import ReceptionistLogin from './components/ReceptionistLogin';
import DoctorLogin from './components/DoctorLogin';
import AdminLogin from './components/AdminLogin';
import NoMatch from './components/NoMatch';
import ConsentTable from './components/ConsentTable';
import WrapperAbhaVerification from './components/WrapperAbhaVerification';
import WrapperAdminSelect from './components/WrapperAdminSelect';
import WrapperDoctorAdd from './components/WrapperDoctorAdd';
import WrapperReceptionistAdd from './components/WrapperReceptionistAdd';
import WrapperReceptionistDelete from './components/WrapperReceptionistDelete';
import WrapperDoctorDelete from './components/WrapperDoctorDelete';
import WrapperPatientLogin from './components/WrapperPatientLogin';
import WrapperConsentRequestForm from "./components/WrapperConsentRequestForm";
import WrapperPatientConsentTable from "./components/WrapperPatientConsentTable";
import WrapperPatientMedicalData from "./components/WrapperPatientMedicalData";
import testOnData from "./components/TestOnData";
import TestOnData from "./components/TestOnData";
import WrapperAllConsent from "./components/WrapperAllConsent";
import AbdmHIULogin from "./components/AbdmHIULogin";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path='/' element={<Home />}></Route>

        <Route path='receptionistlogin' element={<ReceptionistLogin/>}></Route>
        <Route path='abha' element={<WrapperAbhaVerification/>}></Route>
        <Route path='registration' element={<WrapperRegistration/>}></Route>
        <Route path='assignment' element={<WrapperAssignment/>}></Route> 

        <Route path='doctorlogin' element={<DoctorLogin/>}></Route>
        
        <Route path='search' element={<WrapperPatientSearch />}></Route>
        <Route path='consultation' element={<WrapperPatientConsultation />}></Route>
        <Route path='dashboard' element={<WrapperPatientDashboard/>}></Route>
        <Route path='assignment' element={<WrapperAssignment/>}></Route> 
        
        <Route path='patientlogin' element={<WrapperPatientLogin/>}></Route>
        <Route path='adminlogin' element={<AdminLogin/>}></Route>

        <Route path='adminselect' element={<WrapperAdminSelect/>}></Route>

        <Route path='receptionistadd' element={<WrapperReceptionistAdd/>}></Route>
        <Route path='receptionistdelete' element={<WrapperReceptionistDelete/>}></Route>

        <Route path='doctoradd' element={<WrapperDoctorAdd/>}></Route>
        <Route path='doctordelete' element={<WrapperDoctorDelete/>}></Route>
        
        <Route path='consent' element={<WrapperConsentRequestForm/>}></Route>

        <Route path='ptable' element={<ConsentTable/>}></Route>

        <Route path='patientconsenttable' element={<WrapperPatientConsentTable/>}></Route>

        <Route path='patientmedicaldata' element={<WrapperPatientMedicalData/>}></Route>

        <Route path='testOnData' element={<TestOnData/>}></Route>

        <Route path='allconsent' element={<WrapperAllConsent/>}></Route>

        <Route path='abdmhiu' element={<AbdmHIULogin/>}></Route>



        <Route path='*' element={<NoMatch/>}></Route>
      </Routes>
    </>
  );
}

export default App;
