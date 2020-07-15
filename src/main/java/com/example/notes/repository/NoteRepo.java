package com.example.notes.repository;

import com.example.notes.domain.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepo extends CrudRepository<Note,Long> {
}
