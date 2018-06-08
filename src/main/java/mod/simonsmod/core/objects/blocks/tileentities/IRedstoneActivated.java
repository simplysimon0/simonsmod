package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IRedstoneActivated {

	public void onTriggered(World worldIn, BlockPos pos);
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand);

	public void getName(World worldIn, BlockPos pos, ItemStack stack);
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state);
}
