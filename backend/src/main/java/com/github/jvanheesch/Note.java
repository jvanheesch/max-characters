package com.github.jvanheesch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE_GEN")
    @SequenceGenerator(name = "NOTE_GEN", sequenceName = "NOTE_SEQ", allocationSize = 1)
    private Long id;
    private String textInBytes;
    private String textInChars;

    public Long getId() {
        return id;
    }

    public String getTextInBytes() {
        return textInBytes;
    }

    public void setTextInBytes(String textInBytes) {
        this.textInBytes = textInBytes;
    }

    public String getTextInChars() {
        return textInChars;
    }

    public void setTextInChars(String textInChars) {
        this.textInChars = textInChars;
    }
}
