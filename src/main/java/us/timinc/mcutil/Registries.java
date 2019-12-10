package us.timinc.mcutil;

import java.util.*;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.*;
import us.timinc.jsonifycraft.world.feature.*;

public class Registries {
	private HashMap<String, CreativeTabs> creativeTabs = new HashMap<>();
	private HashMap<String, Material> blockMaterials = new HashMap<>();
	private HashMap<String, SoundType> soundTypes = new HashMap<>();
	private HashMap<String, SoundEvent> sounds = new HashMap<>();
	private HashMap<String, Potion> effects = new HashMap<>();
	private HashMap<String, EnumParticleTypes> particleTypes = new HashMap<>();
	private HashMap<String, SoundCategory> soundCategories = new HashMap<>();
	private HashMap<String, Class<? extends TreeGenAbstract>> treeGenerators = new HashMap<>();

	public Registries() {
	}

	@SideOnly(Side.CLIENT)
	public void loadClientRegistries() {
		registerVanillaCreativeTabs();
		registerVanillaSoundCategories();
	}

	public void loadCommonRegistries() {
		registerVanillaBlockMaterials();
		registerVanillaSoundTypes();
		registerVanillaSounds();
		registerVanillaEffects();
		registerVanillaParticleTypes();

		registerDefaultTreeGenerators();
	}

	public void registerCreativeTab(String label, CreativeTabs creativeTab) {
		creativeTabs.put(label, creativeTab);
	}

	public CreativeTabs getCreativeTab(String label) {
		return creativeTabs.get(label);
	}

	private void registerVanillaCreativeTabs() {
		for (CreativeTabs creativeTab : CreativeTabs.CREATIVE_TAB_ARRAY) {
			registerCreativeTab(creativeTab.getTabLabel().toUpperCase(), creativeTab);
		}
	}

	public void registerBlockMaterial(String label, Material material) {
		blockMaterials.put(label, material);
	}

	public Material getBlockMaterial(String name) {
		return blockMaterials.get(name);
	}

	private void registerVanillaBlockMaterials() {
		registerBlockMaterial("AIR", Material.AIR);
		registerBlockMaterial("GRASS", Material.GRASS);
		registerBlockMaterial("GROUND", Material.GROUND);
		registerBlockMaterial("WOOD", Material.WOOD);
		registerBlockMaterial("ROCK", Material.ROCK);
		registerBlockMaterial("IRON", Material.IRON);
		registerBlockMaterial("ANVIL", Material.ANVIL);
		registerBlockMaterial("WATER", Material.WATER);
		registerBlockMaterial("LAVA", Material.LAVA);
		registerBlockMaterial("LEAVES", Material.LEAVES);
		registerBlockMaterial("PLANTS", Material.PLANTS);
		registerBlockMaterial("VINE", Material.VINE);
		registerBlockMaterial("SPONGE", Material.SPONGE);
		registerBlockMaterial("CLOTH", Material.CLOTH);
		registerBlockMaterial("FIRE", Material.FIRE);
		registerBlockMaterial("SAND", Material.SAND);
		registerBlockMaterial("CIRCUITS", Material.CIRCUITS);
		registerBlockMaterial("CARPET", Material.CARPET);
		registerBlockMaterial("GLASS", Material.GLASS);
		registerBlockMaterial("REDSTONE_LIGHT", Material.REDSTONE_LIGHT);
		registerBlockMaterial("TNT", Material.TNT);
		registerBlockMaterial("CORAL", Material.CORAL);
		registerBlockMaterial("ICE", Material.ICE);
		registerBlockMaterial("PACKED_ICE", Material.PACKED_ICE);
		registerBlockMaterial("SNOW", Material.SNOW);
		registerBlockMaterial("CRAFTED_SNOW", Material.CRAFTED_SNOW);
		registerBlockMaterial("CACTUS", Material.CACTUS);
		registerBlockMaterial("CLAY", Material.CLAY);
		registerBlockMaterial("GOURD", Material.GOURD);
		registerBlockMaterial("DRAGON_EGG", Material.DRAGON_EGG);
		registerBlockMaterial("PORTAL", Material.PORTAL);
		registerBlockMaterial("CAKE", Material.CAKE);
		registerBlockMaterial("WEB", Material.WEB);
		registerBlockMaterial("PISTON", Material.PISTON);
		registerBlockMaterial("BARRIER", Material.BARRIER);
		registerBlockMaterial("STRUCTURE_VOID", Material.STRUCTURE_VOID);

	}

	public void registerSoundType(String soundTypeName, SoundType soundType) {
		soundTypes.put(soundTypeName, soundType);
	}

	public SoundType getSoundType(String soundType) {
		return soundTypes.get(soundType);
	}

	private void registerVanillaSoundTypes() {
		registerSoundType("WOOD", SoundType.WOOD);
		registerSoundType("GROUND", SoundType.GROUND);
		registerSoundType("PLANT", SoundType.PLANT);
		registerSoundType("STONE", SoundType.STONE);
		registerSoundType("METAL", SoundType.METAL);
		registerSoundType("GLASS", SoundType.GLASS);
		registerSoundType("CLOTH", SoundType.CLOTH);
		registerSoundType("SAND", SoundType.SAND);
		registerSoundType("SNOW", SoundType.SNOW);
		registerSoundType("LADDER", SoundType.LADDER);
		registerSoundType("ANVIL", SoundType.ANVIL);
		registerSoundType("SLIME", SoundType.SLIME);
	}

	public void registerSound(String soundName, SoundEvent sound) {
		sounds.put(soundName, sound);
	}

	public SoundEvent getSound(String soundName) {
		return sounds.get(soundName);
	}

	private void registerVanillaSounds() {
		registerSound("AMBIENT_CAVE", SoundEvents.AMBIENT_CAVE);
		registerSound("BLOCK_ANVIL_BREAK", SoundEvents.BLOCK_ANVIL_BREAK);
		registerSound("BLOCK_ANVIL_DESTROY", SoundEvents.BLOCK_ANVIL_DESTROY);
		registerSound("BLOCK_ANVIL_FALL", SoundEvents.BLOCK_ANVIL_FALL);
		registerSound("BLOCK_ANVIL_HIT", SoundEvents.BLOCK_ANVIL_HIT);
		registerSound("BLOCK_ANVIL_LAND", SoundEvents.BLOCK_ANVIL_LAND);
		registerSound("BLOCK_ANVIL_PLACE", SoundEvents.BLOCK_ANVIL_PLACE);
		registerSound("BLOCK_ANVIL_STEP", SoundEvents.BLOCK_ANVIL_STEP);
		registerSound("BLOCK_ANVIL_USE", SoundEvents.BLOCK_ANVIL_USE);
		registerSound("ENTITY_ARMORSTAND_BREAK", SoundEvents.ENTITY_ARMORSTAND_BREAK);
		registerSound("ENTITY_ARMORSTAND_FALL", SoundEvents.ENTITY_ARMORSTAND_FALL);
		registerSound("ENTITY_ARMORSTAND_HIT", SoundEvents.ENTITY_ARMORSTAND_HIT);
		registerSound("ENTITY_ARMORSTAND_PLACE", SoundEvents.ENTITY_ARMORSTAND_PLACE);
		registerSound("ITEM_ARMOR_EQUIP_CHAIN", SoundEvents.ITEM_ARMOR_EQUIP_CHAIN);
		registerSound("ITEM_ARMOR_EQUIP_DIAMOND", SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND);
		registerSound("ITEM_ARMOR_EQIIP_ELYTRA", SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA);
		registerSound("ITEM_ARMOR_EQUIP_GENERIC", SoundEvents.ITEM_ARMOR_EQUIP_GENERIC);
		registerSound("ITEM_ARMOR_EQUIP_GOLD", SoundEvents.ITEM_ARMOR_EQUIP_GOLD);
		registerSound("ITEM_ARMOR_EQUIP_IRON", SoundEvents.ITEM_ARMOR_EQUIP_IRON);
		registerSound("ITEM_ARMOR_EQUIP_LEATHER", SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
		registerSound("ENTITY_ARROW_HIT", SoundEvents.ENTITY_ARROW_HIT);
		registerSound("ENTITY_ARROW_HIT_PLAYER", SoundEvents.ENTITY_ARROW_HIT_PLAYER);
		registerSound("ENTITY_ARROW_SHOOT", SoundEvents.ENTITY_ARROW_SHOOT);
		registerSound("ENTITY_BAT_AMBIENT", SoundEvents.ENTITY_BAT_AMBIENT);
		registerSound("ENTITY_BAT_DEATH", SoundEvents.ENTITY_BAT_DEATH);
		registerSound("ENTITY_BAT_HURT", SoundEvents.ENTITY_BAT_HURT);
		registerSound("ENTITY_BAT_LOOP", SoundEvents.ENTITY_BAT_LOOP);
		registerSound("ENTITY_BAT_TAKEOFF", SoundEvents.ENTITY_BAT_TAKEOFF);
		registerSound("ENTITY_BLAZE_AMBIENT", SoundEvents.ENTITY_BLAZE_AMBIENT);
		registerSound("ENTITY_BLAZE_BURN", SoundEvents.ENTITY_BLAZE_BURN);
		registerSound("ENTITY_BLAZE_DEATH", SoundEvents.ENTITY_BLAZE_DEATH);
		registerSound("ENTITY_BLAZE_HURT", SoundEvents.ENTITY_BLAZE_HURT);
		registerSound("ENTITY_BLAZE_SHOOT", SoundEvents.ENTITY_BLAZE_SHOOT);
		registerSound("ENTITY_BOAT_PADDLE_LAND", SoundEvents.ENTITY_BOAT_PADDLE_LAND);
		registerSound("ENTITY_BOAT_PADDLE_WATER", SoundEvents.ENTITY_BOAT_PADDLE_WATER);
		registerSound("ENTITY_BOBBER_RETRIEVE", SoundEvents.ENTITY_BOBBER_RETRIEVE);
		registerSound("ENTITY_BOBBER_SPLASH", SoundEvents.ENTITY_BOBBER_SPLASH);
		registerSound("ENTITY_BOBBER_THROW", SoundEvents.ENTITY_BOBBER_THROW);
		registerSound("ITEM_BOTTLE_EMPTY", SoundEvents.ITEM_BOTTLE_EMPTY);
		registerSound("ITEM_BOTTLE_FILL", SoundEvents.ITEM_BOTTLE_FILL);
		registerSound("ITEM_BOTTLE_FILL_DRAGONBREATH", SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH);
		registerSound("BLOCK_BREWING_STAND_BREW", SoundEvents.BLOCK_BREWING_STAND_BREW);
		registerSound("ITEM_BUCKET_EMPTY", SoundEvents.ITEM_BUCKET_EMPTY);
		registerSound("ITEM_BUCKET_EMPTY_LAVA", SoundEvents.ITEM_BUCKET_EMPTY_LAVA);
		registerSound("ITEM_BUCKET_FILL", SoundEvents.ITEM_BUCKET_FILL);
		registerSound("ITEM_BUCKET_FILL_LAVA", SoundEvents.ITEM_BUCKET_FILL_LAVA);
		registerSound("ENTITY_CAT_AMBIENT", SoundEvents.ENTITY_CAT_AMBIENT);
		registerSound("ENTITY_CAT_DEATH", SoundEvents.ENTITY_CAT_DEATH);
		registerSound("ENTITY_CAT_HISS", SoundEvents.ENTITY_CAT_HISS);
		registerSound("ENTITY_CAT_HURT", SoundEvents.ENTITY_CAT_HURT);
		registerSound("ENTITY_CAT_PURR", SoundEvents.ENTITY_CAT_PURR);
		registerSound("ENTITY_CAT_PURREOW", SoundEvents.ENTITY_CAT_PURREOW);
		registerSound("BLOCK_CHEST_CLOSE", SoundEvents.BLOCK_CHEST_CLOSE);
		registerSound("BLOCK_CHEST_LOCKED", SoundEvents.BLOCK_CHEST_LOCKED);
		registerSound("BLOCK_CHEST_OPEN", SoundEvents.BLOCK_CHEST_OPEN);
		registerSound("ENTITY_CHICKEN_AMBIENT", SoundEvents.ENTITY_CHICKEN_AMBIENT);
		registerSound("ENTITY_CHICKEN_DEATH", SoundEvents.ENTITY_CHICKEN_DEATH);
		registerSound("ENTITY_CHICKEN_EGG", SoundEvents.ENTITY_CHICKEN_EGG);
		registerSound("ENTITY_CHICKEN_HURT", SoundEvents.ENTITY_CHICKEN_HURT);
		registerSound("ENTITY_CHICKEN_STEP", SoundEvents.ENTITY_CHICKEN_STEP);
		registerSound("BLOCK_CHORUS_FLOWER_DEATH", SoundEvents.BLOCK_CHORUS_FLOWER_DEATH);
		registerSound("BLOCK_CHORUS_FLOWER_GROW", SoundEvents.BLOCK_CHORUS_FLOWER_GROW);
		registerSound("ITEM_CHORUS_FRUIT_TELEPORT", SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT);
		registerSound("BLOCK_CLOTH_BREAK", SoundEvents.BLOCK_CLOTH_BREAK);
		registerSound("BLOCK_CLOTH_FALL", SoundEvents.BLOCK_CLOTH_FALL);
		registerSound("BLOCK_CLOTH_HIT", SoundEvents.BLOCK_CLOTH_HIT);
		registerSound("BLOCK_CLOTH_PLACE", SoundEvents.BLOCK_CLOTH_PLACE);
		registerSound("BLOCK_CLOTH_STEP", SoundEvents.BLOCK_CLOTH_STEP);
		registerSound("BLOCK_COMPARATOR_CLICK", SoundEvents.BLOCK_COMPARATOR_CLICK);
		registerSound("ENTITY_COW_AMBIENT", SoundEvents.ENTITY_COW_AMBIENT);
		registerSound("ENTITY_COW_DEATH", SoundEvents.ENTITY_COW_DEATH);
		registerSound("ENTITY_COW_HURT", SoundEvents.ENTITY_COW_HURT);
		registerSound("ENTITY_COW_MILK", SoundEvents.ENTITY_COW_MILK);
		registerSound("ENTITY_COW_STEP", SoundEvents.ENTITY_COW_STEP);
		registerSound("ENTITY_CREEPER_DEATH", SoundEvents.ENTITY_CREEPER_DEATH);
		registerSound("ENTITY_CREEPER_HURT", SoundEvents.ENTITY_CREEPER_HURT);
		registerSound("ENTITY_CREEPER_PRIMED", SoundEvents.ENTITY_CREEPER_PRIMED);
		registerSound("BLOCK_DISPENSER_DISPENSE", SoundEvents.BLOCK_DISPENSER_DISPENSE);
		registerSound("BLOCK_DISPENSER_FAIL", SoundEvents.BLOCK_DISPENSER_FAIL);
		registerSound("BLOCK_DISPENSER_LAUNCH", SoundEvents.BLOCK_DISPENSER_LAUNCH);
		registerSound("ENTITY_DONKEY_AMBIENT", SoundEvents.ENTITY_DONKEY_AMBIENT);
		registerSound("ENTITY_DONKEY_ANGRY", SoundEvents.ENTITY_DONKEY_ANGRY);
		registerSound("ENTITY_DONKEY_CHEST", SoundEvents.ENTITY_DONKEY_CHEST);
		registerSound("ENTITY_DONKEY_DEATH", SoundEvents.ENTITY_DONKEY_DEATH);
		registerSound("ENTITY_DONKEY_HURT", SoundEvents.ENTITY_DONKEY_HURT);
		registerSound("ENTITY_EGG_THROW", SoundEvents.ENTITY_EGG_THROW);
		registerSound("ENTITY_ELDER_GUARDIAN_AMBIENT", SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT);
		registerSound("ENTITY_ELDERGUARDIAN_AMBIENTLAND", SoundEvents.ENTITY_ELDERGUARDIAN_AMBIENTLAND);
		registerSound("ENTITY_ELDER_GUARDIAN_CURSE", SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE);
		registerSound("ENTITY_ELDER_GUARDIAN_DEATH", SoundEvents.ENTITY_ELDER_GUARDIAN_DEATH);
		registerSound("ENTITY_ELDER_GUARDIAN_DEATH_LAND", SoundEvents.ENTITY_ELDER_GUARDIAN_DEATH_LAND);
		registerSound("ENTITY_ELDER_GUARDIAN_FLOP", SoundEvents.ENTITY_ELDER_GUARDIAN_FLOP);
		registerSound("ENTITY_ELDER_GUARDIAN_HURT", SoundEvents.ENTITY_ELDER_GUARDIAN_HURT);
		registerSound("ENTITY_ELDER_GUARDIAN_HURT_LAND", SoundEvents.ENTITY_ELDER_GUARDIAN_HURT_LAND);
		registerSound("ITEM_ELYTRA_FLYING", SoundEvents.ITEM_ELYTRA_FLYING);
		registerSound("BLOCK_ENCHANTMENT_TABLE_USE", SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE);
		registerSound("BLOCK_ENDERCHEST_CLOSE", SoundEvents.BLOCK_ENDERCHEST_CLOSE);
		registerSound("BLOCK_ENDERCHEST_OPEN", SoundEvents.BLOCK_ENDERCHEST_OPEN);
		registerSound("ENTITY_ENDERDRAGON_AMBIENT", SoundEvents.ENTITY_ENDERDRAGON_AMBIENT);
		registerSound("ENTITY_ENDERDRAGON_DEATH", SoundEvents.ENTITY_ENDERDRAGON_DEATH);
		registerSound("ENTITY_ENDERDRAGON_FIREBALL_EPLD", SoundEvents.ENTITY_ENDERDRAGON_FIREBALL_EPLD);
		registerSound("ENTITY_ENDERDRAGON_FLAP", SoundEvents.ENTITY_ENDERDRAGON_FLAP);
		registerSound("ENTITY_ENDERDRAGON_GROWL", SoundEvents.ENTITY_ENDERDRAGON_GROWL);
		registerSound("ENTITY_ENDERDRAGON_HURT", SoundEvents.ENTITY_ENDERDRAGON_HURT);
		registerSound("ENTITY_ENDERDRAGON_SHOOT", SoundEvents.ENTITY_ENDERDRAGON_SHOOT);
		registerSound("ENTITY_ENDEREYE_DEATH", SoundEvents.ENTITY_ENDEREYE_DEATH);
		registerSound("ENTITY_ENDEREYE_LAUNCH", SoundEvents.ENTITY_ENDEREYE_LAUNCH);
		registerSound("ENTITY_ENDERMEN_AMBIENT", SoundEvents.ENTITY_ENDERMEN_AMBIENT);
		registerSound("ENTITY_ENDERMEN_DEATH", SoundEvents.ENTITY_ENDERMEN_DEATH);
		registerSound("ENTITY_ENDERMEN_HURT", SoundEvents.ENTITY_ENDERMEN_HURT);
		registerSound("ENTITY_ENDERMEN_SCREAM", SoundEvents.ENTITY_ENDERMEN_SCREAM);
		registerSound("ENTITY_ENDERMEN_STARE", SoundEvents.ENTITY_ENDERMEN_STARE);
		registerSound("ENTITY_ENDERMEN_TELEPORT", SoundEvents.ENTITY_ENDERMEN_TELEPORT);
		registerSound("ENTITY_ENDERMITE_AMBIENT", SoundEvents.ENTITY_ENDERMITE_AMBIENT);
		registerSound("ENTITY_ENDERMITE_DEATH", SoundEvents.ENTITY_ENDERMITE_DEATH);
		registerSound("ENTITY_ENDERMITE_HURT", SoundEvents.ENTITY_ENDERMITE_HURT);
		registerSound("ENTITY_ENDERMITE_STEP", SoundEvents.ENTITY_ENDERMITE_STEP);
		registerSound("ENTITY_ENDERPEARL_THROW", SoundEvents.ENTITY_ENDERPEARL_THROW);
		registerSound("BLOCK_END_GATEWAY_SPAWN", SoundEvents.BLOCK_END_GATEWAY_SPAWN);
		registerSound("BLOCK_END_PORTAL_FRAME_FILL", SoundEvents.BLOCK_END_PORTAL_FRAME_FILL);
		registerSound("BLOCK_END_PORTAL_SPAWN", SoundEvents.BLOCK_END_PORTAL_SPAWN);
		registerSound("EVOCATION_FANGS_ATTACK", SoundEvents.EVOCATION_FANGS_ATTACK);
		registerSound("ENTITY_EVOCATION_ILLAGER_AMBIENT", SoundEvents.ENTITY_EVOCATION_ILLAGER_AMBIENT);
		registerSound("EVOCATION_ILLAGER_CAST_SPELL", SoundEvents.EVOCATION_ILLAGER_CAST_SPELL);
		registerSound("EVOCATION_ILLAGER_DEATH", SoundEvents.EVOCATION_ILLAGER_DEATH);
		registerSound("ENTITY_EVOCATION_ILLAGER_HURT", SoundEvents.ENTITY_EVOCATION_ILLAGER_HURT);
		registerSound("EVOCATION_ILLAGER_PREPARE_ATTACK", SoundEvents.EVOCATION_ILLAGER_PREPARE_ATTACK);
		registerSound("EVOCATION_ILLAGER_PREPARE_SUMMON", SoundEvents.EVOCATION_ILLAGER_PREPARE_SUMMON);
		registerSound("EVOCATION_ILLAGER_PREPARE_WOLOLO", SoundEvents.EVOCATION_ILLAGER_PREPARE_WOLOLO);
		registerSound("ENTITY_EXPERIENCE_BOTTLE_THROW", SoundEvents.ENTITY_EXPERIENCE_BOTTLE_THROW);
		registerSound("ENTITY_EXPERIENCE_ORB_PICKUP", SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
		registerSound("BLOCK_FENCE_GATE_CLOSE", SoundEvents.BLOCK_FENCE_GATE_CLOSE);
		registerSound("BLOCK_FENCE_GATE_OPEN", SoundEvents.BLOCK_FENCE_GATE_OPEN);
		registerSound("ITEM_FIRECHARGE_USE", SoundEvents.ITEM_FIRECHARGE_USE);
		registerSound("ENTITY_FIREWORK_BLAST", SoundEvents.ENTITY_FIREWORK_BLAST);
		registerSound("ENTITY_FIREWORK_BLAST_FAR", SoundEvents.ENTITY_FIREWORK_BLAST_FAR);
		registerSound("ENTITY_FIREWORK_LARGE_BLAST", SoundEvents.ENTITY_FIREWORK_LARGE_BLAST);
		registerSound("ENTITY_FIREWORK_LARGE_BLAST_FAR", SoundEvents.ENTITY_FIREWORK_LARGE_BLAST_FAR);
		registerSound("ENTITY_FIREWORK_LAUNCH", SoundEvents.ENTITY_FIREWORK_LAUNCH);
		registerSound("ENTITY_FIREWORK_SHOOT", SoundEvents.ENTITY_FIREWORK_SHOOT);
		registerSound("ENTITY_FIREWORK_TWINKLE", SoundEvents.ENTITY_FIREWORK_TWINKLE);
		registerSound("ENTITY_FIREWORK_TWINKLE_FAR", SoundEvents.ENTITY_FIREWORK_TWINKLE_FAR);
		registerSound("BLOCK_FIRE_AMBIENT", SoundEvents.BLOCK_FIRE_AMBIENT);
		registerSound("BLOCK_FIRE_EXTINGUISH", SoundEvents.BLOCK_FIRE_EXTINGUISH);
		registerSound("ITEM_FLINTANDSTEEL_USE", SoundEvents.ITEM_FLINTANDSTEEL_USE);
		registerSound("BLOCK_FURNACE_FIRE_CRACKLE", SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE);
		registerSound("ENTITY_GENERIC_BIG_FALL", SoundEvents.ENTITY_GENERIC_BIG_FALL);
		registerSound("ENTITY_GENERIC_BURN", SoundEvents.ENTITY_GENERIC_BURN);
		registerSound("ENTITY_GENERIC_DEATH", SoundEvents.ENTITY_GENERIC_DEATH);
		registerSound("ENTITY_GENERIC_DRINK", SoundEvents.ENTITY_GENERIC_DRINK);
		registerSound("ENTITY_GENERIC_EAT", SoundEvents.ENTITY_GENERIC_EAT);
		registerSound("ENTITY_GENERIC_EXPLODE", SoundEvents.ENTITY_GENERIC_EXPLODE);
		registerSound("ENTITY_GENERIC_EXTINGUISH_FIRE", SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE);
		registerSound("ENTITY_GENERIC_HURT", SoundEvents.ENTITY_GENERIC_HURT);
		registerSound("ENTITY_GENERIC_SMALL_FALL", SoundEvents.ENTITY_GENERIC_SMALL_FALL);
		registerSound("ENTITY_GENERIC_SPLASH", SoundEvents.ENTITY_GENERIC_SPLASH);
		registerSound("ENTITY_GENERIC_SWIM", SoundEvents.ENTITY_GENERIC_SWIM);
		registerSound("ENTITY_GHAST_AMBIENT", SoundEvents.ENTITY_GHAST_AMBIENT);
		registerSound("ENTITY_GHAST_DEATH", SoundEvents.ENTITY_GHAST_DEATH);
		registerSound("ENTITY_GHAST_HURT", SoundEvents.ENTITY_GHAST_HURT);
		registerSound("ENTITY_GHAST_SCREAM", SoundEvents.ENTITY_GHAST_SCREAM);
		registerSound("ENTITY_GHAST_SHOOT", SoundEvents.ENTITY_GHAST_SHOOT);
		registerSound("ENTITY_GHAST_WARN", SoundEvents.ENTITY_GHAST_WARN);
		registerSound("BLOCK_GLASS_BREAK", SoundEvents.BLOCK_GLASS_BREAK);
		registerSound("BLOCK_GLASS_FALL", SoundEvents.BLOCK_GLASS_FALL);
		registerSound("BLOCK_GLASS_HIT", SoundEvents.BLOCK_GLASS_HIT);
		registerSound("BLOCK_GLASS_PLACE", SoundEvents.BLOCK_GLASS_PLACE);
		registerSound("BLOCK_GLASS_STEP", SoundEvents.BLOCK_GLASS_STEP);
		registerSound("BLOCK_GRASS_BREAK", SoundEvents.BLOCK_GRASS_BREAK);
		registerSound("BLOCK_GRASS_FALL", SoundEvents.BLOCK_GRASS_FALL);
		registerSound("BLOCK_GRASS_HIT", SoundEvents.BLOCK_GRASS_HIT);
		registerSound("BLOCK_GRASS_PLACE", SoundEvents.BLOCK_GRASS_PLACE);
		registerSound("BLOCK_GRASS_STEP", SoundEvents.BLOCK_GRASS_STEP);
		registerSound("BLOCK_GRAVEL_BREAK", SoundEvents.BLOCK_GRAVEL_BREAK);
		registerSound("BLOCK_GRAVEL_FALL", SoundEvents.BLOCK_GRAVEL_FALL);
		registerSound("BLOCK_GRAVEL_HIT", SoundEvents.BLOCK_GRAVEL_HIT);
		registerSound("BLOCK_GRAVEL_PLACE", SoundEvents.BLOCK_GRAVEL_PLACE);
		registerSound("BLOCK_GRAVEL_STEP", SoundEvents.BLOCK_GRAVEL_STEP);
		registerSound("ENTITY_GUARDIAN_AMBIENT", SoundEvents.ENTITY_GUARDIAN_AMBIENT);
		registerSound("ENTITY_GUARDIAN_AMBIENT_LAND", SoundEvents.ENTITY_GUARDIAN_AMBIENT_LAND);
		registerSound("ENTITY_GUARDIAN_ATTACK", SoundEvents.ENTITY_GUARDIAN_ATTACK);
		registerSound("ENTITY_GUARDIAN_DEATH", SoundEvents.ENTITY_GUARDIAN_DEATH);
		registerSound("ENTITY_GUARDIAN_DEATH_LAND", SoundEvents.ENTITY_GUARDIAN_DEATH_LAND);
		registerSound("ENTITY_GUARDIAN_FLOP", SoundEvents.ENTITY_GUARDIAN_FLOP);
		registerSound("ENTITY_GUARDIAN_HURT", SoundEvents.ENTITY_GUARDIAN_HURT);
		registerSound("ENTITY_GUARDIAN_HURT_LAND", SoundEvents.ENTITY_GUARDIAN_HURT_LAND);
		registerSound("ITEM_HOE_TILL", SoundEvents.ITEM_HOE_TILL);
		registerSound("ENTITY_HORSE_AMBIENT", SoundEvents.ENTITY_HORSE_AMBIENT);
		registerSound("ENTITY_HORSE_ANGRY", SoundEvents.ENTITY_HORSE_ANGRY);
		registerSound("ENTITY_HORSE_ARMOR", SoundEvents.ENTITY_HORSE_ARMOR);
		registerSound("ENTITY_HORSE_BREATHE", SoundEvents.ENTITY_HORSE_BREATHE);
		registerSound("ENTITY_HORSE_DEATH", SoundEvents.ENTITY_HORSE_DEATH);
		registerSound("ENTITY_HORSE_EAT", SoundEvents.ENTITY_HORSE_EAT);
		registerSound("ENTITY_HORSE_GALLOP", SoundEvents.ENTITY_HORSE_GALLOP);
		registerSound("ENTITY_HORSE_HURT", SoundEvents.ENTITY_HORSE_HURT);
		registerSound("ENTITY_HORSE_JUMP", SoundEvents.ENTITY_HORSE_JUMP);
		registerSound("ENTITY_HORSE_LAND", SoundEvents.ENTITY_HORSE_LAND);
		registerSound("ENTITY_HORSE_SADDLE", SoundEvents.ENTITY_HORSE_SADDLE);
		registerSound("ENTITY_HORSE_STEP", SoundEvents.ENTITY_HORSE_STEP);
		registerSound("ENTITY_HORSE_STEP_WOOD", SoundEvents.ENTITY_HORSE_STEP_WOOD);
		registerSound("ENTITY_HOSTILE_BIG_FALL", SoundEvents.ENTITY_HOSTILE_BIG_FALL);
		registerSound("ENTITY_HOSTILE_DEATH", SoundEvents.ENTITY_HOSTILE_DEATH);
		registerSound("ENTITY_HOSTILE_HURT", SoundEvents.ENTITY_HOSTILE_HURT);
		registerSound("ENTITY_HOSTILE_SMALL_FALL", SoundEvents.ENTITY_HOSTILE_SMALL_FALL);
		registerSound("ENTITY_HOSTILE_SPLASH", SoundEvents.ENTITY_HOSTILE_SPLASH);
		registerSound("ENTITY_HOSTILE_SWIM", SoundEvents.ENTITY_HOSTILE_SWIM);
		registerSound("ENTITY_HUSK_AMBIENT", SoundEvents.ENTITY_HUSK_AMBIENT);
		registerSound("ENTITY_HUSK_DEATH", SoundEvents.ENTITY_HUSK_DEATH);
		registerSound("ENTITY_HUSK_HURT", SoundEvents.ENTITY_HUSK_HURT);
		registerSound("ENTITY_HUSK_STEP", SoundEvents.ENTITY_HUSK_STEP);
		registerSound("ENTITY_ILLUSION_ILLAGER_AMBIENT", SoundEvents.ENTITY_ILLUSION_ILLAGER_AMBIENT);
		registerSound("ENTITY_ILLAGER_CAST_SPELL", SoundEvents.ENTITY_ILLAGER_CAST_SPELL);
		registerSound("ENTITY_ILLAGER_DEATH", SoundEvents.ENTITY_ILLAGER_DEATH);
		registerSound("ENTITY_ILLUSION_ILLAGER_HURT", SoundEvents.ENTITY_ILLUSION_ILLAGER_HURT);
		registerSound("ENTITY_ILLAGER_MIRROR_MOVE", SoundEvents.ENTITY_ILLAGER_MIRROR_MOVE);
		registerSound("ENTITY_ILLAGER_PREPARE_BLINDNESS", SoundEvents.ENTITY_ILLAGER_PREPARE_BLINDNESS);
		registerSound("ENTITY_ILLAGER_PREPARE_MIRROR", SoundEvents.ENTITY_ILLAGER_PREPARE_MIRROR);
		registerSound("ENTITY_IRONGOLEM_ATTACK", SoundEvents.ENTITY_IRONGOLEM_ATTACK);
		registerSound("ENTITY_IRONGOLEM_DEATH", SoundEvents.ENTITY_IRONGOLEM_DEATH);
		registerSound("ENTITY_IRONGOLEM_HURT", SoundEvents.ENTITY_IRONGOLEM_HURT);
		registerSound("ENTITY_IRONGOLEM_STEP", SoundEvents.ENTITY_IRONGOLEM_STEP);
		registerSound("BLOCK_IRON_DOOR_CLOSE", SoundEvents.BLOCK_IRON_DOOR_CLOSE);
		registerSound("BLOCK_IRON_DOOR_OPEN", SoundEvents.BLOCK_IRON_DOOR_OPEN);
		registerSound("BLOCK_IRON_TRAPDOOR_CLOSE", SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE);
		registerSound("BLOCK_IRON_TRAPDOOR_OPEN", SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN);
		registerSound("ENTITY_ITEMFRAME_ADD_ITEM", SoundEvents.ENTITY_ITEMFRAME_ADD_ITEM);
		registerSound("ENTITY_ITEMFRAME_BREAK", SoundEvents.ENTITY_ITEMFRAME_BREAK);
		registerSound("ENTITY_ITEMFRAME_PLACE", SoundEvents.ENTITY_ITEMFRAME_PLACE);
		registerSound("ENTITY_ITEMFRAME_REMOVE_ITEM", SoundEvents.ENTITY_ITEMFRAME_REMOVE_ITEM);
		registerSound("ENTITY_ITEMFRAME_ROTATE_ITEM", SoundEvents.ENTITY_ITEMFRAME_ROTATE_ITEM);
		registerSound("ENTITY_ITEM_BREAK", SoundEvents.ENTITY_ITEM_BREAK);
		registerSound("ENTITY_ITEM_PICKUP", SoundEvents.ENTITY_ITEM_PICKUP);
		registerSound("BLOCK_LADDER_BREAK", SoundEvents.BLOCK_LADDER_BREAK);
		registerSound("BLOCK_LADDER_FALL", SoundEvents.BLOCK_LADDER_FALL);
		registerSound("BLOCK_LADDER_HIT", SoundEvents.BLOCK_LADDER_HIT);
		registerSound("BLOCK_LADDER_PLACE", SoundEvents.BLOCK_LADDER_PLACE);
		registerSound("BLOCK_LADDER_STEP", SoundEvents.BLOCK_LADDER_STEP);
		registerSound("BLOCK_LAVA_AMBIENT", SoundEvents.BLOCK_LAVA_AMBIENT);
		registerSound("BLOCK_LAVA_EXTINGUISH", SoundEvents.BLOCK_LAVA_EXTINGUISH);
		registerSound("BLOCK_LAVA_POP", SoundEvents.BLOCK_LAVA_POP);
		registerSound("ENTITY_LEASHKNOT_BREAK", SoundEvents.ENTITY_LEASHKNOT_BREAK);
		registerSound("ENTITY_LEASHKNOT_PLACE", SoundEvents.ENTITY_LEASHKNOT_PLACE);
		registerSound("BLOCK_LEVER_CLICK", SoundEvents.BLOCK_LEVER_CLICK);
		registerSound("ENTITY_LIGHTNING_IMPACT", SoundEvents.ENTITY_LIGHTNING_IMPACT);
		registerSound("ENTITY_LIGHTNING_THUNDER", SoundEvents.ENTITY_LIGHTNING_THUNDER);
		registerSound("ENTITY_LINGERINGPOTION_THROW", SoundEvents.ENTITY_LINGERINGPOTION_THROW);
		registerSound("ENTITY_LLAMA_AMBIENT", SoundEvents.ENTITY_LLAMA_AMBIENT);
		registerSound("ENTITY_LLAMA_ANGRY", SoundEvents.ENTITY_LLAMA_ANGRY);
		registerSound("ENTITY_LLAMA_CHEST", SoundEvents.ENTITY_LLAMA_CHEST);
		registerSound("ENTITY_LLAMA_DEATH", SoundEvents.ENTITY_LLAMA_DEATH);
		registerSound("ENTITY_LLAMA_EAT", SoundEvents.ENTITY_LLAMA_EAT);
		registerSound("ENTITY_LLAMA_HURT", SoundEvents.ENTITY_LLAMA_HURT);
		registerSound("ENTITY_LLAMA_SPIT", SoundEvents.ENTITY_LLAMA_SPIT);
		registerSound("ENTITY_LLAMA_STEP", SoundEvents.ENTITY_LLAMA_STEP);
		registerSound("ENTITY_LLAMA_SWAG", SoundEvents.ENTITY_LLAMA_SWAG);
		registerSound("ENTITY_MAGMACUBE_DEATH", SoundEvents.ENTITY_MAGMACUBE_DEATH);
		registerSound("ENTITY_MAGMACUBE_HURT", SoundEvents.ENTITY_MAGMACUBE_HURT);
		registerSound("ENTITY_MAGMACUBE_JUMP", SoundEvents.ENTITY_MAGMACUBE_JUMP);
		registerSound("ENTITY_MAGMACUBE_SQUISH", SoundEvents.ENTITY_MAGMACUBE_SQUISH);
		registerSound("BLOCK_METAL_BREAK", SoundEvents.BLOCK_METAL_BREAK);
		registerSound("BLOCK_METAL_FALL", SoundEvents.BLOCK_METAL_FALL);
		registerSound("BLOCK_METAL_HIT", SoundEvents.BLOCK_METAL_HIT);
		registerSound("BLOCK_METAL_PLACE", SoundEvents.BLOCK_METAL_PLACE);
		registerSound("BLOCK_METAL_PRESSPLATE_CLICK_OFF", SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_OFF);
		registerSound("BLOCK_METAL_PRESSPLATE_CLICK_ON", SoundEvents.BLOCK_METAL_PRESSPLATE_CLICK_ON);
		registerSound("BLOCK_METAL_STEP", SoundEvents.BLOCK_METAL_STEP);
		registerSound("ENTITY_MINECART_INSIDE", SoundEvents.ENTITY_MINECART_INSIDE);
		registerSound("ENTITY_MINECART_RIDING", SoundEvents.ENTITY_MINECART_RIDING);
		registerSound("ENTITY_MOOSHROOM_SHEAR", SoundEvents.ENTITY_MOOSHROOM_SHEAR);
		registerSound("ENTITY_MULE_AMBIENT", SoundEvents.ENTITY_MULE_AMBIENT);
		registerSound("ENTITY_MULE_CHEST", SoundEvents.ENTITY_MULE_CHEST);
		registerSound("ENTITY_MULE_DEATH", SoundEvents.ENTITY_MULE_DEATH);
		registerSound("ENTITY_MULE_HURT", SoundEvents.ENTITY_MULE_HURT);
		registerSound("MUSIC_CREATIVE", SoundEvents.MUSIC_CREATIVE);
		registerSound("MUSIC_CREDITS", SoundEvents.MUSIC_CREDITS);
		registerSound("MUSIC_DRAGON", SoundEvents.MUSIC_DRAGON);
		registerSound("MUSIC_END", SoundEvents.MUSIC_END);
		registerSound("MUSIC_GAME", SoundEvents.MUSIC_GAME);
		registerSound("MUSIC_MENU", SoundEvents.MUSIC_MENU);
		registerSound("MUSIC_NETHER", SoundEvents.MUSIC_NETHER);
		registerSound("BLOCK_NOTE_BASEDRUM", SoundEvents.BLOCK_NOTE_BASEDRUM);
		registerSound("BLOCK_NOTE_BASS", SoundEvents.BLOCK_NOTE_BASS);
		registerSound("BLOCK_NOTE_BELL", SoundEvents.BLOCK_NOTE_BELL);
		registerSound("BLOCK_NOTE_CHIME", SoundEvents.BLOCK_NOTE_CHIME);
		registerSound("BLOCK_NOTE_FLUTE", SoundEvents.BLOCK_NOTE_FLUTE);
		registerSound("BLOCK_NOTE_GUITAR", SoundEvents.BLOCK_NOTE_GUITAR);
		registerSound("BLOCK_NOTE_HARP", SoundEvents.BLOCK_NOTE_HARP);
		registerSound("BLOCK_NOTE_HAT", SoundEvents.BLOCK_NOTE_HAT);
		registerSound("BLOCK_NOTE_PLING", SoundEvents.BLOCK_NOTE_PLING);
		registerSound("BLOCK_NOTE_SNARE", SoundEvents.BLOCK_NOTE_SNARE);
		registerSound("BLOCK_NOTE_XYLOPHONE", SoundEvents.BLOCK_NOTE_XYLOPHONE);
		registerSound("ENTITY_PAINTING_BREAK", SoundEvents.ENTITY_PAINTING_BREAK);
		registerSound("ENTITY_PAINTING_PLACE", SoundEvents.ENTITY_PAINTING_PLACE);
		registerSound("ENTITY_PARROT_AMBIENT", SoundEvents.ENTITY_PARROT_AMBIENT);
		registerSound("ENTITY_PARROT_DEATH", SoundEvents.ENTITY_PARROT_DEATH);
		registerSound("ENTITY_PARROT_EAT", SoundEvents.ENTITY_PARROT_EAT);
		registerSound("ENTITY_PARROT_FLY", SoundEvents.ENTITY_PARROT_FLY);
		registerSound("ENTITY_PARROT_HURT", SoundEvents.ENTITY_PARROT_HURT);
		registerSound("E_PARROT_IM_BLAZE", SoundEvents.E_PARROT_IM_BLAZE);
		registerSound("E_PARROT_IM_CREEPER", SoundEvents.E_PARROT_IM_CREEPER);
		registerSound("E_PARROT_IM_ELDER_GUARDIAN", SoundEvents.E_PARROT_IM_ELDER_GUARDIAN);
		registerSound("E_PARROT_IM_ENDERDRAGON", SoundEvents.E_PARROT_IM_ENDERDRAGON);
		registerSound("E_PARROT_IM_ENDERMAN", SoundEvents.E_PARROT_IM_ENDERMAN);
		registerSound("E_PARROT_IM_ENDERMITE", SoundEvents.E_PARROT_IM_ENDERMITE);
		registerSound("E_PARROT_IM_EVOCATION_ILLAGER", SoundEvents.E_PARROT_IM_EVOCATION_ILLAGER);
		registerSound("E_PARROT_IM_GHAST", SoundEvents.E_PARROT_IM_GHAST);
		registerSound("E_PARROT_IM_HUSK", SoundEvents.E_PARROT_IM_HUSK);
		registerSound("E_PARROT_IM_ILLUSION_ILLAGER", SoundEvents.E_PARROT_IM_ILLUSION_ILLAGER);
		registerSound("E_PARROT_IM_MAGMACUBE", SoundEvents.E_PARROT_IM_MAGMACUBE);
		registerSound("E_PARROT_IM_POLAR_BEAR", SoundEvents.E_PARROT_IM_POLAR_BEAR);
		registerSound("E_PARROT_IM_SHULKER", SoundEvents.E_PARROT_IM_SHULKER);
		registerSound("E_PARROT_IM_SILVERFISH", SoundEvents.E_PARROT_IM_SILVERFISH);
		registerSound("E_PARROT_IM_SKELETON", SoundEvents.E_PARROT_IM_SKELETON);
		registerSound("E_PARROT_IM_SLIME", SoundEvents.E_PARROT_IM_SLIME);
		registerSound("E_PARROT_IM_SPIDER", SoundEvents.E_PARROT_IM_SPIDER);
		registerSound("E_PARROT_IM_STRAY", SoundEvents.E_PARROT_IM_STRAY);
		registerSound("E_PARROT_IM_VEX", SoundEvents.E_PARROT_IM_VEX);
		registerSound("E_PARROT_IM_VINDICATION_ILLAGER", SoundEvents.E_PARROT_IM_VINDICATION_ILLAGER);
		registerSound("E_PARROT_IM_WITCH", SoundEvents.E_PARROT_IM_WITCH);
		registerSound("E_PARROT_IM_WITHER", SoundEvents.E_PARROT_IM_WITHER);
		registerSound("E_PARROT_IM_WITHER_SKELETON", SoundEvents.E_PARROT_IM_WITHER_SKELETON);
		registerSound("E_PARROT_IM_WOLF", SoundEvents.E_PARROT_IM_WOLF);
		registerSound("E_PARROT_IM_ZOMBIE", SoundEvents.E_PARROT_IM_ZOMBIE);
		registerSound("E_PARROT_IM_ZOMBIE_PIGMAN", SoundEvents.E_PARROT_IM_ZOMBIE_PIGMAN);
		registerSound("E_PARROT_IM_ZOMBIE_VILLAGER", SoundEvents.E_PARROT_IM_ZOMBIE_VILLAGER);
		registerSound("ENTITY_PARROT_STEP", SoundEvents.ENTITY_PARROT_STEP);
		registerSound("ENTITY_PIG_AMBIENT", SoundEvents.ENTITY_PIG_AMBIENT);
		registerSound("ENTITY_PIG_DEATH", SoundEvents.ENTITY_PIG_DEATH);
		registerSound("ENTITY_PIG_HURT", SoundEvents.ENTITY_PIG_HURT);
		registerSound("ENTITY_PIG_SADDLE", SoundEvents.ENTITY_PIG_SADDLE);
		registerSound("ENTITY_PIG_STEP", SoundEvents.ENTITY_PIG_STEP);
		registerSound("BLOCK_PISTON_CONTRACT", SoundEvents.BLOCK_PISTON_CONTRACT);
		registerSound("BLOCK_PISTON_EXTEND", SoundEvents.BLOCK_PISTON_EXTEND);
		registerSound("ENTITY_PLAYER_ATTACK_CRIT", SoundEvents.ENTITY_PLAYER_ATTACK_CRIT);
		registerSound("ENTITY_PLAYER_ATTACK_KNOCKBACK", SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK);
		registerSound("ENTITY_PLAYER_ATTACK_NODAMAGE", SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE);
		registerSound("ENTITY_PLAYER_ATTACK_STRONG", SoundEvents.ENTITY_PLAYER_ATTACK_STRONG);
		registerSound("ENTITY_PLAYER_ATTACK_SWEEP", SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP);
		registerSound("ENTITY_PLAYER_ATTACK_WEAK", SoundEvents.ENTITY_PLAYER_ATTACK_WEAK);
		registerSound("ENTITY_PLAYER_BIG_FALL", SoundEvents.ENTITY_PLAYER_BIG_FALL);
		registerSound("ENTITY_PLAYER_BREATH", SoundEvents.ENTITY_PLAYER_BREATH);
		registerSound("ENTITY_PLAYER_BURP", SoundEvents.ENTITY_PLAYER_BURP);
		registerSound("ENTITY_PLAYER_DEATH", SoundEvents.ENTITY_PLAYER_DEATH);
		registerSound("ENTITY_PLAYER_HURT", SoundEvents.ENTITY_PLAYER_HURT);
		registerSound("ENTITY_PLAYER_HURT_DROWN", SoundEvents.ENTITY_PLAYER_HURT_DROWN);
		registerSound("ENTITY_PLAYER_HURT_ON_FIRE", SoundEvents.ENTITY_PLAYER_HURT_ON_FIRE);
		registerSound("ENTITY_PLAYER_LEVELUP", SoundEvents.ENTITY_PLAYER_LEVELUP);
		registerSound("ENTITY_PLAYER_SMALL_FALL", SoundEvents.ENTITY_PLAYER_SMALL_FALL);
		registerSound("ENTITY_PLAYER_SPLASH", SoundEvents.ENTITY_PLAYER_SPLASH);
		registerSound("ENTITY_PLAYER_SWIM", SoundEvents.ENTITY_PLAYER_SWIM);
		registerSound("ENTITY_POLAR_BEAR_AMBIENT", SoundEvents.ENTITY_POLAR_BEAR_AMBIENT);
		registerSound("ENTITY_POLAR_BEAR_BABY_AMBIENT", SoundEvents.ENTITY_POLAR_BEAR_BABY_AMBIENT);
		registerSound("ENTITY_POLAR_BEAR_DEATH", SoundEvents.ENTITY_POLAR_BEAR_DEATH);
		registerSound("ENTITY_POLAR_BEAR_HURT", SoundEvents.ENTITY_POLAR_BEAR_HURT);
		registerSound("ENTITY_POLAR_BEAR_STEP", SoundEvents.ENTITY_POLAR_BEAR_STEP);
		registerSound("ENTITY_POLAR_BEAR_WARNING", SoundEvents.ENTITY_POLAR_BEAR_WARNING);
		registerSound("BLOCK_PORTAL_AMBIENT", SoundEvents.BLOCK_PORTAL_AMBIENT);
		registerSound("BLOCK_PORTAL_TRAVEL", SoundEvents.BLOCK_PORTAL_TRAVEL);
		registerSound("BLOCK_PORTAL_TRIGGER", SoundEvents.BLOCK_PORTAL_TRIGGER);
		registerSound("ENTITY_RABBIT_AMBIENT", SoundEvents.ENTITY_RABBIT_AMBIENT);
		registerSound("ENTITY_RABBIT_ATTACK", SoundEvents.ENTITY_RABBIT_ATTACK);
		registerSound("ENTITY_RABBIT_DEATH", SoundEvents.ENTITY_RABBIT_DEATH);
		registerSound("ENTITY_RABBIT_HURT", SoundEvents.ENTITY_RABBIT_HURT);
		registerSound("ENTITY_RABBIT_JUMP", SoundEvents.ENTITY_RABBIT_JUMP);
		registerSound("RECORD_11", SoundEvents.RECORD_11);
		registerSound("RECORD_13", SoundEvents.RECORD_13);
		registerSound("RECORD_BLOCKS", SoundEvents.RECORD_BLOCKS);
		registerSound("RECORD_CAT", SoundEvents.RECORD_CAT);
		registerSound("RECORD_CHIRP", SoundEvents.RECORD_CHIRP);
		registerSound("RECORD_FAR", SoundEvents.RECORD_FAR);
		registerSound("RECORD_MALL", SoundEvents.RECORD_MALL);
		registerSound("RECORD_MELLOHI", SoundEvents.RECORD_MELLOHI);
		registerSound("RECORD_STAL", SoundEvents.RECORD_STAL);
		registerSound("RECORD_STRAD", SoundEvents.RECORD_STRAD);
		registerSound("RECORD_WAIT", SoundEvents.RECORD_WAIT);
		registerSound("RECORD_WARD", SoundEvents.RECORD_WARD);
		registerSound("BLOCK_REDSTONE_TORCH_BURNOUT", SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT);
		registerSound("BLOCK_SAND_BREAK", SoundEvents.BLOCK_SAND_BREAK);
		registerSound("BLOCK_SAND_FALL", SoundEvents.BLOCK_SAND_FALL);
		registerSound("BLOCK_SAND_HIT", SoundEvents.BLOCK_SAND_HIT);
		registerSound("BLOCK_SAND_PLACE", SoundEvents.BLOCK_SAND_PLACE);
		registerSound("BLOCK_SAND_STEP", SoundEvents.BLOCK_SAND_STEP);
		registerSound("ENTITY_SHEEP_AMBIENT", SoundEvents.ENTITY_SHEEP_AMBIENT);
		registerSound("ENTITY_SHEEP_DEATH", SoundEvents.ENTITY_SHEEP_DEATH);
		registerSound("ENTITY_SHEEP_HURT", SoundEvents.ENTITY_SHEEP_HURT);
		registerSound("ENTITY_SHEEP_SHEAR", SoundEvents.ENTITY_SHEEP_SHEAR);
		registerSound("ENTITY_SHEEP_STEP", SoundEvents.ENTITY_SHEEP_STEP);
		registerSound("ITEM_SHIELD_BLOCK", SoundEvents.ITEM_SHIELD_BLOCK);
		registerSound("ITEM_SHIELD_BREAK", SoundEvents.ITEM_SHIELD_BREAK);
		registerSound("ITEM_SHOVEL_FLATTEN", SoundEvents.ITEM_SHOVEL_FLATTEN);
		registerSound("ENTITY_SHULKER_AMBIENT", SoundEvents.ENTITY_SHULKER_AMBIENT);
		registerSound("BLOCK_SHULKER_BOX_CLOSE", SoundEvents.BLOCK_SHULKER_BOX_CLOSE);
		registerSound("BLOCK_SHULKER_BOX_OPEN", SoundEvents.BLOCK_SHULKER_BOX_OPEN);
		registerSound("ENTITY_SHULKER_BULLET_HIT", SoundEvents.ENTITY_SHULKER_BULLET_HIT);
		registerSound("ENTITY_SHULKER_BULLET_HURT", SoundEvents.ENTITY_SHULKER_BULLET_HURT);
		registerSound("ENTITY_SHULKER_CLOSE", SoundEvents.ENTITY_SHULKER_CLOSE);
		registerSound("ENTITY_SHULKER_DEATH", SoundEvents.ENTITY_SHULKER_DEATH);
		registerSound("ENTITY_SHULKER_HURT", SoundEvents.ENTITY_SHULKER_HURT);
		registerSound("ENTITY_SHULKER_HURT_CLOSED", SoundEvents.ENTITY_SHULKER_HURT_CLOSED);
		registerSound("ENTITY_SHULKER_OPEN", SoundEvents.ENTITY_SHULKER_OPEN);
		registerSound("ENTITY_SHULKER_SHOOT", SoundEvents.ENTITY_SHULKER_SHOOT);
		registerSound("ENTITY_SHULKER_TELEPORT", SoundEvents.ENTITY_SHULKER_TELEPORT);
		registerSound("ENTITY_SILVERFISH_AMBIENT", SoundEvents.ENTITY_SILVERFISH_AMBIENT);
		registerSound("ENTITY_SILVERFISH_DEATH", SoundEvents.ENTITY_SILVERFISH_DEATH);
		registerSound("ENTITY_SILVERFISH_HURT", SoundEvents.ENTITY_SILVERFISH_HURT);
		registerSound("ENTITY_SILVERFISH_STEP", SoundEvents.ENTITY_SILVERFISH_STEP);
		registerSound("ENTITY_SKELETON_AMBIENT", SoundEvents.ENTITY_SKELETON_AMBIENT);
		registerSound("ENTITY_SKELETON_DEATH", SoundEvents.ENTITY_SKELETON_DEATH);
		registerSound("ENTITY_SKELETON_HORSE_AMBIENT", SoundEvents.ENTITY_SKELETON_HORSE_AMBIENT);
		registerSound("ENTITY_SKELETON_HORSE_DEATH", SoundEvents.ENTITY_SKELETON_HORSE_DEATH);
		registerSound("ENTITY_SKELETON_HORSE_HURT", SoundEvents.ENTITY_SKELETON_HORSE_HURT);
		registerSound("ENTITY_SKELETON_HURT", SoundEvents.ENTITY_SKELETON_HURT);
		registerSound("ENTITY_SKELETON_SHOOT", SoundEvents.ENTITY_SKELETON_SHOOT);
		registerSound("ENTITY_SKELETON_STEP", SoundEvents.ENTITY_SKELETON_STEP);
		registerSound("ENTITY_SLIME_ATTACK", SoundEvents.ENTITY_SLIME_ATTACK);
		registerSound("BLOCK_SLIME_BREAK", SoundEvents.BLOCK_SLIME_BREAK);
		registerSound("ENTITY_SLIME_DEATH", SoundEvents.ENTITY_SLIME_DEATH);
		registerSound("BLOCK_SLIME_FALL", SoundEvents.BLOCK_SLIME_FALL);
		registerSound("BLOCK_SLIME_HIT", SoundEvents.BLOCK_SLIME_HIT);
		registerSound("ENTITY_SLIME_HURT", SoundEvents.ENTITY_SLIME_HURT);
		registerSound("ENTITY_SLIME_JUMP", SoundEvents.ENTITY_SLIME_JUMP);
		registerSound("BLOCK_SLIME_PLACE", SoundEvents.BLOCK_SLIME_PLACE);
		registerSound("ENTITY_SLIME_SQUISH", SoundEvents.ENTITY_SLIME_SQUISH);
		registerSound("BLOCK_SLIME_STEP", SoundEvents.BLOCK_SLIME_STEP);
		registerSound("ENTITY_SMALL_MAGMACUBE_DEATH", SoundEvents.ENTITY_SMALL_MAGMACUBE_DEATH);
		registerSound("ENTITY_SMALL_MAGMACUBE_HURT", SoundEvents.ENTITY_SMALL_MAGMACUBE_HURT);
		registerSound("ENTITY_SMALL_MAGMACUBE_SQUISH", SoundEvents.ENTITY_SMALL_MAGMACUBE_SQUISH);
		registerSound("ENTITY_SMALL_SLIME_DEATH", SoundEvents.ENTITY_SMALL_SLIME_DEATH);
		registerSound("ENTITY_SMALL_SLIME_HURT", SoundEvents.ENTITY_SMALL_SLIME_HURT);
		registerSound("ENTITY_SMALL_SLIME_JUMP", SoundEvents.ENTITY_SMALL_SLIME_JUMP);
		registerSound("ENTITY_SMALL_SLIME_SQUISH", SoundEvents.ENTITY_SMALL_SLIME_SQUISH);
		registerSound("ENTITY_SNOWBALL_THROW", SoundEvents.ENTITY_SNOWBALL_THROW);
		registerSound("ENTITY_SNOWMAN_AMBIENT", SoundEvents.ENTITY_SNOWMAN_AMBIENT);
		registerSound("ENTITY_SNOWMAN_DEATH", SoundEvents.ENTITY_SNOWMAN_DEATH);
		registerSound("ENTITY_SNOWMAN_HURT", SoundEvents.ENTITY_SNOWMAN_HURT);
		registerSound("ENTITY_SNOWMAN_SHOOT", SoundEvents.ENTITY_SNOWMAN_SHOOT);
		registerSound("BLOCK_SNOW_BREAK", SoundEvents.BLOCK_SNOW_BREAK);
		registerSound("BLOCK_SNOW_FALL", SoundEvents.BLOCK_SNOW_FALL);
		registerSound("BLOCK_SNOW_HIT", SoundEvents.BLOCK_SNOW_HIT);
		registerSound("BLOCK_SNOW_PLACE", SoundEvents.BLOCK_SNOW_PLACE);
		registerSound("BLOCK_SNOW_STEP", SoundEvents.BLOCK_SNOW_STEP);
		registerSound("ENTITY_SPIDER_AMBIENT", SoundEvents.ENTITY_SPIDER_AMBIENT);
		registerSound("ENTITY_SPIDER_DEATH", SoundEvents.ENTITY_SPIDER_DEATH);
		registerSound("ENTITY_SPIDER_HURT", SoundEvents.ENTITY_SPIDER_HURT);
		registerSound("ENTITY_SPIDER_STEP", SoundEvents.ENTITY_SPIDER_STEP);
		registerSound("ENTITY_SPLASH_POTION_BREAK", SoundEvents.ENTITY_SPLASH_POTION_BREAK);
		registerSound("ENTITY_SPLASH_POTION_THROW", SoundEvents.ENTITY_SPLASH_POTION_THROW);
		registerSound("ENTITY_SQUID_AMBIENT", SoundEvents.ENTITY_SQUID_AMBIENT);
		registerSound("ENTITY_SQUID_DEATH", SoundEvents.ENTITY_SQUID_DEATH);
		registerSound("ENTITY_SQUID_HURT", SoundEvents.ENTITY_SQUID_HURT);
		registerSound("BLOCK_STONE_BREAK", SoundEvents.BLOCK_STONE_BREAK);
		registerSound("BLOCK_STONE_BUTTON_CLICK_OFF", SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF);
		registerSound("BLOCK_STONE_BUTTON_CLICK_ON", SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON);
		registerSound("BLOCK_STONE_FALL", SoundEvents.BLOCK_STONE_FALL);
		registerSound("BLOCK_STONE_HIT", SoundEvents.BLOCK_STONE_HIT);
		registerSound("BLOCK_STONE_PLACE", SoundEvents.BLOCK_STONE_PLACE);
		registerSound("BLOCK_STONE_PRESSPLATE_CLICK_OFF", SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF);
		registerSound("BLOCK_STONE_PRESSPLATE_CLICK_ON", SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON);
		registerSound("BLOCK_STONE_STEP", SoundEvents.BLOCK_STONE_STEP);
		registerSound("ENTITY_STRAY_AMBIENT", SoundEvents.ENTITY_STRAY_AMBIENT);
		registerSound("ENTITY_STRAY_DEATH", SoundEvents.ENTITY_STRAY_DEATH);
		registerSound("ENTITY_STRAY_HURT", SoundEvents.ENTITY_STRAY_HURT);
		registerSound("ENTITY_STRAY_STEP", SoundEvents.ENTITY_STRAY_STEP);
		registerSound("ENCHANT_THORNS_HIT", SoundEvents.ENCHANT_THORNS_HIT);
		registerSound("ENTITY_TNT_PRIMED", SoundEvents.ENTITY_TNT_PRIMED);
		registerSound("ITEM_TOTEM_USE", SoundEvents.ITEM_TOTEM_USE);
		registerSound("BLOCK_TRIPWIRE_ATTACH", SoundEvents.BLOCK_TRIPWIRE_ATTACH);
		registerSound("BLOCK_TRIPWIRE_CLICK_OFF", SoundEvents.BLOCK_TRIPWIRE_CLICK_OFF);
		registerSound("BLOCK_TRIPWIRE_CLICK_ON", SoundEvents.BLOCK_TRIPWIRE_CLICK_ON);
		registerSound("BLOCK_TRIPWIRE_DETACH", SoundEvents.BLOCK_TRIPWIRE_DETACH);
		registerSound("UI_BUTTON_CLICK", SoundEvents.UI_BUTTON_CLICK);
		registerSound("UI_TOAST_IN", SoundEvents.UI_TOAST_IN);
		registerSound("UI_TOAST_OUT", SoundEvents.UI_TOAST_OUT);
		registerSound("UI_TOAST_CHALLENGE_COMPLETE", SoundEvents.UI_TOAST_CHALLENGE_COMPLETE);
		registerSound("ENTITY_VEX_AMBIENT", SoundEvents.ENTITY_VEX_AMBIENT);
		registerSound("ENTITY_VEX_CHARGE", SoundEvents.ENTITY_VEX_CHARGE);
		registerSound("ENTITY_VEX_DEATH", SoundEvents.ENTITY_VEX_DEATH);
		registerSound("ENTITY_VEX_HURT", SoundEvents.ENTITY_VEX_HURT);
		registerSound("ENTITY_VILLAGER_AMBIENT", SoundEvents.ENTITY_VILLAGER_AMBIENT);
		registerSound("ENTITY_VILLAGER_DEATH", SoundEvents.ENTITY_VILLAGER_DEATH);
		registerSound("ENTITY_VILLAGER_HURT", SoundEvents.ENTITY_VILLAGER_HURT);
		registerSound("ENTITY_VILLAGER_NO", SoundEvents.ENTITY_VILLAGER_NO);
		registerSound("ENTITY_VILLAGER_TRADING", SoundEvents.ENTITY_VILLAGER_TRADING);
		registerSound("ENTITY_VILLAGER_YES", SoundEvents.ENTITY_VILLAGER_YES);
		registerSound("VINDICATION_ILLAGER_AMBIENT", SoundEvents.VINDICATION_ILLAGER_AMBIENT);
		registerSound("VINDICATION_ILLAGER_DEATH", SoundEvents.VINDICATION_ILLAGER_DEATH);
		registerSound("ENTITY_VINDICATION_ILLAGER_HURT", SoundEvents.ENTITY_VINDICATION_ILLAGER_HURT);
		registerSound("BLOCK_WATERLILY_PLACE", SoundEvents.BLOCK_WATERLILY_PLACE);
		registerSound("BLOCK_WATER_AMBIENT", SoundEvents.BLOCK_WATER_AMBIENT);
		registerSound("WEATHER_RAIN", SoundEvents.WEATHER_RAIN);
		registerSound("WEATHER_RAIN_ABOVE", SoundEvents.WEATHER_RAIN_ABOVE);
		registerSound("ENTITY_WITCH_AMBIENT", SoundEvents.ENTITY_WITCH_AMBIENT);
		registerSound("ENTITY_WITCH_DEATH", SoundEvents.ENTITY_WITCH_DEATH);
		registerSound("ENTITY_WITCH_DRINK", SoundEvents.ENTITY_WITCH_DRINK);
		registerSound("ENTITY_WITCH_HURT", SoundEvents.ENTITY_WITCH_HURT);
		registerSound("ENTITY_WITCH_THROW", SoundEvents.ENTITY_WITCH_THROW);
		registerSound("ENTITY_WITHER_AMBIENT", SoundEvents.ENTITY_WITHER_AMBIENT);
		registerSound("ENTITY_WITHER_BREAK_BLOCK", SoundEvents.ENTITY_WITHER_BREAK_BLOCK);
		registerSound("ENTITY_WITHER_DEATH", SoundEvents.ENTITY_WITHER_DEATH);
		registerSound("ENTITY_WITHER_HURT", SoundEvents.ENTITY_WITHER_HURT);
		registerSound("ENTITY_WITHER_SHOOT", SoundEvents.ENTITY_WITHER_SHOOT);
		registerSound("ENTITY_WITHER_SKELETON_AMBIENT", SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT);
		registerSound("ENTITY_WITHER_SKELETON_DEATH", SoundEvents.ENTITY_WITHER_SKELETON_DEATH);
		registerSound("ENTITY_WITHER_SKELETON_HURT", SoundEvents.ENTITY_WITHER_SKELETON_HURT);
		registerSound("ENTITY_WITHER_SKELETON_STEP", SoundEvents.ENTITY_WITHER_SKELETON_STEP);
		registerSound("ENTITY_WITHER_SPAWN", SoundEvents.ENTITY_WITHER_SPAWN);
		registerSound("ENTITY_WOLF_AMBIENT", SoundEvents.ENTITY_WOLF_AMBIENT);
		registerSound("ENTITY_WOLF_DEATH", SoundEvents.ENTITY_WOLF_DEATH);
		registerSound("ENTITY_WOLF_GROWL", SoundEvents.ENTITY_WOLF_GROWL);
		registerSound("ENTITY_WOLF_HOWL", SoundEvents.ENTITY_WOLF_HOWL);
		registerSound("ENTITY_WOLF_HURT", SoundEvents.ENTITY_WOLF_HURT);
		registerSound("ENTITY_WOLF_PANT", SoundEvents.ENTITY_WOLF_PANT);
		registerSound("ENTITY_WOLF_SHAKE", SoundEvents.ENTITY_WOLF_SHAKE);
		registerSound("ENTITY_WOLF_STEP", SoundEvents.ENTITY_WOLF_STEP);
		registerSound("ENTITY_WOLF_WHINE", SoundEvents.ENTITY_WOLF_WHINE);
		registerSound("BLOCK_WOODEN_DOOR_CLOSE", SoundEvents.BLOCK_WOODEN_DOOR_CLOSE);
		registerSound("BLOCK_WOODEN_DOOR_OPEN", SoundEvents.BLOCK_WOODEN_DOOR_OPEN);
		registerSound("BLOCK_WOODEN_TRAPDOOR_CLOSE", SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE);
		registerSound("BLOCK_WOODEN_TRAPDOOR_OPEN", SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN);
		registerSound("BLOCK_WOOD_BREAK", SoundEvents.BLOCK_WOOD_BREAK);
		registerSound("BLOCK_WOOD_BUTTON_CLICK_OFF", SoundEvents.BLOCK_WOOD_BUTTON_CLICK_OFF);
		registerSound("BLOCK_WOOD_BUTTON_CLICK_ON", SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON);
		registerSound("BLOCK_WOOD_FALL", SoundEvents.BLOCK_WOOD_FALL);
		registerSound("BLOCK_WOOD_HIT", SoundEvents.BLOCK_WOOD_HIT);
		registerSound("BLOCK_WOOD_PLACE", SoundEvents.BLOCK_WOOD_PLACE);
		registerSound("BLOCK_WOOD_PRESSPLATE_CLICK_OFF", SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_OFF);
		registerSound("BLOCK_WOOD_PRESSPLATE_CLICK_ON", SoundEvents.BLOCK_WOOD_PRESSPLATE_CLICK_ON);
		registerSound("BLOCK_WOOD_STEP", SoundEvents.BLOCK_WOOD_STEP);
		registerSound("ENTITY_ZOMBIE_AMBIENT", SoundEvents.ENTITY_ZOMBIE_AMBIENT);
		registerSound("ENTITY_ZOMBIE_ATTACK_DOOR_WOOD", SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD);
		registerSound("ENTITY_ZOMBIE_ATTACK_IRON_DOOR", SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR);
		registerSound("ENTITY_ZOMBIE_BREAK_DOOR_WOOD", SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD);
		registerSound("ENTITY_ZOMBIE_DEATH", SoundEvents.ENTITY_ZOMBIE_DEATH);
		registerSound("ENTITY_ZOMBIE_HORSE_AMBIENT", SoundEvents.ENTITY_ZOMBIE_HORSE_AMBIENT);
		registerSound("ENTITY_ZOMBIE_HORSE_DEATH", SoundEvents.ENTITY_ZOMBIE_HORSE_DEATH);
		registerSound("ENTITY_ZOMBIE_HORSE_HURT", SoundEvents.ENTITY_ZOMBIE_HORSE_HURT);
		registerSound("ENTITY_ZOMBIE_HURT", SoundEvents.ENTITY_ZOMBIE_HURT);
		registerSound("ENTITY_ZOMBIE_INFECT", SoundEvents.ENTITY_ZOMBIE_INFECT);
		registerSound("ENTITY_ZOMBIE_PIG_AMBIENT", SoundEvents.ENTITY_ZOMBIE_PIG_AMBIENT);
		registerSound("ENTITY_ZOMBIE_PIG_ANGRY", SoundEvents.ENTITY_ZOMBIE_PIG_ANGRY);
		registerSound("ENTITY_ZOMBIE_PIG_DEATH", SoundEvents.ENTITY_ZOMBIE_PIG_DEATH);
		registerSound("ENTITY_ZOMBIE_PIG_HURT", SoundEvents.ENTITY_ZOMBIE_PIG_HURT);
		registerSound("ENTITY_ZOMBIE_STEP", SoundEvents.ENTITY_ZOMBIE_STEP);
		registerSound("ENTITY_ZOMBIE_VILLAGER_AMBIENT", SoundEvents.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
		registerSound("ENTITY_ZOMBIE_VILLAGER_CONVERTED", SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED);
		registerSound("ENTITY_ZOMBIE_VILLAGER_CURE", SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE);
		registerSound("ENTITY_ZOMBIE_VILLAGER_DEATH", SoundEvents.ENTITY_ZOMBIE_VILLAGER_DEATH);
		registerSound("ENTITY_ZOMBIE_VILLAGER_HURT", SoundEvents.ENTITY_ZOMBIE_VILLAGER_HURT);
		registerSound("ENTITY_ZOMBIE_VILLAGER_STEP", SoundEvents.ENTITY_ZOMBIE_VILLAGER_STEP);

	}

	public void registerEffect(String name, Potion effect) {
		effects.put(name, effect);
	}

	public Potion getEffect(String effect) {
		return effects.get(effect);
	}

	private void registerVanillaEffects() {
		registerEffect("SPEED", MobEffects.SPEED);
		registerEffect("SLOWNESS", MobEffects.SLOWNESS);
		registerEffect("HASTE", MobEffects.HASTE);
		registerEffect("MINING_FATIGUE", MobEffects.MINING_FATIGUE);
		registerEffect("STRENGTH", MobEffects.STRENGTH);
		registerEffect("INSTANT_HEALTH", MobEffects.INSTANT_HEALTH);
		registerEffect("INSTANT_DAMAGE", MobEffects.INSTANT_DAMAGE);
		registerEffect("JUMP_BOOST", MobEffects.JUMP_BOOST);
		registerEffect("NAUSEA", MobEffects.NAUSEA);
		registerEffect("REGENERATION", MobEffects.REGENERATION);
		registerEffect("RESISTANCE", MobEffects.RESISTANCE);
		registerEffect("FIRE_RESISTANCE", MobEffects.FIRE_RESISTANCE);
		registerEffect("WATER_BREATHING", MobEffects.WATER_BREATHING);
		registerEffect("INVISIBILITY", MobEffects.INVISIBILITY);
		registerEffect("BLINDNESS", MobEffects.BLINDNESS);
		registerEffect("NIGHT_VISION", MobEffects.NIGHT_VISION);
		registerEffect("HUNGER", MobEffects.HUNGER);
		registerEffect("WEAKNESS", MobEffects.WEAKNESS);
		registerEffect("POISON", MobEffects.POISON);
		registerEffect("WITHER", MobEffects.WITHER);
		registerEffect("HEALTH_BOOST", MobEffects.HEALTH_BOOST);
		registerEffect("ABSORPTION", MobEffects.ABSORPTION);
		registerEffect("SATURATION", MobEffects.SATURATION);
		registerEffect("GLOWING", MobEffects.GLOWING);
		registerEffect("LEVITATION", MobEffects.LEVITATION);
		registerEffect("LUCK", MobEffects.LUCK);
		registerEffect("UNLUCK", MobEffects.UNLUCK);
	}

	public void registerParticleType(String key, EnumParticleTypes value) {
		particleTypes.put(key, value);
	}

	public EnumParticleTypes getParticleType(String key) {
		return particleTypes.get(key);
	}

	private void registerVanillaParticleTypes() {
		Set<String> vanillaNames = EnumParticleTypes.getParticleNames();
		for (String name : vanillaNames) {
			registerParticleType(name.toUpperCase(), EnumParticleTypes.getByName(name));
		}
	}

	public void registerSoundCategory(String key, SoundCategory value) {
		soundCategories.put(key, value);
	}

	public SoundCategory getSoundCategory(String key) {
		return soundCategories.get(key);
	}

	private void registerVanillaSoundCategories() {
		Set<String> vanillaNames = SoundCategory.getSoundCategoryNames();
		for (String name : vanillaNames) {
			registerSoundCategory(name.toUpperCase(), SoundCategory.getByName(name));
		}
	}

	public Class<? extends TreeGenAbstract> getTreeGenerator(String name) {
		return treeGenerators.get(name);
	}

	public void registerTreeGenerator(String name, Class<? extends TreeGenAbstract> treeGenerator) {
		treeGenerators.put(name, treeGenerator);
	}

	private void registerDefaultTreeGenerators() {
		registerTreeGenerator("oak", TreeGenOak.class);
		registerTreeGenerator("birch", TreeGenOak.class);
		registerTreeGenerator("spruce", TreeGenPine.class);
	}
}
