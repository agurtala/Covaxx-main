package ASS.covaxx.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
public class Files {

    @Id
    public ObjectId CertID;

    public Map<String, String> Certificates = new HashMap<>();

    public String DocumentsID;
    public String PatID;


}
