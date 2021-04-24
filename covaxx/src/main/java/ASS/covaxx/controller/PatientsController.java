package ASS.covaxx.controller;

import ASS.covaxx.model.Patients;
import ASS.covaxx.repo.PatientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class PatientsController {

    @Autowired
    private PatientsRepo PatientsRepo;

    @GetMapping("/patients")
    public @ResponseBody Collection<Patients> getAll(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String CertType

    ){
        return this.PatientsRepo.find(patientName, CertType);
    }

    @GetMapping("/patients/{patientId}")
   public @ResponseBody
    Patients getOne(
           @PathVariable String patientId)
    {

        Patients covaxx =  this.PatientsRepo.getById(patientId);

        if (covaxx == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx patient with this ID");

        return covaxx;
   }

   @PostMapping("/patients")
   public @ResponseBody
   Patients createNew(@RequestBody Patients covaxx) {

        if (covaxx.patientId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify an ID");

       if (covaxx.DDMY == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specify");

       if (covaxx.TimeHM == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specify");

       if (covaxx.patientName == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify an name");

       Patients existingCovaxx = this.PatientsRepo.getById(covaxx.patientId);

       if (existingCovaxx != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Covaxx patient ID is already used");
       }
        this.PatientsRepo.save(covaxx);

        return covaxx;
   }

   @PatchMapping("/patients/{patientId}")
   public @ResponseBody
   Patients updateExisting(@PathVariable String patientId, @RequestBody Patients changes) {

        Patients existingCovaxx = this.PatientsRepo.getById(patientId);

        if (existingCovaxx == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Covaxx profile does not exist");
        }

        if (changes.patientName != null)
            existingCovaxx.patientName = changes.patientName;

       if (changes.DDMY != null)
           existingCovaxx.DDMY = changes.DDMY;

       if (changes.TimeHM != null)
           existingCovaxx.TimeHM = changes.TimeHM;

        this.PatientsRepo.save(existingCovaxx);

        return existingCovaxx;


   }

}
