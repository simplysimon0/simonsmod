package mod.simonsmod.core.containers;

import mod.simonsmod.core.objects.tileEntity.TileEntityEnergyCube;
import mod.simonsmod.core.objects.tileEntity.TileEntityTest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerEnergyCube extends Container {

	
	private final TileEntityEnergyCube tileentity;
	public ContainerEnergyCube(InventoryPlayer player, TileEntityEnergyCube tileentity) {
		this.tileentity = tileentity;
	}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}
