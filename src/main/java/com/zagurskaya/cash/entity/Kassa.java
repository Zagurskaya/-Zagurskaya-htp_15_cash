package com.zagurskaya.cash.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private BigDecimal received;
    /**
     * Coming
     */
    private BigDecimal coming;
    /**
     * Spending
     */
    private BigDecimal spending;
    /**
     * Transmitted
     */
    private BigDecimal transmitted;
    /**
     * Balance
     */
    private BigDecimal balance;
    /**
     * User code
     */
    private Long userId;
    /**
     * Date
     */
    private LocalDate date;
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
    public BigDecimal getReceived() {
        return received;
    }

    /**
     * Get field value {@link Kassa#coming}
     *
     * @return идентификатор
     */
    public BigDecimal getComing() {
        return coming;
    }

    /**
     * Get field value {@link Kassa#spending}
     *
     * @return идентификатор
     */
    public BigDecimal getSpending() {
        return spending;
    }

    /**
     * Get field value {@link Kassa#transmitted}
     *
     * @return идентификатор
     */
    public BigDecimal getTransmitted() {
        return transmitted;
    }

    /**
     * Get field value {@link Kassa#balance}
     *
     * @return идентификатор
     */
    public BigDecimal getBalance() {
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

    public LocalDate getDate() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kassa kassa = (Kassa) o;

        if (id != null ? !id.equals(kassa.id) : kassa.id != null) return false;
        if (currencyId != null ? !currencyId.equals(kassa.currencyId) : kassa.currencyId != null) return false;
        if (received != null ? !received.equals(kassa.received) : kassa.received != null) return false;
        if (coming != null ? !coming.equals(kassa.coming) : kassa.coming != null) return false;
        if (spending != null ? !spending.equals(kassa.spending) : kassa.spending != null) return false;
        if (transmitted != null ? !transmitted.equals(kassa.transmitted) : kassa.transmitted != null) return false;
        if (balance != null ? !balance.equals(kassa.balance) : kassa.balance != null) return false;
        if (userId != null ? !userId.equals(kassa.userId) : kassa.userId != null) return false;
        if (date != null ? !date.equals(kassa.date) : kassa.date != null) return false;
        return dutiesId != null ? dutiesId.equals(kassa.dutiesId) : kassa.dutiesId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currencyId != null ? currencyId.hashCode() : 0);
        result = 31 * result + (received != null ? received.hashCode() : 0);
        result = 31 * result + (coming != null ? coming.hashCode() : 0);
        result = 31 * result + (spending != null ? spending.hashCode() : 0);
        result = 31 * result + (transmitted != null ? transmitted.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (dutiesId != null ? dutiesId.hashCode() : 0);
        return result;
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
        public Kassa.Builder addReceived(BigDecimal received) {
            newKassa.received = received;
            return this;
        }

        /**
         * Coming definition {@link Kassa#coming}
         *
         * @param coming - coming
         * @return Builder
         */
        public Kassa.Builder addComing(BigDecimal coming) {
            newKassa.coming = coming;
            return this;
        }

        /**
         * Spending definition {@link Kassa#spending}
         *
         * @param spending - spending
         * @return Builder
         */
        public Kassa.Builder addSpending(BigDecimal spending) {
            newKassa.spending = spending;
            return this;
        }

        /**
         * Transmitted definition {@link Kassa#transmitted}
         *
         * @param transmitted - transmitted
         * @return Builder
         */
        public Kassa.Builder addTransmitted(BigDecimal transmitted) {
            newKassa.transmitted = transmitted;
            return this;
        }

        /**
         * Balance definition {@link Kassa#balance}
         *
         * @param balance - balance
         * @return Builder
         */
        public Kassa.Builder addBalance(BigDecimal balance) {
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

        public Kassa.Builder addData(LocalDate date) {
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
