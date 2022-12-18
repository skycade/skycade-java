package net.skycade.java.api.redis.service;

import net.skycade.java.api.redis.handler.RedisPacketHandler;
import net.skycade.java.api.redis.packet.RedisPacket;
import net.skycade.java.api.redis.packet.response.PacketResponseCache;
import net.skycade.java.api.redis.packet.topic.TopicKey;
import org.redisson.api.RFuture;

public abstract class RedisServiceProvider {

  /**
   * Gets the response cache.
   */
  public abstract PacketResponseCache responseCache();

  /**
   * Listens for packets directed at the local server.
   *
   * @param channel the channel to listen on (e.g. {@code "move_player"} or {@code "update_permissions"})
   *                <p> Essentially, this is the packet type.
   * @param handler the handler for the packet.
   * @param <T>     the type of the packet.
   */
  protected abstract <T extends RedisPacket> void listenForDirectPacket(TopicKey channel,
                                                                        RedisPacketHandler<T> handler);

  /**
   * Listens for packets directed at the entire network.
   *
   * @param channel the channel to listen on (e.g. {@code "move_player"} or {@code "update_permissions"})
   *                <p> Essentially, this is the packet type.
   * @param handler the handler for the packet.
   * @param <T>     the type of the packet.
   */
  protected abstract <T extends RedisPacket> void listenForGlobalPacket(TopicKey channel,
                                                                        RedisPacketHandler<T> handler);


  /**
   * Publishes a packet to the Redis server.
   *
   * @param packet the packet to publish.
   * @return the result of the publish operation.
   */
  public abstract long publish(RedisPacket packet);

  /**
   * Publishes a packet to the Redis server asynchronously.
   *
   * @param packet the packet to publish.
   * @return a future representing the result of the publish operation.
   */
  public abstract RFuture<Long> publishAsync(RedisPacket packet);

  /**
   * Publishes a packet to the Redis server and handles a response.
   *
   * @param packet  the packet to publish.
   * @param handler the handler for the response.
   * @return the result of the publish operation.
   */
  public abstract <T extends RedisPacket> long publish(RedisPacket packet,
                                                       RedisPacketHandler<T> handler);

  /**
   * Publishes a packet to the Redis server and handles a response asynchronously.
   *
   * @param packet  the packet to publish.
   * @param handler the handler for the response.
   * @return a future representing the result of the publish operation.
   */
  public abstract <T extends RedisPacket> RFuture<Long> publishAsync(RedisPacket packet,
                                                                     RedisPacketHandler<T> handler);
}

