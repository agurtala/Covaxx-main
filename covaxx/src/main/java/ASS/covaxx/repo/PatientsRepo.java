package ASS.covaxx.repo;

import ASS.covaxx.model.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class PatientsRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Patients covaxx) {

        this.mongo.save(covaxx);
    }

    public Patients getById(String patientId) {
        return this.mongo.findById(patientId, Patients.class);

    }

    public Collection<Patients> getAll() {
        return this.mongo.findAll(Patients.class);

    }

    public Collection <Patients> find(String patientName, String CertType){

        Query query = new Query();

        if (patientName != null)
            query.addCriteria(Criteria.where("patientName").is(patientName));

        return this.mongo.find(query, Patients.class);
    }

}
