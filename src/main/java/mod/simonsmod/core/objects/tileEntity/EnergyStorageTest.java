package mod.simonsmod.core.objects.tileEntity;

import net.minecraftforge.energy.IEnergyStorage;

/**
 * Reference implementation of {@link IEnergyStorage}. Use/extend this or implement your own.
 *
 * Derived from the Redstone Flux power system designed by King Lemming and originally utilized in Thermal Expansion and related mods.
 * Created with consent and permission of King Lemming and Team CoFH. Released with permission under LGPL 2.1 when bundled with Forge.
 */
public class EnergyStorageTest implements IEnergyStorage
{
    public int energy;
    public int capacity;
    public int maxReceive;
    public int maxExtract;

    public EnergyStorageTest(int capacity)
    {
        this(capacity, capacity, capacity, 0);
    }

    public EnergyStorageTest(int capacity, int maxTransfer)
    {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public EnergyStorageTest(int capacity, int maxReceive, int maxExtract)
    {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public EnergyStorageTest(int capacity, int maxReceive, int maxExtract, int energy)
    {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0 , Math.min(capacity, energy));
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            energy += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            energy -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public int getEnergyStored()
    {
        return energy;
    }

    @Override
    public int getMaxEnergyStored()
    {
        return capacity;
    }

    @Override
    public boolean canExtract()
    {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive()
    {
        return this.maxReceive > 0;
    }
}