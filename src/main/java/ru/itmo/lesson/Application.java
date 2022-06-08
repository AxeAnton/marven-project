package ru.itmo.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        Pojo pojo = new Pojo(1); // 2й урок -
        System.out.println(pojo); // 2й урок -
        // 2й урок 16. - https://jsonplaceholder.typicode.com/posts?_limit=10 - берем простенький сервис, который присылает посты из инта.

        // 2й урок 18. - ОБЪЕКТ ЗАПРОСА, позволяет отправлять запросы.
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 2й урок 17. - создаем экземпляр клиента, что бы отпроавлять запрос
        // 2й урок 19. - вроде он ругается красным, поэтому посмотреть, что подсказывает среда и либо выкинуть ошибку через - throws IOException или try с ресурсами (ресурсы - круглые скобочки), либо вызываем метод close!!!

        String url = "https://jsonplaceholder.typicode.com/posts?_limit=10"; // 2й урок 22. - строка запроса должна быть обязательно иначе запрос ни куда не уйдет.
        HttpUriRequest httpGet = new HttpGet(url); // 2й урок 20 - объект самого запроса (сам запрос), будет отправлен на указанный в аргументе адрес
        // 2й урок 21. - на любой сервер по http протоколу можно отправить гет запрос, для данной библиотеки это класс HttpGet - если мы отправляем get запрос предполагается, что мы хотим получить ответ от из сервера

        // 2й урок 22.- сообщение ОТ КЛИЕНТА на сорвер состоит из:
        // 2й урок 22.1. - строки запроса, если нужно клиент может передать сообщение в строке запроса после "?" знака ?_limit=10&limit2=10 (имя данных = значение&имя2 данных = значение.....) данные передаются в строчках если это get запрос или delete запрос.
        // 2й урок 22.2. - заголовков - это дополнительная инфо о т сообщения
        // 2й урок 22.3. - тело сообщения - пустое или с данными, данные (сообщение) передаются в парах (имя и значение) в строчном виде, только в случае если это если это post запрос, put запрос или patch запрос. Данные в теле сообщения передавать более безопасно, чем в строчке запроса, в сообщении можно передать ФАЙЛЫ (байты).

        // 2й урок 23. - типы запросов:
        // 2й урок 23.1 - get запрос для получения данных, максимально безопасен для сервера. HttpGet
        // 2й урок 23.2 - post запрос, после того как клиент собрал инфо и добавляет ее на сервер в базу данных. HttpPost (пример 32)
        // 2й урок 23.3 - put и patch запрос - это обновление информации (клиент хочет изменить пароль или еще что то) HttpPut и Http Patch
        // 2й урок 23.4 - delete запрос, клиент захотел удалить аккаунт. И вот тогда надо передать уникальные данные о клиенте. HttpDelete

        // 2й урок 24. - ОБЪЕКТ ОТВЕТА получим данные которые пришлет сервер
        // 2й урок 30. - json строчки формируются не в ручную, а с помощью классов и свойств, для разных языков по разному, существуют специальные библиотеки, например мы создаем объект (как создать и преобразовать объект в jaso и обратно будет ниже), потом с помощью библиотеки данный объект превращается в json строчку/HTML/XML....(может другие еще есть ) и эта json строчка отправляется на сервер, либо нам приходит json строчка мы обращаемся к библиотекам и на основе json строчки библиотека создает допустим ArrayList внутри которого, находятся объекты того ТД которые нам нужны.
        CloseableHttpResponse responseGet = httpClient.execute(httpGet);
        // 2й урок - 25. сервер формирует ОТВЕТНОЕ СООБЩЕНИЕ, которые состоят из:
        // 2й урок - 25.1. статус ответа, например 200, 400... (успех или ошибка)
        // 2й урок - 25.2. заголовки
        // 2й урок - 25.3. тело сообщения - пустое или с данными

        // 2й урок 26. - ДЛЯ ПОЛУЧЕНИЯ ДАННЫХ необходим объект
        // 2й урок 27. - получаемые данные будут либо в HTML формате или в json, либо еще есть вариант получения инфо в формате HTML через браузер (но он не часто встречается).
        HttpEntity entityGet = responseGet.getEntity();
        // 2й урок 28. - считывание информации происходит из entityGet
/*        // 2й урок 29. - { // FIXME json формат, ЭТО СТРОКА. Все вместе это объект.
                            "userId": 1, // FIXME у объекта есть свойства - ключи
                                "id": 9, //FIXME свойство - ключ
                            "title": // FIXME свойство - ключ
                            // FIXME знвчение объекта
                            "nesciunt iure omnis dolorem tempora et accusantium",
                            "body": "consectetur animi nesciunt iure dolore\nenim quia ad\nveniam autem ut quam aut nobis\net est aut quod aut provident voluptas autem voluptas" },
*/
        String jsonGet = EntityUtils.toString(entityGet); // 2й урок 31. - обращаемся к классу EntityUtils вызываем метод toString, передаем в него jason строчку.
        System.out.println(jsonGet);
        ObjectMapper mapper = new ObjectMapper(); // 2й урок 50. - Этот объект отвечает за преобразование строки в объект и обратно. ДЛя этого мы используем вот эту библиотечку или метод ObjectMapperю. Мы хотим создать список (объект) со строчками jason, но бес класса объект создать нельзя, поэтому необходимо создать класс, далее класс Post


        // 2й урок 53. - если необходимо создать коллекция объектов из json строки. Создать ТД CollectionType дальше стандартно (без объяснений).
        // 2й урок 54. - Если нужны объекты внутри коллекции type, то в аргумент метода помещаем первым аргументом ArrayList.class, внутри ArrayList листа будут объекты типа Post.class ()
        CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Post.class);
        ArrayList<Post> posts = mapper.readValue(jsonGet, type); // 2й урок 54. - говорим, что хотим получить коллекцию с этими постами у маппера вызываем метод readValue (всегда используется для создания объекта из jason строчки), в аргумент передаем jason строчку и type (ВСЕГДА нужен когда собираем коллекцию, если не коллекцию смотри комментарий №56) и
        System.out.println(posts); // 2й урок 55. - получаем коллекцию с постами.

        // 2й урок - CloseableHttpResponse, CloseableHttpClient необходимо закрыть если он создан не в try () with resources.

        // 2й урок 32. - Post запрос и передача данных в теле сообщения (причем создание запроса для get, put и patch, будут одинаково).
        HttpPost httpPost = new HttpPost("https://jsonplaceholder.typicode.com/posts"); // 2й урок 33. - на сервере смотрим, как должна выглядеть строчка запроса.

        // 2й урок - 34. добавление данных в тело сообщений (put, patch и post)
        List<NameValuePair> params = new ArrayList<>(); // 2й урок 35. - сначала формируется список параметров, из этого списка параметры будут добавлены в тело сообщения. NameValuePair - это ТД списка для добавления сообщений и списку нужен данный ТД, это инфо из их документации. В общем получился экземпляр объекта в виде ArrayList, в который будут добавлены параметры с ТД  List<NameValuePair>. Если такая библиотечка уже есть на компе ее можно подсоединить выбрав File->Project Structure -> Liberary далее справа (третий столбец) первый плюсик и через add, добавляем необходимую библиотеку.

        // 2й урок 36. - Что бы добавить инфо смотрим на сервер и там должно быть написано, что необходимо прислать ключи или параметры. И мы их должны отправить. Напр. нам нужно добавить те что в аргументах
        params.add(new BasicNameValuePair("userId", "45")); // 2й урок 37. - двнные 1
        params.add(new BasicNameValuePair("title", "java 16")); // 2й урок 38. - двнные 1
        params.add(new BasicNameValuePair("body", "Record type")); // 2й урок 39. - двнные 1


        // 2й урок 40. - далее кодируем и добавляем в тело сообщения
        httpPost.setEntity(new UrlEncodedFormEntity(params)); // 2й урок 41. - httpPost- из коммент 32, вызываем setEntity и параметры будут добавлять ся в тело сообщения, кодировать их необходимо, т.к сообщения могут содержать не валитные символы, кирилица или пробелы, он все сделает удобоворимым.

         // 2й урок 41 - ПОЛУЧЕНИЕ запроса будет, выглядеть одинаково
        CloseableHttpResponse responsePost = httpClient.execute(httpPost);
        HttpEntity entityPost = responsePost.getEntity();// 2й урок 42 -получаем тело сообщения
        String jesonPost = EntityUtils.toString(entityPost); // 2й урок 43 -переводим jeson строчку в toString
        System.out.println(jesonPost); // 2й урок 44 - получим все те же данные плюс что и отправляли идентификатор поста

/*        { // FIXME 2й урок 45 - получим в консоли
            "userId": "45",
                "title": "java 16",
                "body": "Record type",
                "id": 101 // FIXME 2й урок 46 -  три строчки до это то что отправляли и сервер добавил id.
        }
*/

        // 2й урок 56. - здесь ни каких tape не создаем, пишем
        Post post = mapper.readValue(jesonPost, Post.class); // 2й урок 57. - собираем объект поста, обращаемся к мапперу вызываем метод readValue и передаем в аргумет jesonPost (jason строчка) и Post.class (ссылка на класс поста).
        System.out.println(post);

        // 2й урок 58 - ДЗ - postgreSQL + pgAdmin - скачать программы, пароли не теряем.

        // 2й урок 58 (хотя это дали на третьем урока но смысл не должен поменяться т.к это продолжение темы 2го урока)
        // 2й урок 59 Создание объекта из json строки.
        Post newPost = new Post(); // 2й урок 60 у нас есть объект у которого можно вызвать сетторы (четыре строчки ниже),
        newPost.setId(34);
        newPost.setUserId(12);
        newPost.setTitle("Новая запись");
        newPost.setBody("текст записи");

        String jsonStr = mapper.writeValueAsString(newPost); // 2й урок 61 мы обращаемся к методу mapper (для преобразования в json и обратно в объект) и вызываем метод writeValueAsString, те. Записываем объект в jason строчку (которая содержит информацию об указанном объекте), здесь если коллекция ни какие дополнительные типы и ссылки не нужны, ссылки на классы не нужны (не очень понятное предложение).
        System.out.println(jsonStr);

        // 2й урок 61 mapper - этот же самый объект может не только приоразовавать json строчку но и сразу записывать из json файл, может быть удобно для хронения информации об обычных объектах или о каких нибудь настройках.
        mapper.writeValue(new File("file.json"),newPost); // 2й урок 62 - для записи в файлик нам нужен файл, если его нет то он будет создан и ссылка на объект который должен быть записан в этот файлик (скорее всего начтройки).

        // 2й урок 63 - чтение из файлика.
        Post fromFile = mapper.readValue(new File("file.json"), Post.class); // 2й урок 64 - работает так, mapper читает jeson строчку из файлика (он должен существовать), а потом преобразует ее нужный объект, объект того ТД который мы укажем. Если в файлеке коллекция с объектами, то во втором аргументе необходимо передать ссылка на ТД коллекции, как в комментах - 2й урок 53-55...
        // 2й урок 65 - ну и вообще по принципу работы по библиотекам с jeson, принцип работу у них у всех одинаковый, примерно такой: сразу смотрите анотации в документации, что включается в жейсон строчку, что нет, далее как исключить свойство из джейсон строчки, для каких то библиотек это будет анатация @JasonIgnor над свойством, которая будит исключать свойство из ждейсон нстрочки а возможно просто в свойства добавляется transient (private transient int userId;), так же необходима исключать из джесон строчки свойства с Null (@JasonInclude.Include.NON_NULL и @JasonInclude.Include.NON_DEFAULT(а это будут исключены derult, false, null, char пустой, и ноль, ноль)), и надо обратить внимание как библиотека работает с датой и временем(может для каких то библиотек необходимо указать возможность сериализации (возможность переводить врем.и дату в строчку)).

    }
}
