package ilinykh.ru.context;


import ilinykh.ru.factory.DependencyFactory;
import ilinykh.ru.factory.DependencyFactoryImp;
import ilinykh.ru.service.InjectionService;
import ilinykh.ru.service.InjectionServiceImpl;
import ilinykh.ru.service.SearchService;
import ilinykh.ru.service.SearchServiceImpl;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IntensiveContext {
    private final String basePackage;
    private final SearchService searchService;
    private final InjectionService injectionService;
    private final DependencyFactory dependencyFactory;
    private final Map<Class<?>, Object> components = new HashMap<>();
    private final Map<Class<?>, Class<?>> interfaceImplementations = new HashMap<>();

    /**
     * Конструктор для создания IntensiveContext.
     *
     * @param basePackage имя пакета для поиска классов, аннотированных @IntensiveComponent.
     */
    public IntensiveContext(String basePackage) {
        this.basePackage = basePackage;
        dependencyFactory = new DependencyFactoryImp();
        searchService = new SearchServiceImpl();
        injectionService = new InjectionServiceImpl();
    }

    /**
     * Метод для получения экземпляра класса или интерфейса.
     *
     * @param <T>  тип запрашиваемого объекта
     * @param type класс или интерфейс запрашиваемого объекта
     * @return экземпляр запрашиваемого объекта
     * @throws Exception если не удается создать экземпляр или найти реализацию
     */
    public <T> T getObject(Class<T> type) throws Exception {
        if (components.containsKey(type)) {
            return type.cast(components.get(type));
        }

        Set<Class<?>> componentClasses = searchService.search(basePackage);
        for (Class<?> clazz : componentClasses) {
            if (clazz.isInterface()) {
                continue;
            }

            for (Class<?> iface : clazz.getInterfaces()) {
                if (interfaceImplementations.containsKey(iface)) {
                    throw new RuntimeException("Multiple implementations found for interface: " + iface.getName());
                }
                interfaceImplementations.put(iface, clazz);
            }
        }

        Class<?> implementationClass = type;
        if (type.isInterface()) {
            if (!interfaceImplementations.containsKey(type)) {
                throw new RuntimeException("No implementation found for interface: " + type.getName());
            }
            implementationClass = interfaceImplementations.get(type);
        }

        T instance = type.cast(dependencyFactory.createInstance(implementationClass));
        components.put(type, instance);
        injectionService.injectDependency(instance, this);

        return instance;
    }

}
