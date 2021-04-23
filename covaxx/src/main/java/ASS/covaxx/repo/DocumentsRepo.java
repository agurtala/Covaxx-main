package ASS.covaxx.repo;

import ASS.covaxx.model.Documents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DocumentsRepo {
    @Autowired
    private MongoTemplate mongo;

    public void save(Documents documents) {
        mongo.save(documents);
    }

    public Documents getById(String DocID) {
        return mongo.findById(DocID, Documents.class);

    }

    public Collection<Documents> getAll() {
        return mongo.findAll(Documents.class);

    }
}
