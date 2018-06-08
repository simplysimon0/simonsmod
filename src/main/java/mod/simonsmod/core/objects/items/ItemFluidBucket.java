package mod.simonsmod.core.objects.items;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFluidBucket extends Item implements IHasModel {

	public Block liquidState;

	public ItemFluidBucket(String name, Block liquid) {
		liquidState = liquid;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SimonsMod.SIMONSTAB);

		ItemInit.ITEMS.add(this);
	}

	public ActionResult<ItemStack> onItemRightClick(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		
			worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), liquidState.getDefaultState());
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(this, 0, "inventory");

	}

}
