package ilinykh.ru.factory;

/**
 * Интерфейс для создания экземпляров классов.
 */

public interface DependencyFactory {

    /**
     * @param beanClass класс экземпляр которого необходимо создать
     * @param <T> тип возвращаемого экземпляра
     * @return экземпляр класса
     * @throws Exception если не удалось создать экземпляр класса
     */
    public <T> T createInstance(Class<T> beanClass) throws Exception;
}
