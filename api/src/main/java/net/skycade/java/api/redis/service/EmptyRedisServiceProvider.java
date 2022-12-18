package net.skycade.java.api.redis.service;

import net.skycade.java.api.redis.handler.RedisPacketHandler;
import net.skycade.java.api.redis.packet.RedisPacket;
import net.skycade.java.api.redis.packet.response.PacketResponseCache;
import net.skycade.java.api.redis.packet.topic.TopicKey;
import org.redisson.api.RFuture;

public class EmptyRedisServiceProvider extends RedisServiceProvider {

  @Override
  public PacketResponseCache responseCache() {
    throw new UnsupportedOperationException("Redis is not enabled");
  }

  @Override
  protected <T extends RedisPacket> void listenForDirectPacket(TopicKey channel,
                                                               RedisPacketHandler<T> handler) {
    throw new UnsupportedOperationException("Redis is not enabled");

  }

  @Override
  protected <T extends RedisPacket> void listenForGlobalPacket(TopicKey channel,
                                                               RedisPacketHandler<T> handler) {
    throw new UnsupportedOperationException("Redis is not enabled");

  }

  @Override
  public long publish(RedisPacket packet) {
    throw new UnsupportedOperationException("Redis is not enabled");
  }

  @Override
  public RFuture<Long> publishAsync(RedisPacket packet) {
    throw new UnsupportedOperationException("Redis is not enabled");
  }

  @Override
  public <T extends RedisPacket> long publish(RedisPacket packet, RedisPacketHandler<T> handler) {
    throw new UnsupportedOperationException("Redis is not enabled");
  }

  @Override
  public <T extends RedisPacket> RFuture<Long> publishAsync(RedisPacket packet,
                                                            RedisPacketHandler<T> handler) {
    throw new UnsupportedOperationException("Redis is not enabled");
  }
}
