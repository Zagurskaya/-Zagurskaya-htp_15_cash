package com.zagurskaya.cash.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Rate NB with characteristics <b>id</b>, <b>currencyId</b>, <b>date</b> и <b>sum</b>.
 */
public class RateNB {
    /**
     * Identifier
     */
    private Long id;
    /**
     * Currency code
     */
    private Long currencyId;
    /**
     * Date
     */
    private LocalDate date;
    /**
     * Sum
     */
    private BigDecimal sum;

    /**
     * Get field value {@link RateNB#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get field value {@link RateNB#currencyId}
     *
     * @return currency code
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Get field value {@link RateNB#date}
     *
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get field value {@link RateNB#sum}
     *
     * @return sum
     */
    public BigDecimal getSum() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RateNB rateNB = (RateNB) o;

        if (id != null ? !id.equals(rateNB.id) : rateNB.id != null) return false;
        if (currencyId != null ? !currencyId.equals(rateNB.currencyId) : rateNB.currencyId != null) return false;
        if (date != null ? !date.equals(rateNB.date) : rateNB.date != null) return false;
        return sum != null ? sum.equals(rateNB.sum) : rateNB.sum == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currencyId != null ? currencyId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }

    /**
     * Rate NB construction.
     */
    public static class Builder {
        private RateNB newRateNB;

        /**
         * Constructor
         */
        public Builder() {
            newRateNB = new RateNB();
        }

        /**
         * Identifier definition {@link RateNB#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newRateNB.id = id;
            return this;
        }

        /**
         * Currency code definition {@link RateNB#currencyId}
         *
         * @param currencyId - currency code
         * @return Builder
         */
        public Builder addCurrencyId(Long currencyId) {
            newRateNB.currencyId = currencyId;
            return this;
        }

        /**
         * Date definition {@link RateNB#date}
         *
         * @param date - date
         * @return Builder
         */
        public Builder addDate(LocalDate date) {
            newRateNB.date = date;
            return this;
        }

        /**
         * Sum definition {@link RateNB#sum}
         *
         * @param sum - sum
         * @return Builder
         */
        public Builder addSum(BigDecimal sum) {
            newRateNB.sum = sum;
            return this;
        }

        /**
         * Returns the constructed rate NB
         *
         * @return rate NB
         */
        public RateNB build() {
            return newRateNB;
        }
    }
}
