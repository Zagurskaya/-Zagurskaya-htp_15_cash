package com.zagurskaya.cash.entity;

import java.sql.Timestamp;

/**
 * Проведенная операция со свойствами <b>id</b>, <b>timestamp</b>, <b>rate</b>, <b>sum</b>, <b>currencyId</b>,
 * <b>userId</b>, <b>dutiesId</b>, <b>operationId</b>, <b>specification</b>, <b>checkingAccount</b> и <b>fullName</b>.
 */
public class UserOperation {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Дата и время проведения операции
     */
    private Timestamp timestamp;
    /**
     * Курс
     */
    private Double rate;
    /**
     * Сумма
     */
    private Double sum;
    /**
     * Код валюты
     */
    private Long currencyId;
    /**
     * Код пользователя
     */
    private Long userId;
    /**
     * Код смены
     */
    private Long dutiesId;
    /**
     * Код операции
     */
    private Long operationId;
    /**
     * Спецификация
     */
    private String specification;
    /**
     * Расчетный счет
     */
    private String checkingAccount;
    /**
     * ФИО
     */
    private String fullName;

    /**
     * Получение значения поля {@link UserOperation#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link UserOperation#timestamp}
     *
     * @return дата и времени смены
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Получение курс {@link UserOperation#rate}
     *
     * @return курс
     */
    public Double getRate() {
        return rate;
    }

    /**
     * Получение сумма {@link UserOperation#sum}
     *
     * @return сумма
     */
    public Double getSum() {
        return sum;
    }

    /**
     * Получение валюты {@link UserOperation#currencyId}
     *
     * @return валюта
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Получение пользователь {@link UserOperation#userId}
     *
     * @return пользователь
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Получение смены {@link UserOperation#dutiesId}
     *
     * @return смена
     */
    public Long getDutiesId() {
        return dutiesId;
    }

    /**
     * Получение операция {@link UserOperation#operationId}
     *
     * @return операция
     */
    public Long getOperationId() {
        return operationId;
    }

    /**
     * Получение спецификации {@link UserOperation#specification}
     *
     * @return спецификации
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * Получение рассчетного счета {@link UserOperation#checkingAccount}
     *
     * @return расчетный счет
     */
    public String getCheckingAccount() {
        return checkingAccount;
    }

    /**
     * Получение ФИО {@link UserOperation#fullName}
     *
     * @return ФИО
     */
    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserOperation{")
                .append("id=")
                .append(id)
                .append(", timestamp=")
                .append(timestamp)
                .append(", rate=")
                .append(rate)
                .append(", sum=")
                .append(sum)
                .append(", currencyId=")
                .append(currencyId)
                .append(", userId=")
                .append(userId)
                .append(", dutiesId=")
                .append(dutiesId)
                .append(", operationId=")
                .append(operationId)
                .append(", specification=")
                .append(specification)
                .append(", checkingAccount=")
                .append(checkingAccount)
                .append(", fullName=")
                .append(fullName)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование проведенной операции.
     */
    public static class Builder {
        private UserOperation newUserOperation;

        /**
         * Конструктор
         */
        public Builder() {
            newUserOperation = new UserOperation();
        }

        /**
         * Определение идентификатора {@link UserOperation#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newUserOperation.id = id;
            return this;
        }

        /**
         * Определение даты и времени смены {@link UserOperation#timestamp}
         *
         * @param timestamp - дата и время
         * @return Builder
         */
        public Builder addTimestamp(Timestamp timestamp) {
            newUserOperation.timestamp = timestamp;
            return this;
        }

        /**
         * Определение курс операции {@link UserOperation#rate}
         *
         * @param rate - курс операции
         * @return Builder
         */
        public Builder addRate(Double rate) {
            newUserOperation.rate = rate;
            return this;
        }

        /**
         * Определение сумму операции {@link UserOperation#sum}
         *
         * @param sum - сумму операции
         * @return Builder
         */
        public Builder addSum(Double sum) {
            newUserOperation.sum = sum;
            return this;
        }

        /**
         * Определение код валюты {@link UserOperation#currencyId}
         *
         * @param currencyId -код валюты
         * @return Builder
         */
        public Builder addCurrencyId(Long currencyId) {
            newUserOperation.currencyId = currencyId;
            return this;
        }

        /**
         * Определение код пользователя {@link UserOperation#userId}
         *
         * @param userId -код пользователя
         * @return Builder
         */
        public Builder addUserId(Long userId) {
            newUserOperation.userId = userId;
            return this;
        }

        /**
         * Определение код смены {@link UserOperation#dutiesId}
         *
         * @param dutiesId -код смены
         * @return Builder
         */
        public Builder addDutiesId(Long dutiesId) {
            newUserOperation.dutiesId = dutiesId;
            return this;
        }

        /**
         * Определение код операции {@link UserOperation#operationId}
         *
         * @param operationId -код операции
         * @return Builder
         */
        public Builder addOperationId(Long operationId) {
            newUserOperation.operationId = operationId;
            return this;
        }

        /**
         * Определение спецификации {@link UserOperation#specification}
         *
         * @param specification -спецификация
         * @return Builder
         */
        public Builder addSpecification(String specification) {
            newUserOperation.specification = specification;
            return this;
        }

        /**
         * Определение расчетного счета {@link UserOperation#checkingAccount}
         *
         * @param checkingAccount - расчетный счет
         * @return Builder
         */
        public Builder addCheckingAccount(String checkingAccount) {
            newUserOperation.checkingAccount = checkingAccount;
            return this;
        }

        /**
         * Определение ФИО {@link UserOperation#fullName}
         *
         * @param fullName - ФИО
         * @return Builder
         */
        public Builder addFullName(String fullName) {
            newUserOperation.fullName = fullName;
            return this;
        }

        /**
         * Возвращает построенную проведенную операция
         *
         * @return смена
         */
        public UserOperation build() {
            return newUserOperation;
        }
    }
}
