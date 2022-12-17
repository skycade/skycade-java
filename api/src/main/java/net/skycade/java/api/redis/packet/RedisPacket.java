package net.skycade.java.api.redis.packet;

import java.util.UUID;
import net.skycade.java.api.redis.packet.topic.RedisPacketKey;

public class RedisPacket {

  /**
   * The key of the packet.
   * <p>
   * Contains the target and channel, and is sent as the first part of the packet.
   */
  private final RedisPacketKey key;

  /**
   * The id of the packet's sender.
   */
  private final String sender;

  /**
   * The id of the packet itself
   * <p>
   * This is used to identify the packet when it is received, and is used to send a response if needed.
   */
  private final UUID packetId;

  /**
   * Constructor.
   *
   * @param key    the topic key.
   * @param sender the sender of the packet.
   */
  public RedisPacket(RedisPacketKey key, String sender) {
    this.key = key;
    this.sender = sender;
    this.packetId = UUID.randomUUID();
  }

  /**
   * Gets the key of the packet.
   */
  public RedisPacketKey key() {
    return key;
  }

  /**
   * Gets the sender of the packet.
   */
  public String sender() {
    return sender;
  }

  /**
   * Gets the id of the packet.
   */
  public UUID packetId() {
    return packetId;
  }
}
