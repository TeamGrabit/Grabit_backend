package grabit.grabit_backend.dto;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@AllArgsConstructor
@SuperBuilder
public class ResponsePagingDTO {
    public Pageable pageable;
    public int totalPages;
    public long totalElements;
    public boolean first;
    public boolean last;
    public int numberOfElements;
    public int size;
    public int number;
    public Sort sort;
}
