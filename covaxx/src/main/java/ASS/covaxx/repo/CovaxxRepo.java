package ASS.covaxx.repo;

import ASS.covaxx.model.Covaxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class CovaxxRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Covaxx covaxx) {

        this.mongo.save(covaxx);
    }

    public Covaxx getById(String patientId) {
        return this.mongo.findById(patientId, Covaxx.class);

    }

    public Collection<Covaxx> getAll() {
        return this.mongo.findAll(Covaxx.class);

    }
    

}
