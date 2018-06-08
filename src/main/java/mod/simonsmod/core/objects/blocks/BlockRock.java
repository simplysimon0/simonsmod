package mod.simonsmod.core.objects.blocks;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.IMetaName;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.blocks.item.ItemBlockVariants;
import mod.simonsmod.core.objects.simonsEnums.EnumRock;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockRock extends Block implements IHasModel, IMetaName {
	public static final PropertyEnum<EnumRock> VARIANT = PropertyEnum.<EnumRock>create("variant", EnumRock.class);

	private String name;

	public BlockRock(String name) {

		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(1.5F);
		setResistance(10);
		setCreativeTab(SimonsMod.SIMONSTAB);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumRock.STONE));

		this.name = name;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public int damageDropped(IBlockState state) {
		if (((EnumRock) state.getValue(VARIANT)).getMetadata() == 0) {
			return 1;
		} else {
			return ((EnumRock) state.getValue(VARIANT)).getMetadata();
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumRock) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumRock.byMetadata(meta));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumRock variant : EnumRock.values()) {
			items.add(new ItemStack(this, 1, variant.getMetadata()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumRock.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumRock.values().length; i++) {
			SimonsMod.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i,
					name + "_" + EnumRock.values()[i].getName(), "inventory");
		}
	}
}