package mod.simonsmod.core.objects.blocks;

import java.util.Random;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.IMetaName;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.blocks.item.ItemBlockVariants;
import mod.simonsmod.core.objects.simonsEnums.EnumColor;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockColorLight extends Block implements IHasModel, IMetaName {
	public static final PropertyEnum<EnumColor> COLOR = PropertyEnum.<EnumColor>create("variant", EnumColor.class);
	private final boolean isOn;
	private String name;

	public BlockColorLight(String name, boolean isOn) {
		super(Material.REDSTONE_LIGHT);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SimonsMod.SIMONSTAB);
		setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumColor.WHITE));
		setHardness(0.3F);
		setResistance(1.5F);
		this.name = name;
		BlockInit.BLOCKS.add(this);
		if(!name.contains("lit"))
			ItemInit.ITEMBLOCKS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
		this.isOn = isOn;
		if (isOn) {
			this.setLightLevel(1.0F);
		}
		
	}

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

	/**
	 * Called after the block is set in the Chunk data, but before the Tile
	 * Entity is set
	 */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			if (this.isOn && !worldIn.isBlockPowered(pos)) {
				//System.out.println("Off");
				int i = this.getMetaFromState(state);
				worldIn.setBlockState(pos, BlockInit.COLOR_LAMP.getDefaultState().withProperty(COLOR, EnumColor.byMetadata(i)), 2);
			} else if (!this.isOn && worldIn.isBlockPowered(pos)) {
				//System.out.println("On");
				int i = this.getMetaFromState(state);
				worldIn.setBlockState(pos, BlockInit.LIT_COLOR_LAMP.getDefaultState().withProperty(COLOR, EnumColor.byMetadata(i)), 2);
			}
		}
	}

	/**
	 * Called when a neighboring block was changed and marks that this state
	 * should perform any checks during a neighbor change. Cases may include
	 * when redstone power is updated, cactus blocks popping off due to a
	 * neighboring solid block, etc.
	 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!worldIn.isRemote) {
			boolean flag = worldIn.isBlockPowered(pos);
			if (this.isOn && !flag) {
				worldIn.scheduleUpdate(pos, this, 4);
			} else if (!this.isOn && flag) {
				int i = this.getMetaFromState(state);
				worldIn.setBlockState(pos, BlockInit.LIT_COLOR_LAMP.getDefaultState().withProperty(COLOR, EnumColor.byMetadata(i)), 2);
			}
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (this.isOn && !worldIn.isBlockPowered(pos)) {
				int i = this.getMetaFromState(state);
				worldIn.setBlockState(pos, BlockInit.COLOR_LAMP.getDefaultState().withProperty(COLOR, EnumColor.byMetadata(i)), 2);
			}
		}
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockInit.COLOR_LAMP);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(BlockInit.COLOR_LAMP);
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(BlockInit.COLOR_LAMP);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumColor) state.getValue(COLOR)).getMetadata();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumColor) state.getValue(COLOR)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(COLOR, EnumColor.byMetadata(meta));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		if(name.contains("lit"))
			return new ItemStack(Item.getItemFromBlock(BlockInit.COLOR_LAMP), 1, getMetaFromState(world.getBlockState(pos)));
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		if (name.equals("color_lamp")) {
			for (EnumColor variant : EnumColor.values()) {
				items.add(new ItemStack(this, 1, variant.getMetadata()));
			}
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { COLOR });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumColor.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumColor.values().length; i++) {
			SimonsMod.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i,
					name + "_" + EnumColor.values()[i].getName(), "inventory");
		}
	}
}