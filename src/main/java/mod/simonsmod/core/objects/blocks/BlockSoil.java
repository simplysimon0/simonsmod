package mod.simonsmod.core.objects.blocks;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.IMetaName;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.blocks.item.ItemBlockVariants;
import mod.simonsmod.core.objects.simonsEnums.EnumSoil;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
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

public class BlockSoil extends BlockFalling implements IHasModel, IMetaName {
	public static final PropertyEnum<EnumSoil> VARIANT = PropertyEnum.<EnumSoil>create("variant", EnumSoil.class);

	private String name;

	public BlockSoil(String name) {
		super(Material.SAND);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(0.5F);
		setResistance(10);
		setSoundType(blockSoundType.SAND);
		setCreativeTab(SimonsMod.SIMONSTAB);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumSoil.GREYSAND));

		this.name = name;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	
	
	@Override
	public int damageDropped(IBlockState state) {
		return ((EnumSoil) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumSoil) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumSoil.byMetadata(meta));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumSoil variant : EnumSoil.values()) {
			items.add(new ItemStack(this, 1, variant.getMetadata()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumSoil.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumSoil.values().length; i++) {
			SimonsMod.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i,
					"soil_" + EnumSoil.values()[i].getName(), "inventory");
		}
	}

}