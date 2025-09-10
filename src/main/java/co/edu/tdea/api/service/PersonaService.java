package co.edu.tdea.api.service;


import co.edu.tdea.api.model.Person;
import co.edu.tdea.api.repository.PersonRepository;
import co.edu.tdea.api.strategy.sort.SortStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private  final PersonRepository personRepository;

    private final Map<String, SortStrategy> sortStrategies;

    public List<Person> listByNameAsc (String sortKey){
        SortStrategy strategy = sortStrategies.getOrDefault(sortKey,sortStrategies.get("byNameAsc"));
        Sort sort = strategy.sort();
        return personRepository.findAll(sort);
    }
}
