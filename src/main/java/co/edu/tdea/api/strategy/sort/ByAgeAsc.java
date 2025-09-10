package co.edu.tdea.api.strategy.sort;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component("byAgeAsc")
public class ByAgeAsc implements  SortStrategy{

    @Override
    public Sort sort() {
        return Sort.by("age").ascending();
    }
}
