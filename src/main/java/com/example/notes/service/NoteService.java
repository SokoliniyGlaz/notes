package com.example.notes.service;

import com.example.notes.domain.Note;
import com.example.notes.domain.User;
import com.example.notes.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    @Autowired
    NoteRepo repository;

    public void add(Note note){
        repository.save(note);
    }
    public Iterable<Note> findAll(){
        return repository.findAll();
    }
    public void update(Long id, String title, String text, User user){
        Note note = find(id);
        if(note != null){
            note.setAuthor(user);
            note.setText(text);
            note.setTitle(title);
            note.getChanges().add(note.getFormattedDate(note.getUpdateOn()));
            repository.save(note);

        }
    }
    public void delete(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }
    public Note find(Long id){
        return repository.findById(id).orElse(null);
    }
}
