- run oracle container: `docker run -p 1521:1521 max-characters-oracle:latest`
- run backend
    - note: restarting backend will result
      in `java.sql.SQLSyntaxErrorException: ORA-00955: name is already used by an existing object`.
      Replacing `spring.sql.init.mode=always` with `spring.sql.init.mode=never` will resolve this problem 
- serve angular app
  - `cd frontend`
  - `npm start`
