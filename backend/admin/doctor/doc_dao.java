package com.example.HAD.admin.doctor;

import com.example.HAD.login.bean.LoginBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component

public interface doc_dao extends JpaRepository<docbean,Integer> {
    //void deleteByLoginBean(LoginBean bean);

    //docbean findByLoginBean(LoginBean bean);

//   LoginBean findById(String requestid);
//



@Component
    public class DoctorHospitalIdGenerator {
        private static int sequence = 0;

        public String generateDoctorHospitalId(String doctorName, String specialty) {
            // Get the current date in the format of YYMMDD

            // Increment the sequence number and format it with leading zeroes
            sequence++;
            String sequenceNumber = String.format("%03d", sequence);

            // Combine the doctor's initials, specialty, date, and sequence number
            String initialsname = getInitials(doctorName);
            String initialsspec = getInitials(specialty);
            String hospitalIdgen = initialsname + "-" + initialsspec + "-" + sequenceNumber;

            return hospitalIdgen;
        }

        private String getInitials(String name) {
            String[] parts = name.split(" ");
            StringBuilder initialsBuilder = new StringBuilder();

            for (int i = 0; i < parts.length && i < 4; i++) {
                if (parts[i].length() >= 1) {
                    initialsBuilder.append(parts[i].charAt(0));
                }
                if (parts[i].length() >= 2) {
                    initialsBuilder.append(parts[i].charAt(1));
                }
                if (parts[i].length() >= 3) {
                    initialsBuilder.append(parts[i].charAt(2));
                }
            }
            System.out.println(initialsBuilder);
            return initialsBuilder.toString().toUpperCase();
        }
    }
}
