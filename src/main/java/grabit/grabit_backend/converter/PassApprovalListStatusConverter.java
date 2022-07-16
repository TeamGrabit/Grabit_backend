package grabit.grabit_backend.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PassApprovalListStatusConverter implements AttributeConverter<String, Boolean> {

	@Override
	public Boolean convertToDatabaseColumn(String attribute) {
		if (attribute.equals("승인")) {
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}

	@Override
	public String convertToEntityAttribute(Boolean dbData) {
		if (dbData.equals(Boolean.TRUE)){
			return "승인";
		}else{
			return "미승인";
		}
	}
}
