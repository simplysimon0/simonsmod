package mod.simonsmod.core.objects.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSprinkler extends Block implements IHasModel {

	protected static final AxisAlignedBB Duct = new AxisAlignedBB(0.35D, 0.70D, 0.35D, 0.65D, 1.0D, 0.65D);
	
	public BlockSprinkler(String name) {
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.0F);
		this.setTickRandomly(true);
		setResistance(5);
		setCreativeTab(SimonsMod.SIMONSTAB);
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	

	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		if(side == EnumFacing.DOWN)
			return true;
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(2) == 0) {
			BlockPos blockpos = pos.down();

			double d0 = (double) ((float) pos.getX()+ 0.55D);
			double d1 = (double) pos.getY() - 0.05D ;
			double d2 = (double) ((float) pos.getZ()+ 0.55D);
			worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, true, d0, d1, d2, 1.0D, 1.0D, 1.0D, Block.getStateId(stateIn));

		}
	}
}
