package com.zagurskaya.cash.entity;

import java.sql.Timestamp;

/**
 * Duties with characteristics <b>id</b>, <b>userId</b>, <b>timestamp</b>, <b>number</b> и <b>isClose</b>.
 */
public class Duties {
    /**
     * Identifier
     */
    private Long id;
    /**
     * User code
     */
    private Long userId;
    /**
     * Date and time of open duties
     */
    private Timestamp timestamp;
    /**
     * Number duties
     */
    private Integer number;
    /**
     * Shift open/ close duties
     */
    private boolean isClose;

    /**
     * Get field value {@link Duties#id}
     *
     * @return identifier
     */
    public long getId() {
        return id;
    }

    /**
     * Get field value {@link Duties#userId}
     *
     * @return user code
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Get field value {@link Duties#timestamp}
     *
     * @return date and time
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Get field value {@link Duties#number}
     *
     * @return number duties
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get field value {@link Duties#isClose}
     *
     * @return shift open/ close duties
     */
    public boolean getIsClose() {
        return isClose;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Duties{")
                .append("id=")
                .append(id)
                .append(", userId=")
                .append(userId)
                .append(", timestamp=")
                .append(timestamp)
                .append(", number=")
                .append(number)
                .append(", isClose=")
                .append(isClose)
                .append("}");
        return sb.toString();
    }

    /**
     * Duties construction.
     */
    public static class Builder {
        private Duties newDuties;

        /**
         * Constructor
         */
        public Builder() {
            newDuties = new Duties();
        }

        /**
         * Identifier definition {@link Duties#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newDuties.id = id;
            return this;
        }

        /**
         * User code definition {@link Duties#userId}
         *
         * @param userId - user code
         * @return Builder
         */
        public Builder addUserId(Long userId) {
            newDuties.userId = userId;
            return this;
        }

        /**
         * Date and time of open duties definition {@link Duties#timestamp}
         *
         * @param timestamp - date and time
         * @return Builder
         */
        public Builder addTimestamp(Timestamp timestamp) {
            newDuties.timestamp = timestamp;
            return this;
        }

        /**
         * Number duties definition {@link Duties#number}
         *
         * @param number - number duties
         * @return Builder
         */
        public Builder addNumber(Integer number) {
            newDuties.number = number;
            return this;
        }

        /**
         * Shift open/close duties definition {@link Duties#isClose}
         *
         * @param isClose - shift open/close duties
         * @return Builder
         */
        public Builder addIsClose(Boolean isClose) {
            newDuties.isClose = isClose;
            return this;
        }

        /**
         * Returns the constructed duties
         *
         * @return duties
         */
        public Duties build() {
            return newDuties;
        }
    }
}
