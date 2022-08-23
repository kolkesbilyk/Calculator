Данний калькулятор може виконувати функціїї: множення, ділення, додавання, та віднімання, 
також вираховує приоритетність функцій включаючи використання дужок

Графічний інтерфейс виконаний за допомою JavaFx
Бекова частина це Java core, OOP, Steams

Для запуска калькулятора через Intellij idea потрібно скачати(https://gluonhq.com/products/javafx) JavaFX SDK і 
підключить папку lib в модуль File -> Project Structure -> Calculator -> Dependencies,
а також добавити Run -> Edit Configurations -> VM Options :

--module-path
D:\Java\javafx-sdk-11.0.2\lib (Тут прописуєте ваш шлях)
--add-modules
javafx.controls,javafx.fxml
