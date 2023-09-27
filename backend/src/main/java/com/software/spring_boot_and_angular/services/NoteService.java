package com.software.spring_boot_and_angular.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.software.spring_boot_and_angular.security.JwtTokenProvider;
import com.software.spring_boot_and_angular.dao.NoteRepository;
import com.software.spring_boot_and_angular.dao.UserRepository;
import com.software.spring_boot_and_angular.dao.Note;
import com.software.spring_boot_and_angular.dao.User;


@Service
public class NoteService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private NoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<Note> read(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            return repository.findByUserUsername(username);
        }
        return null;
    }

    public Note create(Note note, String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            User user = userRepository.findByUsername(username);
            note.setUser(user);
            return repository.save(note);
        }
        return null;        
    }

    public Optional<Note> update(Note note, Long id, String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            Note noteFromDatabase = repository.findById(id)
                .get();
            if (noteFromDatabase.getUser().getUsername().equals(username)) {
                noteFromDatabase.setTitle(note.getTitle());
                noteFromDatabase.setBody(note.getBody());
                return Optional.of(repository.save(noteFromDatabase));
            }
        }
        return null;        
    }

    public boolean delete(Long id, String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            Optional<Note> note = repository.findById(id);
            if (note.get().getUser().getUsername().equals(username)) {
                repository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public Optional<Note> readOne(Long id, String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsernameFromToken(token);
            Optional<Note> note = repository.findById(id);
            if (note.get().getUser().getUsername().equals(username)) return note;
        }
        return null;
    }
}
