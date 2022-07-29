package grabit.grabit_backend.converter;

import grabit.grabit_backend.enums.PassApprovalResultStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PassApprovalResultStatusConverter implements AttributeConverter<PassApprovalResultStatus, Boolean> {

	@Override
	public Boolean convertToDatabaseColumn(PassApprovalResultStatus attribute) {
		if (attribute.equals(PassApprovalResultStatus.APPROVED)) {
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;
		}
	}

	@Override
	public PassApprovalResultStatus convertToEntityAttribute(Boolean dbData) {
		if (dbData.equals(Boolean.TRUE)){
			return PassApprovalResultStatus.APPROVED;
		}else{
			return PassApprovalResultStatus.NOT_APPROVED;
		}
	}
}
