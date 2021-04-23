package ASS.covaxx.controller;

import ASS.covaxx.model.Certificate;
import ASS.covaxx.repo.CertificateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

public class CertificateController {

    @Autowired
    private CertificateRepo CertificateRepo;

    @GetMapping("/certificate")
    public @ResponseBody
    Collection<Certificate> getAll(
            @RequestParam(required = false) String CertID,
            @RequestParam(required = false) String CertType

    ) {
        return this.CertificateRepo.find(CertID, CertType);
    }

    @GetMapping("/certificate/{CertID}")
    public @ResponseBody
    Certificate getOne(
            @PathVariable String CertID) {

        Certificate covaxx = this.CertificateRepo.getById(CertID);

        if (covaxx == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx certificate with this ID");

        return covaxx;
    }

    @PostMapping("/certificate")
    public @ResponseBody
    Certificate createNew(@RequestBody Certificate covaxx) {

        if (covaxx.CertID == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Certificate must specify an ID");

        if (covaxx.CertType == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Type must be specify");

        if (covaxx.CertResult == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Result must be specify");

        if (covaxx.Facility == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Facility must specify a name");

        Certificate existingCovaxx = this.CertificateRepo.getById(covaxx.CertID);

        if (existingCovaxx != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Covaxx patient ID is already used");
        }
        this.CertificateRepo.save(covaxx);

        return covaxx;
    }

    @PatchMapping("/patients/{patientId}")
    public @ResponseBody
    Certificate updateExisting(@PathVariable String patientId, @RequestBody Certificate changes) {

        Certificate existingCovaxx = this.CertificateRepo.getById(patientId);

        if (existingCovaxx == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Covaxx profile does not exist");
        }

        if (changes.CertType != null)
            existingCovaxx.CertType = changes.CertType;

        if (changes.CertResult != null)
            existingCovaxx.CertResult = changes.CertResult;

        if (changes.Facility != null)
            existingCovaxx.Facility = changes.Facility;

        this.CertificateRepo.save(existingCovaxx);

        return existingCovaxx;


    }
}


