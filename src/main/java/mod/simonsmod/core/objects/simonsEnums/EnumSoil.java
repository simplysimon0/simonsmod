package mod.simonsmod.core.objects.simonsEnums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumSoil implements IStringSerializable
{
	GREYSAND (0, "grey_sand");

    private static final EnumSoil[] META_LOOKUP = new EnumSoil[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    
	private EnumSoil(int meta, String name) 
	{
		this(meta, name, name);

	}
	private EnumSoil(int meta, String name, String unlocalizedName)
	{
	this.meta = meta;
	this.name = name;
	this.unlocalizedName = unlocalizedName;

	}

    public int getMetadata()
    {
        return this.meta;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public static EnumSoil byMetadata(int meta)
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
        for (EnumSoil enumdyecolor : values())
        {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }
}