package mod.simonsmod.core.objects.blocks.tileentities;

import mod.simonsmod.core.handlers.SimonSoundHandler;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AlarmSound extends MovingSound {
	// private final EntityMinecart minecart;
	private float distance = 0.0F;
	private boolean isOn;
	private float Volume;

	public AlarmSound(boolean isOn, float volume, BlockPos pos) {
		super(SimonSoundHandler.ALARM, SoundCategory.BLOCKS);
		// this.minecart = minecartIn;
		this.isOn = isOn;
		this.Volume = volume;
		this.repeat = true;
		this.repeatDelay = 38;
		this.xPosF = pos.getX();
        this.yPosF = pos.getY();
        this.zPosF = pos.getZ();
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	public void update() {
		if (!isOn) {
			this.donePlaying = true;
		} else {
			this.distance = 1.0F;
			this.volume = Volume;

		}
	}
}