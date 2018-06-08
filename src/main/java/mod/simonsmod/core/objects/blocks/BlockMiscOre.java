package mod.simonsmod.core.objects.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.handlers.IMetaName;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.blocks.item.ItemBlockVariants;
import mod.simonsmod.core.objects.simonsEnums.EnumMiscOre;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMiscOre extends Block implements IHasModel, IMetaName {
	public static final PropertyEnum<EnumMiscOre> VARIANT = PropertyEnum.<EnumMiscOre>create("variant",
			EnumMiscOre.class);

	private String name;

	public BlockMiscOre(String name) {
		super(Material.CLOTH);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(0.5F);
		setResistance(10);
		setCreativeTab(SimonsMod.SIMONSTAB);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumMiscOre.CHALK));
		if(name.contains("rubber"))
			setSoundType(SoundType.WOOD);
		else
			setSoundType(SoundType.STONE);
		this.name = name;

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMBLOCKS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list=new ArrayList<ItemStack>();  
		
		if(state == this.blockState.getBaseState().withProperty(VARIANT, EnumMiscOre.CHALK))
			list.add(new ItemStack(ItemInit.CRAFTING, 2+fortune, 0));
		else if(state == this.blockState.getBaseState().withProperty(VARIANT, EnumMiscOre.RUBBER))
		{
			list.add(new ItemStack(Blocks.LOG, 1, 0));
			list.add(new ItemStack(ItemInit.CRAFTING, 1+fortune, 1));
		}
		return list;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
		

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumMiscOre) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumMiscOre.byMetadata(meta));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumMiscOre variant : EnumMiscOre.values()) {
			items.add(new ItemStack(this, 1, variant.getMetadata()));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumMiscOre.values()[stack.getItemDamage()].getName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < EnumMiscOre.values().length; i++) {
			SimonsMod.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, name +"_"+ EnumMiscOre.values()[i].getName(), "inventory");
		}
	}
}