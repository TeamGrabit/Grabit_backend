package grabit.grabit_backend.domain;

public class Chatting {
	private Long id;
	private String name;
	private String content;
	private Long challengeId;
	private String chattingType;

	@Override
	public String toString() {
		return "Chating :" + id + ", name: " + name + ", content: " + content + ", challengeId: " + challengeId;
	}

	public String getChattingType() {
		return chattingType;
	}

	public void setChattingType(String chattingType) {
		this.chattingType = chattingType;
	}

	public Long getChallengeId() {
		return challengeId;
	}

	public void setChallengeId(Long challengeId) {
		this.challengeId = challengeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
