package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "", produces = "application/json")
    public List<Map<String, Object>> getPeoples(){
        String query = "SELECT * FROM person;";
        return jdbc.queryForList(query);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Map<String, Object> getPeople(@PathVariable long id) {
        String query = "SELECT * FROM person WHERE id=?;";
        return jdbc.queryForMap(query, id);
    }
    // END
}
