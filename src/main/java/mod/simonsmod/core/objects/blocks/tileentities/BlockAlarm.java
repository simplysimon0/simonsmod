package mod.simonsmod.core.objects.blocks.tileentities;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.SimonSoundHandler;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.simonsEnums.EnumColor;
import mod.simonsmod.core.objects.tileEntity.TileEntityAlarm;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlarm extends Block implements IHasModel {

	protected static final AxisAlignedBB FULL = new AxisAlignedBB(0.1F, 0.0F, 0.1F, 0.9F, 0.35F, 0.9F);
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	public static final PropertyInteger VOLUME = PropertyInteger.create("volume", 0, 4);

	public BlockAlarm(String name) {
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.5F);
		setResistance(10);
		setCreativeTab(SimonsMod.SIMONSTAB);

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false));
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		if(GuiScreen.isShiftKeyDown())
		{
			info.add("Makes a loud sound when applied with a redstone signal");
		}
		else
			info.add("-Press Shift-");
	}
	
	public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityAlarm();
    }

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		for (int i = 0; i < 5; i++) {
			if (state.equals(this.getDefaultState().withProperty(VOLUME, 4))) {
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(VOLUME, 0));
				worldIn.playSound(null, pos, SimonSoundHandler.CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F);
				return true;
			} else if (state.equals(this.getDefaultState().withProperty(VOLUME, i))) {
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(VOLUME, i + 1));
				worldIn.playSound(null, pos, SimonSoundHandler.CLICK, SoundCategory.BLOCKS, 1.0F, 1.0F + 0.1F * i);
				return true;
			}
		}
		return false;
	}

	@Override
	public int tickRate(World worldIn) {
		return 2;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		float Volume = 0.0F;
		for (int i = 0; i < 5; i++) {
			if (state.equals(BlockInit.BLOCK_ALARM.getDefaultState().withProperty(BlockAlarm.VOLUME, i)))
				Volume = 0.2F + i * 0.2F;
		}
		if (!worldIn.isRemote) {
			boolean flag1 = ((Boolean) state.getValue(POWERED)).booleanValue();
			//AlarmSound alarm = new AlarmSound(flag1, Volume, pos);
		}
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.updateState(worldIn, pos, state);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			this.updateState(worldIn, pos, state);
		}

	}

	private void updateState(World worldIn, BlockPos pos, IBlockState state) {
		boolean flag = worldIn.isBlockPowered(pos);
		float Volume = 0.0F;
		for (int i = 0; i < 5; i++) {
			if (state.equals(BlockInit.BLOCK_ALARM.getDefaultState().withProperty(BlockAlarm.VOLUME, i)))
				Volume = 0.2F + i * 0.2F;
		}
		
		if (flag != ((Boolean) state.getValue(POWERED)).booleanValue()) {
			worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag)));
			worldIn.playSound(null, pos, SimonSoundHandler.ALARM, SoundCategory.BLOCKS, Volume, 1.0F);
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VOLUME, Integer.valueOf(meta & 4)).withProperty(POWERED,
				Boolean.valueOf((meta & 4) != 0));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if (((Boolean) state.getValue(POWERED)).booleanValue()) {
			i |= 4;
		}

		return i + state.getValue(VOLUME);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { POWERED, VOLUME });
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL);

	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
