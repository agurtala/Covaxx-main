package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Patients {

    @Id
    public String patientId;

    public String DDMY;
    public String TimeHM;

    @Indexed
    public String patientName;

    @Indexed
    public String CertType;

    public String CertResult;
    public String Facility;

}
