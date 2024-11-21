package com.software.spring_boot_and_angular.dao;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import com.software.spring_boot_and_angular.dao.User;


@Entity
public class Note {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String title;
    private String body;
    private @ManyToOne @JoinColumn(name = "user_id") User user;

    Note() {}

    Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (!(object instanceof Note)) return false;
        
        Note note = (Note) object;

        return (
            Objects.equals(this.id, note.id) 
            && Objects.equals(this.title, note.title)
            && Objects.equals(this.body, note.body)
            && Objects.equals(this.user, note.user)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.body, this.user);
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + this.id + ", title='" + this.title + "\'}";
    }
}
