package com.example.HAD.patientqueue.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.example.HAD.patientqueue.bean.DoctorId;
import com.example.HAD.patientqueue.bean.PatientListResponse;
import com.example.HAD.patientqueue.bean.PatientRequestBean;
import com.example.HAD.patientqueue.service.PatientQueueService;

@Service
public class PatientQueueServiceImpl implements PatientQueueService{

	 Map<String, Queue<PatientRequestBean>> map = new HashMap<>();
	@Override
	public String addPatient(PatientRequestBean bean) {
		 Queue<PatientRequestBean> que= new LinkedList<PatientRequestBean>();
		 bean.setStatus("Active");
		 if(map.get((bean.getDoctorId()))!=null)
		 {
		 que.addAll(map.get((bean.getDoctorId())));
		 }
		 
		 que.add(bean);
		map.put((bean.getDoctorId()), que);
		return "Success";
	}

	@Override
	public String deletePatient(PatientRequestBean bean) {
		
		Queue<PatientRequestBean> que= new LinkedList<PatientRequestBean>();
		 if(map.get((bean.getDoctorId()))!=null)
		 {
			
		 que.addAll(map.get((bean.getDoctorId())));
		 
		 PatientListResponse patientListResponse=new PatientListResponse();
		 List<PatientRequestBean> ptnList=new ArrayList<>();
		 Iterator iterator = que.iterator();
		  
	        while (iterator.hasNext()) {
	        	
	        	PatientRequestBean data= 	(PatientRequestBean) iterator.next();
	        	if(data.getPatientId().equalsIgnoreCase(bean.getPatientId()))
	        	{
	        		data.setStatus("Inactive");
	        	}
	        	ptnList.add(data);
	        }
	        Queue<PatientRequestBean>  que1=new  LinkedList<PatientRequestBean>();
	        que1.addAll(ptnList);
	        map.put((bean.getDoctorId()), que1);
		// que.remove();
	
			return "Deleted";
		 }
	
		// TODO Auto-generated method stub
		return "No records to delete";
	}

	@Override
	public PatientListResponse getPatient(DoctorId id) {
		Queue<PatientRequestBean> que= new LinkedList<PatientRequestBean>();
		 if(map.get((id.getDoctorId()))!=null)
		 {
		 que.addAll(map.get((id.getDoctorId())));
		 PatientListResponse patientListResponse=new PatientListResponse();
		 List<PatientRequestBean> ptnList=new ArrayList<>();
		 Iterator iterator = que.iterator();
		  
	        while (iterator.hasNext()) {
	        	ptnList.add((PatientRequestBean) iterator.next());
	        }
		 patientListResponse.setPatientList(ptnList);
		 
		 

		return patientListResponse;
	}
		 return null;

}
}
