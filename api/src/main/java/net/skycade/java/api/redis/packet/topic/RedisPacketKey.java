package net.skycade.java.api.redis.packet.topic;

import java.util.Collections;
import net.skycade.java.api.redis.RedisConstants;

/**
 * Represents a key of a packet.
 * <p>
 * It is sent as the topic of the packet, and contains a target server id and a channel name.
 * The channel name is always required, but the server id is optional; if it is not specified,
 * the packet will be sent to all servers.
 */
public final class RedisPacketKey extends TopicKey {

  /**
   * Constructor.
   *
   * @param target  the target server id.
   * @param channel the channel.
   */
  public RedisPacketKey(String target, String channel) {
    super(target, Collections.singletonList(new TopicKey(channel)));
  }

  /**
   * Constructor.
   *
   * @param target  the target server id.
   * @param channel the channel.
   */
  public RedisPacketKey(String target, TopicKey channel) {
    super(target, Collections.singletonList(channel));
  }


  /**
   * Constructor.
   *
   * @param channel the channel.
   */
  public RedisPacketKey(TopicKey channel) {
    super(RedisConstants.ALL_SERVERS_TARGET_ID, Collections.singletonList(channel));
  }

  /**
   * Constructor.
   *
   * @param channel the channel.
   */
  public RedisPacketKey(String channel) {
    super(RedisConstants.ALL_SERVERS_TARGET_ID, Collections.singletonList(new TopicKey(channel)));
  }

  /**
   * Creates a packet key.
   *
   * @param target  the target server id.
   * @param channel the channel.
   * @return the packet key.
   */
  public static RedisPacketKey of(String target, String channel) {
    return new RedisPacketKey(target, channel);
  }


  /**
   * Creates a packet key.
   *
   * @param channel the channel.
   * @return the packet key.
   */
  public static RedisPacketKey ofGlobal(String channel) {
    return new RedisPacketKey(channel);
  }
}
