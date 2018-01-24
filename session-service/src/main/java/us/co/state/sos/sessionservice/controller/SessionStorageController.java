package us.co.state.sos.sessionservice.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import us.co.state.sos.sessionservice.domain.SessionData;
import us.co.state.sos.sessionservice.model.services.SessionStorageService;

@RestController
@RequestMapping("/session/")
public class SessionStorageController {

	@Inject
	private SessionStorageService sessionStorageService;

	/**
	 * Retrieve from MongoDB JSON data.
	 * 
	 * @param sessionId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<String> getById(@PathVariable String id) {
		SessionData retievedSessionData = null;
		if (id != null) {
			retievedSessionData = sessionStorageService.retrieveSession(id);
		}
		// return JSON content
		return new ResponseEntity<String>(retievedSessionData.getContent(), HttpStatus.OK);
	}

	/**
	 * Store JSON string by injecting it into a SessionData object and persisting in
	 * MongoDB
	 * 
	 * @param sessionDataJSON
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> storeSessionWithJSON(@RequestBody String sessionDataJSON) {
		SessionData sessionData = null;
		if (sessionDataJSON != null) {
			// inject the incoming JSON into SessionData
			sessionData = new SessionData();
			sessionData.setContent(sessionDataJSON);
			sessionStorageService.saveSession(sessionData);
		}
		// return id associated with this session data
		return new ResponseEntity<String>(sessionData.getId(), HttpStatus.OK);
	}

}
