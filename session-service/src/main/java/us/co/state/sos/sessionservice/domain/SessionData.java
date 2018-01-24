package us.co.state.sos.sessionservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sessions")
public class SessionData {

	@Id
	private String id;
	private String content;

	public SessionData() {
	}

	public SessionData(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "SessionData [id=" + id + ", content=" + content + "]";
	}

}
