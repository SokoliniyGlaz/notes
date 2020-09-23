package com.example.notes.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@Entity
public class Note {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;
    @JsonIgnore
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createOn;
    @JsonIgnore
    @LastModifiedDate
    @Column(nullable = false)
    private  LocalDateTime updateOn;

    public String getFormattedDate(LocalDateTime time){
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
     @JsonIgnore
    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public Note(String title,String text,User user){
        this.title = title;
        this.text = text;
        author = user;
    }

}
