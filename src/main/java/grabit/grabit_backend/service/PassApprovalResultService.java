package grabit.grabit_backend.service;

import grabit.grabit_backend.domain.Pass;
import grabit.grabit_backend.domain.PassApproval;
import grabit.grabit_backend.domain.PassApprovalResult;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.enums.PassApprovalResultStatus;
import grabit.grabit_backend.exception.ForbiddenException;
import grabit.grabit_backend.exception.NotFoundException;
import grabit.grabit_backend.repository.PassApprovalRepository;
import grabit.grabit_backend.repository.PassApprovalResultRepository;
import grabit.grabit_backend.repository.PassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassApprovalResultService {

	private final PassApprovalResultRepository passApprovalResultRepository;
	private final PassApprovalRepository passApprovalRepository;
	private final PassRepository passRepository;

	public PassApprovalResultService(PassApprovalResultRepository passApprovalResultRepository, PassApprovalRepository passApprovalRepository, PassRepository passRepository) {
		this.passApprovalResultRepository = passApprovalResultRepository;
		this.passApprovalRepository = passApprovalRepository;
		this.passRepository = passRepository;
	}

	@Transactional
	public String acceptPassApproval(Long id, User user) {
		PassApprovalResult passApprovalList = readPassApproval(id, user);
		Integer allCount = passApprovalResultRepository.countByPassApproval(passApprovalList.getPassApproval());
		Integer acceptCount = passApprovalResultRepository.countByPassApprovalAndStatus(passApprovalList.getPassApproval(), PassApprovalResultStatus.APPROVED);

		if ((acceptCount+1)/(double)allCount >= 0.5) {
			PassApproval passApproval = passApprovalList.getPassApproval();
			Pass pass = Pass.builder()
					.user(passApproval.getUser())
					.challenge(passApprovalList.getChallenge())
					.date(passApproval.getTargetDate()).build();
			passRepository.save(pass);
			passApprovalRepository.delete(passApproval);
		}else{
			passApprovalList.setStatus(PassApprovalResultStatus.APPROVED);
			passApprovalResultRepository.save(passApprovalList);
		}

		return "success";
	}

	@Transactional
	public String rejectPassApproval(Long id, User user) {
		PassApprovalResult passApprovalList = readPassApproval(id, user);
		Integer allCount = passApprovalResultRepository.countByPassApproval(passApprovalList.getPassApproval());
		Integer rejectCount = passApprovalResultRepository.countByPassApprovalAndStatus(passApprovalList.getPassApproval(), grabit.grabit_backend.enums.PassApprovalResultStatus.REJECT);

		if ((rejectCount+1)/(double)allCount >= 0.5) {
			PassApproval passApproval = passApprovalList.getPassApproval();
			passApprovalRepository.delete(passApproval);
		}else{
			passApprovalList.setStatus(PassApprovalResultStatus.REJECT);
			passApprovalResultRepository.save(passApprovalList);
		}

		return "success";
	}

	public PassApprovalResult readPassApproval(Long id, User user) {
		PassApprovalResult passApprovalList = passApprovalResultRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("승인 요청 정보를 찾을 수 없습니다."));

		if (!passApprovalList.getUser().getId().equals(user.getId())) {
			throw new ForbiddenException();
		}

		return passApprovalList;
	}
}
