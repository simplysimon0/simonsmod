package mod.simonsmod.core.objects.simonsEnums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumDusts implements IStringSerializable
{
	DUST_ONE (0, "one"),
	DUST_TWO(1, "two");

    private static final EnumDusts[] META_LOOKUP = new EnumDusts[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    
	private EnumDusts(int meta, String name) 
	{
		this(meta, name, name);

	}
	private EnumDusts(int meta, String name, String unlocalizedName)
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

    public static EnumDusts byMetadata(int meta)
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
        for (EnumDusts enumdyecolor : values())
        {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }
}