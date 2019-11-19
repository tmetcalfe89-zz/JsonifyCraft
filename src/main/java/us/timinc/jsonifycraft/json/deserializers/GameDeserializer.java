package us.timinc.jsonifycraft.json.deserializers;

import java.lang.reflect.*;
import java.util.*;

import com.google.gson.*;

import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.*;

public class GameDeserializer implements JsonDeserializer<JsonDescription> {
	private static HashMap<String, Class> gameObjectClasses = new HashMap<>();

	public static void registerGameObject(String name, Class _class) {
		JsonifyCraft.LOGGER.info("Registering game object: " + name);
		gameObjectClasses.put(name, _class);
	}

	@Override
	public JsonDescription deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		String type = jsonObject.get("type").getAsString();
		return context.deserialize(jsonObject, gameObjectClasses.get(type));
	}

}
