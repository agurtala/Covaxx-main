package ASS.covaxx.controller;

import ASS.covaxx.model.Documents;
import ASS.covaxx.repo.DocumentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

public class DocumentsController {

    @Autowired
    private DocumentsRepo DocumentsRepo;

    @GetMapping("/documents")
    public @ResponseBody
    Collection<Documents> getAll() {

        return this.DocumentsRepo.getAll();
    }

    @GetMapping("/documents/{DocID}")
    public @ResponseBody
    Documents getOne(
            @PathVariable String DocID) {

        Documents covaxx = this.DocumentsRepo.getById(DocID);

        if (covaxx == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx certificate with this ID");

        return covaxx;
    }

    @PostMapping("/documents")
    public @ResponseBody
    Documents createNew(@RequestBody Documents covaxx) {

        if (covaxx.DocID == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Certificate must specify an ID");

        if (covaxx.DocType == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Type must be specify");

        if (covaxx.DocResult == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Result must be specify");

        if (covaxx.Facility == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Facility must specify a name");

        Documents existingCovaxx = this.DocumentsRepo.getById(covaxx.DocID);

        if (existingCovaxx != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Covaxx patient ID is already used");
        }
        this.DocumentsRepo.save(covaxx);

        return covaxx;
    }

    @PatchMapping("/document/{DocID}")
    public @ResponseBody
    Documents updateExisting(@PathVariable String DocID, @RequestBody Documents changes) {

        Documents existingCovaxx = this.DocumentsRepo.getById(DocID);

        if (existingCovaxx == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Covaxx profile does not exist");
        }

        if (changes.DocType != null)
            existingCovaxx.DocType = changes.DocType;

        if (changes.DocResult != null)
            existingCovaxx.DocResult = changes.DocResult;

        if (changes.Facility != null)
            existingCovaxx.Facility = changes.Facility;

        this.DocumentsRepo.save(existingCovaxx);

        return existingCovaxx;


    }
}


