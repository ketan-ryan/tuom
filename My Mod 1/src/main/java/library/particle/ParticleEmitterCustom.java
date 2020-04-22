package library.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEmitter;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticleEmitterCustom extends ParticleEmitter {

    private int age;
    private int lifetime;
    private EnumParticleTypes particleTypes;
    private List<Particle> listParticles = new ArrayList<>();
    private ParticleBehavior behavior = ParticleBehavior.SWIRL;
    private ParticleShape shape = ParticleShape.CUBE;
    //biggest size it gets
    private float sizeRadius = 5;
    private float sizeRadiusMax = 5;
    //best for sphere
    private int amountPerLayer = 30;
    //best for cube
    private int amountPerSide = 9 * 9;
    //private int layerCount = 8;
    private int particleCount = 0;
    //private float thickness = 1F;

    private double motionYExtra = 0;

    public ParticleEmitterCustom(World p_i47219_1_, Entity p_i47219_2_, EnumParticleTypes p_i47219_3_, ParticleShape shape, ParticleBehavior behavior, float radius, float thickness, int duration) {
        super(p_i47219_1_, p_i47219_2_, p_i47219_3_, duration);
        lifetime = duration;
        particleTypes = p_i47219_3_;
        this.shape = shape;
        this.sizeRadius = radius;
        this.sizeRadiusMax = this.sizeRadius;
        this.behavior = behavior;
        //this.thickness = thickness;

        int amountPerSizeOf1 = 9;
        amountPerSizeOf1 *= thickness;

        //all
        amountPerLayer = amountPerSizeOf1;

        //cube


        //sphere
        //layerCount = amountPerSizeOf1;

        if (shape == ParticleShape.CUBE) {
            amountPerLayer = (int)((float)amountPerSizeOf1 / 2F);
            amountPerSide = amountPerLayer * amountPerLayer;
            particleCount = amountPerSide * 6;
        } else if (shape == ParticleShape.SPHERE) {
            particleCount = amountPerLayer * amountPerSizeOf1;
        }


        spawnParticles();
    }

    public void spawnParticles() {
        int particleCountForShape = particleCount;
        if (shape == ParticleShape.CUBE) {
            particleCountForShape = amountPerSide * 6;
        }
        for (int i = 0; i < particleCountForShape; i++) {
            Particle particle = Minecraft.getMinecraft().effectRenderer.spawnEffectParticle(
                    particleTypes.getParticleID(), this.posX, this.posY, this.posZ, 0, 0, 0);
            listParticles.add(particle);
        }


    }

    public void positionParticles() {

        if (behavior == ParticleBehavior.UP) {
            motionYExtra += 0.01D * age;
        } else if (behavior == ParticleBehavior.DROP) {
            motionYExtra -= 0.01D * age;
        }

        Iterator<Particle> it = listParticles.iterator();
        int index = 0;
        while (it.hasNext()) {
            Particle particle = it.next();
            if (!particle.isAlive()) {
                it.remove();
            } else {

                float x = 0F;
                float y = 0F;
                float z = 0F;

                if (behavior == ParticleBehavior.EMIT) {
                    sizeRadius = sizeRadiusMax * ((float)age / (float)lifetime);
                } else if (behavior == ParticleBehavior.VORTEX) {
                    sizeRadius = sizeRadiusMax - (sizeRadiusMax * ((float)age / (float)lifetime));
                }

                if (shape == ParticleShape.SPHERE) {
                    float subIndexHoriz = index % amountPerLayer;
                    float subIndexVertical = index / amountPerLayer;

                    //correct usage doesnt look as good, so lets not use it
                    boolean correctUsage = false;

                    float yaw = 360F / amountPerLayer * subIndexHoriz;
                    float pitch = (correctUsage ? 180F : 360F) / amountPerLayer * subIndexVertical;

                    //System.out.println("yaw: " + yaw + " - pitch: " + pitch);

                    float f = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
                    float f1 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
                    float f2 = -MathHelper.cos(-pitch * 0.017453292F - (correctUsage ? (float)Math.PI/2F : 0));
                    float f3 = MathHelper.sin(-pitch * 0.017453292F - (correctUsage ? (float)Math.PI/2F : 0));
                    Vec3d vec = new Vec3d((double) (f1 * f2) * sizeRadius, (double) f3 * sizeRadius, (double) (f * f2) * sizeRadius);

                    x = (float)vec.x;
                    y = (float)vec.y;
                    z = (float)vec.z;
                } else if (shape == ParticleShape.CUBE) {
                    float amountPerLayerSide = (int)Math.sqrt(amountPerSide);

                    //cube side size needs to be double the size of radius from center for it to make sense
                    float step = sizeRadius / amountPerLayerSide * 2F;
                    //same goes for offset, double
                    float offset = sizeRadius;

                    //makes borders play nicer, insead of overlapping on 3 sides and missing on 1, this makes all sides even
                    float connectionFix = step/2F;

                    if (index < amountPerSide) {
                        //right side +x
                        int subIndex = index;
                        float subIndexHoriz = subIndex % amountPerLayerSide;
                        float subIndexVertical = ((int) (subIndex / amountPerLayerSide));

                        x = sizeRadius;
                        y = -offset + (step * subIndexVertical) + connectionFix;
                        z = -offset + (step * subIndexHoriz) + connectionFix;
                    } else if (index < amountPerSide * 2) {
                        //left side -x
                        int subIndex = index - amountPerSide;
                        float subIndexHoriz = subIndex % amountPerLayerSide;
                        float subIndexVertical = ((int) (subIndex / amountPerLayerSide));

                        x = -sizeRadius;
                        y = -offset + (step * subIndexVertical) + connectionFix;
                        z = -offset + (step * subIndexHoriz) + connectionFix;
                    } else if (index < amountPerSide * 3) {
                        //front size -z
                        int subIndex = index - amountPerSide * 2;
                        float subIndexHoriz = subIndex % amountPerLayerSide;
                        float subIndexVertical = ((int) (subIndex / amountPerLayerSide));

                        z = -sizeRadius;
                        y = -offset + (step * subIndexVertical) + connectionFix;
                        x = -offset + (step * subIndexHoriz) + connectionFix;
                    } else if (index < amountPerSide * 4) {
                        //back size +z
                        int subIndex = index - amountPerSide * 3;
                        float subIndexHoriz = subIndex % amountPerLayerSide;
                        float subIndexVertical = ((int) (subIndex / amountPerLayerSide));

                        z = sizeRadius;
                        y = -offset + (step * subIndexVertical) + connectionFix;
                        x = -offset + (step * subIndexHoriz) + connectionFix;
                    } else if (index < amountPerSide * 5) {
                        //top +y
                        int subIndex = index - amountPerSide * 4;
                        float subIndexHoriz = subIndex % amountPerLayerSide;
                        float subIndexVertical = ((int) (subIndex / amountPerLayerSide));

                        y = sizeRadius;
                        z = -offset + (step * subIndexVertical) + connectionFix;
                        x = -offset + (step * subIndexHoriz) + connectionFix;
                    } else if (index < amountPerSide * 6) {
                        //bottom -y
                        int subIndex = index - amountPerSide * 5;
                        float subIndexHoriz = subIndex % amountPerLayerSide;
                        float subIndexVertical = ((int) (subIndex / amountPerLayerSide));

                        y = -sizeRadius;
                        z = -offset + (step * subIndexVertical) + connectionFix;
                        x = -offset + (step * subIndexHoriz) + connectionFix;
                    }
                }

                double rotateRate = 2;

                double xzDist = Math.sqrt(x * x + z * z);
                double rotationYaw = Math.atan2(z, x);

                if (behavior == ParticleBehavior.SWIRL) {
                    rotationYaw += Math.toRadians((world.getTotalWorldTime() * rotateRate) % 360);
                } else if (behavior == ParticleBehavior.EMIT) {
                    //xzDist = sizeRadius / age;
                } else if (behavior == ParticleBehavior.VORTEX) {
                    rotationYaw += Math.toRadians((world.getTotalWorldTime() * rotateRate) % 360);
                    //xzDist = particleMaxAge - (sizeRadius / age);
                }

                x = (float)(Math.sin(rotationYaw) * xzDist);
                z = (float)(Math.cos(rotationYaw) * xzDist);

                particle.setPosition(this.posX + x, this.posY + 1 + y + motionYExtra, this.posZ + z);

                int age = ReflectionHelper.getPrivateValue(Particle.class, particle, "field_70546_d", "particleAge");
                if (age > 10) {
                    ReflectionHelper.setPrivateValue(Particle.class, particle, 1, "field_70546_d", "particleAge");
                }
            }
            index++;
        }


    }

    @Override
    public void onUpdate() {
        if (particleTypes == null) return;
        positionParticles();
        /*for (int i = 0; i < 16; ++i)
        {
            double d0 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
            double d1 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
            double d2 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);

            if (d0 * d0 + d1 * d1 + d2 * d2 <= 1.0D)
            {
                double d3 = this.posX;
                double d4 = this.posY + 2;
                double d5 = this.posZ;
                this.world.spawnParticle(this.particleTypes, false, d3, d4, d5, d0, d1 + 0.2D, d2);
            }
        }*/

        ++this.age;

        if (this.age >= this.lifetime)
        {
            this.setExpired();
            for (Particle particle : listParticles) {
                particle.setExpired();
            }
        }
    }
}
