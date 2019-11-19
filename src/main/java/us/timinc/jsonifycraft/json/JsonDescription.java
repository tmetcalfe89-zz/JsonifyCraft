package us.timinc.jsonifycraft.json;

import java.util.*;

import us.timinc.jsonifycraft.*;

public abstract class JsonDescription {
	public String flags[] = null;

	public boolean hasFlag(String... searchFlags) {
		if (flags == null)
			return false;
		return Arrays.stream(flags).anyMatch(e -> Arrays.stream(searchFlags).anyMatch(f -> f.equals(e)));
	}

	protected void log(String message, Object... variables) {
		JsonifyCraft.log(message, variables);
	}
}
