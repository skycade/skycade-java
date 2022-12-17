package net.skycade.java.api.redis;

public class RedisConstants {

  /**
   * The channel that is used to send packets to all servers.
   * <p>
   * This is used when the channel is not specified in the packet.
   * By default, this is {@code all}.
   */
  public static String ALL_SERVERS_TARGET_ID = "all";

  /**
   * Sets the id for the redis channel/target id that is used to send packets to all servers.
   *
   * @param allServersTargetId the id for the redis channel/target id that is used to send packets to all servers
   */
  public static void setAllServersTargetId(String allServersTargetId) {
    ALL_SERVERS_TARGET_ID = allServersTargetId;
  }
}
