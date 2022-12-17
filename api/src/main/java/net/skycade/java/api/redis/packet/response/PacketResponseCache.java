package net.skycade.java.api.redis.packet.response;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import net.skycade.java.api.redis.handler.RedisPacketHandler;
import net.skycade.java.api.redis.packet.RedisPacket;

/**
 * Represents a cache for a {@link RedisResponsePacket}.
 * <p>
 * A cache is used to store a packet that is a response to a previous packet. This allows for
 * the packet to be handled correctly when it is received.
 *
 * @author Jacob Cohen
 */
public final class PacketResponseCache {

  /**
   * The cache.
   */
  private final ExpiringMap<UUID, RedisPacketHandler<?>> cache;

  /**
   * Constructor.
   *
   * @param expirationDuration the duration after which a packet will expire.
   */
  public PacketResponseCache(Duration expirationDuration) {
    this.cache = ExpiringMap.builder().expirationPolicy(ExpirationPolicy.CREATED)
        .expiration(expirationDuration.toMillis(), TimeUnit.MILLISECONDS).build();
  }

  /**
   * Puts a packet handler in the cache.
   *
   * @param id      the id of the packet.
   * @param handler the handler.
   */
  public <T extends RedisPacket> void put(UUID id, RedisPacketHandler<T> handler) {
    cache.put(id, handler);
  }

  /**
   * Gets a handler from the cache.
   *
   * @param id the id of the handler.
   * @return the handler.
   */
  public <T extends RedisPacket> RedisPacketHandler<T> get(UUID id) {
    return (RedisPacketHandler<T>) cache.get(id);
  }

  /**
   * Removes a handler from the cache.
   *
   * @param id the id of the handler.
   */
  public void remove(UUID id) {
    cache.remove(id);
  }
}
