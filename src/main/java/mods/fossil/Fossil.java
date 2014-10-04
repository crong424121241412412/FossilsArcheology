package mods.fossil;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import mods.fossil.blocks.BlockAmberOre;
import mods.fossil.blocks.BlockAncientGlass;
import mods.fossil.blocks.BlockAncientStone;
import mods.fossil.blocks.BlockAncientStoneSlab;
import mods.fossil.blocks.BlockAncientStoneStairs;
import mods.fossil.blocks.BlockAncientStonebrick;
import mods.fossil.blocks.BlockAncientWood;
import mods.fossil.blocks.BlockAncientWoodPillar;
import mods.fossil.blocks.BlockAncientWoodPlate;
import mods.fossil.blocks.BlockAncientWoodSlab;
import mods.fossil.blocks.BlockAncientWoodStairs;
import mods.fossil.blocks.BlockFern;
import mods.fossil.blocks.BlockFigurine;
import mods.fossil.blocks.BlockFigurineItem;
import mods.fossil.blocks.BlockFossil;
import mods.fossil.blocks.BlockFossilSkull;
import mods.fossil.blocks.BlockIcedStone;
import mods.fossil.blocks.BlockMarble;
import mods.fossil.blocks.BlockPalaePlanks;
import mods.fossil.blocks.BlockPalaeSlab;
import mods.fossil.blocks.BlockPalaeStairs;
import mods.fossil.blocks.BlockPalmLeaves;
import mods.fossil.blocks.BlockPalmLog;
import mods.fossil.blocks.BlockPalmSapling;
import mods.fossil.blocks.BlockPermafrost;
import mods.fossil.blocks.BlockSarracenia;
import mods.fossil.blocks.BlockTar;
import mods.fossil.blocks.BlockVaseAmphora;
import mods.fossil.blocks.BlockVaseAmphoraItem;
import mods.fossil.blocks.BlockVaseKylix;
import mods.fossil.blocks.BlockVaseKylixItem;
import mods.fossil.blocks.BlockVaseVolute;
import mods.fossil.blocks.BlockVaseVoluteItem;
import mods.fossil.blocks.BlockVolcanicAsh;
import mods.fossil.blocks.BlockVolcanicBrick;
import mods.fossil.blocks.BlockVolcanicRock;
import mods.fossil.blocks.BlockVolcanicSlab;
import mods.fossil.blocks.BlockVolcanicStairs;
import mods.fossil.client.DinoSoundHandler;
import mods.fossil.client.FossilGuiHandler;
import mods.fossil.client.FossilMessageHandler;
import mods.fossil.client.FossilOptions;
import mods.fossil.client.LocalizationStrings;
import mods.fossil.client.renderer.tileentity.RenderFeeder;
import mods.fossil.enchantments.EnchantmentArcheology;
import mods.fossil.enchantments.EnchantmentPaleontology;
import mods.fossil.entity.BehaviorDodoEggDispense;
import mods.fossil.entity.BehaviorJavelinDispense;
import mods.fossil.entity.EntityAncientJavelin;
import mods.fossil.entity.EntityCultivatedDodoEgg;
import mods.fossil.entity.EntityDinoEgg;
import mods.fossil.entity.EntityDodoEgg;
import mods.fossil.entity.EntityJavelin;
import mods.fossil.entity.EntityMLighting;
import mods.fossil.entity.EntityStoneboard;
import mods.fossil.entity.EntityTerrorBirdEgg;
import mods.fossil.entity.mob.EntityBones;
import mods.fossil.entity.mob.EntityCoelacanth;
import mods.fossil.entity.mob.EntityDodo;
import mods.fossil.entity.mob.EntityFailuresaurus;
import mods.fossil.entity.mob.EntityFriendlyPigZombie;
import mods.fossil.entity.mob.EntityMammoth;
import mods.fossil.entity.mob.EntityNautilus;
import mods.fossil.entity.mob.EntityPigBoss;
import mods.fossil.entity.mob.EntityPregnantCow;
import mods.fossil.entity.mob.EntityPregnantHorse;
import mods.fossil.entity.mob.EntityPregnantPig;
import mods.fossil.entity.mob.EntityPregnantSheep;
import mods.fossil.entity.mob.EntityQuagga;
import mods.fossil.entity.mob.EntitySmilodon;
import mods.fossil.entity.mob.EntityTerrorBird;
import mods.fossil.fossilEnums.EnumDinoFoodMob;
import mods.fossil.fossilEnums.EnumDinoType;
import mods.fossil.gens.AcademyGenerator;
import mods.fossil.gens.FossilGenerator;
import mods.fossil.gens.ShipWreckGenerator;
import mods.fossil.gens.TarGenerator;
import mods.fossil.gens.VolcanicRockGenerator;
import mods.fossil.gens.WorldGeneratorPalaeoraphe;
import mods.fossil.guiBlocks.BlockAnalyzer;
import mods.fossil.guiBlocks.BlockCultivate;
import mods.fossil.guiBlocks.BlockDrum;
import mods.fossil.guiBlocks.BlockFeeder;
import mods.fossil.guiBlocks.BlockSifter;
import mods.fossil.guiBlocks.BlockTimeMachine;
import mods.fossil.guiBlocks.BlockWorktable;
import mods.fossil.guiBlocks.TileEntityAnalyzer;
import mods.fossil.guiBlocks.TileEntityCultivate;
import mods.fossil.guiBlocks.TileEntityDrum;
import mods.fossil.guiBlocks.TileEntityFeeder;
import mods.fossil.guiBlocks.TileEntityFigurine;
import mods.fossil.guiBlocks.TileEntitySifter;
import mods.fossil.guiBlocks.TileEntityTimeMachine;
import mods.fossil.guiBlocks.TileEntityVase;
import mods.fossil.guiBlocks.TileEntityWorktable;
import mods.fossil.handler.FossilAchievementHandler;
import mods.fossil.handler.FossilConnectionHandler;
import mods.fossil.handler.FossilCraftingHandler;
import mods.fossil.handler.FossilInteractEvent;
import mods.fossil.handler.FossilLivingEvent;
import mods.fossil.handler.FossilOreDictionary;
import mods.fossil.handler.FossilPickupHandler;
import mods.fossil.handler.FossilRecipeHandler;
import mods.fossil.handler.FossilSpawnEggs;
import mods.fossil.handler.FossilToolEvent;
import mods.fossil.handler.FossilTradeHandler;
import mods.fossil.items.ItemAmber;
import mods.fossil.items.ItemAncientEgg;
import mods.fossil.items.ItemAncientHelmet;
import mods.fossil.items.ItemAncientsword;
import mods.fossil.items.ItemBioFossil;
import mods.fossil.items.ItemChickenEss;
import mods.fossil.items.ItemCultivatedDodoEgg;
import mods.fossil.items.ItemDinosaurModels;
import mods.fossil.items.ItemDinosaurBones;
import mods.fossil.items.ItemDodoEgg;
import mods.fossil.items.ItemEmbryoSyringe;
import mods.fossil.items.ItemFeet;
import mods.fossil.items.ItemFemurs;
import mods.fossil.items.ItemFernSeed;
import mods.fossil.items.ItemFossilRecord;
import mods.fossil.items.ItemIcedMeat;
import mods.fossil.items.ItemJavelin;
import mods.fossil.items.ItemLivingCoelacanth;
import mods.fossil.items.ItemMagicConch;
import mods.fossil.items.ItemRibCage;
import mods.fossil.items.ItemSkullHelmet;
import mods.fossil.items.ItemSlabAncientStone;
import mods.fossil.items.ItemSlabAncientWood;
import mods.fossil.items.ItemSlabPalae;
import mods.fossil.items.ItemSlabVolcanic;
import mods.fossil.items.ItemStoneBoard;
import mods.fossil.items.ItemTerrorBirdEgg;
import mods.fossil.items.ItemWhip;
import mods.fossil.items.forge.ForgeAxe;
import mods.fossil.items.forge.ForgeFood;
import mods.fossil.items.forge.ForgeHoe;
import mods.fossil.items.forge.ForgeItem;
import mods.fossil.items.forge.ForgePickaxe;
import mods.fossil.items.forge.ForgeShovel;
import mods.fossil.items.forge.ForgeSword;
import mods.fossil.tabs.TabFBlocks;
import mods.fossil.tabs.TabFBones;
import mods.fossil.tabs.TabFCombat;
import mods.fossil.tabs.TabFFigurines;
import mods.fossil.tabs.TabFFood;
import mods.fossil.tabs.TabFItems;
import mods.fossil.tabs.TabFMaterial;
import mods.fossil.tabs.TabFTools;
import mods.fossil.util.FossilBonemealEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IChatListener;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;

//import mods.fossil.gens.TarGenerator;
//import mods.fossil.gens.WorldGenAcademy;
//import mods.fossil.gens.WorldGenShips;
//import mods.fossil.gens.WorldGenWeaponShop;
//import mods.fossil.gens.WorldGeneratorVolcanicRock;

@Mod(modid = Fossil.modid, name = "Fossil/Archeology", version = Fossil.modversion)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)

public class Fossil
{
    public static final String modid = "fossil";
    public static final String modversion = "1.6.4 Build 6.4.1rc1";

    /*
     * Set mod state here
     * 0 = Dev build
     * 1 = Beta build
     * 2 = Release build
     */
    
    public static final int modState = 2;

    @SidedProxy(clientSide = "mods.fossil.client.ClientProxy", serverSide = "mods.fossil.CommonProxy")
    public static CommonProxy proxy;

    @Instance("fossil")
    public static Fossil instance;

    public static final Logger Log = Logger.getLogger("FOSSILS");
    public static FossilGuiHandler GH = new FossilGuiHandler();
    public static FossilOptions FossilOptions;
    public static Properties LangProps = new Properties();
    public static Object ToPedia;

    public static IChatListener messagerHandler = new FossilMessageHandler();

    public Configuration config;

    /*
     * If DebugMode = true
     * Dinosaur/Syringe times are accelerated but not disabled to allow for error checking.
     */
    //public static boolean DebugMode = FossilOptions.FossilDebug;
    public static boolean DebugMode() {
        return false;
    }

    public static final double MESSAGE_DISTANCE = 25.0D;

    public static CreativeTabs tabFBlocks = new TabFBlocks(CreativeTabs.getNextID(), "Fossil Blocks");
    public static CreativeTabs tabFItems = new TabFItems(CreativeTabs.getNextID(), "Fossil Items");
    public static CreativeTabs tabFFood = new TabFFood(CreativeTabs.getNextID(), "Fossil Food");
    public static CreativeTabs tabFCombat = new TabFCombat(CreativeTabs.getNextID(), "Fossil Combat");
    public static CreativeTabs tabFTools = new TabFTools(CreativeTabs.getNextID(), "Fossil Deco");
    public static CreativeTabs tabFMaterial = new TabFMaterial(CreativeTabs.getNextID(), "Fossil Material");
    public static CreativeTabs tabFFigurines = new TabFFigurines(CreativeTabs.getNextID(), "Fossil Test");
    public static CreativeTabs tabFBones = new TabFBones(CreativeTabs.getNextID(), "Fossil Bones");

    //public static WorldType fossil = new WorldTypeFossil(3, "Dino Test");
    
    //Render IDs
	public static int feederRenderID;

	
  //enchantments
    public static Enchantment paleontology;
    public static Enchantment archeology;

    //Blocks
    public static Block blockFossil;
    public static Block blockSkull;
    public static Block skullLantern;
    public static Block blockanalyzerIdle;
    public static Block blockanalyzerActive;
    public static Block blockcultivateIdle;
    public static Block blockcultivateActive;
    public static Block blockworktableIdle;
    public static Block blockworktableActive;
    public static Block blockTimeMachine;
    public static Block ferns;
    //public static Block fernUpper;
    public static Block drum;
    public static Block feederIdle;
    public static Block feederActive;
    public static Block blockPermafrost;
    public static Block blockIcedStone;
    public static Block volcanicAsh;
    public static Block volcanicRock;
    public static Block volcanicRockHot;
    public static Block tar;
    public static Block palmLog;
    public static Block palmLeaves;
    public static Block palmSap;
    public static Block palaePlanks;
    public static BlockHalfSlab palaeSingleSlab;
    public static BlockHalfSlab palaeDoubleSlab;
    public static Block palaeStairs;
    public static Block sarracina;
    public static Block volcanicBrick;
    public static Block amberOre;
    public static Block ancientStone;
    public static Block ancientStonebrick;
    public static Block ancientWood;
    public static Block ancientWoodPillar;
    public static Block ancientGlass;
    public static Block ancientWoodPlate;
    public static Block ancientWoodStairs;
    public static BlockHalfSlab ancientWoodSingleSlab;
    public static BlockHalfSlab ancientWoodDoubleSlab;
    public static Block ancientStoneStairs;
    public static BlockHalfSlab ancientStoneSingleSlab;
    public static BlockHalfSlab ancientStoneDoubleSlab;
    public static Block marble;
    public static Block figurineBlock;
    public static Block blockSifterIdle;
    public static Block blockSifterActive;
    public static Block volcanicStairs;
    public static BlockHalfSlab volcanicSingleSlab;
    public static BlockHalfSlab volcanicDoubleSlab;
    public static Block vaseAmphoraBlock;
    public static Block vaseKylixBlock;
    public static Block vaseVoluteBlock;
    

    //Items
    public static Item biofossil;
    public static Item relic;
    public static Item stoneboard;
    public static Item ancientSword;
    public static Item brokenSword;
    public static Item fernSeed;
    public static Item ancienthelmet;
    public static Item brokenhelmet;
    public static Item skullStick;
    public static Item gem;
    public static Item gemAxe;
    public static Item gemPickaxe;
    public static Item gemSword;
    public static Item gemHoe;
    public static Item gemShovel;
    public static Item dinoPedia;
    public static Item archNotebook;
    public static Item emptyShell;
    public static Item magicConch;
    public static Item icedMeat;
    public static Item woodjavelin;
    public static Item stonejavelin;
    public static Item ironjavelin;
    public static Item goldjavelin;
    public static Item diamondjavelin;
    public static Item ancientJavelin;
    public static Item whip;
    public static Item legBone;
    public static Item claw;
    public static Item foot;
    public static Item skull;
    public static Item brokenSapling;
    public static Item amber;
    public static Item ancientVase;
    public static Item ancientVaseBroken;
    public static Item boneArrow;
    public static Item boneBow;
    public static Item boneGlue;
    public static Item boneRod;
    public static Item boneSword;
    public static Item powderyString;
    public static Item animalCoin;
    public static Item dinoCoin;
    public static Item dodoEgg;
    public static Item cultivatedDodoEgg;
    public static Item dodoWing;
    public static Item dodoWingCooked;
    public static Item figurineItem;
    public static Item brokenHeadRelic;
    public static Item potteryShards;
    public static Item livingCoelacanth;
    public static Item dinosaurModels;
    public static Item armBone;
    public static Item dinoRibCage;
    public static Item vertebrae;
    public static Item terrorBirdEgg;
    public static Item cultivatedTerrorBirdEgg;
    public static Item terrorBirdMeat;
    public static Item terrorBirdMeatCooked;
    public static Item quaggaMeat;
    public static Item quaggaMeatCooked;

    //Armor
    public static Item skullHelmet;
    public static Item ribCage;
    public static Item femurs;
    public static Item feet;
    //public static Item newArmor;
    //public static Item newArmor;
    //public static Item newArmor;
    //public static Item newArmor;
    //public static Item newArmor;
    //public static Item newArmor;
    //public static Item newArmor;
    //public static Item newArmor;

    //DNA - See DinoEnum
    //public static Item[] DNAItems= new Item[EnumDinoType.values().length];

    //public static Item newDinoDNA;
    //public static Item newDinoDNA;
    //public static Item newDinoDNA;
    //public static Item newDinoDNA;
    //public static Item newDinoDNA;

    //Animal Dna
    //public static Item animalDNA;
    public static Item dnaPig;
    public static Item dnaSheep;
    public static Item dnaCow;
    public static Item dnaChicken;
    public static Item dnaSmilodon;
    public static Item dnaMammoth;
    public static Item dnaDodo;
    public static Item dnaCoelacanth;
    public static Item dnaHorse;
    public static Item dnaQuagga;
    public static Item dnaTerrorBird;

    //Mob DNA
    //public static Item mobDNA;
    //public static Item dnaPigZombie;
    //public static Item dnaZombie;
    //public static Item dnaGhast;
    //public static Item dnaWither;
    //public static Item dnaSpider;
    //public static Item dnaSkeleton;

    //Ancient Egg - See DinoEnum
    //public static Item[] EGGItems= new Item[EnumDinoType.values().length];

    //Embryos
    //public static Item embyoSyringe;
    public static Item embryoPig;
    public static Item embryoSheep;
    public static Item embryoCow;
    public static Item embryoChicken;
    public static Item embryoSmilodon;
    public static Item embryoMammoth;
    public static Item embryoHorse;
    public static Item embryoQuagga;

    //Item Food
    public static Item cookedChickenSoup;
    public static Item rawChickenSoup;
    public static Item chickenEss;
    public static Item sjl;
    //public static Item[] RAWItems= new Item[EnumDinoType.values().length];
    public static Item cookedDinoMeat;

    //Music Discs
    public static Item fossilrecordBones;

    //Config ID INTs
    
    //Achievements
    public static int a_firstEggID;
    public static int a_allEggsID;
    public static int a_foundFossilsID;
    public static int a_pigBossID;
    public static int a_permafrostID;
    public static int a_archWorkbenchID;
    public static int a_analyzerID;
    public static int a_cultVatID;
    public static int a_sifterID;
    public static int a_dinopediaID;
    public static int a_iceAgeID;
    public static int a_theKingID;
    public static int a_pigBossOnEarthID;
    
    public static int e_archeologyID;
    public static int e_paleontologyID;
    
    //Blocks
    public static int blockFossilID;
    public static int blockSkullID;
    public static int skullLanternID;
    public static int blockanalyzerIdleID;
    public static int blockanalyzerActiveID;
    public static int blockcultivateIdleID;
    public static int blockcultivateActiveID;
    public static int blockworktableIdleID;
    public static int blockworktableActiveID;
    public static int blockTimeMachineID;
    public static int fernsID;
    //public static int fernUpperID;
    public static int drumID;
    public static int feederIdleID;
    public static int feederActiveID;
    public static int blockPermafrostID;
    public static int blockIcedStoneID;
    public static int volcanicAshID;
    public static int volcanicRockID;
    public static int volcanicRockHotID;
    public static int tarID;
    public static int palmLogID;
    public static int palmLeavesID;
    public static int palmSapID;
    public static int palaePlanksID;
    public static int palaeSingleSlabID;
    public static int palaeDoubleSlabID;
    public static int palaeStairsID;
    public static int sarracinaID;
    public static int volcanicBrickID;
    public static int amberOreID;
    public static int ancientStoneID;
    public static int ancientStonebrickID;
    public static int ancientWoodID;
    public static int ancientWoodPillarID;
    public static int ancientGlassID;
    public static int ancientWoodPlateID;
    public static int ancientWoodStairsID;
    public static int ancientWoodSingleSlabID;
    public static int ancientWoodDoubleSlabID;
    public static int ancientStoneStairsID;
    public static int ancientStoneSingleSlabID;
    public static int ancientStoneDoubleSlabID;
    public static int marbleID;
    public static int figurineBlockID;
    public static int blockSifterIdleID;
    public static int blockSifterActiveID;
    public static int volcanicStairsID;
    public static int volcanicSingleSlabID;
    public static int volcanicDoubleSlabID;
    public static int vaseAmphoraBlockID;
    public static int vaseKylixBlockID;
    public static int vaseVoluteBlockID;
    
    
    //Items
    public static int biofossilID;
    public static int relicID;
    public static int stoneboardID;
    public static int ancientSwordID;
    public static int brokenSwordID;
    public static int fernSeedID;
    public static int ancienthelmetID;
    public static int brokenhelmetID;
    public static int skullStickID;
    public static int gemID;
    public static int gemAxeID;
    public static int gemPickaxeID;
    public static int gemSwordID;
    public static int gemHoeID;
    public static int gemShovelID;
    public static int dinoPediaID;
    public static int archNotebookID;
    public static int emptyShellID;
    public static int magicConchID;
    public static int icedMeatID;
    public static int woodjavelinID;
    public static int stonejavelinID;
    public static int ironjavelinID;
    public static int goldjavelinID;
    public static int diamondjavelinID;
    public static int ancientJavelinID;
    public static int whipID;
    public static int legBoneID;
    public static int clawID;
    public static int footID;
    public static int skullID;
    public static int brokenSaplingID;
    public static int amberID;
    public static int ancientVaseID;
    public static int ancientVaseBrokenID;
    public static int boneArrowID;
    public static int boneBowID;
    public static int boneGlueID;
    public static int boneRodID;
    public static int boneSwordID;
    public static int powderyStringID;
    public static int animalCoinID;
    public static int dinoCoinID;
    public static int dodoEggID;
    public static int cultivatedDodoEggID;
    public static int dodoWingID;
    public static int dodoWingCookedID;
    public static int figurineItemID;
    public static int brokenHeadRelicID;
    public static int potteryShardsID;
    public static int livingCoelacanthID;
    public static int dinosaurModelsID;
    public static int armBoneID;
    public static int dinoRibCageID;
    public static int vertebraeID;
    public static int terrorBirdEggID;
    public static int cultivatedTerrorBirdEggID;
    public static int terrorBirdMeatID;
    public static int terrorBirdMeatCookedID;
    public static int quaggaMeatID;
    public static int quaggaMeatCookedID;

    //Armor
    public static int skullHelmetID;
    public static int ribCageID;
    public static int femursID;
    public static int feetID;
    //public static int newArmorID;
    //public static int newArmorID;
    //public static int newArmorID;
    //public static int newArmorID;
    //public static int newArmorID;
    //public static int newArmorID;
    //public static int newArmorID;
    //public static int newArmorID;

    //DNA
    //public static int dnaID;
    public static int[] DNAIds = new int[EnumDinoType.values().length];
    //public static int newDinoDNAID;
    //public static int newDinoDNAID;
    //public static int newDinoDNAID;
    //public static int newDinoDNAID;
    //public static int newDinoDNAID;

    //Animal DNA
    //public static int animalDNAID;
    public static int dnaPigID;
    public static int dnaSheepID;
    public static int dnaCowID;
    public static int dnaChickenID;
    public static int dnaSmilodonID;
    public static int dnaMammothID;
    public static int dnaDodoID;
    public static int dnaCoelacanthID;
    public static int dnaHorseID;
    public static int dnaQuaggaID;
    public static int dnaTerrorBirdID;

    //Mob DNA
    //public static int mobDNAID;
    //public static int dnaPigZombieID;
    //public static int dnaZombieID;
    //public static int dnaGhastID;
    //public static int dnaWitherID;
    //public static int dnaSpiderID;
    //public static int dnaSkeletonID;

    //Ancient Egg
    //public static int ancienteggID;
    public static int[] EGGIds = new int[EnumDinoType.values().length];
    //public static int eggNewID;
    //public static int eggNewID;
    //public static int eggNewID;
    //public static int eggNewID;

    //Embryos
    //public static int embyoSyringeID;
    public static int embryoPigID;
    public static int embryoSheepID;
    public static int embryoCowID;
    public static int embryoChickenID;
    public static int embryoSmilodonID;
    public static int embryoMammothID;
    public static int embryoHorseID;
    public static int embryoQuaggaID;
//   public static int embryoDodoID;
    //public static int embryoPigZombieID;
    //public static int embryoZombieID;
    //public static int embryoGhastID;
    //public static int embryoWitherID;
    //public static int embryoSkeletonID;
    //public static int embryoSpiderID;

    //Food
    public static int cookedChickenSoupID;
    public static int rawChickenSoupID;
    public static int chickenEssID;
    public static int sjlID;

    public static int[] RAWIds = new int[EnumDinoType.values().length];
    public static int cookedDinoMeatID;

    public static int fossilRecordID;

    static EnumArmorMaterial bone = EnumHelper.addArmorMaterial("Bone", 25, new int[] {2, 7, 6, 2}, 15);
    static EnumToolMaterial scarab = EnumHelper.addToolMaterial("Scarab", 3, 1861, 8.0F, 4.0F, 25);
    static EnumArmorMaterial scarabArmor = EnumHelper.addArmorMaterial("Scarab", 50, new int[]{3, 8, 6, 3}, 10);
    static EnumArmorMaterial RELIC = EnumHelper.addArmorMaterial("Relic", 5, new int[]{1, 3, 2, 1}, 15); 		
    
    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
//       Localizations.loadLanguages();
    	MinecraftForge.EVENT_BUS.register(new DinoSoundHandler());
        MinecraftForge.EVENT_BUS.register(new FossilBonemealEvent());
        VillagerRegistry.instance().registerVillageTradeHandler(10, new FossilTradeHandler());
    	VillagerRegistry.instance().registerVillagerId(10);
        config = new Configuration(event.getSuggestedConfigurationFile());

        try
        {
            config.load();
            
            //Achievements
            a_firstEggID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_FIRST_EGG, 5011).getInt();
            a_allEggsID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_ALL_EGGS, 5012).getInt();
            a_foundFossilsID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_FOUND_FOSSILS, 5013).getInt();
            a_pigBossID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_PIGBOSS, 5014).getInt();
            a_permafrostID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_PERMAFROST, 5015).getInt();
            a_archWorkbenchID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_ARCHWORKBENCH, 5016).getInt();
            a_analyzerID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_ANALYZER, 5017).getInt();
            a_cultVatID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_CULTVAT, 5018).getInt();
            a_sifterID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_SIFTER, 5019).getInt();
            a_dinopediaID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_DINOPEDIA, 5020).getInt();
            a_iceAgeID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_ICEAGE, 5021).getInt();
            a_theKingID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_THEKING, 5022).getInt();
            a_pigBossOnEarthID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ACHIEVEMENT_PIGBOSS, 5023).getInt();
            
          //Enchantments
            e_paleontologyID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ENCHANTMENT_PALEONTOLOGY, 90).getInt();
            e_archeologyID = config.get(Configuration.CATEGORY_GENERAL, LocalizationStrings.ENCHANTMENT_ARCHEOLOGY, 91).getInt();
            
            //Blocks
            blockFossilID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_FOSSIL_NAME, 3000).getInt();
            blockSkullID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_SKULL_NAME, 3001).getInt();
            skullLanternID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.SKULL_LANTERN_NAME, 3002).getInt();
            blockanalyzerIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_ANALYZER_IDLE_NAME, 3003).getInt();
            blockanalyzerActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_ANALYZER_ACTIVE_NAME, 3004).getInt();
            blockcultivateIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_CULTIVATE_IDLE_NAME, 3005).getInt();
            blockcultivateActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_CULTIVATE_ACTIVE_NAME, 3006).getInt();
            blockworktableIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_WORKTABLE_IDLE_NAME, 3007).getInt();
            blockworktableActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_WORKTABLE_ACTIVE_NAME, 3008).getInt();
            blockTimeMachineID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_TIMEMACHINE_NAME, 3009).getInt();
            fernsID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.FERN_BLOCK_NAME, 3010).getInt();
            drumID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.DRUM_NAME, 3012).getInt();
            feederIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.FEEDER_IDLE_NAME, 3013).getInt();
            feederActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.FEEDER_ACTIVE_NAME, 3014).getInt();
            blockPermafrostID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_PERMAFROST_NAME, 3015).getInt();
            blockIcedStoneID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_ICEDSTONE_NAME, 3016).getInt();
            volcanicAshID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VOLCANIC_ASH_NAME, 3017).getInt();
            volcanicRockID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VOLCANIC_ROCK_NAME, 3018).getInt();
            volcanicRockHotID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VOLCANIC_ROCK_HOT_NAME, 3019).getInt();
            tarID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.TAR_NAME, 3020).getInt();
            palmLogID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.PALAE_LOG_NAME, 3021).getInt();
            palmLeavesID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.PALAE_LEAVES_NAME, 3022).getInt();
            palmSapID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.PALAE_SAP_NAME, 3023).getInt();
            palaePlanksID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.PALAE_PLANKS_NAME, 3024).getInt();
            palaeSingleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.PALAE_SINGLESLAB_NAME, 3024).getInt();
            palaeDoubleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.PALAE_DOUBLESLAB_NAME, 3025).getInt();
            palaeStairsID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.PALAE_STAIRS_NAME, 3026).getInt();
            sarracinaID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.SARRACINA_NAME, 3027).getInt();
            volcanicBrickID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VOLCANIC_BRICK_NAME, 3028).getInt();
            amberOreID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.AMBER_ORE_NAME, 3029).getInt();
            ancientStoneID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_STONE_NAME, 3030).getInt();
            ancientStonebrickID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_STONE_BRICK_NAME, 3031).getInt();
            ancientWoodID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_WOOD_NAME, 3032).getInt();
            ancientWoodPillarID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_WOOD_PILLAR_NAME, 3033).getInt();
            ancientGlassID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_GLASS_NAME, 3034).getInt();
            ancientWoodPlateID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_WOOD_PLATE_NAME, 3035).getInt();
            ancientWoodStairsID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_WOOD_STAIRS_NAME, 3036).getInt();
            ancientWoodSingleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_WOOD_SINGLESLAB_NAME, 3037).getInt();
            ancientWoodDoubleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_WOOD_DOUBLESLAB_NAME, 3038).getInt();
            ancientStoneStairsID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_STONE_STAIRS_NAME, 3039).getInt();
            ancientStoneSingleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_STONE_SINGLESLAB_NAME, 3040).getInt();
            ancientStoneDoubleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.ANCIENT_STONE_DOUBLESLAB_NAME, 3041).getInt();
            marbleID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.MARBLE_NAME, 3042).getInt();
            figurineBlockID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.FIGURINE_NAME, 3043).getInt();
            blockSifterIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_SIFTER_IDLE, 3044).getInt();
            blockSifterActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.BLOCK_SIFTER_ACTIVE, 3045).getInt();
            volcanicStairsID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VOLCANIC_STAIRS, 3046).getInt();
            volcanicSingleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VOLCANIC_SINGLESLAB_NAME, 3047).getInt();
            volcanicDoubleSlabID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VOLCANIC_DOUBLESLAB_NAME, 3048).getInt();
            vaseVoluteBlockID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VASE_VOLUTE, 3049).getInt();
            vaseAmphoraBlockID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VASE_AMPHORA, 3050).getInt();
            vaseKylixBlockID = config.getBlock(Configuration.CATEGORY_BLOCK, LocalizationStrings.VASE_KYLIX, 3051).getInt();            
            
            //Items
            biofossilID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BIO_FOSSIL_NAME, 10000).getInt();
            relicID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.RELIC_NAME, 10001).getInt();
            stoneboardID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.TABLET_NAME, 10002).getInt();
            ancientSwordID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ANCIENT_SWORD_NAME, 10003).getInt();
            brokenSwordID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BROKEN_SWORD_NAME, 10004).getInt();
            fernSeedID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.FERNSEED_NAME, 10005).getInt();
            ancienthelmetID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ANCIENT_HELMET_NAME, 10006).getInt();
            brokenhelmetID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BROKEN_HELMET_NAME, 10007).getInt();
            skullStickID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SKULL_STICK_NAME, 10008).getInt();
            gemID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SCARAB_GEM_NAME, 10009).getInt();
            gemAxeID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SCARAB_AXE_NAME, 10010).getInt();
            gemPickaxeID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SCARAB_PICKAXE_NAME, 10011).getInt();
            gemSwordID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SCARAB_SWORD_NAME, 10012).getInt();
            gemHoeID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SCARAB_HOE_NAME, 10013).getInt();
            gemShovelID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SCARAB_SHOVEL_NAME, 10014).getInt();
            dinoPediaID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DINOPEDIA_NAME, 10015).getInt();
            emptyShellID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMPTY_SHELL_NAME, 10016).getInt();
            magicConchID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.MAGIC_CONCH_NAME, 10017).getInt();
            icedMeatID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ICED_MEAT_NAME, 10018).getInt();
            woodjavelinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.WOOD_JAVELIN_NAME, 10019).getInt();
            stonejavelinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.STONE_JAVELIN_NAME, 10020).getInt();
            ironjavelinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.IRON_JAVELIN_NAME, 10021).getInt();
            goldjavelinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.GOLD_JAVELIN_NAME, 10022).getInt();
            diamondjavelinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DIAMOND_JAVELIN_NAME, 10023).getInt();
            ancientJavelinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ANCIENT_JAVELIN_NAME, 10024).getInt();
            whipID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.WHIP_NAME, 10025).getInt();
            legBoneID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.LEGBONE_NAME, 10026).getInt();
            clawID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.CLAW_NAME, 10027).getInt();
            footID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.FOOT_NAME, 10028).getInt();
            skullID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SKULL_NAME, 10029).getInt();
            brokenSaplingID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BROKEN_SAPLING_NAME, 10030).getInt();
            amberID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.AMBER_NAME, 10031).getInt();
            ancientVaseID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ANCIENT_VASE_NAME, 10032).getInt();
            ancientVaseBrokenID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ANCIENT_VASE_BROKEN_NAME, 10033).getInt();
            boneArrowID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BONE_ARROW_NAME, 10034).getInt();
            boneBowID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BONE_BOW_NAME, 10035).getInt();
            boneGlueID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BONE_GLUE_NAME, 10036).getInt();
            boneRodID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BONE_ROD_NAME, 10037).getInt();
            boneSwordID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BONE_SWORD_NAME, 10038).getInt();
            powderyStringID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.POWDERY_STRING_NAME, 10039).getInt();
            //ancientWoodPlateID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ANCIENTWOODPLATE_NAME, 10040).getInt();
            animalCoinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ANIMALCOIN_NAME, 10041).getInt();
            dinoCoinID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DINO_COIN_NAME, 10042).getInt();
            dodoEggID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DODO_EGG_NAME, 10043).getInt();
            cultivatedDodoEggID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.CULTIVATED_DODO_EGG_NAME, 10044).getInt();
            fossilRecordID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.FOSSILRECORD_NAME, 10045).getInt();
            livingCoelacanthID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.LIVING_COELACANTH_NAME, 10046).getInt();


            //Armor
            skullHelmetID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SKULL_HELMET_NAME, 10047).getInt();
            ribCageID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.RIBCAGE_NAME, 10048).getInt();
            femursID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.FEMURS_NAME, 10049).getInt();
            feetID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.FEET_NAME, 10050).getInt();
            brokenHeadRelicID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.BROKEN_HEAD_RELIC, 10051).getInt();
            //newArmorID = config.getItem(Configuration.CATEGORY_ITEM, "newArmor", 10052).getInt();
            //newArmorID = config.getItem(Configuration.CATEGORY_ITEM, "newArmor", 10053).getInt();
            //newArmorID = config.getItem(Configuration.CATEGORY_ITEM, "newArmor", 10054).getInt();
            //newArmorID = config.getItem(Configuration.CATEGORY_ITEM, "newArmor", 10055).getInt();
            //newArmorID = config.getItem(Configuration.CATEGORY_ITEM, "newArmor", 10056).getInt();
            //newArmorID = config.getItem(Configuration.CATEGORY_ITEM, "newArmor", 10057).getInt();
            //newArmorID = config.getItem(Configuration.CATEGORY_ITEM, "newArmor", 10058).getInt();

            //DNA
            //dnaID = config.getItem(Configuration.CATEGORY_ITEM, "dna", 10059).getInt();
            for (int i = 0; i < EnumDinoType.values().length; i++)
            {
                DNAIds[i] = config.getItem(Configuration.CATEGORY_ITEM, "dna" + EnumDinoType.values()[i].name(), 10060 + i).getInt();
            }

            //newDinoDNAID = config.getItem(Configuration.CATEGORY_ITEM, "newDinoDNA", 10071).getInt();
            //newDinoDNAID = config.getItem(Configuration.CATEGORY_ITEM, "newDinoDNA", 10072).getInt();
            //newDinoDNAID = config.getItem(Configuration.CATEGORY_ITEM, "newDinoDNA", 10073).getInt();
            //newDinoDNAID = config.getItem(Configuration.CATEGORY_ITEM, "newDinoDNA", 10074).getInt();
            //newDinoDNAID = config.getItem(Configuration.CATEGORY_ITEM, "newDinoDNA", 10075).getInt();
            //Animal DNA
            //animalDNAID = config.getItem(Configuration.CATEGORY_ITEM, "animalDNA", 10076).getInt();
            dnaSheepID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_SHEEP_NAME, 10078).getInt();
            dnaCowID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_COW_NAME, 10079).getInt();
            dnaChickenID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_CHICKEN_NAME, 10080).getInt();
            dnaSmilodonID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_SMILODON_NAME, 10081).getInt();
            dnaMammothID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_MAMMOTH_NAME, 10082).getInt();
            dnaDodoID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_DODO_NAME, 10083).getInt();
            dnaCoelacanthID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_COELACANTH_NAME, 10084).getInt();
            livingCoelacanthID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.LIVING_COELACANTH_NAME, 10085).getInt();
            dnaHorseID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_HORSE_NAME, 10086).getInt();
            dnaQuaggaID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_QUAGGA_NAME, 10087).getInt();
            dnaPigID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_PIG_NAME, 10088).getInt();
            //dnaSpiderID = config.getItem(Configuration.CATEGORY_ITEM, "dnaMammoth", 10088).getInt();
            //dnaSkeletonID = config.getItem(Configuration.CATEGORY_ITEM, "dnaMammoth", 10089).getInt();

            //Ancient Egg
            //ancienteggID = config.getItem(Configuration.CATEGORY_ITEM, "ancientegg", 10090).getInt();
            for (int i = 0; i < (EnumDinoType.values().length); i++)
            {
                EGGIds[i] = config.getItem(Configuration.CATEGORY_ITEM, "egg" + EnumDinoType.values()[i].name(), 10091 + i).getInt();
            }

            //Embryos
            //embyoSyringeID = config.getItem(Configuration.CATEGORY_ITEM, "embyoSyringe", 10107).getInt();
            embryoSheepID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_SHEEP_NAME, 10109).getInt();
            embryoCowID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_COW_NAME, 10110).getInt();
            embryoChickenID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_CHICKEN_NAME, 10111).getInt();
            embryoSmilodonID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_SMILODON_NAME, 10112).getInt();
            embryoMammothID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_MAMMOTH_NAME, 10113).getInt();
            embryoHorseID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_HORSE_NAME, 10114).getInt();
            embryoQuaggaID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_QUAGGA_NAME, 10114).getInt();
            embryoPigID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EMBRYO_PIG_NAME, 10115).getInt();
            //embryoWitherID = config.getItem(Configuration.CATEGORY_ITEM, "embryoWither", 10117).getInt();
            //embryoSkeletonID = config.getItem(Configuration.CATEGORY_ITEM, "embryoSkeleton", 10118).getInt();
            //embryoSpiderID = config.getItem(Configuration.CATEGORY_ITEM, "embryoSpider", 10119).getInt();
            //Food
            cookedChickenSoupID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.COOKED_CHICKEN_SOUP_NAME, 10120).getInt();
            rawChickenSoupID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.RAW_CHICKEN_SOUP_NAME, 10121).getInt();
            chickenEssID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.EOC_NAME, 10122).getInt();
            sjlID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.SJL_NAME, 10123).getInt();
            dodoWingID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DODO_WING_NAME, 10200).getInt();
            dodoWingCookedID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DODO_WING_COOKED_NAME, 10201).getInt();

            cookedDinoMeatID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DINO_STEAK_NAME, 10124).getInt();

            //DINOSAUR IDS START AT 10125, GIVE PLENTY OF BUFFER ROOM
            for (int i = 0; i < EnumDinoType.values().length; i++)
            {
                RAWIds[i] = config.getItem(Configuration.CATEGORY_ITEM, "raw" + EnumDinoType.values()[i].name(), 10125 + i).getInt();
            }

            figurineItemID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ITEM_FIGURINE_NAME, 10200).getInt();
            potteryShardsID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.POTTERY_SHARDS, 10201).getInt();
            dinosaurModelsID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DINOSAUR_MODELS, 10202).getInt();
            armBoneID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.ARM_BONE_NAME, 10203).getInt();
            dinoRibCageID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DINO_RIB_CAGE_NAME, 10204).getInt();
            vertebraeID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.VERTEBRAE_NAME, 10205).getInt();
            
            terrorBirdEggID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.TERROR_BIRD_EGG_NAME, 10206).getInt();
            cultivatedTerrorBirdEggID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.CULTIVATED_TERROR_BIRD_EGG_NAME, 10207).getInt();
            dnaTerrorBirdID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.DNA_TERROR_BIRD_NAME, 10208).getInt();
            terrorBirdMeatID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.TERROR_BIRD_MEAT, 10209).getInt();
            terrorBirdMeatCookedID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.TERROR_BIRD_MEAT_COOKED, 10210).getInt();
            quaggaMeatID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.QUAGGA_MEAT, 10211).getInt();
            quaggaMeatCookedID = config.getItem(Configuration.CATEGORY_ITEM, LocalizationStrings.QUAGGA_MEAT_COOKED, 10212).getInt();

            //Config options
            FossilOptions.Gen_Palaeoraphe = config.get("option", "Palaeoraphe", false).getBoolean(false);
            FossilOptions.Gen_Academy = config.get("option", "Academy", true).getBoolean(true);
            FossilOptions.Gen_Ships = config.get("option", "Ships", true).getBoolean(true);
            FossilOptions.Heal_Dinos = config.get("option", "Heal_Dinos", true).getBoolean(true);
            FossilOptions.Dinos_Starve = config.get("option", "Dinos_Starve", true).getBoolean(true);
            FossilOptions.Dino_Block_Breaking = config.get("option", "Dino_Block_Breaking", true).getBoolean(true);
            FossilOptions.Skull_Overlay = config.get("option", "Skull_Overlay", false).getBoolean(false);
            FossilOptions.LoginMessage = config.get("option", "Display_Login_Message", true).getBoolean(false);
            FossilOptions.Anu_Spawn = config.get("option", "Anu_Spawn", false).getBoolean(false);
            FossilOptions.Anu_Allowed_Overworld = config.get("option", "Anu_Allowed_Overworld", false).getBoolean(false);
            FossilOptions.AllowBreeding = config.get("option", "Allow_Dinosaur_Breeding", true).getBoolean(true);

            
            //Dinosaur Feathers
            FossilOptions.TRexFeathers = config.get("toggle_feathers", "TRex Feathers", false).getBoolean(false);
            FossilOptions.DeinonychusFeathers = config.get("toggle_feathers", "Deinonychus Feathers", true).getBoolean(true);
            FossilOptions.GallimimusFeathers = config.get("toggle_feathers",  "Gallimimus Feathers", false).getBoolean(false);
            FossilOptions.CompsognathusFeathers = config.get("toggle_feathers",  "Compsognathus Feathers", false).getBoolean(false);
            FossilOptions.VelociraptorFeathers = config.get("toggle_feathers",  "Velociraptor Feathers", false).getBoolean(false);
            
            //Enchantment Toggle
            FossilOptions.AllowTableEnchantments = config.get("option", "Allow Table Enchantments", true).getBoolean(true);
            FossilOptions.AllowBookEnchantments = config.get("option", "Allow Book Enchantments", true).getBoolean(true);

            
        }
        catch (Exception var7)
        {
            FMLLog.log(Level.SEVERE, var7, "Fossil Mod Not loading configuration", new Object[0]);
        }
        finally
        {
            config.save();
        }

        if (event.getSide() == Side.CLIENT)
        {
            proxy.registerSounds();
        }


        
        if (event.getSide() == Side.CLIENT)
        {
            proxy.registerEvents();
        }
        
        Log.setParent(FMLLog.getLogger());
        
        FossilAchievementHandler.loadAchievements();
       
    }
    
    //@SuppressWarnings("static-access")
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        LanguageRegistry.instance().addStringLocalization("itemGroup." + this.modid, "en_US", this.modid);
        
        paleontology = new EnchantmentPaleontology(e_paleontologyID, 2, EnumEnchantmentType.digger);
        archeology = new EnchantmentArcheology(e_archeologyID, 2, EnumEnchantmentType.digger);
        
        //Blocks
        skullLantern = new BlockFossilSkull(skullLanternID, true).setHardness(1.0F).setLightValue(0.9375F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.SKULL_LANTERN_NAME).setCreativeTab(this.tabFBlocks);
        blockanalyzerIdle = new BlockAnalyzer(blockanalyzerIdleID, false).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_ANALYZER_IDLE_NAME).setCreativeTab(this.tabFBlocks);
        blockanalyzerActive = new BlockAnalyzer(blockanalyzerActiveID, true).setLightValue(0.9375F).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_ANALYZER_ACTIVE_NAME);
        blockcultivateIdle = new BlockCultivate(blockcultivateIdleID, false).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_CULTIVATE_IDLE_NAME).setCreativeTab(this.tabFBlocks);
        blockcultivateActive = new BlockCultivate(blockcultivateActiveID, true).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_CULTIVATE_ACTIVE_NAME);
        blockworktableIdle = new BlockWorktable(blockworktableIdleID, false).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_WORKTABLE_IDLE_NAME).setCreativeTab(this.tabFBlocks);
        blockworktableActive = new BlockWorktable(blockworktableActiveID, true).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_WORKTABLE_ACTIVE_NAME);
        feederIdle = new BlockFeeder(feederIdleID).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.FEEDER_IDLE_NAME);
        feederActive = new BlockFeeder(feederActiveID).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.FEEDER_ACTIVE_NAME).setCreativeTab(this.tabFBlocks);
        blockTimeMachine = new BlockTimeMachine(blockTimeMachineID, 0, Material.glass).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_TIMEMACHINE_NAME).setCreativeTab(this.tabFBlocks);
        ferns = new BlockFern(fernsID, Material.plants).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab((CreativeTabs)null);
        drum = new BlockDrum(drumID).setHardness(0.8F).setCreativeTab(this.tabFBlocks);
        blockPermafrost = new BlockPermafrost(blockPermafrostID).setHardness(0.5F).setLightOpacity(3).setStepSound(Block.soundGrassFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_PERMAFROST_NAME).setCreativeTab(this.tabFBlocks);
        blockIcedStone = new BlockIcedStone(blockIcedStoneID).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_ICEDSTONE_NAME).setCreativeTab(this.tabFBlocks);
        blockFossil = new BlockFossil(blockFossilID, 1).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_FOSSIL_NAME).setCreativeTab(this.tabFBlocks);
        blockSkull = new BlockFossilSkull(blockSkullID, false).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_SKULL_NAME).setCreativeTab(this.tabFBlocks);
        palmLog = new BlockPalmLog(palmLogID).setStepSound(Block.soundWoodFootstep).setHardness(1.4F)/*.setResistance(1.0F)*/.setUnlocalizedName(LocalizationStrings.PALAE_LOG_NAME);
        palmLeaves = new BlockPalmLeaves(palmLeavesID).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setResistance(1F).setUnlocalizedName(LocalizationStrings.PALAE_LEAVES_NAME);
        palmSap = new BlockPalmSapling(palmSapID).setStepSound(Block.soundGrassFootstep).setHardness(0.2F).setResistance(1F).setUnlocalizedName(LocalizationStrings.PALAE_SAP_NAME);
        palaePlanks = new BlockPalaePlanks(palaePlanksID, Material.wood).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.PALAE_PLANKS_NAME);
        palaeDoubleSlab = (BlockHalfSlab)(new BlockPalaeSlab(palaeDoubleSlabID, true)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.PALAE_DOUBLESLAB_NAME);
        palaeSingleSlab = (BlockHalfSlab)(new BlockPalaeSlab(palaeSingleSlabID, false)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.PALAE_SINGLESLAB_NAME).setCreativeTab(this.tabFBlocks);
        palaeStairs = new BlockPalaeStairs(palaeStairsID, palaePlanks).setUnlocalizedName(LocalizationStrings.PALAE_STAIRS_NAME);
        volcanicAsh = new BlockVolcanicAsh(volcanicAshID).setHardness(0.2F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName(LocalizationStrings.VOLCANIC_ASH_NAME).setCreativeTab(this.tabFBlocks);
        volcanicRock = new BlockVolcanicRock(volcanicRockID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.VOLCANIC_ROCK_NAME).setCreativeTab(this.tabFBlocks);
        volcanicBrick = new BlockVolcanicBrick(volcanicBrickID).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(LocalizationStrings.VOLCANIC_BRICK_NAME).setCreativeTab(this.tabFBlocks);
        sarracina = new BlockSarracenia(sarracinaID).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName(LocalizationStrings.SARRACINA_NAME).setCreativeTab(this.tabFBlocks);
        tar = new BlockTar(tarID, Material.water).setHardness(100.0F).setUnlocalizedName(LocalizationStrings.TAR_NAME);
        amberOre  = new BlockAmberOre(amberOreID, Material.rock).setHardness(3.0F).setUnlocalizedName(LocalizationStrings.AMBER_ORE_NAME);
        ancientStone  = new BlockAncientStone(ancientStoneID).setHardness(1.5F).setUnlocalizedName(LocalizationStrings.ANCIENT_STONE_NAME);
        ancientStonebrick  = new BlockAncientStonebrick(ancientStonebrickID).setHardness(1.5F).setUnlocalizedName(LocalizationStrings.ANCIENT_STONE_BRICK_NAME);
        ancientWood  = new BlockAncientWood(ancientWoodID, Material.wood).setHardness(2.0F).setUnlocalizedName(LocalizationStrings.ANCIENT_WOOD_NAME);
        ancientWoodPillar = new BlockAncientWoodPillar(ancientWoodPillarID, Material.wood).setHardness(2.0F).setUnlocalizedName(LocalizationStrings.ANCIENT_WOOD_PILLAR_NAME);
        ancientGlass = new BlockAncientGlass(ancientGlassID, Material.glass, false).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName(LocalizationStrings.ANCIENT_GLASS_NAME);
        ancientWoodPlate = new BlockAncientWoodPlate(ancientWoodPlateID, Material.wood).setHardness(0.6F).setUnlocalizedName(LocalizationStrings.ANCIENT_WOOD_PLATE_NAME);
        ancientWoodStairs = new BlockAncientWoodStairs(ancientWoodStairsID, ancientWood).setUnlocalizedName(LocalizationStrings.ANCIENT_WOOD_STAIRS_NAME);
        ancientWoodDoubleSlab = (BlockHalfSlab)(new BlockAncientWoodSlab(ancientWoodDoubleSlabID, true)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.ANCIENT_WOOD_DOUBLESLAB_NAME);
        ancientWoodSingleSlab = (BlockHalfSlab)(new BlockAncientWoodSlab(ancientWoodSingleSlabID, false)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.ANCIENT_WOOD_SINGLESLAB_NAME).setCreativeTab(this.tabFBlocks);
        ancientStoneStairs = new BlockAncientStoneStairs(ancientStoneStairsID, ancientStonebrick).setUnlocalizedName(LocalizationStrings.ANCIENT_STONE_STAIRS_NAME);
        ancientStoneDoubleSlab = (BlockHalfSlab)(new BlockAncientStoneSlab(ancientStoneDoubleSlabID, true)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.ANCIENT_STONE_DOUBLESLAB_NAME);
        ancientStoneSingleSlab = (BlockHalfSlab)(new BlockAncientStoneSlab(ancientStoneSingleSlabID, false)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.ANCIENT_STONE_SINGLESLAB_NAME).setCreativeTab(this.tabFBlocks);
        marble  = new BlockMarble(marbleID).setHardness(2.0F).setHardness(1.5F).setUnlocalizedName(LocalizationStrings.MARBLE_NAME);
        figurineBlock = new BlockFigurine(figurineBlockID).setUnlocalizedName(LocalizationStrings.FIGURINE_NAME);
        blockSifterIdle = new BlockSifter(blockSifterIdleID, false).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_SIFTER_IDLE).setCreativeTab(this.tabFBlocks);
        blockSifterActive = new BlockSifter(blockSifterActiveID, true).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(LocalizationStrings.BLOCK_SIFTER_ACTIVE);
        volcanicStairs = new BlockVolcanicStairs(volcanicStairsID, volcanicBrick).setUnlocalizedName(LocalizationStrings.VOLCANIC_STAIRS);
        volcanicDoubleSlab = (BlockHalfSlab)(new BlockVolcanicSlab(volcanicDoubleSlabID, true)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.VOLCANIC_DOUBLESLAB_NAME);
        volcanicSingleSlab = (BlockHalfSlab)(new BlockVolcanicSlab(volcanicSingleSlabID, false)).setHardness(1.4F).setResistance(7.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(LocalizationStrings.VOLCANIC_SINGLESLAB_NAME).setCreativeTab(this.tabFBlocks);
        vaseVoluteBlock = new BlockVaseVolute(vaseVoluteBlockID).setUnlocalizedName(LocalizationStrings.VASE_VOLUTE);
        vaseAmphoraBlock = new BlockVaseAmphora(vaseAmphoraBlockID).setUnlocalizedName(LocalizationStrings.VASE_AMPHORA);
        vaseKylixBlock = new BlockVaseKylix(vaseKylixBlockID).setUnlocalizedName(LocalizationStrings.VASE_KYLIX);       
        
        Block.fire.setBurnProperties(Fossil.ferns.blockID, 30, 60);
        Block.fire.setBurnProperties(Fossil.palmLeaves.blockID, 30, 60);
        Block.fire.setBurnProperties(Fossil.palaePlanks.blockID, 5, 20);
        Block.fire.setBurnProperties(Fossil.tar.blockID, 500, 1);
        Block.fire.setBurnProperties(Fossil.ancientWood.blockID, 10, 20);
        Block.fire.setBurnProperties(Fossil.ancientWoodPlate.blockID, 5, 10);
        Block.fire.setBurnProperties(Fossil.ancientWoodStairs.blockID, 10, 20);
        //Items
        biofossil = new ItemBioFossil(biofossilID).setUnlocalizedName(LocalizationStrings.BIO_FOSSIL_NAME).setCreativeTab(this.tabFItems);
        relic = new ForgeItem(relicID, "Relic_Scrap").setUnlocalizedName(LocalizationStrings.RELIC_NAME).setCreativeTab(this.tabFItems);
        stoneboard = new ItemStoneBoard(stoneboardID).setUnlocalizedName(LocalizationStrings.TABLET_NAME).setCreativeTab(this.tabFItems);
        ancientSword = new ItemAncientsword(ancientSwordID).setUnlocalizedName(LocalizationStrings.ANCIENT_SWORD_NAME).setCreativeTab(this.tabFCombat);
        brokenSword = new ForgeItem(brokenSwordID, "Broken_Ancient_Sword").setMaxStackSize(1).setUnlocalizedName(LocalizationStrings.BROKEN_SWORD_NAME).setCreativeTab(this.tabFMaterial);
        fernSeed = new ItemFernSeed(fernSeedID, ferns.blockID).setUnlocalizedName(LocalizationStrings.FERNSEED_NAME).setCreativeTab(this.tabFItems);
        ancienthelmet = new ItemAncientHelmet(ancienthelmetID, EnumArmorMaterial.IRON, 3, 0).setUnlocalizedName(LocalizationStrings.ANCIENT_HELMET_NAME).setCreativeTab(this.tabFCombat);
        brokenhelmet = new ForgeItem(brokenhelmetID, "Broken_Ancient_Helm").setMaxStackSize(1).setUnlocalizedName(LocalizationStrings.BROKEN_HELMET_NAME).setCreativeTab(this.tabFMaterial);
        skullStick = new ForgeItem(skullStickID, "Skull_Stick").setUnlocalizedName(LocalizationStrings.SKULL_STICK_NAME).setCreativeTab(this.tabFItems);
        gem = new ForgeItem(gemID, "Scarab_Gem").setUnlocalizedName(LocalizationStrings.SCARAB_GEM_NAME).setCreativeTab(this.tabFItems);
        gemAxe = new ForgeAxe(gemAxeID, scarab, "Gem_Axe").setUnlocalizedName(LocalizationStrings.SCARAB_AXE_NAME).setCreativeTab(this.tabFTools);
        gemPickaxe = new ForgePickaxe(gemPickaxeID, scarab, "Gem_Pickaxe").setUnlocalizedName(LocalizationStrings.SCARAB_PICKAXE_NAME).setCreativeTab(this.tabFTools);
        gemSword = new ForgeSword(gemSwordID, scarab, "Gem_Sword").setUnlocalizedName(LocalizationStrings.SCARAB_SWORD_NAME).setCreativeTab(this.tabFCombat);;
        gemHoe = new ForgeHoe(gemHoeID, scarab, "Gem_Hoe").setUnlocalizedName(LocalizationStrings.SCARAB_HOE_NAME).setCreativeTab(this.tabFTools);
        gemShovel = new ForgeShovel(gemShovelID, scarab, "Gem_Shovel").setUnlocalizedName(LocalizationStrings.SCARAB_SHOVEL_NAME).setCreativeTab(this.tabFTools);
        dinoPedia = new ForgeItem(dinoPediaID, "Dinopedia").setUnlocalizedName(LocalizationStrings.DINOPEDIA_NAME).setCreativeTab(this.tabFItems);
        emptyShell = new ForgeItem(emptyShellID, "Empty_Shell").setUnlocalizedName(LocalizationStrings.EMPTY_SHELL_NAME).setCreativeTab(this.tabFItems);
        magicConch = new ItemMagicConch(magicConchID).setUnlocalizedName(LocalizationStrings.MAGIC_CONCH_NAME).setCreativeTab(this.tabFTools);
        icedMeat = new ItemIcedMeat(icedMeatID, EnumToolMaterial.EMERALD).setUnlocalizedName(LocalizationStrings.ICED_MEAT_NAME).setCreativeTab(this.tabFItems);
        amber = new ItemAmber(amberID).setUnlocalizedName(LocalizationStrings.AMBER_NAME).setCreativeTab(this.tabFItems);
        woodjavelin = new ItemJavelin(woodjavelinID, EnumToolMaterial.WOOD, "Wooden_Javelin").setUnlocalizedName(LocalizationStrings.WOOD_JAVELIN_NAME).setCreativeTab(this.tabFCombat);
        stonejavelin = new ItemJavelin(stonejavelinID, EnumToolMaterial.STONE, "Stone_Javelin").setUnlocalizedName(LocalizationStrings.STONE_JAVELIN_NAME).setCreativeTab(this.tabFCombat);
        ironjavelin = new ItemJavelin(ironjavelinID, EnumToolMaterial.IRON, "Iron_Javelin").setUnlocalizedName(LocalizationStrings.IRON_JAVELIN_NAME).setCreativeTab(this.tabFCombat);
        goldjavelin = new ItemJavelin(goldjavelinID, EnumToolMaterial.GOLD, "Gold_Javelin").setUnlocalizedName(LocalizationStrings.GOLD_JAVELIN_NAME).setCreativeTab(this.tabFCombat);
        diamondjavelin = new ItemJavelin(diamondjavelinID, EnumToolMaterial.EMERALD, "Diamond_Javelin").setUnlocalizedName(LocalizationStrings.DIAMOND_JAVELIN_NAME).setCreativeTab(this.tabFCombat);
        ancientJavelin = new ItemJavelin(ancientJavelinID, scarab, "Ancient_Javelin").setUnlocalizedName(LocalizationStrings.ANCIENT_JAVELIN_NAME).setCreativeTab(this.tabFCombat);
        whip = new ItemWhip(whipID).setUnlocalizedName(LocalizationStrings.WHIP_NAME).setCreativeTab(this.tabFTools);

        legBone = new ItemDinosaurBones(legBoneID, "legBone").setUnlocalizedName(LocalizationStrings.LEGBONE_NAME);
        claw = new ItemDinosaurBones(clawID, "uniqueItem").setUnlocalizedName(LocalizationStrings.CLAW_NAME);
        foot = new ItemDinosaurBones(footID, "foot").setUnlocalizedName(LocalizationStrings.FOOT_NAME);
        skull = new ItemDinosaurBones(skullID, "skull").setUnlocalizedName(LocalizationStrings.SKULL_NAME);
        armBone = new ItemDinosaurBones(armBoneID, "armBone").setUnlocalizedName(LocalizationStrings.ARM_BONE_NAME);
        dinoRibCage = new ItemDinosaurBones(dinoRibCageID, "dinoRibCage").setUnlocalizedName(LocalizationStrings.DINO_RIB_CAGE_NAME);
        vertebrae = new ItemDinosaurBones(vertebraeID, "vertebrae").setUnlocalizedName(LocalizationStrings.VERTEBRAE_NAME);
        //dinosaurModels = new ItemDinosaurModels(dinosaurModelsID).setUnlocalizedName(LocalizationStrings.DINOSAUR_MODELS).setCreativeTab(this.tabFBones);

        brokenSapling = new ForgeItem(brokenSaplingID, "Palae_Fossil").setUnlocalizedName(LocalizationStrings.BROKEN_SAPLING_NAME).setCreativeTab(this.tabFMaterial);
        dodoEgg = new ItemDodoEgg(dodoEggID).setUnlocalizedName(LocalizationStrings.DODO_EGG_NAME);
        cultivatedDodoEgg = new ItemCultivatedDodoEgg(cultivatedDodoEggID).setUnlocalizedName(LocalizationStrings.CULTIVATED_DODO_EGG_NAME);
       // archNotebook = new ForgeItem(archNotebookID, "Arch_Notebook").setUnlocalizedName(LocalizationStrings.ARCH_NOTEBOOK_NAME).setCreativeTab(this.tabFItems);
        potteryShards = new ForgeItem(potteryShardsID, "PotteryShard").setUnlocalizedName(LocalizationStrings.POTTERY_SHARDS).setCreativeTab(this.tabFItems);
       // brokenHeadRelic = new ItemHeadRelic(brokenHeadRelicID, RELIC, 3, 0).setUnlocalizedName(LocalizationStrings.BROKEN_HEAD_RELIC).setCreativeTab(Fossil.tabFTest);
        livingCoelacanth = new ItemLivingCoelacanth(livingCoelacanthID, 1).setUnlocalizedName(LocalizationStrings.LIVING_COELACANTH_NAME).setCreativeTab(this.tabFMaterial);
        terrorBirdEgg = new ItemTerrorBirdEgg(terrorBirdEggID, false).setUnlocalizedName(LocalizationStrings.TERROR_BIRD_EGG_NAME);
        cultivatedTerrorBirdEgg = new ItemTerrorBirdEgg(cultivatedTerrorBirdEggID, true).setUnlocalizedName(LocalizationStrings.CULTIVATED_TERROR_BIRD_EGG_NAME);
       
        //BoneArmor
        skullHelmet = new ItemSkullHelmet(skullHelmetID, bone, 3, 0).setUnlocalizedName(LocalizationStrings.SKULL_HELMET_NAME).setCreativeTab(Fossil.tabFCombat);
        ribCage = new ItemRibCage(ribCageID, bone, 3, 1).setUnlocalizedName(LocalizationStrings.RIBCAGE_NAME).setCreativeTab(Fossil.tabFCombat);
        femurs = new ItemFemurs(femursID, bone, 3, 2).setUnlocalizedName(LocalizationStrings.FEMURS_NAME).setCreativeTab(Fossil.tabFCombat);
        feet = new ItemFeet(feetID, bone, 3, 3).setUnlocalizedName(LocalizationStrings.FEET_NAME).setCreativeTab(this.tabFCombat);

        //Ancient Egg
        //Moved to fossilEnums.EnumDinoType

        for (int i = 0; i < EnumDinoType.values().length; i++)
        {
            EnumDinoType.values()[i].EggItem = new ItemAncientEgg(EGGIds[i], i).setUnlocalizedName("egg" + EnumDinoType.values()[i].name()).setCreativeTab(this.tabFMaterial);
        }

        //DNA
        //Moved to fossilEnums.EnumDinoType
        for (int i = 0; i < EnumDinoType.values().length; i++)
        {
            EnumDinoType.values()[i].DNAItem = new ForgeItem(DNAIds[i], EnumDinoType.values()[i].name() + "_DNA").setUnlocalizedName("dna" + EnumDinoType.values()[i].name()).setCreativeTab(this.tabFMaterial);
        }

        //animalDNA
        //animalDNA = new ItemNonDinoDNA(animalDNAID);
        dnaPig = new ForgeItem(dnaPigID, "Pig_DNA").setUnlocalizedName(LocalizationStrings.DNA_PIG_NAME).setCreativeTab(this.tabFMaterial);
        dnaSheep = new ForgeItem(dnaSheepID, "Sheep_DNA").setUnlocalizedName(LocalizationStrings.DNA_SHEEP_NAME).setCreativeTab(this.tabFMaterial);
        dnaCow = new ForgeItem(dnaCowID, "Cow_DNA").setUnlocalizedName(LocalizationStrings.DNA_COW_NAME).setCreativeTab(this.tabFMaterial);
        dnaChicken = new ForgeItem(dnaChickenID, "Chicken_DNA").setUnlocalizedName(LocalizationStrings.DNA_CHICKEN_NAME).setCreativeTab(this.tabFMaterial);
        dnaSmilodon = new ForgeItem(dnaSmilodonID, "Smilodon_DNA").setUnlocalizedName(LocalizationStrings.DNA_SMILODON_NAME).setCreativeTab(this.tabFMaterial);
        dnaMammoth = new ForgeItem(dnaMammothID, "Mammoth_DNA").setUnlocalizedName(LocalizationStrings.DNA_MAMMOTH_NAME).setCreativeTab(this.tabFMaterial);
        dnaDodo = new ForgeItem(dnaDodoID, "Dodo_DNA").setUnlocalizedName(LocalizationStrings.DNA_DODO_NAME).setCreativeTab(this.tabFMaterial);
        dnaCoelacanth = new ForgeItem(dnaCoelacanthID, "Coelacanth_DNA").setUnlocalizedName(LocalizationStrings.DNA_COELACANTH_NAME).setCreativeTab(this.tabFMaterial);
        dnaHorse = new ForgeItem(dnaHorseID, "Horse_DNA").setUnlocalizedName(LocalizationStrings.DNA_HORSE_NAME).setCreativeTab(this.tabFMaterial);
        dnaQuagga = new ForgeItem(dnaQuaggaID, "Quagga_DNA").setUnlocalizedName(LocalizationStrings.DNA_QUAGGA_NAME).setCreativeTab(this.tabFMaterial);
        dnaTerrorBird = new ForgeItem(dnaTerrorBirdID, "TerrorBird/TerrorBird_DNA").setUnlocalizedName(LocalizationStrings.DNA_TERROR_BIRD_NAME).setCreativeTab(this.tabFMaterial);

        //Ebryos
        //embyoSyringe = new ItemEmbryoSyringe(embyoSyringeID);
        embryoPig = new ItemEmbryoSyringe(embryoPigID, 0).setUnlocalizedName(LocalizationStrings.EMBRYO_PIG_NAME).setCreativeTab(this.tabFItems);
        embryoSheep = new ItemEmbryoSyringe(embryoSheepID, 1).setUnlocalizedName(LocalizationStrings.EMBRYO_SHEEP_NAME).setCreativeTab(this.tabFItems);
        embryoCow = new ItemEmbryoSyringe(embryoCowID, 2).setUnlocalizedName(LocalizationStrings.EMBRYO_COW_NAME).setCreativeTab(this.tabFItems);
        embryoChicken = new ItemEmbryoSyringe(embryoChickenID, 3).setUnlocalizedName(LocalizationStrings.EMBRYO_CHICKEN_NAME).setCreativeTab(this.tabFItems);
        embryoSmilodon = new ItemEmbryoSyringe(embryoSmilodonID, 4).setUnlocalizedName(LocalizationStrings.EMBRYO_SMILODON_NAME).setCreativeTab(this.tabFItems);
        embryoMammoth = new ItemEmbryoSyringe(embryoMammothID, 5).setUnlocalizedName(LocalizationStrings.EMBRYO_MAMMOTH_NAME).setCreativeTab(this.tabFItems);
        embryoHorse = new ItemEmbryoSyringe(embryoHorseID, 6).setUnlocalizedName(LocalizationStrings.EMBRYO_HORSE_NAME).setCreativeTab(this.tabFItems);
        embryoQuagga = new ItemEmbryoSyringe(embryoQuaggaID, 7).setUnlocalizedName(LocalizationStrings.EMBRYO_QUAGGA_NAME).setCreativeTab(this.tabFItems);

        //Item Food
        //Moved to fossilEnums.EnumDinoType
        for (int i = 0; i < EnumDinoType.values().length; i++)
        {
            EnumDinoType.values()[i].DropItem = new ForgeFood(RAWIds[i], 3, 0.3F, true, EnumDinoType.values()[i].name() + "_Meat").setUnlocalizedName("raw" + EnumDinoType.values()[i].name()).setCreativeTab(this.tabFFood);
        }

        cookedDinoMeat = new ForgeFood(cookedDinoMeatID, 8, 0.8F, true, "Dino_Steak").setUnlocalizedName(LocalizationStrings.DINO_STEAK_NAME).setCreativeTab(this.tabFFood);
        cookedChickenSoup = new ForgeItem(cookedChickenSoupID, "Cooked_Chicken_Soup").setUnlocalizedName(LocalizationStrings.COOKED_CHICKEN_SOUP_NAME).setMaxStackSize(1).setContainerItem(Item.bucketEmpty).setCreativeTab(this.tabFFood);
        rawChickenSoup = new ForgeItem(rawChickenSoupID, "Raw_Chicken_Soup").setUnlocalizedName(LocalizationStrings.RAW_CHICKEN_SOUP_NAME).setMaxStackSize(1).setContainerItem(Item.bucketEmpty).setCreativeTab(this.tabFFood);
        chickenEss = new ItemChickenEss(chickenEssID, 10, 0.0F, false, "Essence_Of_Chicken").setUnlocalizedName(LocalizationStrings.EOC_NAME).setContainerItem(Item.glassBottle).setCreativeTab(this.tabFFood);
        sjl = new ForgeFood(sjlID, 8, 2.0F, false, "Sio_Chiu_Le").setUnlocalizedName(LocalizationStrings.SJL_NAME).setCreativeTab(this.tabFFood);
        dodoWing = new ForgeFood(dodoWingID,3, 0.3F, false, "Raw_Dodo_Wing").setPotionEffect(Potion.hunger.id, 30, 0, 0.3F).setUnlocalizedName(LocalizationStrings.DODO_WING_NAME).setCreativeTab(this.tabFFood);
        dodoWingCooked = new ForgeFood(dodoWingCookedID,8, 0.8F, false, "Cooked_Dodo_Wing").setUnlocalizedName(LocalizationStrings.DODO_WING_COOKED_NAME).setCreativeTab(this.tabFFood);
        terrorBirdMeat = new ForgeFood(terrorBirdMeatID, 2, 0.8F, false, "TerrorBird/TerrorBird_Meat").setPotionEffect(Potion.hunger.id, 30, 0, 0.3F).setUnlocalizedName(LocalizationStrings.TERROR_BIRD_MEAT).setCreativeTab(this.tabFFood);
        terrorBirdMeatCooked = new ForgeFood(terrorBirdMeatCookedID, 4, 0.8F, false, "TerrorBird/TerrorBird_Meat_Cooked").setUnlocalizedName(LocalizationStrings.TERROR_BIRD_MEAT_COOKED).setCreativeTab(this.tabFFood);
        quaggaMeat = new ForgeFood(quaggaMeatID, 2, 0.8F, false, "Quagga_Meat").setPotionEffect(Potion.hunger.id, 30, 0, 0.3F).setUnlocalizedName(LocalizationStrings.QUAGGA_MEAT).setCreativeTab(this.tabFFood);
        quaggaMeatCooked = new ForgeFood(quaggaMeatCookedID, 7, 1F, false, "Quagga_Meat_Cooked").setUnlocalizedName(LocalizationStrings.QUAGGA_MEAT_COOKED).setCreativeTab(this.tabFFood);

        //        figurineItem = new ItemFigurine(figurineItemID).setUnlocalizedName(LocalizationStrings.FIGURINE_NAME).setCreativeTab(this.tabFTest);
        // Music Discs
        fossilrecordBones = new ItemFossilRecord(fossilRecordID, "fossil:record_bones").setUnlocalizedName(LocalizationStrings.FOSSILRECORD_NAME);
        //Initiate some other things...
        BlockDispenser.dispenseBehaviorRegistry.putObject(Fossil.ancientJavelin, new BehaviorJavelinDispense(MinecraftServer.getServer(), -1));
        BlockDispenser.dispenseBehaviorRegistry.putObject(Fossil.woodjavelin, new BehaviorJavelinDispense(MinecraftServer.getServer(), 0));
        BlockDispenser.dispenseBehaviorRegistry.putObject(Fossil.stonejavelin, new BehaviorJavelinDispense(MinecraftServer.getServer(), 1));
        BlockDispenser.dispenseBehaviorRegistry.putObject(Fossil.ironjavelin, new BehaviorJavelinDispense(MinecraftServer.getServer(), 2));
        BlockDispenser.dispenseBehaviorRegistry.putObject(Fossil.diamondjavelin, new BehaviorJavelinDispense(MinecraftServer.getServer(), 3));
        BlockDispenser.dispenseBehaviorRegistry.putObject(Fossil.goldjavelin, new BehaviorJavelinDispense(MinecraftServer.getServer(), 4));
        BlockDispenser.dispenseBehaviorRegistry.putObject(Fossil.dodoEgg, new BehaviorDodoEggDispense(MinecraftServer.getServer(), 5));
        //HarvestLevel
        MinecraftForge.setBlockHarvestLevel(blockFossil, 0, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockPermafrost, 0, "shovel", 2);
        MinecraftForge.setBlockHarvestLevel(blockIcedStone, 0, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(blockIcedStone, 1, "pickaxe", 1);
        //Block Registry
        GameRegistry.registerBlock(blockFossil, LocalizationStrings.BLOCK_FOSSIL_NAME);
        GameRegistry.registerBlock(blockSkull, LocalizationStrings.BLOCK_SKULL_NAME);
        GameRegistry.registerBlock(skullLantern, LocalizationStrings.SKULL_LANTERN_NAME);
        GameRegistry.registerBlock(blockanalyzerIdle, LocalizationStrings.BLOCK_ANALYZER_IDLE_NAME);
        GameRegistry.registerBlock(blockanalyzerActive, LocalizationStrings.BLOCK_ANALYZER_ACTIVE_NAME);
        GameRegistry.registerBlock(blockcultivateIdle, LocalizationStrings.BLOCK_CULTIVATE_IDLE_NAME);
        GameRegistry.registerBlock(blockcultivateActive, LocalizationStrings.BLOCK_CULTIVATE_ACTIVE_NAME);
        GameRegistry.registerBlock(blockworktableIdle, LocalizationStrings.BLOCK_WORKTABLE_IDLE_NAME);
        GameRegistry.registerBlock(blockworktableActive, LocalizationStrings.BLOCK_WORKTABLE_ACTIVE_NAME);
        GameRegistry.registerBlock(ferns, LocalizationStrings.FERN_BLOCK_NAME);
        GameRegistry.registerBlock(drum, LocalizationStrings.DRUM_NAME);
        GameRegistry.registerBlock(feederIdle, LocalizationStrings.FEEDER_IDLE_NAME);
        GameRegistry.registerBlock(feederActive, LocalizationStrings.FEEDER_ACTIVE_NAME);
        GameRegistry.registerBlock(blockPermafrost, LocalizationStrings.BLOCK_PERMAFROST_NAME);
        GameRegistry.registerBlock(blockIcedStone, LocalizationStrings.BLOCK_ICEDSTONE_NAME);
        GameRegistry.registerBlock(blockTimeMachine, LocalizationStrings.BLOCK_TIMEMACHINE_NAME);
        GameRegistry.registerBlock(palmLog, LocalizationStrings.PALAE_LOG_NAME);
        GameRegistry.registerBlock(palmLeaves, LocalizationStrings.PALAE_LEAVES_NAME);
        GameRegistry.registerBlock(palmSap, LocalizationStrings.PALAE_SAP_NAME);
        GameRegistry.registerBlock(palaeSingleSlab, ItemSlabPalae.class, LocalizationStrings.PALAE_SINGLESLAB_NAME);
        GameRegistry.registerBlock(palaeDoubleSlab, ItemSlabPalae.class, LocalizationStrings.PALAE_DOUBLESLAB_NAME);
        GameRegistry.registerBlock(palaeStairs, LocalizationStrings.PALAE_STAIRS_NAME);
        GameRegistry.registerBlock(palaePlanks, LocalizationStrings.PALAE_PLANKS_NAME);
        GameRegistry.registerBlock(volcanicAsh, LocalizationStrings.VOLCANIC_ASH_NAME);
        GameRegistry.registerBlock(volcanicBrick, LocalizationStrings.VOLCANIC_BRICK_NAME);
        GameRegistry.registerBlock(volcanicRock, LocalizationStrings.VOLCANIC_ROCK_NAME);
        GameRegistry.registerBlock(tar, LocalizationStrings.TAR_NAME);
        GameRegistry.registerBlock(sarracina, LocalizationStrings.SARRACINA_NAME);
        GameRegistry.registerBlock(amberOre, LocalizationStrings.AMBER_ORE_NAME);
        GameRegistry.registerBlock(ancientStone, LocalizationStrings.ANCIENT_STONE_NAME);
        GameRegistry.registerBlock(ancientStonebrick, LocalizationStrings.ANCIENT_STONE_BRICK_NAME);
        GameRegistry.registerBlock(ancientWood, LocalizationStrings.ANCIENT_WOOD_NAME);
        GameRegistry.registerBlock(ancientWoodPillar, LocalizationStrings.ANCIENT_WOOD_PILLAR_NAME);
        GameRegistry.registerBlock(ancientGlass, LocalizationStrings.ANCIENT_GLASS_NAME);
        GameRegistry.registerBlock(ancientWoodPlate, LocalizationStrings.ANCIENT_WOOD_PLATE_NAME);
        GameRegistry.registerBlock(ancientWoodStairs, LocalizationStrings.ANCIENT_WOOD_STAIRS_NAME);
        GameRegistry.registerBlock(ancientWoodSingleSlab, ItemSlabAncientWood.class, LocalizationStrings.ANCIENT_WOOD_SINGLESLAB_NAME);
        GameRegistry.registerBlock(ancientWoodDoubleSlab, ItemSlabAncientWood.class, LocalizationStrings.ANCIENT_WOOD_DOUBLESLAB_NAME);
        GameRegistry.registerBlock(ancientStoneStairs, LocalizationStrings.ANCIENT_STONE_STAIRS_NAME);
        GameRegistry.registerBlock(ancientStoneSingleSlab, ItemSlabAncientStone.class, LocalizationStrings.ANCIENT_STONE_SINGLESLAB_NAME);
        GameRegistry.registerBlock(ancientStoneDoubleSlab, ItemSlabAncientStone.class, LocalizationStrings.ANCIENT_STONE_DOUBLESLAB_NAME);
        GameRegistry.registerBlock(figurineBlock, BlockFigurineItem.class, modid + (figurineBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(blockSifterIdle, LocalizationStrings.BLOCK_SIFTER_IDLE);
        GameRegistry.registerBlock(volcanicStairs, LocalizationStrings.VOLCANIC_STAIRS);
        GameRegistry.registerBlock(volcanicSingleSlab, ItemSlabVolcanic.class, LocalizationStrings.VOLCANIC_SINGLESLAB_NAME);
        GameRegistry.registerBlock(volcanicDoubleSlab, ItemSlabVolcanic.class, LocalizationStrings.VOLCANIC_DOUBLESLAB_NAME);
        GameRegistry.registerBlock(vaseVoluteBlock, BlockVaseVoluteItem.class, modid + (vaseVoluteBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(vaseAmphoraBlock, BlockVaseAmphoraItem.class, modid + (vaseAmphoraBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(vaseKylixBlock, BlockVaseKylixItem.class, modid + (vaseKylixBlock.getUnlocalizedName().substring(5)));
        
        //Item Registry
      GameRegistry.registerItem(biofossil, LocalizationStrings.BIO_FOSSIL_NAME);
      GameRegistry.registerItem(relic, LocalizationStrings.RELIC_NAME);
      GameRegistry.registerItem(stoneboard, LocalizationStrings.TABLET_NAME);
      GameRegistry.registerItem(ancientSword, LocalizationStrings.ANCIENT_SWORD_NAME); 
      GameRegistry.registerItem(brokenSword, LocalizationStrings.BROKEN_SWORD_NAME);
      GameRegistry.registerItem(fernSeed, LocalizationStrings.FERNSEED_NAME);
      GameRegistry.registerItem(ancienthelmet, LocalizationStrings.ANCIENT_HELMET_NAME);
      GameRegistry.registerItem(brokenhelmet, LocalizationStrings.BROKEN_HELMET_NAME);
      GameRegistry.registerItem(skullStick, LocalizationStrings.SKULL_STICK_NAME);
      GameRegistry.registerItem(gem, LocalizationStrings.SCARAB_GEM_NAME);
      GameRegistry.registerItem(gemAxe, LocalizationStrings.SCARAB_AXE_NAME);
      GameRegistry.registerItem(gemPickaxe, LocalizationStrings.SCARAB_PICKAXE_NAME);
      GameRegistry.registerItem(gemSword, LocalizationStrings.SCARAB_SWORD_NAME);
      GameRegistry.registerItem(gemHoe, LocalizationStrings.SCARAB_HOE_NAME); 
      GameRegistry.registerItem(gemShovel, LocalizationStrings.SCARAB_SHOVEL_NAME);
      GameRegistry.registerItem(dinoPedia, LocalizationStrings.DINOPEDIA_NAME);
      GameRegistry.registerItem(emptyShell, LocalizationStrings.EMPTY_SHELL_NAME);
      GameRegistry.registerItem(magicConch, LocalizationStrings.MAGIC_CONCH_NAME);
      GameRegistry.registerItem(icedMeat, LocalizationStrings.ICED_MEAT_NAME);
      GameRegistry.registerItem(amber, LocalizationStrings.AMBER_NAME);
      GameRegistry.registerItem(woodjavelin, LocalizationStrings.WOOD_JAVELIN_NAME);
      GameRegistry.registerItem(stonejavelin, LocalizationStrings.STONE_JAVELIN_NAME);
      GameRegistry.registerItem(ironjavelin, LocalizationStrings.IRON_JAVELIN_NAME);
      GameRegistry.registerItem(goldjavelin, LocalizationStrings.GOLD_JAVELIN_NAME);
      GameRegistry.registerItem(diamondjavelin, LocalizationStrings.DIAMOND_JAVELIN_NAME);
      GameRegistry.registerItem(ancientJavelin, LocalizationStrings.ANCIENT_JAVELIN_NAME);
		GameRegistry.registerItem(whip, LocalizationStrings.WHIP_NAME);
		GameRegistry.registerItem(legBone, LocalizationStrings.LEGBONE_NAME);
		GameRegistry.registerItem(claw, LocalizationStrings.CLAW_NAME);
		GameRegistry.registerItem(foot, LocalizationStrings.FOOT_NAME);
		GameRegistry.registerItem(skull, LocalizationStrings.SKULL_NAME);
		GameRegistry.registerItem(vertebrae, LocalizationStrings.VERTEBRAE_NAME);
		GameRegistry.registerItem(armBone, LocalizationStrings.ARM_BONE_NAME);
		GameRegistry.registerItem(dinoRibCage, LocalizationStrings.DINO_RIB_CAGE_NAME);
		//GameRegistry.registerItem(dinosaurModels, LocalizationStrings.DINOSAUR_MODELS);
		GameRegistry.registerItem(brokenSapling, LocalizationStrings.BROKEN_SAPLING_NAME);
		GameRegistry.registerItem(dodoEgg, LocalizationStrings.DODO_EGG_NAME);
		GameRegistry.registerItem(cultivatedDodoEgg, LocalizationStrings.CULTIVATED_DODO_EGG_NAME);
//		GameRegistry.registerItem(archNotebook, LocalizationStrings.ARCH_NOTEBOOK_NAME);
		GameRegistry.registerItem(potteryShards, LocalizationStrings.POTTERY_SHARDS);
//		GameRegistry.registerItem(brokenHeadRelic, LocalizationStrings.BROKEN_HEAD_RELIC);
		GameRegistry.registerItem(skullHelmet, LocalizationStrings.SKULL_HELMET_NAME);
		GameRegistry.registerItem(ribCage, LocalizationStrings.RIBCAGE_NAME);
		GameRegistry.registerItem(femurs, LocalizationStrings.FEMURS_NAME);
		GameRegistry.registerItem(feet, LocalizationStrings.FEET_NAME);
		GameRegistry.registerItem(dnaPig, LocalizationStrings.DNA_PIG_NAME);
		GameRegistry.registerItem(dnaSheep, LocalizationStrings.DNA_SHEEP_NAME);
		GameRegistry.registerItem(dnaCow, LocalizationStrings.DNA_COW_NAME);
		GameRegistry.registerItem(dnaHorse, LocalizationStrings.DNA_HORSE_NAME);
		GameRegistry.registerItem(dnaQuagga, LocalizationStrings.DNA_QUAGGA_NAME);
		GameRegistry.registerItem(dnaChicken, LocalizationStrings.DNA_CHICKEN_NAME);
		GameRegistry.registerItem(dnaSmilodon, LocalizationStrings.DNA_SMILODON_NAME);
		GameRegistry.registerItem(dnaMammoth, LocalizationStrings.DNA_MAMMOTH_NAME);
		GameRegistry.registerItem(dnaCoelacanth, LocalizationStrings.DNA_COELACANTH_NAME);
		GameRegistry.registerItem(dnaDodo, LocalizationStrings.DNA_DODO_NAME);
		GameRegistry.registerItem(dnaTerrorBird, LocalizationStrings.DNA_TERROR_BIRD_NAME);
		GameRegistry.registerItem(embryoPig, LocalizationStrings.EMBRYO_PIG_NAME);
		GameRegistry.registerItem(embryoSheep, LocalizationStrings.EMBRYO_SHEEP_NAME);
		GameRegistry.registerItem(embryoCow, LocalizationStrings.EMBRYO_COW_NAME);
		GameRegistry.registerItem(embryoHorse, LocalizationStrings.EMBRYO_HORSE_NAME);
		GameRegistry.registerItem(embryoQuagga, LocalizationStrings.EMBRYO_QUAGGA_NAME);
		GameRegistry.registerItem(embryoChicken, LocalizationStrings.EMBRYO_CHICKEN_NAME);
		GameRegistry.registerItem(embryoSmilodon, LocalizationStrings.EMBRYO_SMILODON_NAME);
		GameRegistry.registerItem(embryoMammoth, LocalizationStrings.EMBRYO_MAMMOTH_NAME);
		GameRegistry.registerItem(cookedDinoMeat, LocalizationStrings.DINO_STEAK_NAME);
		GameRegistry.registerItem(cookedChickenSoup, LocalizationStrings.COOKED_CHICKEN_SOUP_NAME);
		GameRegistry.registerItem(rawChickenSoup, LocalizationStrings.RAW_CHICKEN_SOUP_NAME);
		GameRegistry.registerItem(chickenEss, LocalizationStrings.EOC_NAME);
		GameRegistry.registerItem(sjl, LocalizationStrings.SJL_NAME);
		GameRegistry.registerItem(dodoWing, LocalizationStrings.DODO_WING_NAME);
		GameRegistry.registerItem(dodoWingCooked, LocalizationStrings.DODO_WING_COOKED_NAME);
//		GameRegistry.registerItem(figurineItem, LocalizationStrings.FIGURINE_NAME);
		GameRegistry.registerItem(fossilrecordBones, LocalizationStrings.FOSSILRECORD_NAME);
		GameRegistry.registerItem(livingCoelacanth, LocalizationStrings.LIVING_COELACANTH_NAME);
		GameRegistry.registerItem(terrorBirdEgg, LocalizationStrings.TERROR_BIRD_EGG_NAME);
		GameRegistry.registerItem(cultivatedTerrorBirdEgg, LocalizationStrings.CULTIVATED_TERROR_BIRD_EGG_NAME);
		GameRegistry.registerItem(terrorBirdMeat, LocalizationStrings.TERROR_BIRD_MEAT);
		GameRegistry.registerItem(terrorBirdMeatCooked, LocalizationStrings.TERROR_BIRD_MEAT_COOKED);
		GameRegistry.registerItem(quaggaMeat,LocalizationStrings.QUAGGA_MEAT);
		GameRegistry.registerItem(quaggaMeatCooked,LocalizationStrings.QUAGGA_MEAT_COOKED);

	
		
		//Dinosaur Eggs
        for (int i = 0; i < EnumDinoType.values().length; i++)
        {
        	GameRegistry.registerItem(EnumDinoType.values()[i].EggItem, "egg" + EnumDinoType.values()[i].name());
        }

        //Dinosaur DNA
        for (int i = 0; i < EnumDinoType.values().length; i++)
        {
        	GameRegistry.registerItem(EnumDinoType.values()[i].DNAItem, "dna" + EnumDinoType.values()[i].name());
        }

        //Dinosaur Meat
        for (int i = 0; i < EnumDinoType.values().length; i++)
        {
        	GameRegistry.registerItem(EnumDinoType.values()[i].DropItem, "raw" + EnumDinoType.values()[i].name());
        }  
        
        EntityRegistry.registerModEntity(EntityStoneboard.class, 			"StoneBoard", 			1, this, 250, Integer.MAX_VALUE, false);
        EntityRegistry.registerModEntity(EntityJavelin.class, 				"Javelin", 				2, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityAncientJavelin.class, 		"AncientJavelin", 		3, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityMLighting.class, 			"FriendlyLighting", 	4, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityFailuresaurus.class, 		"Failuresaurus", 		5, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityBones.class, 				"Bones", 				6, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityDinoEgg.class, 				"DinoEgg", 				8, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityFriendlyPigZombie.class, 	"FriendlyPigZombie", 	12, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityPigBoss.class, 				"PigBoss", 				13, this, 250, 5, true);
        //EntityRegistry.registerModEntity(EntityPregnantSheep.class, 		"PregnantSheep", 		19, this, 250, 5, true);
        //EntityRegistry.registerModEntity(EntityPregnantCow.class, 		"PregnantCow", 			20, this, 250, 5, true);
        //EntityRegistry.registerModEntity(EntityPregnantPig.class, 		"PregnantPig", 			21, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntitySmilodon.class, 				"Smilodon", 			22, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityMammoth.class, 				"Mammoth", 				24, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityDodo.class,           		"Dodo",             	25, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityDodoEgg.class,           	"DodoEgg",              26, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityCultivatedDodoEgg.class, 	"CultivatedDodoEgg",    27, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityCoelacanth.class, 			"Coelacanth",    		28, this, 250, 5, true);
        //EntityRegistry.registerModEntity(EntityPregnantHorse.class, 		"PregnantHorse", 		29, this, 250, 5, true);
        EntityRegistry.registerModEntity(EntityQuagga.class, 				"Quagga", 				30, this, 250, 3, true);
        EntityRegistry.registerModEntity(EntityTerrorBird.class, 			"TerrorBird", 			31, this, 250, 3, true);
        EntityRegistry.registerModEntity(EntityTerrorBirdEgg.class,         "TerrorBirdEgg",        32, this, 250, 5, true);

        for (int i = 0; i < EnumDinoType.values().length; i++)
        {
            EntityRegistry.registerModEntity(EnumDinoType.values()[i].getDinoClass(), EnumDinoType.values()[i].name(), 200 + i, this, 250, 3, true);
        }
        EntityRegistry.addSpawn(EntityCoelacanth.class, 1, 2, 4, EnumCreatureType.waterCreature, new BiomeGenBase[] {BiomeGenBase.ocean});
        EntityRegistry.addSpawn(EntityNautilus.class, 5, 4, 14, EnumCreatureType.waterCreature, new BiomeGenBase[] {BiomeGenBase.river, BiomeGenBase.ocean});

        FossilSpawnEggs.addSpawnEggs();
        //make the dino types complete by registering the dinos items
        EnumDinoType.init();
        EnumDinoFoodMob.init();
        GameRegistry.registerWorldGenerator(new FossilGenerator());
        
        if(FossilOptions.Gen_Palaeoraphe)
        GameRegistry.registerWorldGenerator(new WorldGeneratorPalaeoraphe());
        
        if(FossilOptions.Gen_Academy)
        GameRegistry.registerWorldGenerator(new AcademyGenerator());
        
        if(FossilOptions.Gen_Ships)
        GameRegistry.registerWorldGenerator(new ShipWreckGenerator());
        
        GameRegistry.registerWorldGenerator(new TarGenerator());
        GameRegistry.registerWorldGenerator(new VolcanicRockGenerator());
        
        /*
        GameRegistry.registerWorldGenerator(new WorldGenWeaponShop());
        */
        
        feederRenderID = RenderingRegistry.getNextAvailableRenderId();

        
        NetworkRegistry.instance().registerChatListener(messagerHandler);
        NetworkRegistry.instance().registerGuiHandler(this, GH);
        NetworkRegistry.instance().registerConnectionHandler(new FossilConnectionHandler());
        GameRegistry.registerTileEntity(TileEntityCultivate.class, LocalizationStrings.BLOCK_CULTIVATE_IDLE_NAME);
        GameRegistry.registerTileEntity(TileEntityAnalyzer.class, LocalizationStrings.BLOCK_ANALYZER_IDLE_NAME);
        GameRegistry.registerTileEntity(TileEntityWorktable.class, LocalizationStrings.BLOCK_WORKTABLE_IDLE_NAME);
        GameRegistry.registerTileEntity(TileEntityDrum.class, LocalizationStrings.DRUM_NAME);
        GameRegistry.registerTileEntity(TileEntityFeeder.class, LocalizationStrings.T_FEEDER_IDLE_NAME);
        GameRegistry.registerTileEntity(TileEntityTimeMachine.class, LocalizationStrings.BLOCK_TIMEMACHINE_NAME);
        GameRegistry.registerTileEntity(TileEntitySifter.class, LocalizationStrings.BLOCK_SIFTER_IDLE);
        GameRegistry.registerTileEntity(TileEntityFigurine.class, "figurineType");
        GameRegistry.registerTileEntity(TileEntityVase.class, "vaseType");
        //TickRegistry.registerTickHandler(new RenderHUD(), Side.CLIENT);
        RenderingRegistry.registerBlockHandler(2303, RenderFeeder.INSTANCE);
        proxy.registerTileEntitySpecialRenderer();
        proxy.registerRenderThings();
        FossilOreDictionary.oreRegistration();
        FossilRecipeHandler.addRecipe();
        GameRegistry.registerPickupHandler(new FossilPickupHandler());
        GameRegistry.registerCraftingHandler(new FossilCraftingHandler());
        proxy.onInit(event);
        MinecraftForge.EVENT_BUS.register(new FossilToolEvent());
        MinecraftForge.EVENT_BUS.register(new FossilLivingEvent());
        MinecraftForge.EVENT_BUS.register(new FossilInteractEvent());


        

    }

    public static void ShowMessage(String var0, EntityPlayer var1)
    {
        if (var1 != null)
        {
            var1.addChatMessage(var0);
        }
    }

    public static void Console(String var0)
    {
        if (DebugMode())
        {
            System.out.println("[FOSSIL]: " + var0);
        }
    }
    
    public static void log(Level logLevel, String message)
    {
        Log.log(logLevel, message);
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
//        Item.itemsList[palaeSingleSlab.blockID] = (new ItemSlab(palaeSingleSlab.blockID - 256, (BlockHalfSlab)palaeSingleSlab, (BlockHalfSlab)palaeDoubleSlab, false));
//        Item.itemsList[ancientWoodSingleSlab.blockID] = (new ItemSlab(ancientWoodSingleSlab.blockID - 256, (BlockHalfSlab)ancientWoodSingleSlab, (BlockHalfSlab)ancientWoodDoubleSlab, false));
//        Item.itemsList[ancientStoneSingleSlab.blockID] = (new ItemSlab(ancientStoneSingleSlab.blockID - 256, (BlockHalfSlab)ancientStoneSingleSlab, (BlockHalfSlab)ancientStoneDoubleSlab, false));
//        Item.itemsList[volcanicSingleSlab.blockID] = (new ItemSlab(volcanicSingleSlab.blockID - 256, (BlockHalfSlab)volcanicSingleSlab, (BlockHalfSlab)volcanicDoubleSlab, false));
    }
}