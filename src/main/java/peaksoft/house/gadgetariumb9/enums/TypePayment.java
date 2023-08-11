package peaksoft.house.gadgetariumb9.enums;

public enum TypePayment {
    CASH("Наличные"),
    CARD_ONLINE("Оплатить онлайн"),
    CARD_ON_RECEIPT("Оплатить картой при получений");

    private final String value;

    TypePayment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}