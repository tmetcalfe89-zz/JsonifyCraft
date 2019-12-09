package us.timinc.jsonifycraft.proxy;

import net.minecraftforge.fml.common.event.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.*;
import us.timinc.jsonifycraft.json.behavior.*;
import us.timinc.jsonifycraft.json.condition.*;
import us.timinc.jsonifycraft.json.deserializers.*;
import us.timinc.jsonifycraft.json.world.*;
import us.timinc.mcutil.*;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent evt) {
		JsonifyCraft.LOGGER = evt.getModLog();
		JsonifyCraft.REGISTRIES = new Registries();
		JsonifyCraft.REGISTRIES.loadCommonRegistries();

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
		GameDeserializer.registerGameObject("rotatingblock", RotatingBlockDescription.class);
		GameDeserializer.registerGameObject("item", ItemDescription.class);
		GameDeserializer.registerGameObject("food", FoodDescription.class);
		GameDeserializer.registerGameObject("reactor", ReactorDescription.class);

		JsonifyCraft.GAME_OBJECTS = new DescriptionLoader();
	}

	public void init(FMLInitializationEvent evt) {
	}
}
