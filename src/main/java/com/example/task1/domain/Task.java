package com.example.task1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generare id
    private Long id;

    private String title;
    private String description;

    @Column(name = "remaining_effort")
    private int remainingEffort;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) //coloana legatura
    @JsonIgnore
    private Owner owner;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public Task() {}

    public Task(Long id, String title, String description, Owner owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRemainingEffort() {
        return remainingEffort;
    }

    public void setRemainingEffort(int remainingEffort) {
        this.remainingEffort = remainingEffort;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


}
