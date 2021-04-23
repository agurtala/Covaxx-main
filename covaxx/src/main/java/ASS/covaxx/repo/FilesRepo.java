package ASS.covaxx.repo;

import ASS.covaxx.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.security.cert.Certificate;
import java.util.List;

@Repository
public class FilesRepo {

    @Autowired
    public MongoTemplate mongo;

    public void save(Files files){
        this.mongo.save(files);
    }

    public Certificate getById(String CertID){
        return mongo.findById(CertID, Certificate.class);
    }

    public List<Files> find(String DocumentsID, String PatID) {

        Query query = new Query();

        if (DocumentsID != null)
            query.addCriteria(Criteria.where("DocumentID").is(DocumentsID));

        if (PatID != null)
            query.addCriteria(Criteria.where("PatID").is(PatID));

        return this.mongo.find(query, Files.class);
    }


}
