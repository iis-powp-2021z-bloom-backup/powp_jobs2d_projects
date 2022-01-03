package edu.kis.powp.jobs2d.drivers.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonAdapter implements JsonDeserializer {

	private static final String CLASSNAME = "CLASSNAME";
	private static final String DATA = "DATA";
	private static final String PACKAGE = "edu.kis.powp.jobs2d.command.";

	public Object deserialize(JsonElement jsonElement, Type type,
							  JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
		String className = PACKAGE.concat(prim.getAsString());
		Class klass = getObjectClass(className);
		return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
	}

	public Class getObjectClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new JsonParseException(e.getMessage());
		}
	}
}
