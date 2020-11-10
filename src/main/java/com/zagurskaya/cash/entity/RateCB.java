package com.zagurskaya.cash.entity;

import java.time.LocalDateTime;

/**
 * Rate CB with characteristics <b>id</b>, <b>coming</b>, <b>spending</b>, <b>localDateTime</b>, <b>sum</b> и <b>isBack</b>.
 */
public class RateCB {
    /**
     * Identifier
     */
    private Long id;
    /**
     * Coming
     */
    private Long coming;
    /**
     * Spending
     */
    private Long spending;
    /**
     * Date and Time
     */
    private LocalDateTime localDateTime;
    /**
     * Sum
     */
    private Double sum;
    /**
     * Shift direct/back rate CB
     */
    private boolean isBack;

    /**
     * Get field value {@link RateCB#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get field value {@link RateCB#coming}
     *
     * @return coming
     */
    public Long getComing() {
        return coming;
    }

    /**
     * Get field value {@link RateCB#spending}
     *
     * @return spending
     */
    public Long getSpending() {
        return spending;
    }

    /**
     * Get field value {@link RateCB#localDateTime}
     *
     * @return date and time
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Get field value {@link RateCB#sum}
     *
     * @return sum
     */
    public Double getSum() {
        return sum;
    }

    /**
     * Get field value {@link RateCB#isBack}
     *
     * @return direct/back rate CB
     */
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
                .append(", localDateTime=")
                .append(localDateTime)
                .append(", sum=")
                .append(sum)
                .append(", isBack=")
                .append(isBack)
                .append("}");
        return sb.toString();
    }

    /**
     * Rate CB construction.
     */
    public static class Builder {
        private RateCB newRateCB;

        /**
         * Constructor
         */
        public Builder() {
            newRateCB = new RateCB();
        }

        /**
         * Identifier definition {@link RateCB#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newRateCB.id = id;
            return this;
        }

        /**
         * Coming definition {@link RateCB#coming}
         *
         * @param coming - coming
         * @return Builder
         */
        public Builder addСoming(Long coming) {
            newRateCB.coming = coming;
            return this;
        }

        /**
         * Spending definition {@link RateCB#spending}
         *
         * @param spending - spending
         * @return Builder
         */
        public Builder addSpending(Long spending) {
            newRateCB.spending = spending;
            return this;
        }

        /**
         * Date and time definition {@link RateCB#localDateTime}
         *
         * @param timestamp - date and time
         * @return Builder
         */
        public Builder addLocalDateTime(LocalDateTime timestamp) {
            newRateCB.localDateTime = timestamp;
            return this;
        }

        /**
         * Sum definition {@link RateCB#sum}
         *
         * @param sum - sum
         * @return Builder
         */
        public Builder addSum(Double sum) {
            newRateCB.sum = sum;
            return this;
        }

        /**
         * Direct/back rate CB definition {@link RateCB#isBack}
         *
         * @param isBack - прямой/обратный курс
         * @return Builder
         */
        public Builder addIsBack(boolean isBack) {
            newRateCB.isBack = isBack;
            return this;
        }

        /**
         * Returns the constructed rate CB
         *
         * @return rate CB
         */
        public RateCB build() {
            return newRateCB;
        }
    }
}


