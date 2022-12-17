package net.skycade.java.api.redis;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import net.skycade.java.api.redis.handler.middleware.RedisPacketHandlerInterceptor;
import net.skycade.java.api.redis.packet.RedisPacket;
import net.skycade.java.api.redis.packet.response.PacketResponseCache;
import net.skycade.java.api.redis.packet.topic.RedisPacketKey;
import net.skycade.java.api.redis.packet.topic.TopicKey;
import net.skycade.java.api.redis.handler.RedisPacketHandler;
import org.redisson.api.RFuture;

public class RedisServiceProvider {

  /**
   * The Redisson client provider.
   */
  private final RedissonClientProvider redissonClientProvider;

  /**
   * The target identifier for global targeted packets.
   */
  private final TopicKey globalTargetKey;

  /**
   * The target identifier for local targeted packets.
   */
  private final TopicKey serverTargetKey;

  /**
   * The cache for response packet handlers.
   */
  private final PacketResponseCache responseCache;

  /**
   * Constructs a new {@link RedisServiceProvider} instance.
   *
   * @param redissonClientProvider the Redisson client provider.
   * @param globalTargetKey        the target identifier for global targeted packets.
   * @param serverTargetKey        the target identifier for local targeted packets.
   */
  public RedisServiceProvider(RedissonClientProvider redissonClientProvider,
                              TopicKey globalTargetKey, TopicKey serverTargetKey) {
    this.redissonClientProvider = redissonClientProvider;
    this.globalTargetKey = globalTargetKey;
    this.serverTargetKey = serverTargetKey;

    this.responseCache = new PacketResponseCache(Duration.of(30, ChronoUnit.SECONDS));
  }

  /**
   * Constructs a new {@link RedisServiceProvider} instance.
   *
   * @param redissonClientProvider the Redisson client provider.
   * @param globalTargetId         the target identifier for global targeted packets.
   * @param serverTargetId         the target identifier for local targeted packets.
   */
  public RedisServiceProvider(RedissonClientProvider redissonClientProvider, String globalTargetId,
                              String serverTargetId) {
    this(redissonClientProvider, new TopicKey(globalTargetId), new TopicKey(serverTargetId));
  }

  /**
   * Gets the response cache.
   */
  public PacketResponseCache responseCache() {
    return responseCache;
  }


  /**
   * Listens for packets directed at the local server.
   *
   * @param channel the channel to listen on (e.g. {@code "move_player"} or {@code "update_permissions"})
   *                <p> Essentially, this is the packet type.
   * @param handler the handler for the packet.
   * @param <T>     the type of the packet.
   */
  protected <T extends RedisPacket> void listenForDirectPacket(TopicKey channel,
                                                               RedisPacketHandler<T> handler) {
    this.redissonClientProvider.get()
        .getTopic(RedisPacketKey.of(serverTargetKey.build(), channel.build()).build())
        .addListener(handler.packetClass(), new RedisPacketHandlerInterceptor<>(this, handler));
  }

  /**
   * Listens for packets directed at the entire network.
   *
   * @param channel the channel to listen on (e.g. {@code "move_player"} or {@code "update_permissions"})
   *                <p> Essentially, this is the packet type.
   * @param handler the handler for the packet.
   * @param <T>     the type of the packet.
   */
  protected <T extends RedisPacket> void listenForGlobalPacket(TopicKey channel,
                                                               RedisPacketHandler<T> handler) {
    this.redissonClientProvider.get()
        .getTopic(RedisPacketKey.of(globalTargetKey.build(), channel.build()).build())
        .addListener(handler.packetClass(), new RedisPacketHandlerInterceptor<>(this, handler));

  }

  /**
   * Publishes a packet to the Redis server.
   *
   * @param packet the packet to publish.
   * @return the result of the publish operation.
   */
  public long publish(RedisPacket packet) {
    return this.redissonClientProvider.get().getTopic(packet.key().build()).publish(packet);
  }

  /**
   * Publishes a packet to the Redis server asynchronously.
   *
   * @param packet the packet to publish.
   * @return a future representing the result of the publish operation.
   */
  public RFuture<Long> publishAsync(RedisPacket packet) {
    return this.redissonClientProvider.get().getTopic(packet.key().build()).publishAsync(packet);
  }

  /**
   * Publishes a packet to the Redis server and handles a response.
   *
   * @param packet  the packet to publish.
   * @param handler the handler for the response.
   * @return the result of the publish operation.
   */
  public <T extends RedisPacket> long publish(RedisPacket packet, RedisPacketHandler<T> handler) {
    this.responseCache().put(packet.packetId(), handler);
    return this.publish(packet);
  }

  /**
   * Publishes a packet to the Redis server and handles a response asynchronously.
   *
   * @param packet  the packet to publish.
   * @param handler the handler for the response.
   * @return a future representing the result of the publish operation.
   */
  public <T extends RedisPacket> RFuture<Long> publishAsync(RedisPacket packet,
                                                            RedisPacketHandler<T> handler) {
    this.responseCache().put(packet.packetId(), handler);
    return this.publishAsync(packet);
  }
}
