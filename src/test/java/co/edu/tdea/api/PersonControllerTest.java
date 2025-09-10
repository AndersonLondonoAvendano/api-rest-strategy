package co.edu.tdea.api;

import co.edu.tdea.api.model.Person;
import co.edu.tdea.api.repository.PersonRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Calendar;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        Calendar cal = Calendar.getInstance();
        cal.set(1995, Calendar.JANUARY, 1);
        repository.save(new Person(null, "Carlos", "carlitos", cal.getTime(),12));

        cal.set(1990, Calendar.JUNE, 15);
        repository.save(new Person(null, "Ana", "ana12", cal.getTime(),40));

        cal.set(2000, Calendar.MARCH, 10);
        repository.save(new Person(null, "Anderson", "anderxon", cal.getTime(),87));
    }

    @Test
    void listarPorNombreAsc() throws Exception {
        mockMvc.perform(get("/api/v1/sorting?sort=byNameAsc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", Matchers.contains(
                        "Ana", "Anderson", "Carlos"
                )));
    }


    @Test
    void listarPorDateDesc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sorting?sort=byDateDesc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].userName",
                        Matchers.contains("anderxon", "ana12", "carlitos")));
    }

}
