package com.zagurskaya.cash.entity;

import java.sql.Timestamp;

public class RateCB {
    private Long id;
    private Long coming;
    private Long spending;
    private Timestamp timestamp;
    private Double sum;
    private boolean isBack;

    public Long getId() {
        return id;
    }

    public Long getComing() {
        return coming;
    }

    public Long getSpending() {
        return spending;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Double getSum() {
        return sum;
    }

    public boolean getIsBack() {
        return isBack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RateCB{")
                .append("id=")
                .append(id)
                .append(", coming=")
                .append(coming)
                .append(", spending=")
                .append(spending)
                .append(", timestamp=")
                .append(timestamp)
                .append(", sum=")
                .append(sum)
                .append(", isBack=")
                .append(isBack)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование курса КБ.
     */
    public static class Builder {
        private RateCB newRateCB;

        /**
         * Конструктор
         */
        public Builder() {
            newRateCB = new RateCB();
        }

        /**
         * Определение идентификатора {@link RateCB#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newRateCB.id = id;
            return this;
        }

        /**
         * Определение кода валюты прихода {@link RateCB#coming}
         *
         * @param coming - приход
         * @return Builder
         */
        public Builder addСoming(Long coming) {
            newRateCB.coming = coming;
            return this;
        }

        /**
         * Определение кода валюты расхода {@link RateCB#spending}
         *
         * @param spending - расход
         * @return Builder
         */
        public Builder addSpending(Long spending) {
            newRateCB.spending = spending;
            return this;
        }

        /**
         * Определение время {@link RateCB#timestamp}
         *
         * @param timestamp - время
         * @return Builder
         */
        public Builder addTimestamp(Timestamp timestamp) {
            newRateCB.timestamp = timestamp;
            return this;
        }

        /**
         * Определение суммы {@link RateCB#sum}
         *
         * @param sum - суммы
         * @return Builder
         */
        public Builder addSum(Double sum) {
            newRateCB.sum = sum;
            return this;
        }

        /**
         * Определение прямого/обратного курса {@link RateCB#isBack}
         *
         * @param isBack - прямой/обратный курс
         * @return Builder
         */
        public Builder addIsBack(boolean isBack) {
            newRateCB.isBack = isBack;
            return this;
        }

        /**
         * Возвращает построенный курс
         *
         * @return пользователь
         */
        public RateCB build() {
            return newRateCB;
        }
    }
}


