# Деплой

В этом домашнем задании мы научимся обрабатывать строку запроса и выполнять деплой приложения. Деплой — процесс выкладки приложения на сервер (или сервера). В результате к приложению можно получить доступ через браузер. Для выполнения домашнего задания понадобятся утилиты *git* и *heroku* и зарегистрированный аккаунт на [heroku](https://www.heroku.com/) в том случае, если вы используете heroku. Либо аккаунт на *railway*, если вы используете сервис [Railway](https://railway.app/).

## Ссылки

* [Урок Передача данных query string](https://ru.hexlet.io/courses/http_protocol/lessons/query_string/theory_unit)
* [Установка утилиты Heroku](https://devcenter.heroku.com/articles/heroku-cli#download-and-install)
* [Сайт heroku](https://www.heroku.com/)
* [Деплой на heroku](https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat#deploy-to-heroku)
* [Railway Getting Started](https://docs.railway.app/getting-started)
* [Что делать, если возникли трудности с деплоем Java-приложения на Railway](https://ru.hexlet.io/blog/posts/render-java)

## servlet/CompaniesServlet.java

## Задачи

* Реализуйте логику метода `doGet()`, который должен отображать список компаний в зависимости от параметров запроса. Список всех доступных компаний генерируется методом `getCompanies()` (уже импортирован в модуль). При выводе списка компаний, сортировать их дополнительно не требуется.

  * Если строка запроса содержит параметр `search`, то должен выводиться список только тех компаний, которые содержат в имени переданное значение.

    ```text
    http://localhost:5000/companies?search=ov

    Cartwright-Glover and Sons
    Hermann, Macejkovic and Brekke Group
    ```

  * Если значение параметра `search` отсутствует в именах компаний, то должна выводиться строка `Companies not found`.

    ```text
    http://localhost:5000/companies?search=tl

    Companies not found
    ```

  * В случае отсутствия в строке запроса параметра `search` или если значением является пустая строка — должны выводиться все компании. Подробнее смотрите примеры.

    ```text
    http://localhost:5000/companies?search=
    http://localhost:5000/companies

    <список полностью всех компаний>
    ```

## Подсказки

* Для работы со строкой запроса вам понадобится использовать методы [getParameter()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/ServletRequest.html#getParameter-java.lang.String-) и [getQueryString()](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletRequest.html#getQueryString--).

## App.java

## Задачи

* Зарегистрируйте в контейнере tomcat сервлет `CompaniesServlet`, так чтобы он обрабатывал запросы по пути `/companies`. Для проверки работы приложения запустите сервер, используя команду `make start`. Приложение в браузере будет доступно по адресу *http://localhost:5000*

## build.gradle

## Задачи

* В процессе деплоя heroku и railway с помощью gradle будут запускать задачу *stage*, которую необходимо создать. В простейшем случае задача *stage* должна выполнять установку приложения (задачу *install*), но без запуска тестов — это можно сделать вызвав задачу *installDist*. Добавим задачу *stage* с нужной логикой:

  ```groovy
  task stage(dependsOn: ['clean', 'installDist'])
  installDist.mustRunAfter clean
  ```

  Теперь задача `stage` будет готовить исполняемый файл приложения

## Procfile

## Задачи

* После успешной компиляции и сборки проекта, приложение нужно запустить. Это можно сделать если указать команду которую нужно выполнить. Добавьте команду запуска исполняемого файла:

  ```bash
  web: sh build/install/deploy/bin/deploy
  ```

## Gradle Wrapper

## Задачи

* В процессе деплоя и heroku и railway автоматически определят, что используется проект gradle. Но для корректной работы необходимо использовать нужные версии gradle и java

    Зафиксировать нужную версию gradle можно с помощью, так называемого Wrapper (локальной для проекта копии Gradle). Для этого выполните в директории домашнего задания команду:

    ```bash
    gradle wrapper --gradle-version 7.4
    ```

    Указать целевую версию java можно с помощью конфигурации в файле *system.properties*:

    ```text
    java.runtime.version=17
    ```

## Деплой

## Задачи

* Зарегистрируйте бесплатный аккаунт на [heroku](https://www.heroku.com/) или [Railway](https://railway.app/). Если вы из РФ, вам необходимо подключить [VPN](https://github.com/Hexlet/hexlet-unblock) и указать другую страну.
* Если вы деплоите на Heroku, в директории с домашним заданием выполните последовательно команды, как показано в [руководстве по деплою](https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat#deploy-to-heroku)
* Сервис railway позволяет задеплоить приложение прямо из вашего аккаунта на гитхаб. При создании нового приложения в Dashboard укажите ваш аккаунт с домашними работами на гитхабе. Так как наше приложение, котрое мы собираемся задеплоить, находится в репозитории не в корне, нужно указать директорию, в которой оно расположено – */java-web-ru/deploy/*. Это будет корневая директория приложения – *Root Directory*. Теперь осталось подсказать Railway, при помощи какой команды собрать наше приложение. Это делается в поле *Build Command*. Укажите там запуск задачи `stage`, которую мы подготовили:

  ```bash
  ./gradlew stage
  ```

  На этом настройка приложения окончена, команду запуска исполняемого файла Railway автоматически возьмет из Procfile. Дождитесь, когда приложение соберется и запустится и откойте его
* После открытия задеплоенного приложения в браузере убедитесь, что оно работает

## Подсказки

* Если при работе с сервисами Railway или Heroku возникли сложности, используйте альтернативный – [render.com](https://render.com). Процесс деплоя приложения на render.com описан в нашей [статье](https://ru.hexlet.io/blog/posts/render-java). На render вам тоже понадобится указать в настройках приложения корневую директорию проекта
