package com.zagurskaya.cash.entity;

import java.time.LocalDateTime;

/**
 * Duties with characteristics <b>id</b>, <b>userId</b>, <b>localDateTime</b>, <b>number</b> и <b>isClose</b>.
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
    private LocalDateTime localDateTime;
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
     * Get field value {@link Duties#localDateTime}
     *
     * @return date and time
     */

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
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
                .append(", localDateTime=")
                .append(localDateTime)
                .append(", number=")
                .append(number)
                .append(", isClose=")
                .append(isClose)
                .append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Duties duties = (Duties) o;

        if (isClose != duties.isClose) return false;
        if (id != null ? !id.equals(duties.id) : duties.id != null) return false;
        if (userId != null ? !userId.equals(duties.userId) : duties.userId != null) return false;
        if (localDateTime != null ? !localDateTime.equals(duties.localDateTime) : duties.localDateTime != null)
            return false;
        return number != null ? number.equals(duties.number) : duties.number == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (isClose ? 1 : 0);
        return result;
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
         * Date and time of open duties definition {@link Duties#localDateTime}
         *
         * @param localDateTime - date and time
         * @return Builder
         */
        public Builder addLocalDateTime(LocalDateTime localDateTime) {
            newDuties.localDateTime = localDateTime;
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
