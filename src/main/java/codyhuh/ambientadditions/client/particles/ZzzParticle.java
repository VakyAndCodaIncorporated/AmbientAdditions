package codyhuh.ambientadditions.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZzzParticle extends RisingParticle {
    private final SpriteSet sprites;

    public ZzzParticle(ClientLevel p_107717_, double p_107718_, double p_107719_, double p_107720_, double p_107721_, double p_107722_, double p_107723_, SpriteSet p_107724_) {
        super(p_107717_, p_107718_, p_107719_, p_107720_, p_107721_, p_107722_, p_107723_);
        this.sprites = p_107724_;
        this.lifetime = 100;
        this.hasPhysics = false;
        this.gravity = -0.015F;
        this.setSpriteFromAge(p_107724_);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        super.tick();
        float amount = (1.0F - (age * 0.01F)) * 2.0F;
        float speed = 0.0F;

        switch (age / 10) {
            case 0 -> speed = Mth.lerp(0.05F, 0.0F, amount) * 0.5F;
            case 2, 4, 6, 8, 10 -> speed = Mth.lerp(0.05F, 0.0F, amount);
            case 1, 3, 5, 7, 9 -> speed = -Mth.lerp(0.05F, 0.0F, amount);
        }
        xd = Mth.sin(age * 0.125F) * 0.05F;

        //move(0.0D, 0.005D, speed);
        this.setAlpha(Mth.lerp(0.01F, this.alpha, 0.0F));
        this.setSpriteFromAge(this.sprites);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet p_107739_) {
            this.sprite = p_107739_;
        }

        public Particle createParticle(SimpleParticleType p_107750_, ClientLevel p_107751_, double p_107752_, double p_107753_, double p_107754_, double p_107755_, double p_107756_, double p_107757_) {
            ZzzParticle particle = new ZzzParticle(p_107751_, p_107752_, p_107753_, p_107754_, p_107755_, p_107756_, p_107757_, this.sprite);
            particle.setAlpha(1.0F);
            return particle;
        }
    }
}
