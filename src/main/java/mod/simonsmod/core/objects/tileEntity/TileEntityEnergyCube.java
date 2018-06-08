package mod.simonsmod.core.objects.tileEntity;

import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Multiset.Entry;

import cjminecraft.core.energy.EnergyUnits;
import cjminecraft.core.energy.EnergyUtils;
import cjminecraft.core.energy.compat.forge.CustomForgeEnergyStorage;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.objects.blocks.tileentities.BlockHopperDuct;
import mod.simonsmod.core.util.EnergyUtilSimon;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

public class TileEntityEnergyCube extends TileEntity implements ITickable{
	
	public CustomForgeEnergyStorage storage;
	
	public TileEntityEnergyCube() {
		this.storage = new CustomForgeEnergyStorage(100000, 0);
		
	}
	
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate){
    	return oldState.getBlock()!=newSate.getBlock();
    }
	 
	
	@Override
	public void update() {
		if (this.world != null) {
			if (!this.world.isRemote) {
				EnumFacing enumfacing = BlockHopperDuct.getFacing(this.getBlockMetadata()).getOpposite();
				if (this.storage.getEnergyStored() < this.storage.getMaxEnergyStored()) {
					this.storage.receiveEnergyInternal((int) EnergyUtilSimon.takeEnergyAllFacesButOne(this.world, this.pos, 1000, EnergyUnits.FORGE_ENERGY, false, true, enumfacing), false);
					//markDirty();
					
				}
				if(this.storage.getEnergyStored() > 0)
				{
					//this.storage.extractEnergyInternal((int) EnergyUtilSimon.giveEnergyAllFaces(this.world, this.pos, 10, EnergyUnits.FORGE_ENERGY, false, true), false);
					this.storage.extractEnergyInternal(EnergyUtilSimon.giveEnergyToOneFace(this.world, this.pos, 1000, EnergyUnits.FORGE_ENERGY, false, true, enumfacing), false);
					//markDirty();
				}
			}
		}
	}

	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY)
			return (T) this.storage;
		return super.getCapability(capability, facing);
	}
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityEnergy.ENERGY)
			return true;
		return super.hasCapability(capability, facing);
	}



	public ItemStack getItem()
    {
        ItemStack itemstack = makeCube(this.storage.getEnergyStored());
        return itemstack;
    }
	
	public static ItemStack makeCube(int energy)
    {
        ItemStack itemstack = new ItemStack(BlockInit.ENERGY_CUBE, 1, 0);

        if (energy != 0)
        {
        	itemstack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("Energy", energy);
            itemstack.getTagCompound().setTag("Energy", nbt);
            itemstack.writeToNBT(nbt);
        }

        return itemstack;
    }
	/**
	 * Write data to nbt
	 */
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		//nbt.setTag("Inventory", this.handler.serializeNBT());
		this.storage.writeToNBT(nbt);
		return super.writeToNBT(nbt);
	}

	/**
	 * Read data from nbt
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		//this.handler.deserializeNBT(nbt.getCompoundTag("Inventory"));
		//this.storage.setMaxTransfer(0);
		this.storage.readFromNBT(nbt);
		super.readFromNBT(nbt);
	}

}
