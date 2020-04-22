package library.entities;

public interface IDeathFade {

    boolean isFadeOutDeath();
    int getFadeOutTicks();
    void enableFadeOutDeath(int fadeOutTicks);

}
