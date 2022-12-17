package net.skycade.java.api.redis.packet.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicKey {

  private static final String SEPARATOR = ":";

  /**
   * The key of the topic.
   * Usually represents the target of the packet.
   * This would be a server id or the keyword that represents all servers ('all').
   */
  private final String key;

  private final List<TopicKey> children;

  /**
   * Creates a new topic key.
   *
   * @param key      the key.
   * @param children the children.
   */
  public TopicKey(String key, List<TopicKey> children) {
    this.key = key;
    this.children = children;
  }

  /**
   * Creates a new topic key.
   *
   * @param key the key.
   */
  public TopicKey(String key) {
    this(key, new ArrayList<>());
  }

  /**
   * Adds a child.
   *
   * @param child the child.
   */
  public TopicKey addChild(TopicKey child) {
    children.add(child);
    return this;
  }

  /**
   * Builds the full key.
   *
   * @return the full key.
   */
  public String build() {
    String key = this.key == null ? "" : this.key;
    // If there are no children, return the key.
    if (children.isEmpty()) {
      return key;
    }

    // Otherwise, build the key.
    return key + SEPARATOR +
        children.stream().map(TopicKey::build).collect(Collectors.joining(SEPARATOR));
  }
}
