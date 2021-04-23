package ASS.covaxx.repo;

import ASS.covaxx.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CertificateRepo {
    @Autowired
    private MongoTemplate mongo;

    public void save(Certificate certificate) {

        this.mongo.save(certificate);
    }

    public Certificate getById(String CertID) {
        return this.mongo.findById(CertID, Certificate.class);

    }

    public Collection<Certificate> getAll() {
        return this.mongo.findAll(Certificate.class);

    }

    public Collection <Certificate> find(String CertID, String CertType){

        Query query = new Query();

        if (CertType != null)
            query.addCriteria(Criteria.where("CertType").is(CertType));

        return this.mongo.find(query, Certificate.class);
    }
}
