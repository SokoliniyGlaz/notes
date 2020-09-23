package com.example.notes.controller;

import com.example.notes.domain.Note;
import com.example.notes.domain.User;
import com.example.notes.repository.UserRepo;
import com.example.notes.service.NoteService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MainController {
    @Autowired
    NoteService service;

    @Autowired
    UserRepo userRepo;

 
    @GetMapping
    public String greeting(){
        return "greeting";
    }


    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("notes",service.findAll());
        return "main";
    }
    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String title, @RequestParam String text, Model model){
        service.add(new Note(title,text,user));
        model.addAttribute("notes",service.findAll());
        return "main";
    }
    @GetMapping("/main/{noteId}/edit")
    public String edit(@PathVariable Long noteId,Model model){
        Note note = service.find(noteId);
        if(note==null){
            throw new NullPointerException("Such note doesn't exist");
        }
        model.addAttribute("note",note);
        model.addAttribute("notes",service.findAll());
        return "main";
    }
    @PostMapping("/main/{noteId}/edit")
    public String update(@AuthenticationPrincipal User user,@PathVariable Long noteId,@RequestParam String title, @RequestParam String text){
        service.update(noteId,title,text,user);
        return "redirect:/main";
    }
    @GetMapping("/main/{noteId}/delete")
    public String delete(@PathVariable Long noteId,Model model){
        service.delete(noteId);
        model.addAttribute("notes",service.findAll());
        return "redirect:/main";
    }

    @GetMapping("/main/{noteId}/export")
    public String export(@PathVariable Long noteId) throws IOException {
        Note note = service.find(noteId);
        if(note!= null){
            File jsonDir = new File("C:/Users/jana_/IdeaProjects/notes/json_files");
            if (!jsonDir.exists()) {
                jsonDir.mkdir();
            }
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter objWriter = mapper.writer(new DefaultPrettyPrinter());
            objWriter.writeValue(new File(jsonDir + "/" + note.getTitle() + ".json"),note);
        }
        return "redirect:/main";
    }
   @PostMapping("/import")
    public String importNote(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file,
                             Model model) throws IOException {
        if(file!=null && !file.getOriginalFilename().isEmpty()) {
            File jsonDir = new File("C:/Users/jana_/IdeaProjects/notes/json_files");
            if (!jsonDir.exists()) {
                jsonDir.mkdir();
            }
            ObjectMapper mapper = new ObjectMapper();
            //filePath = name of file, not absolute path
            String originalFilename = file.getOriginalFilename();
            File copy = new File(jsonDir +"/" + originalFilename);
            file.transferTo(copy);
            Note note = mapper.readValue(copy, Note.class);
            note.setAuthor(user);
            service.add(note);
        }
        model.addAttribute("notes",service.findAll());
        return "main";
   }


}

