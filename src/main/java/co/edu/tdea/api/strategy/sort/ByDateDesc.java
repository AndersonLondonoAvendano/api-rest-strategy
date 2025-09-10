package co.edu.tdea.api.strategy.sort;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component("byDateDesc")
public class ByDateDesc implements SortStrategy{

    @Override
    public Sort sort() {
        return Sort.by("date").descending();
    }
}
