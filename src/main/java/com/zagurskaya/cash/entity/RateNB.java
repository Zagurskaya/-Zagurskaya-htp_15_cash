package com.zagurskaya.cash.entity;

import java.sql.Date;

/**
 * Пользователь со свойствами <b>id</b>, <b>currencyId</b>, <b>date</b> и <b>sum</b>.
 */
public class RateNB {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Код валюты
     */
    private Long currencyId;
    /**
     * Дата
     */
    private Date date;
    /**
     * Сумма
     */
    private Double sum;

    /**
     * Получение значения поля {@link RateNB#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link RateNB#currencyId}
     *
     * @return код валюты
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Получение значения поля {@link RateNB#date}
     *
     * @return дата
     */
    public Date getDate() {
        return date;
    }

    /**
     * Получение значения поля {@link RateNB#sum}
     *
     * @return сумма
     */
    public Double getSum() {
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RateNB{{")
                .append("id=")
                .append(id)
                .append(", currencyId=")
                .append(currencyId)
                .append(", date=")
                .append(date)
                .append(", sum=")
                .append(sum)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование курса НБ.
     */
    public static class Builder {
        private RateNB newRateNB;

        /**
         * Конструктор
         */
        public Builder() {
            newRateNB = new RateNB();
        }

        /**
         * Определение идентификатора {@link RateNB#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newRateNB.id = id;
            return this;
        }

        /**
         * Определение кода валюты {@link RateNB#currencyId}
         *
         * @param currencyId - код валюты
         * @return Builder
         */
        public Builder addСurrencyId(Long currencyId) {
            newRateNB.currencyId = currencyId;
            return this;
        }

        /**
         * Определение даты {@link RateNB#date}
         *
         * @param date - дата
         * @return Builder
         */
        public Builder addDate(Date date) {
            newRateNB.date = date;
            return this;
        }

        /**
         * Определение суммы {@link RateNB#sum}
         *
         * @param sum - суммы
         * @return Builder
         */
        public Builder addSum(Double sum) {
            newRateNB.sum = sum;
            return this;
        }

        /**
         * Возвращает построенный курс НБ
         *
         * @return пользователь
         */
        public RateNB build() {
            return newRateNB;
        }
    }
}
