package mod.simonsmod.core.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.annotation.Nonnull;

import cjminecraft.core.energy.EnergyUnits.EnergyUnit;
import cjminecraft.core.energy.support.IEnergySupport;
import cjminecraft.core.energy.EnergyUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class EnergyUtilSimon {

	/**
	 * Takes energy from all connecting energy handlers surrounding the target
	 * block
	 * 
	 * @param world
	 *            The world to get the {@link TileEntity} from
	 * @param pos
	 *            The center position
	 * @param energy
	 *            The energy to take altogether. Will be distributed evenly
	 *            between the {@link TileEntity}s. Needs to be in the
	 *            {@link EnergyUnit} provided
	 * @param unit
	 *            The unit the energy taken will be returned in
	 * @param simulate
	 *            Whether it is a simulation or not. If so, the energy won't
	 *            actually be taken
	 * @param share
	 *            Whether to take the energy from every face or to share the
	 *            amount of energy to take
	 * @return The amount of energy taken in the {@link EnergyUnit} provided
	 */
	public static long takeEnergyAllFaces(@Nonnull World world, BlockPos pos, long energy, @Nonnull EnergyUnit unit,
			boolean simulate, boolean share) {
		HashMap<EnumFacing, TileEntity> tiles = new HashMap<EnumFacing, TileEntity>();
		for (EnumFacing side : EnumFacing.VALUES) {
			TileEntity te = world.getTileEntity(pos.offset(side));
			if (te == null)
				continue;
			if (EnergyUtils.hasSupport(te, side))
				tiles.put(side, te);
		}
		if (tiles.size() <= 0)
			return 0;
		if (share) {
			long energyPerSide = energy / tiles.size();
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyTaken = 0;
			long extraEnergy = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				long et = EnergyUtils.takeEnergy(te, energyPerSide + extraEnergy, unit, simulate, side.getOpposite());
				energyTaken += et;
				if (et < energyPerSide)
					extraEnergy = energyPerSide - et;
				else
					extraEnergy = 0;
			}
			return energyTaken;
		} else {
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyTaken = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				energyTaken += EnergyUtils.takeEnergy(te, energy, unit, simulate, side.getOpposite());
			}
			return energyTaken;
		}
	}
	
	/**
	 * Takes energy from all connecting energy handlers surrounding the target
	 * block
	 * 
	 * @param world
	 *            The world to get the {@link TileEntity} from
	 * @param pos
	 *            The center position
	 * @param energy
	 *            The energy to take altogether. Will be distributed evenly
	 *            between the {@link TileEntity}s. Needs to be in the
	 *            {@link EnergyUnit} provided
	 * @param unit
	 *            The unit the energy taken will be returned in
	 * @param simulate
	 *            Whether it is a simulation or not. If so, the energy won't
	 *            actually be taken
	 * @param share
	 *            Whether to take the energy from every face or to share the
	 *            amount of energy to take
	 * @return The amount of energy taken in the {@link EnergyUnit} provided
	 */
	public static long takeEnergyAllFacesButOne(@Nonnull World world, BlockPos pos, long energy, @Nonnull EnergyUnit unit,
			boolean simulate, boolean share, EnumFacing noEnergyFacing) {
		HashMap<EnumFacing, TileEntity> tiles = new HashMap<EnumFacing, TileEntity>();
		for (EnumFacing side : EnumFacing.VALUES) {
			if (side != noEnergyFacing.getOpposite()) {
				TileEntity te = world.getTileEntity(pos.offset(side));
				if (te == null)
					continue;
				if (EnergyUtils.hasSupport(te, side))
					tiles.put(side, te);
			}
		}
		if (tiles.size() <= 0)
			return 0;
		if (share) {
			long energyPerSide = energy / tiles.size();
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyTaken = 0;
			long extraEnergy = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				long et = EnergyUtils.takeEnergy(te, energyPerSide + extraEnergy, unit, simulate, side.getOpposite());
				energyTaken += et;
				if (et < energyPerSide)
					extraEnergy = energyPerSide - et;
				else
					extraEnergy = 0;
			}
			return energyTaken;
		} else {
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyTaken = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				energyTaken += EnergyUtils.takeEnergy(te, energy, unit, simulate, side.getOpposite());
			}
			return energyTaken;
		}
	}
	
	/**
	 * Gives energy to all connecting energy handlers surrounding the target
	 * block
	 * 
	 * @param world
	 *            The world to get the {@link TileEntity} from
	 * @param pos
	 *            The center position
	 * @param energy
	 *            The energy to give altogether. Will be distributed evenly
	 *            between the {@link TileEntity}s. Needs to be in the
	 *            {@link EnergyUnit} provided
	 * @param unit
	 *            The {@link EnergyUnit} the energy given will be returned in
	 * @param simulate
	 *            Whether it is a simulation or not. If so, the energy won't
	 *            actually be given
	 * @param share
	 *            Whether to give energy to every side or to share the energy to
	 *            give
	 * @return The amount of energy given in the {@link EnergyUnit} provided
	 */
	public static long giveEnergyAllFaces(@Nonnull World world, BlockPos pos, long energy, @Nonnull EnergyUnit unit,
			boolean simulate, boolean share) {
		HashMap<EnumFacing, TileEntity> tiles = new HashMap<EnumFacing, TileEntity>();
		for (EnumFacing side : EnumFacing.VALUES) {
			TileEntity te = world.getTileEntity(pos.offset(side));
			if (te == null)
				continue;
			if (EnergyUtils.hasSupport(te, side))
				tiles.put(side, te);
		}
		if (tiles.size() <= 0)
			return 0;
		if (share) {
			long energyPerSide = energy / tiles.size();
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyGiven = 0;
			long extraEnergy = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				long eg = EnergyUtils.giveEnergy(te, energyPerSide + extraEnergy, unit, simulate, side.getOpposite());
				energyGiven += eg;
				if (eg < energyPerSide)
					extraEnergy = energyPerSide - eg;
				else
					extraEnergy = 0;
			}
			return energyGiven;
		} else {
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyGiven = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				energyGiven += EnergyUtils.giveEnergy(te, energy, unit, simulate, side.getOpposite());
			}
			return energyGiven;
		}
	}
	/**
	 * Gives energy to all connecting energy handlers surrounding the target
	 * block
	 * 
	 * @param world
	 *            The world to get the {@link TileEntity} from
	 * @param pos
	 *            The center position
	 * @param energy
	 *            The energy to give altogether. Will be distributed evenly
	 *            between the {@link TileEntity}s. Needs to be in the
	 *            {@link EnergyUnit} provided
	 * @param unit
	 *            The {@link EnergyUnit} the energy given will be returned in
	 * @param simulate
	 *            Whether it is a simulation or not. If so, the energy won't
	 *            actually be given
	 * @param share
	 *            Whether to give energy to every side or to share the energy to
	 *            give
	 * @return The amount of energy given in the {@link EnergyUnit} provided
	 */
	public static int giveEnergyToOneFace(@Nonnull World world, BlockPos pos, long energy, @Nonnull EnergyUnit unit,
			boolean simulate, boolean share, EnumFacing energyFacing) {
		HashMap<EnumFacing, TileEntity> tiles = new HashMap<EnumFacing, TileEntity>();
		//for (EnumFacing side : EnumFacing.VALUES) {
		for(int i = 0; i < 1; i ++){
		EnumFacing side = energyFacing.getOpposite();
			TileEntity te = world.getTileEntity(pos.offset(side));
			if (te == null)
				continue;
			if (EnergyUtils.hasSupport(te, side))
				tiles.put(side, te);
		}
		//}
		if (tiles.size() <= 0)
			return 0;
		if (share) {
			long energyPerSide = energy / tiles.size();
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyGiven = 0;
			long extraEnergy = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				long eg = EnergyUtils.giveEnergy(te, energyPerSide + extraEnergy, unit, simulate, side.getOpposite());
				energyGiven += eg;
				if (eg < energyPerSide)
					extraEnergy = energyPerSide - eg;
				else
					extraEnergy = 0;
			}
			return (int) energyGiven;
		} else {
			Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
			long energyGiven = 0;
			while (tilesIterator.hasNext()) {
				Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
				EnumFacing side = entry.getKey();
				TileEntity te = entry.getValue();
				energyGiven += EnergyUtils.giveEnergy(te, energy, unit, simulate, side.getOpposite());
			}
			return (int) energyGiven;
		}
	}
	
	/**
	 * Give energy to the given {@link TileEntity}
	 * 
	 * @param te
	 *            The {@link TileEntity} which will receive energy
	 * @param energy
	 *            The energy to be given in the provided {@link EnergyUnit}
	 * @param unit
	 *            The {@link EnergyUnit} of the energy to give and the energy
	 *            returned
	 * @param simulate
	 *            Whether or not it is a simulation. If so, no energy is
	 *            actually given
	 * @param from
	 *            The side of the {@link TileEntity} for use with
	 *            {@link Capability}
	 * @return The amount of energy which was given (or would have been given if
	 *         it is simulated) in the {@link EnergyUnit} provided
	 */
	public static int giveEnergy(TileEntity te, long energy, @Nonnull EnergyUnit unit, boolean simulate,
			EnumFacing from) {
		IEnergySupport support = EnergyUtils.getEnergyConsumerSupport(te, from);
		if (support != null && support.canReceive(support.getContainer(te, from), from))
			return (int) EnergyUtils.convertEnergy(support.defaultEnergyUnit(), unit, support.giveEnergy(support.getContainer(te, from),
					EnergyUtils.convertEnergy(unit, support.defaultEnergyUnit(), energy), simulate, from));
		return 0;
	}
	
	/**
	 * Take energy from the given {@link TileEntity}
	 * 
	 * @param te
	 *            The {@link TileEntity} which will have extracted from
	 * @param energy
	 *            The energy to be taken in the {@link EnergyUnit} provided
	 * @param unit
	 *            The {@link EnergyUnit} the energy will be returned in
	 * @param simulate
	 *            Whether or not it is a simulation. If so, no energy is
	 *            actually taken
	 * @param from
	 *            The side of the {@link TileEntity} for use with
	 *            {@link Capability}
	 * @return The amount of energy which was taken (or would have been taken if
	 *         it is simulated) in the {@link EnergyUnit} provided
	 */
	public static int takeEnergy(TileEntity te, long energy, @Nonnull EnergyUnit unit, boolean simulate,
			EnumFacing from) {
		IEnergySupport support = EnergyUtils.getEnergyProducerSupport(te, from);
		if (support != null && support.canExtract(support.getContainer(te, from), from))
			return (int) EnergyUtils.convertEnergy(support.defaultEnergyUnit(), unit, support.takeEnergy(support.getContainer(te, from),
					EnergyUtils.convertEnergy(unit, support.defaultEnergyUnit(), energy), simulate, from));
		return 0;
	}
}
