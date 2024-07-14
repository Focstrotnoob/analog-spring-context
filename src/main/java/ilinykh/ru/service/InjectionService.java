package ilinykh.ru.service;

import ilinykh.ru.context.IntensiveContext;

public interface InjectionService {
    /**
     * @param instance экземпляр в который необходимо внедрить зависимость
     * @param context контекст
     * @throws Exception при возникновении проблем с внедрением зависимостей
     */
    void injectDependency(Object instance, IntensiveContext context) throws Exception;
}
