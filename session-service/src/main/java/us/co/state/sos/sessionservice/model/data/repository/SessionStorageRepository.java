package us.co.state.sos.sessionservice.model.data.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import us.co.state.sos.sessionservice.domain.SessionData;

@Repository
public class SessionStorageRepository {

	private final Logger log = LoggerFactory.getLogger(SessionStorageRepository.class);

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * Note: Method not in play Save Session Data object into MongoDB
	 * 
	 * @param sessionData
	 */
	public void save(SessionData sessionData) {

		log.debug(sessionData + "SessionData being saved <<<<<<<<<<<<<<<<<<<>>>>>>>>>");

		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(sessionData.getId()));
		SessionData sessionDataStored = mongoTemplate.findOne(query, SessionData.class);

		if (sessionDataStored == null) {
			mongoTemplate.save(sessionData, "sessions");
		}
	}

	/**
	 * Retrieve by session id
	 * 
	 * @param sessionId
	 * @return
	 */
	public SessionData retrieve(String sessionId) {
		SessionData retrievedSessionData = null;

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(sessionId));
		retrievedSessionData = mongoTemplate.findOne(query, SessionData.class);
		log.debug("find - retrievedSessionData : " + retrievedSessionData);

		return retrievedSessionData;

	}

}
