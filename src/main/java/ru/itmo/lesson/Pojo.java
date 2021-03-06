package ru.itmo.lesson;

import java.io.IOException;

/**
* 25. Документация (начинается с ДВУХ звездочек), комментарии к методам и конструкторам. Класс создан для того то и того то, что за джинерик если есть и т.д.
 * 26. @author Anton Klimenko (анатация)
* */
// 24. Документация состоит из трех частей:/** /n * Коментарий, /n * Инфо в свободной форме и третья /n * анатация (что методы возвращают, автор @автор и т.д.)
public class Pojo { // 23. Классы в которых только свойства, гетторы, сетторы и переопределен toString в java ПРИНЯТО называть Pojo, причем сам класс может называться как угодно. Для этого в 16 версии IDEA появилась record (как работает не понятно).
/** 27. поле id уникальный идентификатор*/
    private final int id;
    private String name;
    /**
     * 28. Конструктору можно добавить аннотацию, конструктор принимает на вход значение для поля {@link Pojo#id}, (имя класса # свойство) которое не мб изменено после инициализации
     * */
    public Pojo(int id) {
        this.id = id;
        this.name = name;
    }
/**
 * 29. Метод, то то и тд.....описание метода...
 * 30. @return возвращает значение уникального идентификатора и в каких случаях описываем
 * */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // @throw имя ошибки, описание в свободной форме
    /**
     * 34. Добавляем аннотацию о методе, который выбрасывает исключение - @throws IOException - если id меньше 100.
     *
     * */
    public void someVoid() throws IOException {
        if (id > 100) {
            throw new IOException();
        }
            System.out.println(id);
    }

    /**
     * 31. Описание метода. Если метод, принимает несколько параметров, то у нас будет несколько АНАТАЦИЙ
     * 32. @param name значение свойства name.
     * 33. @param имя значение свойства имя. Это если нескольлко параметров в аргументе.
     * */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
