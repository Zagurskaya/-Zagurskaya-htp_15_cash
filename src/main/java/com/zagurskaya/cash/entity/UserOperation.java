package com.zagurskaya.cash.entity;

import java.sql.Timestamp;

/**
 * Executed operation with characteristics <b>id</b>, <b>timestamp</b>, <b>rate</b>, <b>sum</b>, <b>currencyId</b>,
 * <b>userId</b>, <b>dutiesId</b>, <b>operationId</b>, <b>specification</b>, <b>checkingAccount</b> и <b>fullName</b>.
 */
public class UserOperation {
    /**
     * Identifier
     */
    private Long id;
    /**
     * Date and time executed operation
     */
    private Timestamp timestamp;
    /**
     * Rate
     */
    private Double rate;
    /**
     * Sum
     */
    private Double sum;
    /**
     * Currency code
     */
    private Long currencyId;
    /**
     * User code
     */
    private Long userId;
    /**
     * Duties code
     */
    private Long dutiesId;
    /**
     * Operation code
     */
    private Long operationId;
    /**
     * Specification
     */
    private String specification;
    /**
     * Checking account
     */
    private String checkingAccount;
    /**
     * Full name
     */
    private String fullName;

    /**
     * Get field value {@link UserOperation#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get field value {@link UserOperation#timestamp}
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
     * construction executed operation.
     */
    public static class Builder {
        private UserOperation newUserOperation;

        /**
         * Constructor
         */
        public Builder() {
            newUserOperation = new UserOperation();
        }

        /**
         * Identifier definition {@link UserOperation#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newUserOperation.id = id;
            return this;
        }

        /**
         * Date and time executed operation definition {@link UserOperation#timestamp}
         *
         * @param timestamp - date and time
         * @return Builder
         */
        public Builder addTimestamp(Timestamp timestamp) {
            newUserOperation.timestamp = timestamp;
            return this;
        }

        /**
         * Operation rate definition {@link UserOperation#rate}
         *
         * @param rate - operation rate
         * @return Builder
         */
        public Builder addRate(Double rate) {
            newUserOperation.rate = rate;
            return this;
        }

        /**
         * Operation sum definition {@link UserOperation#sum}
         *
         * @param sum - operation sum
         * @return Builder
         */
        public Builder addSum(Double sum) {
            newUserOperation.sum = sum;
            return this;
        }

        /**
         * Currency code definition {@link UserOperation#currencyId}
         *
         * @param currencyId - currency code
         * @return Builder
         */
        public Builder addCurrencyId(Long currencyId) {
            newUserOperation.currencyId = currencyId;
            return this;
        }

        /**
         * User code definition {@link UserOperation#userId}
         *
         * @param userId - user code
         * @return Builder
         */
        public Builder addUserId(Long userId) {
            newUserOperation.userId = userId;
            return this;
        }

        /**
         * Duties code definition {@link UserOperation#dutiesId}
         *
         * @param dutiesId - duties code
         * @return Builder
         */
        public Builder addDutiesId(Long dutiesId) {
            newUserOperation.dutiesId = dutiesId;
            return this;
        }

        /**
         * Operation code definition {@link UserOperation#operationId}
         *
         * @param operationId - operation code
         * @return Builder
         */
        public Builder addOperationId(Long operationId) {
            newUserOperation.operationId = operationId;
            return this;
        }

        /**
         * Specification definition {@link UserOperation#specification}
         *
         * @param specification - specification
         * @return Builder
         */
        public Builder addSpecification(String specification) {
            newUserOperation.specification = specification;
            return this;
        }

        /**
         * Checking account definition {@link UserOperation#checkingAccount}
         *
         * @param checkingAccount - Checking account
         * @return Builder
         */
        public Builder addCheckingAccount(String checkingAccount) {
            newUserOperation.checkingAccount = checkingAccount;
            return this;
        }

        /**
         * Full name definition {@link UserOperation#fullName}
         *
         * @param fullName - full name
         * @return Builder
         */
        public Builder addFullName(String fullName) {
            newUserOperation.fullName = fullName;
            return this;
        }

        /**
         * Returns the constructed executed operation
         *
         * @return смена
         */
        public UserOperation build() {
            return newUserOperation;
        }
    }
}
