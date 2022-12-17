package net.skycade.java.api.redis.packet.response;

import java.util.UUID;
import net.skycade.java.api.redis.packet.RedisPacket;
import net.skycade.java.api.redis.packet.topic.RedisPacketKey;

public class RedisResponsePacket extends RedisPacket {

  /**
   * The id of the packet that this packet is a response to.
   */
  private final UUID respondingToId;

  /**
   * Constructor.
   *
   * @param key            the key of the packet.
   * @param sender         the id of the sender.
   * @param respondingToId the id of the packet that this packet is a response to.
   */
  public RedisResponsePacket(RedisPacketKey key, String sender, UUID respondingToId) {
    super(key, sender);
    this.respondingToId = respondingToId;
  }

  /**
   * Gets the id of the packet that this packet is a response to.
   */
  public UUID respondingToId() {
    return respondingToId;
  }
}
