package us.co.state.sos.sessionservice.model.services;

import javax.inject.Inject;
import javax.inject.Named;

import us.co.state.sos.sessionservice.domain.SessionData;
import us.co.state.sos.sessionservice.model.data.repository.SessionStorageRepository;

@Named
public class SessionStorageService {

	@Inject
	private SessionStorageRepository sessionStorageRepository;

	public void saveSession(SessionData sessionData) {
		sessionStorageRepository.save(sessionData);
	}

	public SessionData retrieveSession(String sessionId) {
		return sessionStorageRepository.retrieve(sessionId);

	}

}
