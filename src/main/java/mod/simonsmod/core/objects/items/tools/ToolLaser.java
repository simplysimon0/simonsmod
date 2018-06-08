package mod.simonsmod.core.objects.items.tools;

import java.util.List;

import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.entity.laser.EntityLaser;
import mod.simonsmod.core.entity.laser.EntityLaserEntity;
import mod.simonsmod.core.entity.laser.EntityLaserExplosive;
import mod.simonsmod.core.entity.laser.EntityLaserMining;
import mod.simonsmod.core.entity.laser.EntityLaserPrecision;
import mod.simonsmod.core.handlers.SimonSoundHandler;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.util.IHasModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ToolLaser extends Item implements IHasModel {

	public ToolLaser(String name, int uses) {

		this.maxStackSize = 1;
		this.setMaxDamage(uses);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(SimonsMod.SIMONSTAB);
		ItemInit.ITEMS.add(this);
		
	}
	public static NBTTagCompound getOrCreateNbtData(ItemStack stack) {
		NBTTagCompound ret = stack.getTagCompound();
		if (ret == null) {
			ret = new NBTTagCompound();
			stack.setTagCompound(ret);
		}

		return ret;
	}
	public String modeString = "mode";
	// ToolTip
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag b) {
		super.addInformation(stack, world, info, b);
		NBTTagCompound nbt = getOrCreateNbtData(stack);
		String mode;
		switch (nbt.getInteger("mode")) {
			case 1 :
				mode = "Mining";
				break;
			case 2 :
				mode = "Precision";
				break;
			case 3 :
				mode = "Explosive";
				break;
			case 4 :
				mode = "Battle";
				break;
			default :
				return;
		}
		info.add("Mode: "+ mode);
		info.add(TextFormatting.BLUE + "It's a Freaking Laser Dude");
	}

	@Override
	public void registerModels() {
		SimonsMod.proxy.registerItemRenderer(this, 0, "inventory");
	}

	
	public String getMode(int nbt){
		if(nbt == 1)
			return "Mining";
		if(nbt == 2)
			return "Precision";
		if(nbt == 3)
			return "Explosive";
		if(nbt == 4)
			return "Battle";
		return"Mining";
	}
	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			NBTTagCompound nbt = itemstack.getTagCompound();
			if (nbt == null)
			{
				nbt = new NBTTagCompound();
				nbt.setInteger("mode",1);
			}
			if (playerIn.isSneaking()) {
				if (nbt.hasKey("mode"))
					nbt.setInteger("mode", nbt.getInteger("mode") + 1);
				else
					nbt.setInteger("mode", 1);
				if (nbt.getInteger("mode") == 5)
					nbt.setInteger(("mode"), 1);
				itemstack.setTagCompound(nbt);
				playerIn.sendStatusMessage(new TextComponentTranslation("Mode: " + getMode(nbt.getInteger("mode")), new Object[0]), true);
				//playerIn.sendMessage(new TextComponentTranslation())));
				//System.out.println("Click:" + nbt.getInteger("mode"));
			} else {
				if (!playerIn.capabilities.isCreativeMode) {
					itemstack.damageItem(1, playerIn);
					;
				}
				
				worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
						SimonSoundHandler.LASER, SoundCategory.PLAYERS, 0.5F,
						
						0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

				if (nbt.getInteger("mode") == 1) {
					EntityLaserMining entitylaser = new EntityLaserMining(worldIn, playerIn);
					entitylaser.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
					worldIn.spawnEntity(entitylaser);
				}
				if (nbt.getInteger("mode") == 2) {
					EntityLaserPrecision entitylaser = new EntityLaserPrecision(worldIn, playerIn);
					entitylaser.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
					worldIn.spawnEntity(entitylaser);
				}
				if (nbt.getInteger("mode") == 3) {
					EntityLaserExplosive entitylaser = new EntityLaserExplosive(worldIn, playerIn);
					entitylaser.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
					worldIn.spawnEntity(entitylaser);
				}
				if (nbt.getInteger("mode") == 4) {
					EntityLaserEntity entitylaser = new EntityLaserEntity(worldIn, playerIn);
					entitylaser.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
					worldIn.spawnEntity(entitylaser);
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
	}
}