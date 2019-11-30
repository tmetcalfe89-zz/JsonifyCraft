package us.timinc.jsonifycraft;

import org.apache.logging.log4j.*;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import us.timinc.jsonifycraft.client.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.jsonifycraft.json.behavior.*;
import us.timinc.jsonifycraft.json.condition.*;
import us.timinc.jsonifycraft.json.deserializers.*;
import us.timinc.jsonifycraft.json.world.*;
import us.timinc.mcutil.*;

@Mod(modid = JsonifyCraft.MODID, name = JsonifyCraft.NAME, version = JsonifyCraft.VERSION)
public class JsonifyCraft {
	public static final String MODID = "jsonifycraft";
	public static final String NAME = "JsonifyCraft";
	public static final String VERSION = "1.2.2";

	public static Logger LOGGER;
	public static DescriptionLoader GAME_OBJECTS;
	public static Registries REGISTRIES;

	@EventHandler
	public void preInit(FMLPreInitializationEvent evt) {
		LOGGER = evt.getModLog();
		REGISTRIES = new Registries();

		BehaviorDeserializer.registerBehavior("debug", DebugDescription.class);
		BehaviorDeserializer.registerBehavior("changeblock", ChangeBlockDescription.class);
		BehaviorDeserializer.registerBehavior("changerandomblock", ChangeRandomBlockDescription.class);
		BehaviorDeserializer.registerBehavior("consumehelditem", ConsumeHeldItemDescription.class);
		BehaviorDeserializer.registerBehavior("growblock", GrowBlockDescription.class);
		BehaviorDeserializer.registerBehavior("emitparticles", EmitParticlesDescription.class);
		BehaviorDeserializer.registerBehavior("playsound", PlaySoundDescription.class);
		BehaviorDeserializer.registerBehavior("damagehelditem", DamageHeldItemDescription.class);
		BehaviorDeserializer.registerBehavior("repairhelditem", RepairHeldItemDescription.class);
		BehaviorDeserializer.registerBehavior("growtree", GrowTreeDescription.class);
		BehaviorDeserializer.registerBehavior("growmarbletree", GrowMarbleTreeDescription.class);
		BehaviorDeserializer.registerBehavior("dropitem", DropItemDescription.class);
		BehaviorDeserializer.registerBehavior("giveplayeritem", GivePlayerItemDescription.class);

		ConditionDeserializer.registerCondition("checkblock", CheckBlockDescription.class);
		ConditionDeserializer.registerCondition("rolldice", RollDiceDescription.class);
		ConditionDeserializer.registerCondition("or", OrDescription.class);
		ConditionDeserializer.registerCondition("and", AndDescription.class);
		ConditionDeserializer.registerCondition("not", NotDescription.class);
		ConditionDeserializer.registerCondition("playerhasitem", PlayerHasItemDescription.class);
		ConditionDeserializer.registerCondition("cangrow", CanGrowDescription.class);
		ConditionDeserializer.registerCondition("helditemdamage", HeldItemDamageDescription.class);
		ConditionDeserializer.registerCondition("helditem", HeldItemDescription.class);

		GameDeserializer.registerGameObject("block", BlockDescription.class);
		GameDeserializer.registerGameObject("growingblock", GrowingBlockDescription.class);
		GameDeserializer.registerGameObject("item", ItemDescription.class);
		GameDeserializer.registerGameObject("food", FoodDescription.class);
		GameDeserializer.registerGameObject("reactor", ReactorDescription.class);

		GAME_OBJECTS = new DescriptionLoader();
	}

	@EventHandler
	public void onInitialization(FMLInitializationEvent evt) {
		ModelManager.registerItemModels(DescriptionLoader.items);
	}

	public static void log(String message, Object... variables) {
		JsonifyCraft.LOGGER.info(String.format(message, variables));
	}
}
