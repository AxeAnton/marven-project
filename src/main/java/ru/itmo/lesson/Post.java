package ru.itmo.lesson;

public class Post { //2й урок 51. - Т.к нам необходимо, что бы в программе создавались объекты userId, id, title, body поэтому в классе должны быть аналогичные:
    // 2й урок 66 инфо взято с 2й урок 65 (application) - так же необходима исключать из джесон строчки свойства с Null (@JasonInclude.Include.NON_NULL и @JasonInclude.Include.NON_DEFAULT(а это будут исключены derult, false, null, char пустой, и ноль, ноль)), если анатация стоит над полем свойств то исключение будет применено к своству, если над классом, то ко всему классу.
    private int userId;
    private int id;
    private String title;
    private String body;
    // 2й урок 52. - Создаем пустой конструктор + обязательно сетторы и гетторы, ну и toString при необходимости, что бы в удобоваримом виде было..

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }



}
