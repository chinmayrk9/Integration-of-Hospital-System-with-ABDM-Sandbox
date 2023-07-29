package com.example.HAD.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.InMemoryTerminologyServerValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.SnapshotGeneratingValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Composition.CompositionStatus;
import org.hl7.fhir.r4.model.Composition.SectionComponent;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Encounter.DiagnosisComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterStatus;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestIntent;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestStatus;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.springframework.stereotype.Component;

import com.example.HAD.MedicalRecord.bean.MedicalRecords;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;

@Component
public class GenerateOpConsultOutput {


	static FhirContext ctx = FhirContext.forR4();

	static FhirInstanceValidator instanceValidator;
	static FhirValidator validator;
	
//	public GenerateOpConsultOutput() throws DataFormatException, FileNotFoundException
//	 {
//		 init();
//	 }
	
	
	public Bundle populateOPConsultNoteBundle(MedicalRecords opbmrModel,Bundle req) 
	{
		
		System.out.println("opbmrModel"+opbmrModel);
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = new Date(System.currentTimeMillis());
		
		Bundle opCounsultNoteBundle = req;
		List<BundleEntryComponent> listBundleEntries = opCounsultNoteBundle.getEntry();

		if(listBundleEntries.size()>0)
		{
			for(int i=0 ; i<listBundleEntries.size();i++)
			{
				BundleEntryComponent bundle = listBundleEntries.get(i);
				
				if(bundle.getResource().getResourceType().toString().equalsIgnoreCase("Composition"))
				{
				    
					Composition comp = (Composition)bundle.getResource();
				   
					populateOPConsultNoteCompositionResource(opbmrModel ,comp,listBundleEntries );
					
					
				}
			}
		}else
		{
			req = new Bundle();

			
			opCounsultNoteBundle.setId("OPConsultNote-example-01");

			Meta meta = opCounsultNoteBundle.getMeta();
			meta.setVersionId("1");
			meta.setLastUpdatedElement(new InstantType(formatter.format(date)));
			meta.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle");

			
			meta.addSecurity(new Coding("http://terminology.hl7.org/CodeSystem/v3-Confidentiality", "V", "very restricted"));
			
			
			Identifier identifier = opCounsultNoteBundle.getIdentifier();
			identifier.setValue("305fecc2-4ba2-46cc-9ccd-efa755aff51d");
			identifier.setSystem("http://hip.in");

		
			opCounsultNoteBundle.setType(BundleType.DOCUMENT);
			SimpleDateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			Date date2 = new Date(System.currentTimeMillis());
			
			opCounsultNoteBundle.setTimestampElement(new InstantType(formatter2.format(date2)));

			// Add resources entries for bundle with Full URL
			List<BundleEntryComponent> listBundleEntries1 = opCounsultNoteBundle.getEntry();
			if(listBundleEntries1.size()<=0) {
				Composition comp = new Composition();
				
				
			
				   
				comp = populateOPConsultNoteCompositionResource(opbmrModel ,comp,listBundleEntries );
			}
		}
		
		
		System.out.println("opCounsultNoteBundle"+opCounsultNoteBundle);

		return opCounsultNoteBundle;
	}

	/**
	 * This method initiates loading of FHIR default profiles and NDHM profiles for validation 
	 */
	public  void init() throws DataFormatException, FileNotFoundException
	{

		// Create xml parser object for reading profiles
		IParser parser = ctx.newJsonParser();

		// Create a chain that will hold our modules
		ValidationSupportChain supportChain = new ValidationSupportChain();
		
		// Add Default Profile Support
		// DefaultProfileValidationSupport supplies base FHIR definitions. This is generally required
		// even if you are using custom profiles, since those profiles will derive from the base
		// definitions.
		DefaultProfileValidationSupport defaultSupport = new DefaultProfileValidationSupport(ctx);
		
		// Create a PrePopulatedValidationSupport which can be used to load custom definitions.
		// In this example we're loading all the custom Profile Structure Definitions, in other scenario we might
		// load many StructureDefinitions, ValueSets, CodeSystems, etc.
		PrePopulatedValidationSupport prePopulatedSupport = new PrePopulatedValidationSupport(ctx);
		StructureDefinition sd ;
		
		/** LOADING PROFILES **/
		// Read all Profile Structure Definitions 
		
		String resourceFolderName = "Structuredefinitions";
		
		
        URL Url = getClass().getClassLoader().getResource(resourceFolderName);
        String baseFilePath = Url.getPath();
        String[] fileList = new File(baseFilePath).list(new WildcardFileFilter("*.json"));
		for(String file:fileList)
		{
			String fileFullPath = baseFilePath.substring(1) +"/"+ file;
			//Parse All Profiles and add to prepopulated support
			System.out.println(fileFullPath);
			
			sd = parser.parseResource(StructureDefinition.class, new FileReader(fileFullPath));
			
			prePopulatedSupport.addStructureDefinition(sd);
		}

		//Add Snapshot Generation Support
		SnapshotGeneratingValidationSupport snapshotGenerator = new SnapshotGeneratingValidationSupport(ctx);

		//Add prepopulated support consisting all structure definitions and Terminology support
		supportChain.addValidationSupport(defaultSupport);
		supportChain.addValidationSupport(prePopulatedSupport);
		supportChain.addValidationSupport(snapshotGenerator);
		supportChain.addValidationSupport(new InMemoryTerminologyServerValidationSupport(ctx));
		supportChain.addValidationSupport(new CommonCodeSystemsTerminologyService(ctx));

		// Create a validator using the FhirInstanceValidator module and register.
		instanceValidator = new FhirInstanceValidator(supportChain);
		validator = ctx.newValidator().registerValidatorModule(instanceValidator);

	}

	/**
	 * This method validates the FHIR resources 
	 */
	public static boolean validate(IBaseResource resource)
	{
		// Validate
		ValidationResult result = validator.validateWithResult(resource);

		// The result object now contains the validation results
		for (SingleValidationMessage next : result.getMessages()) {
			System.out.println(next.getSeverity().name() + " : " + next.getLocationString() + " " + next.getMessage());
		}

		return result.isSuccessful();
	}
	
	public Composition populateOPConsultNoteCompositionResource(MedicalRecords op , Composition comp, List<BundleEntryComponent> lb)
	{
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = new Date(System.currentTimeMillis());
		Composition composition = comp;
		String patientref = "";
		
		System.out.println("composition"+composition);

		if(composition.getId()==null) {
			composition.setId("Composition-"+new Random().nextInt(900));
			
			
			BundleEntryComponent bundleEntry2 = new BundleEntryComponent();
			bundleEntry2.setFullUrl("Composition/"+composition.getId());
			bundleEntry2.setResource(composition);
			lb.add(bundleEntry2);
			
			
			
			
		}
		

		
		if(composition.getMeta().getId()==null) {
		Meta meta = composition.getMeta();
		meta.setVersionId("1");
		meta.setLastUpdatedElement(new InstantType(formatter.format(date)));
		meta.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/OPConsultRecord");
		}

		if(composition.getLanguage()==null) {
			comp.setLanguage("en-IN");
			
			
			
		}

		if(composition.getText().getStatus()==null) {
			Narrative text= composition.getText();
			text.setStatus((NarrativeStatus.GENERATED));
			text.setDivAsString("This is OPBMR Treatment for patients");

		}
		if(composition.getIdentifier().getValue()==null) {
		Identifier identifier = composition.getIdentifier();
		identifier.setSystem("https://e-manas.karnataka.gov.in/virtualFolderID"); // refer the example bundle for op consultation in fhir website
		identifier.setValue(op.getPatientId());
		}

		// Status can be preliminary | final | amended | entered-in-error
		composition.setStatus(CompositionStatus.FINAL);
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-dd-MM");     
	    Date date1=new Date();
		try {
			date1 = formatter1.parse(op.getDate());
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		composition.setDate((date1));
		
		// Kind of composition ("Clinical consultation report")
		CodeableConcept type = composition.getType();
		type.addCoding(new Coding("http://snomed.info/sct", "371530004", "Clinical consultation report"));
		type.setText("Clinical Consultation report");

		//composition.setType(new CodeableConcept(new Coding("http://snomed.info/sct", "371530004", "Clinical consultation report")).setText("Clinical Consultation report"));

		// Set subject - Who and/or what the composition/OPConsultNote record is about
		
		if(composition.getSubject().getReference()==null) {
			
			
			
			Patient patient = new Patient();
			patient.setId("Patient-"+new Random().nextInt(900));
			patient.getMeta().setVersionId("1").setLastUpdatedElement(new InstantType(formatter.format(date))).addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient");
			patient.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString("Patient Details");
			patient.addIdentifier().setType(
					new CodeableConcept(new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MR", "Medical record number")))
								.setSystem("https://e-manas.karnataka.gov.in/").setValue(op.getPatientId());  //change the  emanas url
			patientref = "Patient/"+patient.getId();

			Reference reference = new Reference();
			reference.setReference("Patient/"+patient.getId());
			reference.setDisplay("Patients BMR Record");
			composition.setSubject(reference);
			
			
			BundleEntryComponent bundleEntry2 = new BundleEntryComponent();
			bundleEntry2.setFullUrl("Patient/"+patient.getId());
			bundleEntry2.setResource(patient);
			lb.add(bundleEntry2);
			
			
		}
		

		// Set Context of the Composition
		//composition.setEncounter(new Reference().setReference("Encounter/Encounter-01"));

		
		composition.setDateElement(new DateTimeType(new Date()));

		// Set author - Who and/or what authored the composition/OPConsultNote record
		
		if(composition.getAuthor().size()==0) {

			Practitioner practitioner = new Practitioner();
			practitioner.setId("Practitioner-"+new Random().nextInt(900));
			practitioner.getMeta().setVersionId("1").setLastUpdatedElement(new InstantType(formatter.format(date))).addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner");
			practitioner.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString(op.getDoctorId());
			practitioner.addIdentifier().setType(new CodeableConcept(new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MD", "Medical License number"))).setSystem("https://ndhm.in/DigiDoc").setValue(op.getDoctorId());
			practitioner.addName().setText(op.getDoctorId());    //getAuthor //name
			
			System.out.println("336");
			
			composition.addAuthor(new Reference().setReference("Practitioner/"+practitioner.getId()));

			
			BundleEntryComponent bundleEntry4 = new BundleEntryComponent();
			bundleEntry4.setFullUrl("Practitioner/"+practitioner.getId());
			bundleEntry4.setResource(practitioner);
			lb.add(bundleEntry4);
			
		}
		

		// Set a Human Readable name/title
		composition.setTitle("MHMS - op_bmr.v2");

		// Set Custodian - Organization which maintains the composition
		
	if(composition.getCustodian()==null) {
		Reference referenceCustodian = new Reference();
		referenceCustodian.setReference("Organization/Organization-01");
		referenceCustodian.setDisplay("0001");
		composition.setCustodian(referenceCustodian); //hip name
	}
	
	SimpleDateFormat formatter2= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	Date date2 = new Date(System.currentTimeMillis());
	if(composition.getEncounter().getReference()==null) {
		
		Encounter en = new Encounter();
	  	en.setId("Encounter-"+new Random().nextInt(999));
	  	en.setStatus(EncounterStatus.FINISHED);
	  	en.getMeta().setLastUpdatedElement(new InstantType(formatter2.format(date2))).addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter");
	  	en.getIdentifier().add(new Identifier().setSystem("https://ndhm.in").setValue(op.getVisitId()));  //getOrgID //hip id
		en.setClass_(new Coding("http://terminology.hl7.org/CodeSystem/v3-ActCode", "IMP", "inpatient encounter"));
		en.setSubject(new Reference().setReference(patientref));
		en.setPeriod(new Period().setStartElement(new DateTimeType(new Date())).setEndElement(new DateTimeType(new Date())));
		Narrative nr = new Narrative(); 
		nr.setDivAsString(op.getInstruction().toString());	 //getTreatmentInstructions
		nr.setStatusAsString("generated");
		en.setText(nr);
		
		
	
//		if(op.getImprovementStatus()!=null) {
//		Narrative nrservice = new Narrative();
//		nrservice.setDivAsString(op.getImprovementStatus().toString());
//		nrservice.setStatusAsString("generated");
//		en.setServiceType(new CodeableConcept()).setText(nrservice);
//		
//		}
		
//		if(op.getProblemDiagnosis()!=null)
//		{
//			
//			 System.out.println("551");
//			Condition condition1 = new Condition();
//			  condition1.setId("Condition-"+new Random().nextInt(900));
//			  condition1.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition");
//			  condition1.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString( op.getProblemDescription());
//			  condition1.setSubject(new Reference(patientref));
//			  condition1.getCode().addCoding(new Coding("http://snomed.info/sct", op.getProblemDiagnosisCode(), op.getDiagnosticCertainity())).setText(op.getProblemDescription());
//					
//			  BundleEntryComponent bundleEntry1 = new BundleEntryComponent();
//				bundleEntry1.setFullUrl("Condition/"+condition1.getId());
//				bundleEntry1.setResource(condition1);
//				  lb.add(bundleEntry1);
//				  
//				  
//				  DiagnosisComponent dc =new DiagnosisComponent() ;
//					dc.getCondition().setReference("Condition/"+condition1.getId());
//				
//					en.addDiagnosis(dc);
//				  
//		}
		
	  BundleEntryComponent bundleEntry2 = new BundleEntryComponent();
		bundleEntry2.setFullUrl("Encounter/"+en.getId());
		bundleEntry2.setResource(en);
		  lb.add(bundleEntry2);

       composition.setEncounter(new Reference().setReference("Encounter/"+en.getId()));
	}
	
	patientref = composition.getSubject().getReference().toString();
		
	List<SectionComponent> section =  composition.getSection();
		
	
	  if(op.getSymptoms()!= null)   //getSymptomSign
	  { 
		  
		  Condition condition1 = new Condition();
		  condition1.setId("Condition-"+new Random().nextInt(900));
		  condition1.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition");
		  condition1.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString(op.getPattern());
		  condition1.setSubject(new Reference(patientref));
		  condition1.getCode().addCoding(new Coding()).setText(op.getSymptoms());
		  
	Boolean sectionPresent = false;	  
	 for(SectionComponent sec : section) {
		 

		  if(sec.getTitle().equalsIgnoreCase("Chief complaints")) {		  
			  if(op.getPattern()!=null) {                                //getIllnessSummary
			  sec.getText().setDivAsString(op.getPattern().toString());
			  sec.getText().setStatusAsString("generated");
			  }
			  	sec.addEntry(new Reference().setReference("Condition/"+condition1.getId()));
				sectionPresent = true;
		  }
	 }
		
	 if(!sectionPresent)
	 {
		 SectionComponent sectionCheifComplaints = new SectionComponent();
		 sectionCheifComplaints.setTitle("Chief complaints");
		 sectionCheifComplaints.getText().setDivAsString(op.getPattern().toString());     ////getIllnessSummary
		 sectionCheifComplaints.getText().setStatusAsString("generated");
		 sectionCheifComplaints.setCode(new CodeableConcept(new Coding("http://snomed.info/sct", "422843007", "Chief complaint section"))).
		  addEntry(new Reference().setReference("Condition/"+condition1.getId()));
		 section.add(sectionCheifComplaints);
			
	 }
	
	 BundleEntryComponent bundleEntry1 = new BundleEntryComponent();
		bundleEntry1.setFullUrl("Condition/"+condition1.getId());
		bundleEntry1.setResource(condition1);
		  lb.add(bundleEntry1);
	  
	  }
		
		 if(op.getMedicine()!=null )//getMedicationItem
		  { 
			 	MedicationRequest medicationRequest = new MedicationRequest();
				medicationRequest.setId("MedicationRequest-"+new Random().nextInt(900));
				medicationRequest.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest");
				medicationRequest.setStatus(MedicationRequestStatus.ACTIVE);
				medicationRequest.setIntent(MedicationRequestIntent.ORDER);
				medicationRequest.setMedication(new CodeableConcept().setText(op.getMedicine()));
				medicationRequest.setSubject(new Reference().setReference(patientref));
				medicationRequest.setAuthoredOnElement(new DateTimeType(formatter2.format(date2)));
				medicationRequest.setRequester(new Reference().setReference("Practitioner/Practitioner-01").setDisplay(op.getDoctorId())); //doctor name
				medicationRequest.getReasonReference().add(new Reference().setReference("Condition/Condition-01"));
				medicationRequest.addDosageInstruction(new Dosage().setText("One tablet at once").addAdditionalInstruction(new CodeableConcept().setText(op.getTimings())).
					//	setTiming(new Timing().setRepeat(new TimingRepeatComponent().setFrequency(1).setPeriod(1).setPeriodUnit(UnitsOfTime.D))).
						setRoute(new CodeableConcept().setText(op.getInstruction())).//getAdditionalInstructions
						setMethod(new CodeableConcept(new Coding("http://snomed.info/sct", "421521009", "Swallow"))).setText(op.getPattern()));
			 //getDirectionDuration
			 
			 
			
		Boolean sectionPresent = false;	  
		 for(SectionComponent sec : section) {
			 

			  if(sec.getTitle().equalsIgnoreCase("Medications")) {		
				  if(op.getMedicine()!=null) {
				  	sec.getText().setDivAsString(op.getMedicine().toString());//getClinicalHistory
				    sec.getText().setStatusAsString("generated");
				  }
				  	sec.addEntry(new Reference().setReference("MedicationRequest/"+medicationRequest.getId()));
					sectionPresent = true;
			  }
		 }
			
		 if(!sectionPresent)
		 {
			 
			 SectionComponent section5 = new SectionComponent();
				section5.setTitle("Medications");
				section5.getText().setDivAsString(op.getMedicine().toString());//getClinicalHistory
				section5.getText().setStatusAsString("generated");
				section5.setCode(new CodeableConcept(new Coding("http://snomed.info/sct", "721912009", "Medication summary document"))).
				addEntry(new Reference().setReference("MedicationRequest/"+medicationRequest.getId()));
				
				section.add(section5);
				
		 }
		 
		
		 BundleEntryComponent bundleEntry1 = new BundleEntryComponent();
			bundleEntry1.setFullUrl("MedicationRequest/"+medicationRequest.getId());
			bundleEntry1.setResource(medicationRequest);
			  lb.add(bundleEntry1);
		  }
			
		
		
		
//		 if(op.getServiceName()!= null && op.getServiceName().equalsIgnoreCase("FollowUp"))
//		  { 
//			
//			 	Narrative nr = new Narrative();
//				nr.setDivAsString(op.getServiceName());
//				Appointment appointment = new Appointment();
//				appointment.setId("Appointment-"+new Random().nextInt(900));
//				appointment.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Appointment");
//				appointment.setStatus(AppointmentStatus.BOOKED);
//				appointment.getParticipant().add(new AppointmentParticipantComponent().setActor(new Reference().setReference(patientref)).
//				 setStatus(ParticipationStatus.ACCEPTED).setActor(new Reference().setReference("Practitioner/Practitioner-01")).setStatus(ParticipationStatus.ACCEPTED));
//			
//				appointment.setAppointmentType(new CodeableConcept(new Coding("http://snomed.info/sct", "185389009", "Follow-up visit"))).setText(nr);
//				
//				appointment.setStartElement(new InstantType(op.getFollowUpDate()));
//			
//				appointment.setEndElement(new InstantType(op.getFollowUpDate()));
//				
//				 if(op.getReferredDoctor()!=null) {
//				appointment.setDescription(op.getReferredDoctor().toString());
//				 }
				
//		Boolean sectionPresent = false;	  
//		 for(SectionComponent sec : section) {
//			  if(sec.getTitle().equalsIgnoreCase("Follow Up")) {		  
//				  
//				  	sec.addEntry(new Reference().setReference("Appointment/"+appointment.getId()));
//					sectionPresent = true;
//			  }
//		 }
			
//		 if(!sectionPresent)
//		 {
//			 SectionComponent sectionFollowup = new SectionComponent();
//			 sectionFollowup.setTitle("Follow Up");
//			 sectionFollowup.setCode(new CodeableConcept(new Coding("http://snomed.info/sct", "736271009", "Outpatient care plan"))).
//				addEntry(new Reference().setReference("Appointment/"+appointment.getId()));
//				section.add(sectionFollowup);
//		 }
//		 
//		 BundleEntryComponent bundleEntry1 = new BundleEntryComponent();
//			bundleEntry1.setFullUrl("Appointment/"+appointment.getId());
//			bundleEntry1.setResource(appointment);
//			lb.add(bundleEntry1); 
//		  
//		  }
			
		if(op.getPattern()!=null)
		{
			
			 Condition condition1 = new Condition();
			  condition1.setId("Condition-"+new Random().nextInt(900));
			  condition1.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition");
			  condition1.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString( op.getPattern());
			  condition1.setSubject(new Reference(patientref));
			  condition1.getCode().addCoding(new Coding("http://snomed.info/sct", "1", "2")).setText(op.getDosage());
					
			  BundleEntryComponent bundleEntry1 = new BundleEntryComponent();//op.getProblemDiagnosisCode(), op.getDiagnosticCertainity()
				bundleEntry1.setFullUrl("Condition/"+condition1.getId());
				bundleEntry1.setResource(condition1);
				  lb.add(bundleEntry1);
				  
				  for(int i=0 ; i<lb.size();i++)
						{
						
							BundleEntryComponent bec = lb.get(i);
							if(bec.getResource().getResourceType().toString().equalsIgnoreCase("Encounter"))
							{
								Encounter encounter = (Encounter)bec.getResource();
							
								DiagnosisComponent dc =new DiagnosisComponent() ;
								dc.getCondition().setReference("Condition/"+condition1.getId());
								
								encounter.addDiagnosis(dc);
									
						
							}
						} 
				  
		}
			  

		

		return composition;
	}

}
