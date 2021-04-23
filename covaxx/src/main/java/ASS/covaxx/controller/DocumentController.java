package ASS.covaxx.controller;

import ASS.covaxx.model.Document;
import ASS.covaxx.repo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

public class DocumentController {

    @Autowired
    private DocumentRepo DocumentRepo;

    @GetMapping("/certificate")
    public @ResponseBody
    Collection<Document> getAll() {

        return this.DocumentRepo.getAll();
    }

    @GetMapping("/certificate/{CertID}")
    public @ResponseBody
    Document getOne(
            @PathVariable String CertID) {

        Document covaxx = this.DocumentRepo.getById(CertID);

        if (covaxx == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx certificate with this ID");

        return covaxx;
    }

    @PostMapping("/certificate")
    public @ResponseBody
    Document createNew(@RequestBody Document covaxx) {

        if (covaxx.DocID == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Certificate must specify an ID");

        if (covaxx.DocType == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Type must be specify");

        if (covaxx.DocResult == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Result must be specify");

        if (covaxx.Facility == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Facility must specify a name");

        Document existingCovaxx = this.DocumentRepo.getById(covaxx.DocID);

        if (existingCovaxx != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Covaxx patient ID is already used");
        }
        this.DocumentRepo.save(covaxx);

        return covaxx;
    }

    @PatchMapping("/patients/{patientId}")
    public @ResponseBody
    Document updateExisting(@PathVariable String patientId, @RequestBody Document changes) {

        Document existingCovaxx = this.DocumentRepo.getById(patientId);

        if (existingCovaxx == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Covaxx profile does not exist");
        }

        if (changes.DocType != null)
            existingCovaxx.DocType = changes.DocType;

        if (changes.DocResult != null)
            existingCovaxx.DocResult = changes.DocResult;

        if (changes.Facility != null)
            existingCovaxx.Facility = changes.Facility;

        this.DocumentRepo.save(existingCovaxx);

        return existingCovaxx;


    }
}


