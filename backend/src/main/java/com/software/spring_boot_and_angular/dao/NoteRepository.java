package com.software.spring_boot_and_angular.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.software.spring_boot_and_angular.dao.Note;


public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserUsername(String username);
}
