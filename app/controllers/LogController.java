package controllers;

import models.formdata.*;
import models.vertices.Log;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;

import services.CommentService;
import services.LogService;
import views.html.index;
import views.html.logs;

import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


import com.wingnest.play2.frames.annotations.WithGraphDB;
import static play.data.Form.form;

@WithGraphDB
public class LogController extends Controller {

	public static Result postLogForm() {
		final Form<LogData> logForm = form(LogData.class).bindFromRequest();
		if ( logForm.hasErrors() ) {
			return badRequest(logForm.errorsAsJson());
		}

		@SuppressWarnings("unused")
		Log log = LogService.getInstance().create(logForm.get());

		return ok();
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result sayHello() {
	  String name = "david";
	  if(name == null) {
		return badRequest("Missing parameter [name]");
	  } else {
		return ok("Hello " + name);
	  }
	}	
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result postLog() {
		LogService logService = LogService.getInstance();
		
		JsonNode json = request().body().asJson();
		logService.createAsJson(json);
		
		return ok(Json.toJson(json));
	}

	public static Result getLogs() {
		LogService logService = LogService.getInstance();
		final Iterable<Log> logModels = logService.getLogs();
		return ok(logs.render(logModels));
	}

	public static Result getLog(Integer logId) {
		LogService logService = LogService.getInstance();
		final Log log = logService.getLog(logId);
		
		return ok(Json.toJson(log));
	}	

}
