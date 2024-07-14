package ilinykh.ru.service;

import ilinykh.ru.context.IntensiveContext;
import ilinykh.ru.annotations.IntensiveComponent;
import java.lang.reflect.Field;


/**
 * Интерфейс для внедрения зависимостей в поля класса.
 */
public class InjectionServiceImpl implements InjectionService {

    /**
     * @param instance экземпляр в который необходимо внедрить зависимость
     * @param context контекст
     * @throws Exception при возникновении проблем с внедрением зависимостей
     */
    @Override
    public void injectDependency(Object instance, IntensiveContext context) throws Exception {
        Field [] fields = instance.getClass().getDeclaredFields();
        for (Field field:fields) {
            if (field.isAnnotationPresent(IntensiveComponent.class)){
                field.setAccessible(true);
                Object injectionObj = context.getObject(field.getType());
                field.set(instance, injectionObj);
            }

        }
    }
}
