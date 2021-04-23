package ASS.covaxx.repo;

import ASS.covaxx.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DocumentRepo {
    @Autowired
    private MongoTemplate mongo;

    public void save(Document document) {
        mongo.save(document);
    }

    public Document getById(String DocID) {
        return mongo.findById(DocID, Document.class);

    }

    public Collection<Document> getAll() {
        return mongo.findAll(Document.class);

    }
}
