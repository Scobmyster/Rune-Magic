package scobmyster.runemagic.api.livingforce;

public interface ILivingForceReciever extends ILivingForceBlock
{

    public boolean isFull();

    public void recieveLivingForce(int livingforce);

    public boolean canRecieveLivingForce();

}
