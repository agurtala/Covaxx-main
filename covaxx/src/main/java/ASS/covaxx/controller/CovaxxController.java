package ASS.covaxx.controller;

import ASS.covaxx.model.Covaxx;
import ASS.covaxx.repo.CovaxxRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class CovaxxController {

    @Autowired
    private CovaxxRepo CovaxxRepo;

    @GetMapping("/patients")
    public @ResponseBody Collection<Covaxx> getAll(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String CertType

    ){
        return this.CovaxxRepo.find(patientName, CertType);
    }

    @GetMapping("/patients/{patientId}")
   public @ResponseBody Covaxx getOne(
           @PathVariable String patientId)
    {

        Covaxx covaxx =  this.CovaxxRepo.getById(patientId);

        if (covaxx == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx patient with this ID");

        return covaxx;
   }

   @PostMapping("/patients")
   public @ResponseBody Covaxx createNew(@RequestBody Covaxx covaxx) {

        if (covaxx.patientId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify an ID");

       if (covaxx.DDMY == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specify");

       if (covaxx.TimeHM == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date must be specify");

       if (covaxx.patientName == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify an name");

       if (covaxx.CertType == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify type of certificate");

       if (covaxx.CertResult == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Patient must specify given result");

       if (covaxx.Facility == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Facility must specify a medical centre name");

       Covaxx existingCovaxx = this.CovaxxRepo.getById(covaxx.patientId);

       if (existingCovaxx != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Covaxx patient ID is already used");
       }
        this.CovaxxRepo.save(covaxx);

        return covaxx;
   }

   @PatchMapping("/patients/{patientId}")
   public @ResponseBody Covaxx updateExisting(@PathVariable String patientId, @RequestBody Covaxx changes) {

        Covaxx existingCovaxx = this.CovaxxRepo.getById(patientId);

        if (existingCovaxx == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Covaxx profile does not exist");
        }

        if (changes.patientName != null)
            existingCovaxx.patientName = changes.patientName;

       if (changes.CertType != null)
           existingCovaxx.CertType = changes.CertType;

       if (changes.CertResult != null)
           existingCovaxx.CertResult = changes.CertResult;

       if (changes.Facility != null)
           existingCovaxx.Facility = changes.Facility;

       if (changes.DDMY != null)
           existingCovaxx.DDMY = changes.DDMY;

       if (changes.TimeHM != null)
           existingCovaxx.TimeHM = changes.TimeHM;

        this.CovaxxRepo.save(existingCovaxx);

        return existingCovaxx;


   }

}
