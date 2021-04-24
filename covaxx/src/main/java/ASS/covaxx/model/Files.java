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
    public ObjectId certID;

    public Map<String, String> certificate = new HashMap<>();

    public String certificateDate;
    public String certificateTime;

    public String DocID;
    public String patientId;


}
