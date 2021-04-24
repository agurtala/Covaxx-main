package ASS.covaxx.controller;

import ASS.covaxx.model.Documents;
import ASS.covaxx.model.Files;
import ASS.covaxx.model.Patients;
import ASS.covaxx.repo.DocumentsRepo;
import ASS.covaxx.repo.FilesRepo;
import ASS.covaxx.repo.PatientsRepo;
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
    private PatientsRepo Patients;

    @GetMapping("/patients/{patientId}/Files")
    private @ResponseBody
    List<ASS.covaxx.model.Files> getFiles(
            @PathVariable String patientId
    ){

        Patients patients = this.Patients.getById(patientId);

        if (patients == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no covaxx certificate with this ID");

        return Files.find(patientId, null);

    }
    @GetMapping("/Files/{CertID}")
    public @ResponseBody
    Files getOne(
            @PathVariable String CertID)
    {

        Files files = this.Files.getById(CertID);

        if (files == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no file with this CertID");

        return files;
    }

    @PatchMapping("/patients/{patientId}")
    public @ResponseBody
    Files updateExisting(@PathVariable String sessionId, @RequestBody Files changes) {

        Files existingFiles = this.Files.getById(sessionId);

        if (existingFiles == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This CertID does not exist");
        }

        if (changes.certificate != null) {
            existingFiles.certificate = changes.certificate;
        }

        if (changes.certificateDate != null) {
            existingFiles.certificateDate = changes.certificateDate;
        }

        if (changes.certificateTime != null) {
            existingFiles.certificateTime = changes.certificateTime;
        }
        if (changes.DocID != null) {
            existingFiles.DocID = changes.DocID;
        }
        if (changes.patientId != null) {
            existingFiles.patientId = changes.patientId;
        }

        this.Files.save(existingFiles);

        return existingFiles;
    }

    @PostMapping("/patients/{patientId}/Files")
    private @ResponseBody
    Files createFiles(
            @PathVariable String patientId,
            @RequestBody Files files

    ){
        if (files.CertID != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New file should not specify any ID");

        files.patientId = patientId;

        vaildate(files);

        this.Files.save(files);
        return files;
    }

    private void vaildate(Files files){

        if (files.patientId == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no patient ID specified");
        }

        if (Files.getById(files.patientId) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " There is no patient with this ID ");
        }
        if (files.DocID == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no Document specified");
        }

        if (Files.getById(files.DocID) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " There no Document with this ID");
        }
        }
    }

