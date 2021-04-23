package ASS.covaxx.repo;

import ASS.covaxx.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class PatientRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Patient covaxx) {

        this.mongo.save(covaxx);
    }

    public Patient getById(String patientId) {
        return this.mongo.findById(patientId, Patient.class);

    }

    public Collection<Patient> getAll() {
        return this.mongo.findAll(Patient.class);

    }

    public Collection <Patient> find(String patientName, String CertType){

        Query query = new Query();

        if (patientName != null)
            query.addCriteria(Criteria.where("patientName").is(patientName));

        return this.mongo.find(query, Patient.class);
    }

}
