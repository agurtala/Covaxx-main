package ASS.covaxx.controller;

import ASS.covaxx.model.Documents;
import ASS.covaxx.model.Files;
import ASS.covaxx.repo.DocumentsRepo;
import ASS.covaxx.repo.FilesRepo;
import ASS.covaxx.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class FilesController {

    @Autowired
    private FilesRepo Files;

    @Autowired
    private DocumentsRepo Documents;

    @Autowired
    private PatientRepo Patient;

    @GetMapping("/patient/{PatID}/Files")
    private @ResponseBody
    List<ASS.covaxx.model.Files> getFiles(
            @PathVariable String DocumentsID
    ){

        Documents document = this.Documents.getById(DocumentsID);

        if (document == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx certificate with this ID");

        return Files.find(DocumentsID,null);

    }

    @PostMapping("/patient/{PatID}/Files")
    private @ResponseBody
    Files createfile(
            @PathVariable String DocumentsID,
            @RequestBody Files files

    ){
        if (files.DocumentsID != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New file should not specify any ID");

        files.DocumentsID = DocumentsID;

        vaildate(files);

        this.Files.save(files);
        return files;
    }

    private void vaildate(Files files){

        if (files.DocumentsID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no Document specified");
        }

        if (Files.getById(files.DocumentsID) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " no Patient ID specified");
        }
        if (files.PatID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no Document specified");
        }

        if (Files.getById(files.PatID) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " no Patient ID specified");
        }
        }
    }

