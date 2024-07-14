package ilinykh.ru.factory;

import java.lang.reflect.Constructor;

/**
 * @author Yury
 */
public class DependencyFactoryImp implements DependencyFactory {
    /**
     * Создает экземпляр экземпляр класса
     * @param beanClass класс экземпляр которого необходимо создать
     * @param <T> тип возвращаемого экземпляра
     * @return экземпляр класса
     * @throws Exception если не удалось создать экземпляр класса
     */
    public <T> T createInstance(Class<T> beanClass) throws Exception {
        Constructor <T> constructor = beanClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }
}
