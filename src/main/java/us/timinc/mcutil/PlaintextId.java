package us.timinc.mcutil;

import java.util.regex.*;

import net.minecraft.block.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraftforge.oredict.*;
import us.timinc.jsonifycraft.*;

/**
 * A util class for turning game objects into ID strings.
 *
 * @author Tim
 *
 */
public class PlaintextId {
	/**
	 * Gets a block ID from a block state.
	 *
	 * @param blockState
	 *            The block state
	 * @return The block ID
	 */
	public static String getBlockId(IBlockState blockState) {
		Block block = blockState.getBlock();
		return block.getRegistryName().toString() + ":" + block.getMetaFromState(blockState);
	}

	/**
	 * Gets an item ID from an item stack.
	 *
	 * @param itemStack
	 *            The item stack
	 * @return The item ID
	 */
	public static String getItemId(ItemStack itemStack) {
		Item item = itemStack.getItem();
		return item.getRegistryName().toString() + ":" + itemStack.getMetadata();
	}

	/**
	 * Creates an item stack from the given item ID.
	 *
	 * @param itemId
	 *            The item ID.
	 * @param count
	 *            The number of items.
	 * @return The item stack.
	 */
	public static ItemStack createItemStackFrom(String itemId, int count) {
		JsonifyCraft.log("Attempting to create item stack from %s.", itemId);
		String[] splitDropItemId = fixMetadata(itemId, IdType.OBJ).split(":");
		ItemStack newItemStack = new ItemStack(Item.getByNameOrId(splitDropItemId[0] + ":" + splitDropItemId[1]),
				count);
		newItemStack.setItemDamage(Integer.parseInt(splitDropItemId[2]));
		return newItemStack;
	}

	/**
	 * Gets a block state from the given block ID.
	 *
	 * @param blockId
	 *            The block ID.
	 * @return The block state.
	 */
	public static IBlockState getBlockStateFrom(String blockId) {
		blockId = fixMetadata(blockId, IdType.OBJ);
		String[] splitIntoBlockId = blockId.split(":");
		return Block.getBlockFromName(splitIntoBlockId[0] + ":" + splitIntoBlockId[1])
				.getStateFromMeta(Integer.parseInt(splitIntoBlockId[2]));
	}

	/**
	 * Determines whether two item IDs match. Does not take meta into account if
	 * the item stack is damageable.
	 *
	 * @param itemId1
	 *            The first item ID.
	 * @param itemId2
	 *            The second item ID.
	 * @return Whether the two item IDs match.
	 */
	public static boolean itemIdsMatch(String itemId1, String itemId2) {
		if (createItemStackFrom(itemId1, 1).isItemStackDamageable()) {
			String[] split1 = itemId1.split(":");
			String[] split2 = itemId2.split(":");
			return split1[0].equals(split2[0]) && split1[1].equals(split2[1]);
		} else
			return itemId1.equals(itemId2);
	}

	/**
	 * Determines whether or not the game object id matches the recipe object
	 * template.
	 *
	 * @param recipeObject
	 *            The recipe template.
	 * @param gameObject
	 *            The game object id.
	 *
	 * @return Whether or not they match.
	 */
	public static boolean matches(String recipeObject, String gameObject) {
		recipeObject = fixMetadata(recipeObject, IdType.CHECK);
		String fixedRecipeObject = recipeObject.replaceAll("\\*", ".*");
		if (!gameObject.equals("minecraft:air:0") && recipeObject.startsWith("ore:")) {
			int[] x = OreDictionary.getOreIDs(PlaintextId.createItemStackFrom(gameObject, 1));
			for (int i : x) {
				if (Pattern.matches(fixedRecipeObject, "ore:" + OreDictionary.getOreName(i) + ":0"))
					return true;
			}
			return false;
		}
		return Pattern.matches(fixedRecipeObject, gameObject);
	}

	public enum IdType {
		CHECK, OBJ
	};

	public static String fixMetadata(String itemId, IdType idType) {
		String[] splitMetadata = itemId.split(":");
		if (splitMetadata.length == 2) {
			switch (idType) {
			case CHECK:
				return itemId + ":*";
			case OBJ:
				return itemId + ":0";
			default:
				return itemId;
			}
		}
		return itemId;
	}
}
