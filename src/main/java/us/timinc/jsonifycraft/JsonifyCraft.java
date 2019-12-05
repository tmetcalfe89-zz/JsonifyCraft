package us.timinc.jsonifycraft;

import org.apache.logging.log4j.*;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import us.timinc.jsonifycraft.proxy.*;
import us.timinc.mcutil.*;

@Mod(modid = JsonifyCraft.MODID, name = JsonifyCraft.NAME, version = JsonifyCraft.VERSION)
public class JsonifyCraft {
	public static final String MODID = "jsonifycraft";
	public static final String NAME = "JsonifyCraft";
	public static final String VERSION = "1.3.0";

	@SidedProxy(clientSide = "us.timinc.jsonifycraft.proxy.ClientProxy", serverSide = "us.timinc.jsonifycraft.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Logger LOGGER;
	public static DescriptionLoader GAME_OBJECTS;
	public static Registries REGISTRIES;

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		proxy.preInit(evt);
	}

	@EventHandler
	public void init(FMLInitializationEvent evt) {
		proxy.init(evt);
	}

	public static void log(String message, Object... variables) {
		JsonifyCraft.LOGGER.info(String.format(message, variables));
	}
}
