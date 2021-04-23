package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Documents {
    @Id
    public String DocID;

    @Indexed
    public String DocType;
    public String DocResult;
    public String Facility;
}
