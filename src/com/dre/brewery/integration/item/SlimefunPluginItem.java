package com.dre.brewery.integration.item;

import com.dre.brewery.P;
import com.dre.brewery.filedata.BConfig;
import com.dre.brewery.recipe.PluginItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.inventory.ItemStack;

public class SlimefunPluginItem extends PluginItem {

// When implementing this, put Brewery as softdepend in your plugin.yml!
// We're calling this as server start:
// PluginItem.registerForConfig("slimefun", SlimefunPluginItem::new);
// PluginItem.registerForConfig("exoticgarden", SlimefunPluginItem::new);

	@Override
	public boolean matches(ItemStack item) {
		if (BConfig.hasSlimefun == null) {
			BConfig.hasSlimefun = P.p.getServer().getPluginManager().isPluginEnabled("Slimefun");
		}
		if (!BConfig.hasSlimefun) return false;

		try {
			SlimefunItem sfItem = SlimefunItem.getByItem(item);
			if (sfItem != null) {
				return sfItem.getID().equalsIgnoreCase(getItemId());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			P.p.errorLog("Could not check Slimefun for Item ID");
			BConfig.hasSlimefun = false;
			return false;
		}
		return false;
	}
}
