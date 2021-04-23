package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Certificate {
    @Id
    public String CertID;

    @Indexed
    public String CertType;
    public String CertResult;
    public String Facility;
}
