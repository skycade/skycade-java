package net.skycade.java.api.model;

/**
 * Represents a provider of a loader for an object.
 *
 * @param <T> the type of the object to be loaded.
 */
public abstract class SkycadeJavaProviderObjectLoader<T> {

  /**
   * Loads the instance of the provided class.
   */
  public abstract T load();
}
