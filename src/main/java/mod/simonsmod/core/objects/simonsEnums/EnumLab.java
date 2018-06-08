package mod.simonsmod.core.objects.simonsEnums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumLab implements IStringSerializable
{
	BLANK (0, "blank"),
	BOLT(1, "bolt"),
	BOLD_TILE(2, "bolt_tile"),
	FLOOR(3, "floor");

    private static final EnumLab[] META_LOOKUP = new EnumLab[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    
	private EnumLab(int meta, String name) 
	{
		this(meta, name, name);

	}
	private EnumLab(int meta, String name, String unlocalizedName)
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

    public static EnumLab byMetadata(int meta)
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
        for (EnumLab enumdyecolor : values())
        {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }
}