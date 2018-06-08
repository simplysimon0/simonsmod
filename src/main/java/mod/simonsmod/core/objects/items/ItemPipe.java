package mod.simonsmod.core.objects.items;

import java.util.List;
import java.util.Random;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPipe extends Item implements IHasModel {

	public final int itemUseDuration;

	public ItemPipe(String name) {

		this.maxStackSize = 1;
		this.setMaxDamage(6);
		// ItemStack itemStack = new ItemStack(ItemInit.PIPE, 1, 0);
		// this.setDamage(pipe, pipe.getMaxDamage()-1);
		this.itemUseDuration = 32;
		canRepair = false;
		setRegistryName(name);
		setUnlocalizedName(name);
		this.setCreativeTab(SimonsMod.SIMONSTAB);
		ItemInit.ITEMS.add(this);

	}
	// enchant

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(this, 0, "inventory");

	}

	public void setPipe(ItemStack stack, String inside) {

		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
			stack.setTagCompound(nbt);
		}

		nbt.setString("type", inside);
		stack.setTagCompound(nbt);
	}
	
	private ItemStack findAmmo(EntityPlayer player)
    {
        if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND)))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND)))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }
	protected boolean isArrow(ItemStack stack)
    {
        return stack.getItem() instanceof ItemCrop;
    }

	// public boolean
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		NBTTagCompound nbt = stack.getTagCompound();

		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
		}
		// System.out.println("biggayboy");

		if (nbt.hasKey("type")) {
			// nbt.setString("type", nbt.getString("type"));
		} else {
			nbt.setString("type", "Empty");
		}
		stack.setTagCompound(nbt);
	}

	public EnumAction getItemUseAction(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (stack.getItemDamage() != stack.getMaxDamage() - 1 && nbt.getString("type") != "Empty")
			return EnumAction.BOW;
		return EnumAction.NONE;
	}

	// ToolTip
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("type")) {
			info.add(stack.getTagCompound().getString("type"));
		}
		// info.add("Empty");
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	// called when the item is no longer being right clicked(when player
	// releases right mouse button)
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (!worldIn.isRemote) {
			NBTTagCompound nbt = stack.getTagCompound();
			if (nbt.getString("type") != "Empty"&&!entityLiving.isSneaking()) {
				entityLiving.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 0));
				if (stack.getItemDamage() == stack.getMaxDamage() - 1) {
					stack.setItemDamage(0);
					nbt.setString("type", "Empty");
				} else
					stack.damageItem(1, entityLiving);
			}
		}
	}

	/**
	 * Called when the equipped item is right clicked.
	 */
	public void randomParticalDisplay(World worldIn, EntityPlayer playerIn, Random rand) {
		if (!playerIn.isSneaking()) {
			BlockPos pos = playerIn.getPosition();
			EnumFacing enumfacing = playerIn.getAdjustedHorizontalFacing();
			double d0 = (double) pos.getX() + 0.2D;
			double d1 = (double) pos.getY() + 1.6 + rand.nextDouble() * 0.1D / 16.0D;
			double d2 = (double) pos.getZ() + 0.2D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D,
					SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			switch (enumfacing) {
			case WEST:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				// worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D,
				// d1, d2
				// + d4, 0.0D, 0.0D, 0.0D);
				break;
			case EAST:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
				// worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D,
				// d1, d2
				// + d4, 0.0D, 0.0D, 0.0D);
				break;
			case NORTH:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
				// worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1,
				// d2 -
				// 0.52D, 0.0D, 0.0D, 0.0D);
				break;
			case SOUTH:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
				// worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1,
				// d2 +
				// 0.52D, 0.0D, 0.0D, 0.0D);
			default:
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2, 0.0D, 0.0D, 0.0D);
				// worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1,
				// d2,
				// 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		ItemStack stack = playerIn.getHeldItem(handIn);
		NBTTagCompound nbt = stack.getTagCompound();
		if (playerIn.isSneaking() && nbt.getString("type").equals("Empty")) {
			ItemStack itemstack = this.findAmmo(playerIn);
			if (!itemstack.isEmpty())
            {
        			nbt.setString("type", "Weed");
        			stack.setTagCompound(nbt);
                    itemstack.shrink(1);

                    if (itemstack.isEmpty())
                    {
                    	playerIn.inventory.deleteStack(itemstack);
                    }
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
                }
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
			
		} else if (!nbt.getString("type").equals("Empty")) {
			if (stack.getItemDamage() != stack.getMaxDamage() - 1 && !nbt.getString("type").equals("Empty")) {
				randomParticalDisplay(worldIn, playerIn, worldIn.rand);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
			}
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		} 
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));

	}
}