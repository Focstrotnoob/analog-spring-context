package ilinykh.ru.service;

import java.util.Set;

/**
 * Интерфейс для поиска классов, аннотированных @IntensiveComponent.
 */
public interface SearchService {

    Set<Class<?>> search(String packageName);
}
