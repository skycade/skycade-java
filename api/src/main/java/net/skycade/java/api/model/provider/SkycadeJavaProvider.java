package net.skycade.java.api.model.provider;

/**
 * @param <T> the type of the model
 * @author Jacob Cohen
 */
public interface SkycadeJavaProvider<T> {

  /**
   * Provides the instance of the specified type.
   *
   * @return the instance of the specified type
   */
  T get();
}
