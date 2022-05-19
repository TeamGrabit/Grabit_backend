package grabit.grabit_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponsePagingDTO {
	public List<ResponseChallengeDTO> content;
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
