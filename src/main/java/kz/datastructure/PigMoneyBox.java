package kz.datastructure;  // Определение пакета, в котором находится класс

// Определение класса PigMoneyBox, который реализует интерфейс MoneyBox
public class PigMoneyBox implements MoneyBox {

    // Поле для хранения суммы денег в копилке
    private int sum;

    // Флаг, указывающий, была ли копилка уже разбита
    private boolean IsBroken;

    // Конструктор с параметром: создает копилку с начальной суммой
    public PigMoneyBox(int initialSum) {
        sum = initialSum;   // Присваиваем переданную сумму полю sum
        IsBroken = false;   // Устанавливаем флаг, что копилка не разбита
    }

    // Конструктор без параметров: создает пустую копилку
    public PigMoneyBox() {
        sum = 0;            // Устанавливаем сумму в 0
    }

    // Метод для добавления денег в копилку
    @Override
    public void add(int currency) {
        // Проверяем, что добавляемая сумма не делает баланс отрицательным
        if (sum + currency <= 0) {
            throw new IllegalArgumentException();  // Выбрасываем исключение, если сумма недопустима
        }

        // Проверяем, не произошел ли переполнения (например, из-за целочисленного переполнения)
        if (sum + currency < sum) {
            throw new MoneyBoxFullException();  // Выбрасываем исключение, если копилка "переполнилась"
        }

        // Увеличиваем сумму в копилке
        sum += currency;
    }

    // Метод для "разбивания" копилки и получения всех денег
    @Override
    public int get() {
        // Проверяем, была ли копилка уже разбита ранее
        if (IsBroken) {
            throw new MoneyBoxAllreadyException();  // Если да, выбрасываем исключение
        }

        // Устанавливаем флаг, что копилка теперь разбита
        IsBroken = true;

        // Возвращаем всю накопленную сумму
        return sum;
    }
}
