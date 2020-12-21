package com.dre.brewery.integration;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.dre.brewery.P;

import vg.civcraft.mc.citadel.Citadel;
import vg.civcraft.mc.citadel.CitadelPermissionHandler;
import vg.civcraft.mc.citadel.ReinforcementManager;
import vg.civcraft.mc.citadel.model.Reinforcement;

/**
 * Basic Citadel support to prevent randos from stealing your barrel aging brews
 * 
 * @author ProgrammerDan
 */
public class CitadelBarrel {
	static P brewery = P.p;

	public static boolean checkAccess(Player player, Block sign) {
		ReinforcementManager manager = Citadel.getInstance().getReinforcementManager();
		Reinforcement rein = manager.getReinforcement(sign);
		
		if (rein == null) return true; // no protections in place.
		
		if (rein.hasPermission(player, CitadelPermissionHandler.getChests())) {
			return true;
		}
		
		// special locked, or no access.
		brewery.msg(player, brewery.languageReader.get("Error_NoBarrelAccess"));
		return false;
	}
}
