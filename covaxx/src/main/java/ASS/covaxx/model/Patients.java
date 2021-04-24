package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Patients {

    @Id
    public String patientId;

    @Indexed
    public String patientFname;
    public String patientLname;



}
