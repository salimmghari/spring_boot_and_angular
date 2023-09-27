package com.software.spring_boot_and_angular.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import com.software.spring_boot_and_angular.dao.Note;
import com.software.spring_boot_and_angular.services.NoteService;


@CrossOrigin(origins="http://localhost:4200", allowedHeaders={"Authorization", "Origin", "Content-Type", "Accept"})
@RestController
public class NoteController {
    @Autowired
    private NoteService service;

    @GetMapping(value="/api/notes", consumes="application/json", produces="application/json")
    ResponseEntity<List<Note>> read(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        List<Note> notes = service.read(token);
        if (notes != null) {
            return ResponseEntity.status(HttpStatus.OK).body(notes);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(value="/api/notes", consumes="application/json", produces="application/json")
    ResponseEntity<Note> create(HttpServletRequest request, @RequestBody Note note) {
        String token = request.getHeader("Authorization");
        Note newNote = service.create(note, token);
        if (newNote != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newNote);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }   

    @PutMapping(value="/api/notes/{id}", consumes="application/json", produces="application/json")
    ResponseEntity<Optional<Note>> update(HttpServletRequest request, @RequestBody Note note, @PathVariable Long id) {
        String token = request.getHeader("Authorization");
        Optional<Note> newNote = service.update(note, id, token);
        if (newNote != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newNote);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping(value="/api/notes/{id}", consumes="application/json", produces="application/json")
    ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable Long id) {
        String token = request.getHeader("Authorization");
        if (service.delete(id, token)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value="/api/notes/{id}", consumes="application/json", produces="application/json")
    ResponseEntity<Optional<Note>> readOne(HttpServletRequest request, @PathVariable Long id) {
        String token = request.getHeader("Authorization");
        Optional<Note> note = service.readOne(id, token);
        if (note != null) {
            return ResponseEntity.status(HttpStatus.OK).body(note);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
