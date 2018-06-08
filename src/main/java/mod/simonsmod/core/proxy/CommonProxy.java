package mod.simonsmod.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {

	
	
	public void registerItemRenderer(Item item, int meta, String id) {}	
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
	public void init() {

	}	
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		//TutorialMain.logger.info("Retrieving player from CommonProxy for message on side " + ctx.side);
		return ctx.getServerHandler().player;
	}
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().player.getServer();
	}
}
