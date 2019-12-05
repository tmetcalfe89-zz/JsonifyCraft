package us.timinc.jsonifycraft.proxy;

import net.minecraftforge.fml.common.event.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.client.*;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent evt) {
		super.preInit(evt);
		JsonifyCraft.REGISTRIES.loadClientRegistries();
	}

	@Override
	public void init(FMLInitializationEvent evt) {
		ModelManager.registerItemModels(DescriptionLoader.items);
	}
}
