package us.timinc.jsonifycraft.json;

import java.io.*;
import java.util.*;

import com.google.gson.*;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraftforge.registries.*;
import us.timinc.jsonifycraft.*;
import us.timinc.jsonifycraft.json.deserializers.*;
import us.timinc.jsonifycraft.json.world.*;

public class DescriptionLoader {
	private Gson gson;
	private GsonBuilder gsonBuilder;

	public static ArrayList<JsonDescription> gameObjects;
	public static ArrayList<Block> blocks;
	public static ArrayList<Item> items;

	public DescriptionLoader() {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(JsonDescription.class, new GameDeserializer());
		gsonBuilder.registerTypeAdapter(BehaviorDescription.class, new BehaviorDeserializer());
		gsonBuilder.registerTypeAdapter(ConditionDescription.class, new ConditionDeserializer());
		gson = gsonBuilder.create();
		DescriptionLoader.gameObjects = new ArrayList<>();
		DescriptionLoader.blocks = new ArrayList<>();
		DescriptionLoader.items = new ArrayList<>();
		loadGameObjects();
	}

	private void loadGameObjects() {
		File globalDir = new File("gameobjects");
		if (!globalDir.exists()) {
			globalDir.mkdirs();
		}
		searchDirectory(globalDir);
	}

	private void searchDirectory(File dir) {
		Arrays.stream(dir.listFiles()).filter(x -> x.isDirectory()).forEach(this::searchDirectory);
		Arrays.stream(dir.listFiles()).filter(x -> x.getName().endsWith(".json")).forEach(this::addObjectsFrom);
	}

	private void addObjectsFrom(File file) {
		JsonDescription[] newObjects = null;
		try {
			newObjects = gson.fromJson(new FileReader(file), JsonDescription[].class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		gameObjects.addAll(Arrays.asList(newObjects));
	}

	public void registerItems(IForgeRegistry<Item> registry) {
		gameObjects.stream().filter(e -> e instanceof ItemDescription).forEach(e -> {
			ItemDescription itemJson = (ItemDescription) e;
			registry.register(itemJson.getItem().setRegistryName(itemJson.name)
					.setUnlocalizedName(JsonifyCraft.MODID + "." + itemJson.name));
			items.add(itemJson.getItem());
		});
		gameObjects.stream().filter(e -> (e instanceof BlockDescription) && !e.hasFlag("itemless")).forEach(e -> {
			BlockDescription blockJson = (BlockDescription) e;
			registry.register(blockJson.getItem().setRegistryName(blockJson.getBlock().getRegistryName())
					.setUnlocalizedName(blockJson.getBlock().getUnlocalizedName()));
			items.add(blockJson.getItem());
		});
	}

	public void registerBlocks(IForgeRegistry<Block> registry) {
		gameObjects.stream().filter(e -> e instanceof BlockDescription).forEach(e -> {
			BlockDescription blockJson = (BlockDescription) e;
			registry.register(blockJson.getBlock().setRegistryName(blockJson.name)
					.setUnlocalizedName(JsonifyCraft.MODID + "." + blockJson.name));
			blocks.add(blockJson.getBlock());
		});
	}

}
