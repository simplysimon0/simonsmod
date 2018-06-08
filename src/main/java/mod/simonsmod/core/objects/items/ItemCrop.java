package mod.simonsmod.core.objects.items;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class ItemCrop extends ItemFood implements IHasModel, IPlantable {

	public Block cropName;
	
	public ItemCrop(String name, int amount, boolean isWolfFood, Block crop)
	{
		super(amount, isWolfFood);
		cropName = crop;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SimonsMod.SIMONSTAB);
		ItemInit.ITEMS.add(this);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		
		return EnumPlantType.Crop;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		IBlockState state  = worldIn.getBlockState(pos);
		if(facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
		{
			worldIn.setBlockState(pos.up(), BlockInit.CROP_CANNABIS.getDefaultState());
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
		else return EnumActionResult.FAIL;
	}
	@Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            //if (stack.getMetadata() > 0)
            	player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));        
        }
    }
	
	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		// TODO Auto-generated method stub
		return BlockInit.CROP_CANNABIS.getDefaultState();
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
}
