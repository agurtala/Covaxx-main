package ASS.covaxx.controller;

import ASS.covaxx.model.Patient;
import ASS.covaxx.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class PatientController {

    @Autowired
    private PatientRepo PatientRepo;

    @GetMapping("/patients")
    public @ResponseBody Collection<Patient> getAll(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String CertType

    ){
        return this.PatientRepo.find(patientName, CertType);
    }

    @GetMapping("/patients/{patientId}")
   public @ResponseBody
    Patient getOne(
           @PathVariable String patientId)
    {

        Patient covaxx =  this.PatientRepo.getById(patientId);

        if (covaxx == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx patient with this ID");

        return covaxx;
   }

   @PostMapping("/patients")
   public @ResponseBody
   Patient createNew(@RequestBody Patient covaxx) {

        if (covaxx.patientId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify an ID");

       if (covaxx.DDMY == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specify");

       if (covaxx.TimeHM == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specify");

       if (covaxx.patientName == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify an name");

       Patient existingCovaxx = this.PatientRepo.getById(covaxx.patientId);

       if (existingCovaxx != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Covaxx patient ID is already used");
       }
        this.PatientRepo.save(covaxx);

        return covaxx;
   }

   @PatchMapping("/patients/{patientId}")
   public @ResponseBody
   Patient updateExisting(@PathVariable String patientId, @RequestBody Patient changes) {

        Patient existingCovaxx = this.PatientRepo.getById(patientId);

        if (existingCovaxx == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Covaxx profile does not exist");
        }

        if (changes.patientName != null)
            existingCovaxx.patientName = changes.patientName;

       if (changes.DDMY != null)
           existingCovaxx.DDMY = changes.DDMY;

       if (changes.TimeHM != null)
           existingCovaxx.TimeHM = changes.TimeHM;

        this.PatientRepo.save(existingCovaxx);

        return existingCovaxx;


   }

}
