package ilinykh.ru.service;

import ilinykh.ru.annotations.IntensiveComponent;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.*;

/**
 * @author Yury
 */
public class SearchServiceImpl implements SearchService {
    /**
     * Производит поиск всех классов в определенном пакете
     *
     * @param basePackage пакет в котором необходимо осуществлять поиск
     * @return множество классов
     */
    @Override
    public Set<Class<?>> search(String basePackage) {
        Reflections reflections = new Reflections(basePackage, Scanners.TypesAnnotated);
        return reflections.getTypesAnnotatedWith(IntensiveComponent.class);
    }
}
