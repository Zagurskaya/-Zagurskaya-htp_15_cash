package com.zagurskaya.cash.entity;

import java.sql.Timestamp;

/**
 * Смена со свойствами <b>id</b>, <b>userId</b>, <b>timestamp</b>, <b>number</b> и <b>isClose</b>.
 */
public class Duties {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Код пользователя
     */
    private Long userId;
    /**
     * Дата и время открытия смены
     */
    private Timestamp timestamp;
    /**
     * Номер смены
     */
    private Integer number;
    /**
     * Признак открытия/закрытия смены
     */
    private boolean isClose;

    /**
     * Получение значения поля {@link Duties#id}
     *
     * @return идентификатор
     */
    public long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link Duties#userId}
     *
     * @return кода пользователя
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Получение значения поля {@link Duties#timestamp}
     *
     * @return дата и времени смены
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Получение значения поля {@link Duties#number}
     *
     * @return номер смены
     */
    public int getNumber() {
        return number;
    }

    /**
     * Получение значения поля {@link Duties#isClose}
     *
     * @return признак открытия/закрытия смены
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
     * Конструирование смены.
     */
    public static class Builder {
        private Duties newDuties;

        /**
         * Конструктор
         */
        public Builder() {
            newDuties = new Duties();
        }

        /**
         * Определение идентификатора {@link Duties#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newDuties.id = id;
            return this;
        }

        /**
         * Определение кода пользователя {@link Duties#userId}
         *
         * @param userId - код пользователя
         * @return Builder
         */
        public Builder addUserId(Long userId) {
            newDuties.userId = userId;
            return this;
        }

        /**
         * Определение даты и времени смены {@link Duties#timestamp}
         *
         * @param timestamp - дата и время
         * @return Builder
         */
        public Builder addTimestamp(Timestamp timestamp) {
            newDuties.timestamp = timestamp;
            return this;
        }

        /**
         * Определение номер смены {@link Duties#number}
         *
         * @param number - номер смены
         * @return Builder
         */
        public Builder addNumber(Integer number) {
            newDuties.number = number;
            return this;
        }

        /**
         * Определение признака открытия/закрытия смены {@link Duties#isClose}
         *
         * @param isClose - признак открытия/закрытия смены
         * @return Builder
         */
        public Builder addIsClose(Boolean isClose) {
            newDuties.isClose = isClose;
            return this;
        }

        /**
         * Возвращает построенную смену
         *
         * @return смена
         */
        public Duties build() {
            return newDuties;
        }
    }
}
