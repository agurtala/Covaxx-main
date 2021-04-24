package ASS.covaxx.repo;

import ASS.covaxx.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.security.cert.Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class FilesRepo {

    @Autowired
    public MongoTemplate mongo;

    public void save(Files files) {
        this.mongo.save(files);
    }

    public Certificate getById(String CertID) {
        return mongo.findById(CertID, Certificate.class);
    }

    public Collection<Files> find(String patientId, String DocID) {

        Query query = new Query();

        if (patientId != null)
            query.addCriteria(Criteria.where("patientId").is(patientId));

        if (DocID != null)
            query.addCriteria(Criteria.where("DocID").is(DocID));

        return this.mongo.find(query, Files.class);
    }


}
