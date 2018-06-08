package mod.simonsmod.core.handlers;

import mod.simonsmod.core.containers.ContainerBreaker;
import mod.simonsmod.core.containers.ContainerEnergyCube;
import mod.simonsmod.core.containers.ContainerHopperDuct;
import mod.simonsmod.core.containers.ContainerHopperOmni;
import mod.simonsmod.core.containers.ContainerPlacer;
import mod.simonsmod.core.containers.ContainerTestTileEntity;
import mod.simonsmod.core.containers.ContainerWorkTable;
import mod.simonsmod.core.containers.GuiBreaker;
import mod.simonsmod.core.containers.GuiEnergyCube;
import mod.simonsmod.core.containers.GuiHopperDuct;
import mod.simonsmod.core.containers.GuiHopperOmni;
import mod.simonsmod.core.containers.GuiPlacer;
import mod.simonsmod.core.containers.GuiTileEntityTest;
import mod.simonsmod.core.containers.GuiWorkTable;
import mod.simonsmod.core.objects.tileEntity.TileEntityBreaker;
import mod.simonsmod.core.objects.tileEntity.TileEntityEnergyCube;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperDuct;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmni;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmniOld;
import mod.simonsmod.core.objects.tileEntity.TileEntityPipe;
import mod.simonsmod.core.objects.tileEntity.TileEntityPlacer;
import mod.simonsmod.core.objects.tileEntity.TileEntityTest;
import mod.simonsmod.core.objects.tileEntity.TileEntityWorkTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public static final int HOPPER_DUCT = 0;
	public static final int HOPPER_OMNI = 1;
	public static final int TEST = 2;
	public static final int WORK_TABLE = 3;
	public static final int PIPE = 4;
	public static final int PLACER = 5;
	public static final int BREAKER = 6;
	public static final int ENERGYCUBE = 7;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID){
		case HOPPER_DUCT:
			TileEntityHopperDuct teHopperDuct = (TileEntityHopperDuct) tileEntity;
			return new ContainerHopperDuct(player.inventory, teHopperDuct, player);
		case HOPPER_OMNI:
			TileEntityHopperOmniOld teHopperOmni = (TileEntityHopperOmniOld) tileEntity;
			return new ContainerHopperOmni(player.inventory, teHopperOmni, player);
		case TEST:
			TileEntityTest teTest = (TileEntityTest) tileEntity;
			return new ContainerTestTileEntity(player.inventory, teTest);
		case WORK_TABLE:
			TileEntityWorkTable teWorkTable = (TileEntityWorkTable) tileEntity;
			return new ContainerWorkTable(player.inventory, teWorkTable);
		case PIPE:
			TileEntityPipe tePipe = (TileEntityPipe) tileEntity;
			return new ContainerHopperDuct(player.inventory, tePipe, player);
		case PLACER:
			TileEntityPlacer tePlacer = (TileEntityPlacer) tileEntity;
			return new ContainerPlacer(player.inventory, tePlacer);
		case BREAKER:
			TileEntityBreaker teBreaker = (TileEntityBreaker) tileEntity;
			return new ContainerBreaker(player.inventory, teBreaker);
		case ENERGYCUBE:
			TileEntityEnergyCube teEnergyC = (TileEntityEnergyCube) tileEntity;
			return new ContainerEnergyCube(player.inventory, teEnergyC);
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID){
		case HOPPER_DUCT:
			TileEntityHopperDuct te = (TileEntityHopperDuct) tileEntity;
			return new GuiHopperDuct(player.inventory, (TileEntityHopperDuct) te);
		case HOPPER_OMNI:
			TileEntityHopperOmniOld teOmni = (TileEntityHopperOmniOld) tileEntity;
			return new GuiHopperOmni(player.inventory, (TileEntityHopperOmniOld) teOmni);
		case TEST:
			TileEntityTest teTest = (TileEntityTest) tileEntity;
			return new GuiTileEntityTest(player.inventory, teTest);
		case WORK_TABLE:
			TileEntityWorkTable teWorkTable = (TileEntityWorkTable) tileEntity;
			return new GuiWorkTable(player.inventory, teWorkTable);
		case PIPE:
			TileEntityPipe tePipe = (TileEntityPipe) tileEntity;
			return new GuiHopperDuct(player.inventory, (TileEntityPipe) tePipe);
		case PLACER:
			TileEntityPlacer tePlacer = (TileEntityPlacer) tileEntity;
			return new GuiPlacer(player.inventory, (TileEntityPlacer) tePlacer);
		case BREAKER:
			TileEntityBreaker teBreaker = (TileEntityBreaker) tileEntity;
			return new GuiBreaker(player.inventory, (TileEntityBreaker) teBreaker);
		case ENERGYCUBE:
			TileEntityEnergyCube teEnergyC = (TileEntityEnergyCube) tileEntity;
			return new GuiEnergyCube(player.inventory, (TileEntityEnergyCube) teEnergyC);
		}
		
		return null;
	}
}