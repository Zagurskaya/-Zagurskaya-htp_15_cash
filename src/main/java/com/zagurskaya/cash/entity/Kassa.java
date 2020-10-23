package com.zagurskaya.cash.entity;

import java.sql.Date;

/**
 * Kassa with characteristics  <b>id</b>, <b>currencyId</b>, <b>received</b>, <b>coming</b> , <b>spending</b> , <b>transmitted</b> ,
 * <b>balance</b> , <b>userId</b> , <b>date</b>  и <b>dutiesId</b>.
 */
public class Kassa {
    /**
     * Identifier
     */
    private Long id;
    /**
     * Currency code
     */
    private Long currencyId;
    /**
     * Received
     */
    private Double received;
    /**
     * Coming
     */
    private Double coming;
    /**
     * Spending
     */
    private Double spending;
    /**
     * Transmitted
     */
    private Double transmitted;
    /**
     * Balance
     */
    private Double balance;
    /**
     * User code
     */
    private Long userId;
    /**
     * Date
     */
    private Date date;
    /**
     * Number duties
     */
    private Long dutiesId;

    /**
     * Get field value {@link Kassa#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get field value {@link Kassa#currencyId}
     *
     * @return currency code
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Get field value {@link Kassa#received}
     *
     * @return идентификатор
     */
    public Double getReceived() {
        return received;
    }

    /**
     * Get field value {@link Kassa#coming}
     *
     * @return идентификатор
     */
    public Double getComing() {
        return coming;
    }

    /**
     * Get field value {@link Kassa#spending}
     *
     * @return идентификатор
     */
    public Double getSpending() {
        return spending;
    }

    /**
     * Get field value {@link Kassa#transmitted}
     *
     * @return идентификатор
     */
    public Double getTransmitted() {
        return transmitted;
    }

    /**
     * Get field value {@link Kassa#balance}
     *
     * @return идентификатор
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Get field value {@link Kassa#userId}
     *
     * @return идентификатор
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Get field value {@link Kassa#date}
     *
     * @return идентификатор
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get field value {@link Kassa#dutiesId}
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
     * Kassa construction.
     */
    public static class Builder {
        private Kassa newKassa;

        /**
         * Constructor
         */
        public Builder() {
            newKassa = new Kassa();
        }

        /**
         * Identifier definition {@link Kassa#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Kassa.Builder addId(Long id) {
            newKassa.id = id;
            return this;
        }

        /**
         * Currency code definition {@link Kassa#currencyId}
         *
         * @param currencyId - Currency code
         * @return Builder
         */
        public Kassa.Builder addCurrencyId(Long currencyId) {
            newKassa.currencyId = currencyId;
            return this;
        }


        /**
         * Received definition {@link Kassa#received}
         *
         * @param received - received
         * @return Builder
         */
        public Kassa.Builder addReceived(Double received) {
            newKassa.received = received;
            return this;
        }

        /**
         * Coming definition {@link Kassa#coming}
         *
         * @param coming - coming
         * @return Builder
         */
        public Kassa.Builder addComing(Double coming) {
            newKassa.coming = coming;
            return this;
        }

        /**
         * Spending definition {@link Kassa#spending}
         *
         * @param spending - spending
         * @return Builder
         */
        public Kassa.Builder addSpending(Double spending) {
            newKassa.spending = spending;
            return this;
        }

        /**
         * Transmitted definition {@link Kassa#transmitted}
         *
         * @param transmitted - transmitted
         * @return Builder
         */
        public Kassa.Builder addTransmitted(Double transmitted) {
            newKassa.transmitted = transmitted;
            return this;
        }

        /**
         * Balance definition {@link Kassa#balance}
         *
         * @param balance - balance
         * @return Builder
         */
        public Kassa.Builder addBalance(Double balance) {
            newKassa.balance = balance;
            return this;
        }

        /**
         * User code definition {@link Kassa#userId}
         *
         * @param userId - код пользователя
         * @return Builder
         */
        public Kassa.Builder addUserId(Long userId) {
            newKassa.userId = userId;
            return this;
        }

        /**
         * Date definition {@link Kassa#date}
         *
         * @param date - даты
         * @return Builder
         */
        public Kassa.Builder addData(Date date) {
            newKassa.date = date;
            return this;
        }

        /**
         * Number duties definition {@link Kassa#dutiesId}
         *
         * @param dutiesId - number duties
         * @return Builder
         */
        public Kassa.Builder addDutiesId(Long dutiesId) {
            newKassa.dutiesId = dutiesId;
            return this;
        }

        /**
         * Returns the constructed duties
         *
         * @return duties
         */
        public Kassa build() {
            return newKassa;
        }
    }
}
