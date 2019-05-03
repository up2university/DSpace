package org.dspace.lrs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dspace.authenticate.AuthenticationServiceImpl;
import org.dspace.core.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rusticisoftware.tincan.Activity;
import com.rusticisoftware.tincan.ActivityDefinition;
import com.rusticisoftware.tincan.Agent;
import com.rusticisoftware.tincan.Context;
import com.rusticisoftware.tincan.ContextActivities;
import com.rusticisoftware.tincan.LanguageMap;
import com.rusticisoftware.tincan.RemoteLRS;
import com.rusticisoftware.tincan.Statement;
import com.rusticisoftware.tincan.StatementRef;
import com.rusticisoftware.tincan.TCAPIVersion;
import com.rusticisoftware.tincan.Verb;
import com.rusticisoftware.tincan.lrsresponses.StatementLRSResponse;

public final class LrsUtils {
	private static RemoteLRS lrs = new RemoteLRS(TCAPIVersion.V100);
	private static Boolean lrsEnabled=false;
	
	/** SLF4J logging category */
	private final static Logger log = (Logger) LoggerFactory.getLogger(LrsUtils.class);

	static {
		try {
			lrs.setEndpoint(ConfigurationManager.getProperty("lrs.endpoint"));
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		lrsEnabled=Boolean.valueOf(ConfigurationManager.getProperty("lrs.enabled"));
		lrs.setUsername(ConfigurationManager.getProperty("lrs.username"));
		lrs.setPassword(ConfigurationManager.getProperty("lrs.password"));
		log.info("USERNAME" + lrs.getUsername());
	}

	public static void sendLoginStatement(String id, String username) {
		// *LRS - Statement*//
		if(lrsEnabled) {
			Agent agent = new Agent();
			agent.setMbox("mailto:" + username);
			agent.setName(id);
	
			Verb verb = null;
			try {
				verb = new Verb("https://brindlewaye.com/xAPITerms/verbs/loggedin");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			verb.setDisplay(new LanguageMap());
			verb.getDisplay().put("en-US", "loggedin");
	
			Activity activity = createActivity("http://id.tincanapi.com/activitytype/community-site/dspace","http://id.tincanapi.com/activitytype/community-site","Up2U DSpace Community","Up2U repository for users where they can upload multimedia content.");
	
			Statement statement = new Statement();
			statement.setActor(agent);
			statement.setVerb(verb);
			statement.setObject(activity);
			log.info(statement.getActor().getName());
			StatementLRSResponse lrsRes = lrs.saveStatement(statement);
		}
	}

	public static void sendCreateNewSubmissionStatement(String userId, String username,String collectionName,String collectionId, String itemId) {
		// *LRS - Statement*//
		if (lrsEnabled) {
			Agent agent = new Agent();
			agent.setMbox("mailto:" + username);
			agent.setName(userId);
	
			Verb verb = null;
			try {
				verb = new Verb("http://activitystrea.ms/schema/1.0/create");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			verb.setDisplay(new LanguageMap());
			verb.getDisplay().put("en-US", "created");
	
			Activity activity = createActivity("http://id.tincanapi.com/activitytype/resource/"+itemId,
					"http://id.tincanapi.com/activitytype/resource",itemId,"Resource can be a video,image or text");
	
			Statement statement = new Statement();
			statement.setActor(agent);
			statement.setVerb(verb);
			statement.setObject(activity);
			statement.setContext(createContext(collectionId,collectionName));
			log.info(statement.getActor().getName());
			StatementLRSResponse lrsRes = lrs.saveStatement(statement);
			log.info(lrsRes.getErrMsg()+lrsRes.getSuccess());
		}
	}
	
	public static void sendDeleteSubmissionStatement(String userId, String username,String collectionName,String collectionId, String itemId) {
		// *LRS - Statement*//
		if (lrsEnabled) {
			Agent agent = new Agent();
			agent.setMbox("mailto:" + username);
			agent.setName(userId);
	
			Verb verb = null;
			try {
				verb = new Verb("http://activitystrea.ms/schema/1.0/delete");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			verb.setDisplay(new LanguageMap());
			verb.getDisplay().put("en-US", "deleted");
	
			Activity activity = createActivity("http://id.tincanapi.com/activitytype/resource/"+itemId,
					"http://id.tincanapi.com/activitytype/resource",itemId,"Resource can be a video,image or text");
	
			Statement statement = new Statement();
			statement.setActor(agent);
			statement.setVerb(verb);
			statement.setObject(activity);
			statement.setContext(createContext(collectionId,collectionName));
			log.info(statement.getActor().getName());
			StatementLRSResponse lrsRes = lrs.saveStatement(statement);
			log.info(lrsRes.getErrMsg()+lrsRes.getSuccess());
		}
	}
	
	public static void sendUpdateSubmissionStatement(String userId, String username,String collectionName,String collectionId, String itemId) {
		// *LRS - Statement*//
		if (lrsEnabled) {
			Agent agent = new Agent();
			agent.setMbox("mailto:" + username);
			agent.setName(userId);
	
			Verb verb = null;
			try {
				verb = new Verb("http://activitystrea.ms/schema/1.0/update");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			verb.setDisplay(new LanguageMap());
			verb.getDisplay().put("en-US", "updated");
	
			Activity activity = createActivity("http://id.tincanapi.com/activitytype/resource/"+itemId,
					"http://id.tincanapi.com/activitytype/resource",itemId,"Resource can be a video,image or text");
	
			Statement statement = new Statement();
			statement.setActor(agent);
			statement.setVerb(verb);
			statement.setObject(activity);
			statement.setContext(createContext(collectionId,collectionName));
			log.info(statement.getActor().getName());
			StatementLRSResponse lrsRes = lrs.saveStatement(statement);
			log.info(lrsRes.getErrMsg()+lrsRes.getSuccess());
		}
	}
	
	public static void sendSubmitSubmissionStatement(String userId, String username,String collectionName,String collectionId, String itemId) {
		// *LRS - Statement*//
		if (lrsEnabled) {
			Agent agent = new Agent();
			agent.setMbox("mailto:" + username);
			agent.setName(userId);
	
			Verb verb = null;
			try {
				verb = new Verb("http://activitystrea.ms/schema/1.0/submit");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			verb.setDisplay(new LanguageMap());
			verb.getDisplay().put("en-US", "submitted");
	
			Activity activity = createActivity("http://id.tincanapi.com/activitytype/resource/"+itemId,
					"http://id.tincanapi.com/activitytype/resource",itemId,"Resource can be a video,image or text");
	
			Statement statement = new Statement();
			statement.setActor(agent);
			statement.setVerb(verb);
			statement.setObject(activity);
			statement.setContext(createContext(collectionId,collectionName));
			log.info(statement.getActor().getName());
			StatementLRSResponse lrsRes = lrs.saveStatement(statement);
			log.info(lrsRes.getErrMsg()+lrsRes.getSuccess());
		}
	}
	
	public static void sendAccessedItemStatement(String userId, String username,String collectionName,String collectionId, String itemId) {
		// *LRS - Statement*//
		if (lrsEnabled) {
			Agent agent = new Agent();
			agent.setMbox("mailto:" + username);
			agent.setName(userId);
	
			Verb verb = null;
			try {
				verb = new Verb("http://id.tincanapi.com/verb/viewed");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			verb.setDisplay(new LanguageMap());
			verb.getDisplay().put("en-US", "viewed");
	
			Activity activity = createActivity("http://id.tincanapi.com/activitytype/resource/"+itemId,
					"http://id.tincanapi.com/activitytype/resource",itemId,"Resource can be a video,image or text");
	
			Statement statement = new Statement();
			statement.setActor(agent);
			statement.setVerb(verb);
			statement.setObject(activity);
			statement.setContext(createContext(collectionId,collectionName));
			log.info(statement.getActor().getName());
			StatementLRSResponse lrsRes = lrs.saveStatement(statement);
			log.info(lrsRes.getErrMsg()+lrsRes.getSuccess());
		}
	}
	
	public static void sendSearchQueryStatement(String userId, String username,String pageUrl) {
		// *LRS - Statement*//
		if (lrsEnabled) {
			Agent agent = new Agent();
			agent.setMbox("mailto:" + username);
			agent.setName(userId);
	
			Verb verb = null;
			try {
				verb = new Verb("http://activitystrea.ms/schema/1.0/search");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			verb.setDisplay(new LanguageMap());
			verb.getDisplay().put("en-US", "searched");
	
			Activity activity = createActivity(pageUrl,
					"http://activitystrea.ms/schema/1.0/page","DSpace search page","");
	
			Statement statement = new Statement();
			statement.setActor(agent);
			statement.setVerb(verb);
			statement.setObject(activity);
			
			//New Context
			 Context context = new Context();
			 context.setPlatform("DSpace");
		     context.setRegistration(UUID.randomUUID());
		     StatementRef statementRef = new StatementRef(UUID.randomUUID());
		     context.setContextActivities(new ContextActivities());
		     context.setStatement(statementRef);
		     
		     statement.setContext(context);
			
			log.info(statement.getActor().getName());
			StatementLRSResponse lrsRes = lrs.saveStatement(statement);
			log.info(lrsRes.getErrMsg()+lrsRes.getSuccess());
		}
	}
	
	public static void sendLogoutStatement(String id, String username) {
		// *LRS - Statement*//
		if (lrsEnabled) {
		Agent agent = new Agent();
		agent.setMbox("mailto:" + username);
		agent.setName(id);

		Verb verb = null;
		try {
			verb = new Verb("https://brindlewaye.com/xAPITerms/verbs/loggedout");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		verb.setDisplay(new LanguageMap());
		verb.getDisplay().put("en-US", "loggedout");

		Activity activity = createActivity("http://id.tincanapi.com/activitytype/community-site/dspace","http://id.tincanapi.com/activitytype/community-site","Up2U DSpace Community","Up2U repository for users where they can upload multimedia content.");

		Statement statement = new Statement();
		statement.setActor(agent);
		statement.setVerb(verb);
		statement.setObject(activity);
		log.info(statement.getActor().getName());
		StatementLRSResponse lrsRes = lrs.saveStatement(statement);
		}
	}

	public static Activity createActivity(String activityUri,
			String definitionUri,String definitionName,String definitionDescription) {
		
		Activity activity = new Activity();
		try {
			activity.setId(new URI(activityUri));
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		activity.setDefinition(new ActivityDefinition());
		try {
			activity.getDefinition().setType(definitionUri);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		activity.getDefinition().setName(new LanguageMap());
		activity.getDefinition().getName().put("en-US", definitionName);
		activity.getDefinition().setDescription(new LanguageMap());
		activity.getDefinition().getDescription().put("en-US",
				definitionDescription);
		
		return activity;
		
	}
	public static Context createContext(String collectionId,String collectionTitle) {
	 Context context = new Context();
	 context.setPlatform("DSpace");
     context.setRegistration(UUID.randomUUID());
     StatementRef statementRef = new StatementRef(UUID.randomUUID());
     context.setContextActivities(new ContextActivities());
     context.setStatement(statementRef);
     context.getContextActivities().setParent(new ArrayList<Activity>());
     context.getContextActivities().getParent().add(createActivity("http://activitystrea.ms/schema/1.0/collection/"+collectionId,"http://activitystrea.ms/schema/1.0/collection",collectionTitle,"This is a DSpace Collection"));
     return context;
	}
	
}
