package mod.simonsmod.core.objects.simonsEnums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumMetal implements IStringSerializable
{
	COPPER (0, "copper", false),
	TIN(1, "tin", false),
	SILVER(2, "silver", false),
	LEAD(3, "lead", true),
	ALUMINUM(4, "aluminum", false),
	NICKEL(5, "nickel", false),
	ZINC(6, "zinc", false),
	PLATINUM(7, "platinum", false),
	IRIDIUM(8, "iridium", false),
	OSMIUM(9, "osmium", false),
	TUNGSTEN(10, "tungsten", false),
	COBALT(11, "cobalt", false),
	TITANIUM(12, "titanium", false),
	LITHIUM(13, "lithium", false),
	CHROMIUM(14, "chromium", false),
	BISMUTH(15, "bismuth", false);

    private static final EnumMetal[] META_LOOKUP = new EnumMetal[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    private final boolean isLead;
    
	private EnumMetal(int meta, String name, boolean isLead) 
	{
		
		this(meta, name, name, isLead);

	}
	
	private EnumMetal(int meta, String name, String unlocalizedName, boolean isLead)
	{
	this.meta = meta;
	this.name = name;
	this.isLead = isLead;
	this.unlocalizedName = unlocalizedName;

	}
	public boolean isLead()
    {
        return this.isLead;
    }
    public int getMetadata()
    {
        return this.meta;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public static EnumMetal byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String toString()
    {
        return this.unlocalizedName;
    }

    public String getName()
    {
        return this.name;
    }

    static
    {
        for (EnumMetal enumdyecolor : values())
        {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }
}