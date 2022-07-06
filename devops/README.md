[Ссылка на новый диск](https://drive.google.com/file/d/1Na4G4yEp0gDX8ECVZLOgLy0V7hvgdUx1/view?usp=sharing)

Данные для входа на сервер - user:2300  
Алгоритм запуска:
- Логинимся на сервер и узнаем IP через `sh get_ip`
- Затем на хосте виртуалки добавляем сервер как удаленный репозиторий
`git remote add live-server user@<---IP--->:/home/user/Ambulance-Data-Parsing`
- Также не забываем сменить IP в строке подключения к базе данных в `application.properties` 
(его также нужно будет закоммитить для отправки на сервер)
- Делаем `git push live-server test` на данный репозитой в ветку test (пока что)
- Возвращаемся на сервер и делаем первый запуск `sh first_boot`
В дальнейшем сервер сам перезапускается при обнаружении полученного `git push`  

Подключение к серверу теперь производится по IP полученного после `sh get_ip` в виде `<---IP--->:8080`


[Ссылка на диск](https://drive.google.com/file/d/12OLn_cnKpZyv_zEonmuEjXOy5VuKX4hC/view?usp=sharing)

Данные для входа на сервер - user:2300  
IP базы и запуск контейнера с ней `sh start_postbd`  
Если IP получить не удалось, смотрим сами через `ip a`  
Данный для входа в базу - postgres:password  
