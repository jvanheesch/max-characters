package com.github.jvanheesch;

import oracle.jdbc.OracleDatabaseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.junit.jupiter.Container;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class ApplicationTest {
    private static final String ONE_BYTE_CHARACTER = "a";
    private static final String TWO_BYTE_CHARACTER = "é";
    private static final String THREE_BYTE_CHARACTER = "♥";
    private static final String FOUR_BYTE_CHARACTER = "🐶";

    @Autowired
    private NoteRepository noteRepository;

    @Container
    private static final GenericContainer<?> ORACLE_CONTAINER = new GenericContainer<>("docker.io/library/max-characters-oracle:latest")
            .withExposedPorts(1521)
            .waitingFor(new LogMessageWaitStrategy()
                    .withRegEx("SQL> Disconnected from Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production\n"));

    @DynamicPropertySource
    static void fixProperties(DynamicPropertyRegistry registry) {
        ORACLE_CONTAINER.start();
        registry.add("spring.datasource.url", () -> String.format("jdbc:oracle:thin:@localhost:%d:XE", ORACLE_CONTAINER.getMappedPort(1521)));
    }

    @Test
    void test_one_byte_character() {
        assertThat(ONE_BYTE_CHARACTER.getBytes(StandardCharsets.UTF_8)).hasSize(1);
    }

    @Test
    void test_two_byte_character() {
        assertThat(TWO_BYTE_CHARACTER.getBytes(StandardCharsets.UTF_8)).hasSize(2);
    }

    @Test
    void test_three_byte_character() {
        assertThat(THREE_BYTE_CHARACTER.getBytes(StandardCharsets.UTF_8)).hasSize(3);
    }

    @Test
    void test_four_byte_character() {
        assertThat(FOUR_BYTE_CHARACTER.getBytes(StandardCharsets.UTF_8)).hasSize(4);
    }

    @Test
    void givenTextInBytesLength10AllOneByteCharacters_whenSave_thenOk() {
        Note note = new Note();
        note.setTextInBytes(ONE_BYTE_CHARACTER.repeat(10));
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInBytes())
                .isEqualTo("aaaaaaaaaa");
    }

    @Test
    void givenTextInCharsLength10AllOneByteCharacters_whenSave_thenOk() {
        Note note = new Note();
        note.setTextInChars(ONE_BYTE_CHARACTER.repeat(10));
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInChars())
                .isEqualTo("aaaaaaaaaa");
    }

    @Test
    void givenTextInBytesLength10WithTwoByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInBytes(ONE_BYTE_CHARACTER.repeat(9) + TWO_BYTE_CHARACTER);
        assertThatThrownBy(() -> noteRepository.save(note))
                .getRootCause()
                .isInstanceOf(OracleDatabaseException.class)
                .hasMessageContaining("ORA-12899: value too large for column");
    }

    @Test
    void givenTextInBytesLength9WithTwoByteCharacter_whenSave_thenOk() {
        Note note = new Note();
        note.setTextInBytes(ONE_BYTE_CHARACTER.repeat(8) + TWO_BYTE_CHARACTER);
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInBytes())
                .isEqualTo("aaaaaaaaé");
    }

    @Test
    void givenTextInCharsLength10WithTwoByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInChars(ONE_BYTE_CHARACTER.repeat(9) + TWO_BYTE_CHARACTER);
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInChars())
                .isEqualTo("aaaaaaaaaé");
    }

    @Test
    void givenTextInBytesLength10WithThreeByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInBytes(ONE_BYTE_CHARACTER.repeat(9) + THREE_BYTE_CHARACTER);
        assertThatThrownBy(() -> noteRepository.save(note))
                .getRootCause()
                .isInstanceOf(OracleDatabaseException.class)
                .hasMessageContaining("ORA-12899: value too large for column");
    }

    @Test
    void givenTextInBytesLength8WithThreeByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInBytes(ONE_BYTE_CHARACTER.repeat(7) + THREE_BYTE_CHARACTER);
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInBytes())
                .isEqualTo("aaaaaaa♥");
    }

    @Test
    void givenTextInCharsLength10WithThreeByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInChars(ONE_BYTE_CHARACTER.repeat(9) + THREE_BYTE_CHARACTER);
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInChars())
                .isEqualTo("aaaaaaaaa♥");
    }

    @Test
    void givenTextInBytesLength10WithFourByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInBytes(ONE_BYTE_CHARACTER.repeat(9) + FOUR_BYTE_CHARACTER);
        assertThatThrownBy(() -> noteRepository.save(note))
                .getRootCause()
                .isInstanceOf(OracleDatabaseException.class)
                .hasMessageContaining("ORA-12899: value too large for column");
    }

    @Test
    void givenTextInBytesLength7WithFourByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInBytes(ONE_BYTE_CHARACTER.repeat(6) + FOUR_BYTE_CHARACTER);
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInBytes())
                .isEqualTo("aaaaaa🐶");
    }

    @Test
    void givenTextInCharsLength10WithFourByteCharacter_whenSave_thenExceptionBecauseValueTooLarge() {
        Note note = new Note();
        note.setTextInChars(ONE_BYTE_CHARACTER.repeat(9) + FOUR_BYTE_CHARACTER);
        Note saved = noteRepository.save(note);
        assertThat(saved.getTextInChars())
                .isEqualTo("aaaaaaaaa🐶");
    }
}
