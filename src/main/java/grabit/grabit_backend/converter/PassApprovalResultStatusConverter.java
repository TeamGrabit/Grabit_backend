package grabit.grabit_backend.converter;

import grabit.grabit_backend.enums.PassApprovalResultStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PassApprovalResultStatusConverter implements AttributeConverter<PassApprovalResultStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(PassApprovalResultStatus attribute) {
		if (attribute.equals(PassApprovalResultStatus.PENDING)) {
			return 0;
		}else if (attribute.equals(PassApprovalResultStatus.APPROVED)){
			return 1;
		}else if (attribute.equals(PassApprovalResultStatus.REJECT)){
			return 2;
		}else {
			throw new IllegalStateException("잘못된 값입니다.");
		}
	}

	@Override
	public PassApprovalResultStatus convertToEntityAttribute(Integer dbData) {
		if (dbData.equals(0)){
			return PassApprovalResultStatus.PENDING;
		}else if (dbData.equals(1)){
			return PassApprovalResultStatus.APPROVED;
		}else if (dbData.equals(2)){
			return PassApprovalResultStatus.REJECT;
		}else {
			throw new IllegalStateException("잘못된 값입니다.");
		}
	}
}
