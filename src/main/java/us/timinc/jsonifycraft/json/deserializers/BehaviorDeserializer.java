package us.timinc.jsonifycraft.json.deserializers;

import java.lang.reflect.*;
import java.util.*;

import com.google.gson.*;

import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.*;

public class BehaviorDeserializer implements JsonDeserializer<BehaviorDescription> {
	private static HashMap<String, Type> behaviorClasses = new HashMap<>();

	public static void registerBehavior(String name, Type type) {
		JsonifyCraft.LOGGER.info("Registering behavior: " + name);
		behaviorClasses.put(name, type);
	}

	@Override
	public BehaviorDescription deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();

		String type = jsonObject.get("type").getAsString();

		return context.deserialize(jsonObject, behaviorClasses.get(type));
	}

}
