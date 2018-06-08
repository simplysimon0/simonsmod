package mod.simonsmod.core.objects.simonsEnums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumColor implements IStringSerializable
{
	BLACK(0 , "black"),
    RED(1,  "red"),
    GREEN(2,  "green"),
    BROWN(3, "brown"),
    BLUE(4,"blue"),
    PURPLE(5,"purple"),
    CYAN(6,"cyan"),
    SILVER(7,"silver"),
    GRAY(8,"gray"),
    PINK(9,"pink"),
    LIME(10,"lime"),
    YELLOW(11,  "yellow"),
    LIGHT_BLUE( 12,  "lightblue"),
    MAGENTA(13, "magenta"),
    ORANGE(14, "orange"),
    WHITE(15, "white");


    private static final EnumColor[] META_LOOKUP = new EnumColor[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    
	private EnumColor(int meta, String name) 
	{
		this(meta, name, name);

	}
	private EnumColor(int meta, String name, String unlocalizedName)
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

    public static EnumColor byMetadata(int meta)
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
        for (EnumColor enumdyecolor : values())
        {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }
}