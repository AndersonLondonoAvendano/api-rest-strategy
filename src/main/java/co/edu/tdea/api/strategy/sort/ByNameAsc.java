package co.edu.tdea.api.strategy.sort;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component("byNameAsc")
public class ByNameAsc implements SortStrategy{

    @Override
    public Sort sort() {
        return Sort.by("name").ascending();
    }
}
