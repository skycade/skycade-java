package net.skycade.java.api.redis.handler;

import net.skycade.java.api.redis.packet.RedisPacket;
import org.redisson.api.listener.MessageListener;

/**
 * Represents a handler for a {@link RedisPacket}.
 *
 * @param <T> the type of the packet's payload.
 * @author Jacob Cohen
 */
public abstract class RedisPacketHandler<T extends RedisPacket> implements MessageListener<T> {

  /**
   * The class of the packet.
   */
  private final Class<T> packetClass;

  /**
   * Constructor.
   *
   * @param packetClass the class of the packet.
   */
  public RedisPacketHandler(Class<T> packetClass) {
    this.packetClass = packetClass;
  }

  /**
   * Handles a packet.
   *
   * @param packet the packet.
   */
  public abstract void handle(T packet);

  @Override
  public void onMessage(CharSequence channel, T msg) {
    // before handling the packet, check if the packet is a response to a previous packet.


    handle(msg);
  }

  /**
   * Gets the class of the packet.
   */
  public Class<T> packetClass() {
    return packetClass;
  }
}
