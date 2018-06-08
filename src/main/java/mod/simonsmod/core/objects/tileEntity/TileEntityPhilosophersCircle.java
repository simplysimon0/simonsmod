package mod.simonsmod.core.objects.tileEntity;

import java.util.List;

import ibxm.Player;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.tileentities.BlockPedestal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityPhilosophersCircle extends TileEntity implements ITickable{
	public static String Cardinal = "";

	public static void Test() {
		System.out.println("test");
	}

	/*
	 * public void update() { EntityPlayer playerIn; double x = (double)
	 * pos.getX(); double y = (double) pos.getY(); double z = (double)
	 * pos.getZ(); AxisAlignedBB scanInside = new AxisAlignedBB(x, y, z, x, y,
	 * z); List entities = world.getEntitiesWithinAABB(EntityItem.class,
	 * scanInside); if (entities != null) System.out.println(entities);
	 * 
	 * }
	 */
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	public void update() {
		//System.out.println("t");
		IBlockState state = world.getBlockState(pos);
		if (state != BlockInit.BLOCK_PEDASTAL.getDefaultState().withProperty(BlockPedestal.FACING, EnumFacing.DOWN))
			if (!BlockPedestal.isMultiBlockStructure(world, pos.getX(), pos.getY(), pos.getZ()))
				BlockInit.BLOCK_PEDASTAL.getDefaultState().withProperty(BlockPedestal.FACING, EnumFacing.DOWN);

	}
	public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
}
