package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@org.springframework.data.mongodb.core.mapping.Document
public class Document {
    @Id
    public String DocID;

    @Indexed
    public String DocType;
    public String DocResult;
    public String Facility;
}
