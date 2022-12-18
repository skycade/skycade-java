package net.skycade.java.api.redis.handler.middleware;

import net.skycade.java.api.SkycadeJavaBootstrapper;
import net.skycade.java.api.redis.service.RedissonServiceProvider;
import net.skycade.java.api.redis.handler.RedisPacketHandler;
import net.skycade.java.api.redis.packet.RedisPacket;
import net.skycade.java.api.redis.packet.response.RedisResponsePacket;
import org.redisson.api.listener.MessageListener;

/**
 * Represents a middleware for a {@link RedisPacketHandler}.
 * <p>
 * A middleware is a class that is used to intercept a packet before it is handled by a handler.
 * This allows for the packet to be checked to see if it is a response to a previous packet, and
 * if so, the packet can be handled correctly. We can also modify the packet before it is handled
 * by the handler.
 */
public class RedisPacketHandlerInterceptor<T extends RedisPacket> implements MessageListener<T> {

  /**
   * The redis service provider.
   */
  private final RedissonServiceProvider serviceProvider;

  /**
   * The next handler in the chain.
   */
  private final RedisPacketHandler<T> next;

  /**
   * Constructor.
   *
   * @param serviceProvider the redis service provider.
   * @param next            the next middleware.
   */
  public RedisPacketHandlerInterceptor(RedissonServiceProvider serviceProvider,
                                       RedisPacketHandler<T> next) {
    this.serviceProvider = serviceProvider;
    this.next = next;
  }

  @Override
  public void onMessage(CharSequence channel, T msg) {
    if (!(msg instanceof RedisResponsePacket)) {
      this.next.handle(msg);
      return;
    }

    // look in the cache for the packet that this packet is a response to.
    RedisPacketHandler<T> handler =
        serviceProvider.responseCache().get(((RedisResponsePacket) msg).respondingToId());

    // if the handler is not null, then the packet is a response to a previous packet.
    if (handler != null) {
      // handle the packet.
      handler.handle(msg);
      // remove the packet from the cache.
      serviceProvider.responseCache().remove(((RedisResponsePacket) msg).respondingToId());
    } else {
      // print an error that the packet is a response to a previous packet, but the packet
      // was not found in the cache.
      SkycadeJavaBootstrapper.LOGGER.error(
          "Packet with responding id of {} is a response to a previous packet, but the packet was not found in the cache.",
          ((RedisResponsePacket) msg).respondingToId());
    }
  }
}
