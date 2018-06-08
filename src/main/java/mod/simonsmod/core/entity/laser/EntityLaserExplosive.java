package mod.simonsmod.core.entity.laser;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicates;

import mod.simonsmod.core.config.MainConfig;
import mod.simonsmod.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLaserExplosive extends Entity implements IProjectile {
	public static final DataParameter<Byte> CRITICAL;
	public int xTile;
	public int yTile;
	public int zTile;
	public Block inTile;
	public int inData;
	protected boolean inGround;
	protected int timeInGround;
	public int arrowShake;
	public Entity shootingEntity;
	public int ticksInGround;
	public int ticksInAir;
	public double damage;
	public int knockbackStrength;
	private float explosionPower = 3.0F;

	public EntityLaserExplosive(World worldIn) {
		super(worldIn);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.damage = 2.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityLaserExplosive(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
	}

	public EntityLaserExplosive(World worldIn, EntityLivingBase shooter) {
		this(worldIn, shooter.posX, shooter.posY + (double) shooter.getEyeHeight() - 0.10000000149011612D,
				shooter.posZ);
		this.shootingEntity = shooter;
		if (shooter instanceof EntityPlayer) {
		}

	}

	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 10.0D;
		if (Double.isNaN(d0)) {
			d0 = 1.0D;
		}

		d0 = d0 * 64.0D * getRenderDistanceWeight();
		return distance < d0 * d0;
	}

	protected void entityInit() {
		this.dataManager.register(CRITICAL, Byte.valueOf((byte) 0));
	}

	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) {
		float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float f1 = -MathHelper.sin(pitch * 0.017453292F);
		float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		this.shoot((double) f, (double) f1, (double) f2, velocity, inaccuracy);
		this.motionX += shooter.motionX;
		this.motionZ += shooter.motionZ;
		if (!shooter.onGround) {
			this.motionY += shooter.motionY;
		}

	}

	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		float f = MathHelper.sqrt(x * x + y * y + z * z);
		x /= (double) f;
		y /= (double) f;
		z /= (double) f;
		x += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		y += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		z += this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
		x *= (double) velocity;
		y *= (double) velocity;
		z *= (double) velocity;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		float f1 = MathHelper.sqrt(x * x + z * z);
		this.rotationYaw = (float) (MathHelper.atan2(x, z) * 57.29577951308232D);
		this.rotationPitch = (float) (MathHelper.atan2(y, (double) f1) * 57.29577951308232D);
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
		this.ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch,
			int posRotationIncrements, boolean teleport) {
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * 57.29577951308232D);
			this.rotationYaw = (float) (MathHelper.atan2(x, z) * 57.29577951308232D);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}

	}

	public void onUpdate() {
		super.onUpdate();
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float blockpos = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * 57.29577951308232D);
			this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) blockpos) * 57.29577951308232D);
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
		}

		BlockPos arg12 = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState iblockstate = this.world.getBlockState(arg12);
		Block block = iblockstate.getBlock();
		if (iblockstate.getMaterial() != Material.AIR) {
			AxisAlignedBB vec3d1 = iblockstate.getCollisionBoundingBox(this.world, arg12);
			if (vec3d1 != Block.NULL_AABB
					&& vec3d1.offset(arg12).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
				this.inGround = true;
			}
		}

		if (this.arrowShake > 0) {
			--this.arrowShake;
		}

		if (this.inGround) {
			int arg13 = block.getMetaFromState(iblockstate);
			if ((block != this.inTile || arg13 != this.inData)
					&& !this.world.collidesWithAnyBlock(this.getEntityBoundingBox().grow(0.05D))) {
				this.inGround = false;
				this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			} else {
				++this.ticksInGround;
				if (this.ticksInGround >= 1200) {
					this.setDead();
				}
			}

			++this.timeInGround;
		} else {
			this.timeInGround = 0;
			++this.ticksInAir;
			Vec3d arg14 = new Vec3d(this.posX, this.posY, this.posZ);
			Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			RayTraceResult raytraceresult = this.world.rayTraceBlocks(arg14, vec3d, false, true, false);
			arg14 = new Vec3d(this.posX, this.posY, this.posZ);
			vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			if (raytraceresult != null) {
				vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
			}

			Entity entity = this.findEntityOnPath(arg14, vec3d);
			if (entity != null) {
				raytraceresult = new RayTraceResult(entity);
			}

			if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer) {
				EntityPlayer f4 = (EntityPlayer) raytraceresult.entityHit;
				if (this.shootingEntity instanceof EntityPlayer
						&& !((EntityPlayer) this.shootingEntity).canAttackPlayer(f4)) {
					raytraceresult = null;
				}
			}

			if (raytraceresult != null && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
				this.onHit(raytraceresult);
			}

			if (this.getIsCritical()) {
				for (int arg15 = 0; arg15 < 4; ++arg15) {
					this.world.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) arg15 / 4.0D,
							this.posY + this.motionY * (double) arg15 / 4.0D,
							this.posZ + this.motionZ * (double) arg15 / 4.0D, -this.motionX, -this.motionY + 0.2D,
							-this.motionZ, new int[0]);
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float arg16 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * 57.29577951308232D);

			for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) arg16)
					* 57.29577951308232D); this.rotationPitch
							- this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float f1 = 0.99F;
			float f2 = 0.05F;
			if (this.isInWater()) {
				for (int i = 0; i < 4; ++i) {
					float f3 = 0.25F;
					this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D,
							this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX,
							this.motionY, this.motionZ, new int[0]);
				}

				f1 = 0.6F;
			}

			if (this.isWet()) {
				this.extinguish();
			}

			this.motionX *= (double) f1;
			this.motionY *= (double) f1;
			this.motionZ *= (double) f1;
			if (!this.hasNoGravity()) {
				this.motionY -= 0.0D;
			}

			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}

	}

	protected void onHit(RayTraceResult raytraceResultIn) {
		Entity entity = raytraceResultIn.entityHit;
		if (entity != null) {
			this.ticksInAir = 0;
			if (!this.world.isRemote && this.motionX * this.motionX + this.motionY * this.motionY
					+ this.motionZ * this.motionZ < 0.0010000000474974513D) {
				this.setDead();
			}

			if (!this.world.isRemote) {
				if (raytraceResultIn.entityHit != null) {
					raytraceResultIn.entityHit
							.attackEntityFrom(DamageSource.causeThrownDamage(this, this.shootingEntity), 3.0F);
					entity.setFire(5);
				}

				this.setDead();
			}
		} else if (!this.world.isRemote) {
			this.world.newExplosion((Entity) null, this.posX, this.posY, this.posZ, (float) this.explosionPower , true,
					true);
			this.setDead();
		}

	}

	protected void setBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			worldIn.setBlockState(pos, state, 11);
		}

	}

	public void move(MoverType type, double x, double y, double z) {
		super.move(type, x, y, z);
		if (this.inGround) {
			this.xTile = MathHelper.floor(this.posX);
			this.yTile = MathHelper.floor(this.posY);
			this.zTile = MathHelper.floor(this.posZ);
		}

	}

	protected void arrowHit(EntityLivingBase living) {
	}

	@Nullable
	protected Entity findEntityOnPath(Vec3d start, Vec3d end) {
		Entity entity = null;
		List list = this.world.getEntitiesInAABBexcluding(this,
				this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D),
				Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE));
		double d0 = 0.0D;

		for (int i = 0; i < list.size(); ++i) {
			Entity entity1 = (Entity) list.get(i);
			if (entity1 != this.shootingEntity || this.ticksInAir >= 5) {
				AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
				RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);
				if (raytraceresult != null) {
					double d1 = start.squareDistanceTo(raytraceresult.hitVec);
					if (d1 < d0 || d0 == 0.0D) {
						entity = entity1;
						d0 = d1;
					}
				}
			}
		}

		return entity;
	}

	public static void registerFixesArrow(DataFixer fixer, String name) {
	}

	public static void registerFixesArrow(DataFixer fixer) {
		registerFixesArrow(fixer, "Arrow");
	}

	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("xTile", this.xTile);
		compound.setInteger("yTile", this.yTile);
		compound.setInteger("zTile", this.zTile);
		compound.setShort("life", (short) this.ticksInGround);
		ResourceLocation resourcelocation = (ResourceLocation) Block.REGISTRY.getNameForObject(this.inTile);
		compound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
		compound.setByte("inData", (byte) this.inData);
		compound.setByte("shake", (byte) this.arrowShake);
		compound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		compound.setDouble("damage", this.damage);
		compound.setBoolean("crit", this.getIsCritical());
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
		this.xTile = compound.getInteger("xTile");
		this.yTile = compound.getInteger("yTile");
		this.zTile = compound.getInteger("zTile");
		this.ticksInGround = compound.getShort("life");
		if (compound.hasKey("inTile", 8)) {
			this.inTile = Block.getBlockFromName(compound.getString("inTile"));
		} else {
			this.inTile = Block.getBlockById(compound.getByte("inTile") & 255);
		}

		this.inData = compound.getByte("inData") & 255;
		this.arrowShake = compound.getByte("shake") & 255;
		this.inGround = compound.getByte("inGround") == 1;
		if (compound.hasKey("damage", 99)) {
			this.damage = compound.getDouble("damage");
		}



		this.setIsCritical(compound.getBoolean("crit"));
	}

	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if (!this.world.isRemote && this.inGround && this.arrowShake <= 0) {
		}

	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public void setDamage(double damageIn) {
		this.damage = damageIn;
	}

	public double getDamage() {
		return this.damage;
	}

	public void setKnockbackStrength(int knockbackStrengthIn) {
		this.knockbackStrength = knockbackStrengthIn;
	}

	public boolean canBeAttackedWithItem() {
		return false;
	}

	public float getEyeHeight() {
		return 0.0F;
	}

	public void setIsCritical(boolean critical) {
		byte b0 = ((Byte) this.dataManager.get(CRITICAL)).byteValue();
		if (critical) {
			this.dataManager.set(CRITICAL, Byte.valueOf((byte) (b0 | 1)));
		} else {
			this.dataManager.set(CRITICAL, Byte.valueOf((byte) (b0 & -2)));
		}

	}

	public boolean getIsCritical() {
		byte b0 = ((Byte) this.dataManager.get(CRITICAL)).byteValue();
		return (b0 & 1) != 0;
	}

	public void setEnchantmentEffectsFromEntity(EntityLivingBase p_190547_1_, float p_190547_2_) {
		int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.POWER, p_190547_1_);
		int j = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.PUNCH, p_190547_1_);
		this.setDamage((double) (p_190547_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D
				+ (double) ((float) this.world.getDifficulty().getDifficultyId() * 0.11F));
		if (i > 0) {
			this.setDamage(this.getDamage() + (double) i * 0.5D + 0.5D);
		}

		if (j > 0) {
			this.setKnockbackStrength(j);
		}

		if (EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FLAME, p_190547_1_) > 0) {
			this.setFire(100);
		}

	}

	protected ItemStack getArrowStack() {
		return null;
	}

	static {
		CRITICAL = EntityDataManager.createKey(EntityLaserExplosive.class, DataSerializers.BYTE);
	}
}