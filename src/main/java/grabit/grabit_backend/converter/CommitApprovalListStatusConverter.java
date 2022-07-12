package grabit.grabit_backend.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CommitApprovalListStatusConverter implements AttributeConverter<String, Integer> {

	@Override
	public Integer convertToDatabaseColumn(String attribute) {
		if (attribute.equals("미확인")) {
			return 0;
		}else if (attribute.equals("승인")){
			return 1;
		}else if (attribute.equals("거절")){
			return 2;
		}else{
			throw new IllegalStateException("잘못된 값입니다.");
		}
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) {
		if (dbData.equals(0)){
			return "미확인";
		}else if (dbData.equals(1)){
			return "승인";
		}else if (dbData.equals(2)){
			return "거절";
		}else{
			throw new IllegalStateException("잘못된 값입니다.");
		}
	}
}
