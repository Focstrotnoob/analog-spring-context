package ilinykh.ru;

import ilinykh.ru.context.IntensiveContext;

public class App 
{
    public static void main( String[] args ) throws Exception {

        IntensiveContext intensiveContext = new IntensiveContext("ilinykh.ru");
        SomeClass someClass = intensiveContext.getObject(SomeClass.class);
        someClass.run();
    }
}
