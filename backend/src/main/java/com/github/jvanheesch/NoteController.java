package com.github.jvanheesch;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/notes")
@RestController
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository NoteRepository) {
        this.noteRepository = NoteRepository;
    }

    @PostMapping
    public void create(@RequestBody Note note) {
        noteRepository.save(note);
    }
}
