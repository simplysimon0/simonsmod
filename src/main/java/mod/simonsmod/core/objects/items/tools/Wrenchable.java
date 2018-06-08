package mod.simonsmod.core.objects.items.tools;

import java.util.ArrayList;

import mod.simonsmod.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Wrenchable {

	//Wrenchable.getWrenchable(block, itemstack, player, worldIn, pos, air, iblockstate);
	public static ArrayList<Block> getWrenchableList()
	{
		ArrayList<Block> blockList = new ArrayList<Block>();
		blockList.add(BlockInit.BLOCK_ALARM);
		blockList.add(BlockInit.HOPPER_DUCT);
		blockList.add(BlockInit.HOPPER_OMNI);
		blockList.add(Blocks.HOPPER);
		blockList.add(BlockInit.BREAKER);
		blockList.add(BlockInit.PLACER);
		blockList.add(Blocks.DISPENSER);
		blockList.add(Blocks.DROPPER);
		blockList.add(Blocks.PISTON);
		blockList.add(Blocks.STICKY_PISTON);
		blockList.add(Blocks.OBSERVER);
		
		return blockList;
	}
}
