package com.zagurskaya.cash.entity;

import java.sql.Date;

/**
 * Смена со свойствами <b>id</b>, <b>currencyId</b>, <b>received</b>, <b>coming</b> , <b>spending</b> , <b>transmitted</b> ,
 * <b>balance</b> , <b>userId</b> , <b>date</b>  и <b>dutiesId</b>.
 */
public class Kassa {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Код валюты
     */
    private Long currencyId;
    /**
     * Получено
     */
    private Double received;
    /**
     * Приход
     */
    private Double coming;
    /**
     * Расход
     */
    private Double spending;
    /**
     * Здано
     */
    private Double transmitted;
    /**
     * Баланс
     */
    private Double balance;
    /**
     * Код пользователя
     */
    private Long userId;
    /**
     * Дата
     */
    private Date date;
    /**
     * Номер смены
     */
    private Long dutiesId;

    /**
     * Получение значения поля {@link Kassa#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link Kassa#currencyId}
     *
     * @return идентификатор
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Получение значения поля {@link Kassa#received}
     *
     * @return идентификатор
     */
    public Double getReceived() {
        return received;
    }

    /**
     * Получение значения поля {@link Kassa#coming}
     *
     * @return идентификатор
     */
    public Double getComing() {
        return coming;
    }

    /**
     * Получение значения поля {@link Kassa#spending}
     *
     * @return идентификатор
     */
    public Double getSpending() {
        return spending;
    }

    /**
     * Получение значения поля {@link Kassa#transmitted}
     *
     * @return идентификатор
     */
    public Double getTransmitted() {
        return transmitted;
    }

    /**
     * Получение значения поля {@link Kassa#balance}
     *
     * @return идентификатор
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Получение значения поля {@link Kassa#userId}
     *
     * @return идентификатор
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Получение значения поля {@link Kassa#date}
     *
     * @return идентификатор
     */
    public Date getDate() {
        return date;
    }

    /**
     * Получение значения поля {@link Kassa#dutiesId}
     *
     * @return идентификатор
     */
    public Long getDutiesId() {
        return dutiesId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Kassa{")
                .append("id=")
                .append(id)
                .append(", currencyId=")
                .append(currencyId)
                .append(", received=")
                .append(received)
                .append(", coming=")
                .append(coming)
                .append(", spending=")
                .append(spending)
                .append(", transmitted=")
                .append(transmitted)
                .append(", balance=")
                .append(balance)
                .append(", userId=")
                .append(userId)
                .append(", date=")
                .append(date)
                .append(", dutiesId=")
                .append(dutiesId)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование кассы.
     */
    public static class Builder {
        private Kassa newKassa;

        /**
         * Конструктор
         */
        public Builder() {
            newKassa = new Kassa();
        }

        /**
         * Определение идентификатора {@link Kassa#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Kassa.Builder addId(Long id) {
            newKassa.id = id;
            return this;
        }

        /**
         * Определение кода валюты {@link Kassa#currencyId}
         *
         * @param currencyId - дата и время
         * @return Builder
         */
        public Kassa.Builder addСurrencyId(Long currencyId) {
            newKassa.currencyId = currencyId;
            return this;
        }


        /**
         * Определение полученого по валюте {@link Kassa#received}
         *
         * @param received - получено
         * @return Builder
         */
        public Kassa.Builder addReceived(Double received) {
            newKassa.received = received;
            return this;
        }

        /**
         * Определение прихода по валюте {@link Kassa#coming}
         *
         * @param coming - приход
         * @return Builder
         */
        public Kassa.Builder addСoming(Double coming) {
            newKassa.coming = coming;
            return this;
        }

        /**
         * Определение определение рассхода по кассе {@link Kassa#spending}
         *
         * @param spending - расход
         * @return Builder
         */
        public Kassa.Builder addSpending(Double spending) {
            newKassa.spending = spending;
            return this;
        }

        /**
         * Определение сданого в кассе по валюте {@link Kassa#transmitted}
         *
         * @param transmitted - сдано по валюте
         * @return Builder
         */
        public Kassa.Builder addTransmitted(Double transmitted) {
            newKassa.transmitted = transmitted;
            return this;
        }

        /**
         * Определение баланса в кассе по валюте {@link Kassa#balance}
         *
         * @param balance - баланс
         * @return Builder
         */
        public Kassa.Builder addBalance(Double balance) {
            newKassa.balance = balance;
            return this;
        }

        /**
         * Определение кода пользователя {@link Kassa#userId}
         *
         * @param userId - код пользователя
         * @return Builder
         */
        public Kassa.Builder addUserId(Long userId) {
            newKassa.userId = userId;
            return this;
        }

        /**
         * Определение даты {@link Kassa#date}
         *
         * @param date - даты
         * @return Builder
         */
        public Kassa.Builder addData(Date date) {
            newKassa.date = date;
            return this;
        }

        /**
         * Определение код смены {@link Kassa#dutiesId}
         *
         * @param dutiesId - код смены
         * @return Builder
         */
        public Kassa.Builder addDutiesId(Long dutiesId) {
            newKassa.dutiesId = dutiesId;
            return this;
        }

        /**
         * Возвращает построенную смену
         *
         * @return смена
         */
        public Kassa build() {
            return newKassa;
        }
    }
}
