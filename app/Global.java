import play.Application;
import play.GlobalSettings;
import play.Logger;

import com.tinkerpop.blueprints.Vertex;
import com.wingnest.play2.frames.GraphDB;

import models.formdata.*;
import models.vertices.Log;

public class Global extends GlobalSettings {

	private static boolean init = false;
	
	public void onStart(Application app) {
		try {
			if(!init) {
				GraphDB.createKeyIndex("className", Vertex.class);
				GraphDB.createKeyIndex("name", Vertex.class);
				init = true;
			}
		} catch (java.lang.UnsupportedOperationException e){
			Logger.info(e.getMessage());
		}
	}

}
