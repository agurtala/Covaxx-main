package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Patient {

    @Id
    public String patientId;
    public String DDMY;
    public String TimeHM;

    @Indexed
    public String patientName;


}
