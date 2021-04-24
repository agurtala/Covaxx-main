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

        Documents documents = this.DocumentsRepo.getById(DocID);

        if (documents == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no documents certificate with this DocID");

        return documents;
    }

    @PostMapping("/documents")
    public @ResponseBody
    Documents createNew(@RequestBody Documents documents) {

        if (documents.DocID == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Covaxx Certificate must specify an ID");

        if (documents.DocType == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Type must be specify");

        if (documents.DocResult == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate Result must be specify");

        if (documents.Facility == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Facility must specify a name");

        Documents existingCovaxx = this.DocumentsRepo.getById(documents.DocID);

        if (existingCovaxx != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Covaxx patient ID is already used");
        }
        this.DocumentsRepo.save(documents);

        return documents;
    }

    @PatchMapping("/document/{DocID}")
    public @ResponseBody
    Documents updateExisting(@PathVariable String DocID, @RequestBody Documents changes) {

        Documents existingDocuments = this.DocumentsRepo.getById(DocID);

        if (existingDocuments == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This Covaxx profile does not exist");
        }

        if (changes.DocType != null)
            existingDocuments.DocType = changes.DocType;

        if (changes.DocResult != null)
            existingDocuments.DocResult = changes.DocResult;

        if (changes.Facility != null)
            existingDocuments.Facility = changes.Facility;

        this.DocumentsRepo.save(existingDocuments);

        return existingDocuments;


    }
}


