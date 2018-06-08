package mod.simonsmod.core.handlers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.Subscribe;

import mod.simonsmod.core.Reference;
import mod.simonsmod.core.SimonsMod;
import mod.simonsmod.core.config.MainConfig;
import mod.simonsmod.core.handlers.recipe.RecipeHandler;
import mod.simonsmod.core.init.BiomeInit;
import mod.simonsmod.core.init.BlockInit;
import mod.simonsmod.core.init.EntityInit;
import mod.simonsmod.core.init.ItemInit;
import mod.simonsmod.core.objects.tileEntity.TileEntityBreaker;
import mod.simonsmod.core.objects.tileEntity.TileEntityEnergyCube;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperDuct;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmni;
import mod.simonsmod.core.objects.tileEntity.TileEntityHopperOmniOld;
import mod.simonsmod.core.objects.tileEntity.TileEntityPhilosophersCircle;
import mod.simonsmod.core.objects.tileEntity.TileEntityPipe;
import mod.simonsmod.core.objects.tileEntity.TileEntityPlacer;
import mod.simonsmod.core.objects.tileEntity.TileEntityTest;
import mod.simonsmod.core.objects.tileEntity.TileEntityWorkTable;
import mod.simonsmod.core.util.IHasModel;
import mod.simonsmod.core.world.gen.LavaGen;
import mod.simonsmod.core.world.gen.OreWorldGen;
import mod.simonsmod.core.world.gen.RemoveVanillaRocks;
import mod.simonsmod.core.world.gen.RocksWorldGen;
import mod.simonsmod.core.world.gen.SimonWorldGenerator;
import mod.simonsmod.core.world.types.WorldTypeSimon;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		List<Item> ALLITEMS = new ArrayList<Item>();
		ALLITEMS.addAll(ItemInit.ITEMBLOCKS);
		ALLITEMS.addAll(ItemInit.ITEMS);
		event.getRegistry().registerAll(ALLITEMS.toArray(new Item[0]));
	}
	
	public static void fluidRegister()
	{
		/*FluidRegistry.registerFluid(FluidInit.FLUID_MERCURY);
		Item mercury = Item.getItemFromBlock(BlockInit.BLOCK_FLUID_MERCURY);
		ModelLoader.setCustomMeshDefinition(mercury, new ItemMeshDefinition(){
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack){
				return FluidMercury.mercury_location;
			}
		});
		ModelLoader.setCustomStateMapper(BlockInit.BLOCK_FLUID_MERCURY, new StateMapperBase(){
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return FluidMercury.mercury_location;
			}
		});*/
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ItemInit.ITEMBLOCKS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		for(Item item : ItemInit.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityHopperDuct.class, 		Reference.MODID + "TEHopperDuct");
		GameRegistry.registerTileEntity(TileEntityHopperOmni.class, 		Reference.MODID + "TEHopperOmni");
		GameRegistry.registerTileEntity(TileEntityHopperOmniOld.class, 		Reference.MODID + "TEHopperOmniOld");
		GameRegistry.registerTileEntity(TileEntityPhilosophersCircle.class, Reference.MODID + "TEPhilCircle");
		GameRegistry.registerTileEntity(TileEntityTest.class, 				Reference.MODID + "TETest");
		GameRegistry.registerTileEntity(TileEntityWorkTable.class, 			Reference.MODID + "TEWorkTable");
		GameRegistry.registerTileEntity(TileEntityPipe.class, 				Reference.MODID + "TEPipe");
		GameRegistry.registerTileEntity(TileEntityPlacer.class, 			Reference.MODID + "TEPlacer");
		GameRegistry.registerTileEntity(TileEntityBreaker.class, 			Reference.MODID + "TEBreaker");
		GameRegistry.registerTileEntity(TileEntityEnergyCube.class, 		Reference.MODID + "TEEnergyCube");

	}
	
	public static void preInitRegistries()
	{
		EntityInit.registerEntities();
	}
	public static void otherPreRegistries()
	{
		//fluidRegister();
		MinecraftForge.ORE_GEN_BUS.register(new RemoveVanillaRocks());
		
		//CraftingSimonManager.init();
		//GameRegistry.registerWorldGenerator(new PlantsWorldDecorator(), 0);
		GameRegistry.registerWorldGenerator(new LavaGen(), 0);
		GameRegistry.registerWorldGenerator(new RocksWorldGen(), 0);
		GameRegistry.registerWorldGenerator(new OreWorldGen(), 0);
		GameRegistry.registerWorldGenerator(new SimonWorldGenerator(), 0);
		MainConfig.preInit();
		MainConfig.clientPreInit();
		BiomeInit.registerBiomes();
		EntityInit.registerEntities();
	}
	

	public static void otherInitRegistries()
	{
		registerTileEntities();
		SimonSoundHandler.registerSounds();
		RecipeHandler.registerFurnaceRecipes();
		RecipeHandler.registerCraftingRecipes();
		MinecraftForge.EVENT_BUS.register(new SimonEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(SimonsMod.instance, new GuiHandler());
		OreDictHandler.registerOreDictionary();
		
		
	}
	  @SubscribeEvent
	    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
	    {
		  if(MainConfig.changeVanillaStones)
		  {
		    //ResourceLocation hopper = new ResourceLocation("minecraft:hopper");
	    	ResourceLocation diorite = new ResourceLocation("minecraft:diorite");
	    	ResourceLocation granite = new ResourceLocation("minecraft:granite");
	    	ResourceLocation andesite = new ResourceLocation("minecraft:andesite");
	        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
	        modRegistry.remove(diorite);
	        modRegistry.remove(granite);
	        modRegistry.remove(andesite);
	        //modRegistry.remove(hopper);
		  }
	    }
	  public static void otherPostInitRegistries(){
		  WorldType SIMON = new WorldTypeSimon();
	  }
}
