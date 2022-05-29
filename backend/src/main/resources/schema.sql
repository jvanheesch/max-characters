CREATE TABLE MY_USER.NOTE
(
    ID            NUMBER NOT NULL,
    -- https://www.oracletutorial.com/oracle-basics/oracle-varchar2/
    TEXT_IN_BYTES VARCHAR2(10 BYTE),
    TEXT_IN_CHARS VARCHAR2(10 CHAR),
    CONSTRAINT NOTE_PK PRIMARY KEY (ID)
);

CREATE SEQUENCE MY_USER.NOTE_SEQ;
