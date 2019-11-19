package us.timinc.mcutil;

import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class PlaintextHelp {
	public static ItemStack getHeldItem(EntityPlayer player, String hand) {
		return player.getHeldItem(EnumHand.valueOf(String.format("%s_HAND", hand.toUpperCase())));
	}
}
