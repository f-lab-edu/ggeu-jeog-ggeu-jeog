package com.rollingpaper.ggeujeogggeujeog.notification.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationMessage {

	private static final String PAPER_TITLE = "새로운 페이퍼가 등록되었습니다.";
	private static final String COMMENT_TITLE = "새로운 답글이 등록되었습니다.";

	private String title;
	private String content;
	private NotificationType type;
	private Long typeId;

	public NotificationMessage() {

	}

	@Builder
	private NotificationMessage(String title, String content,
		NotificationType type, Long typeId) {
		this.title = title;
		this.content = content;
		this.type = type;
		this.typeId = typeId;
	}

	public static NotificationMessage createPaperMessage(
		String content,
		Long paperId
	) {
		return NotificationMessage.builder()
			.title(PAPER_TITLE)
			.content(content)
			.type(NotificationType.PAPER)
			.typeId(paperId)
			.build();
	}

	public static NotificationMessage createCommentMessage(
		String content,
		Long commentId
	) {
		return NotificationMessage.builder()
			.title(PAPER_TITLE)
			.content(content)
			.type(NotificationType.COMMENT)
			.typeId(commentId)
			.build();
	}
}
