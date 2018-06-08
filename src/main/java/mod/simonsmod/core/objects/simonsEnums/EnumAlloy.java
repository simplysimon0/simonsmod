package mod.simonsmod.core.objects.simonsEnums;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumAlloy implements IStringSerializable
{
	BRONZE (0, "bronze"),
	STEEL(1, "steel"),
	ELECTRUM(2, "electrum"),
	INVAR(3, "invar"),
	CONSTANTAN(4, "constantan"),
	BRASS(5, "brass"),
	TOOLSTEEL(6, "toolsteel"),
	MANYULLYN(7, "manyullyn");

    private static final EnumAlloy[] META_LOOKUP = new EnumAlloy[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    
	private EnumAlloy(int meta, String name) 
	{
		this(meta, name, name);

	}
	private EnumAlloy(int meta, String name, String unlocalizedName)
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

    public static EnumAlloy byMetadata(int meta)
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
        for (EnumAlloy enumdyecolor : values())
        {
            META_LOOKUP[enumdyecolor.getMetadata()] = enumdyecolor;
        }
    }
}