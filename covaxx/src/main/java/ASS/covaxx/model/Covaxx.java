package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Covaxx {

    @Id
    public String patientId;
    public String DDMY;
    public String TimeHM;
    public String patientName;
    public String CertType;
    public String CertResult;
    public String Facility;

}
