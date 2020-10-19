package com.zagurskaya.cash.entity;

/**
 * Проведенная проводка со свойствами <b>id</b>, <b>userOperationId</b>, <b>sprEntryId</b>, <b>currencyId</b>, <b>accountDebit</b>,
 * <b>accountCredit</b>, <b>sum</b>, <b>isSpending</b> и <b>rate</b>.
 */
public class UserEntry {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Код проведенной операции
     */
    private Long userOperationId;
    /**
     * Код проводки
     */
    private Long sprEntryId;
    /**
     * Код валюты
     */
    private Long currencyId;
    /**
     * Счет дебета
     */
    private String accountDebit;
    /**
     * Счет кредита
     */
    private String accountCredit;
    /**
     * Сумма
     */
    private Double sum;
    /**
     * Признак прихода/ расхода
     */
    private boolean isSpending;
    /**
     * Курс
     */
    private Double rate;

    /**
     * Получение значения поля {@link UserEntry#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение код проведенной операции {@link UserEntry#userOperationId}
     *
     * @return код проведенной операции
     */
    public Long getUserOperationId() {
        return userOperationId;
    }

    /**
     * Получение код проводки {@link UserEntry#sprEntryId}
     *
     * @return код проводки
     */
    public Long getSprEntryId() {
        return sprEntryId;
    }

    /**
     * Получение валюты {@link UserEntry#currencyId}
     *
     * @return валюта
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Получение счет дебит {@link UserEntry#accountDebit}
     *
     * @return счет средит
     */
    public String getAccountDebit() {
        return accountDebit;
    }

    /**
     * Получение счет кредит {@link UserEntry#accountCredit}
     *
     * @return счет кредит
     */
    public String getAccountCredit() {
        return accountCredit;
    }

    /**
     * Получение сумма {@link UserEntry#sum}
     *
     * @return сумма
     */
    public Double getSum() {
        return sum;
    }

    /**
     * Получение признак прихода/расхода {@link UserEntry#isSpending}
     *
     * @return признак прихода расхода
     */
    public boolean getIsSpending() {
        return isSpending;
    }

    /**
     * Получение курс {@link UserEntry#rate}
     *
     * @return курс
     */
    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserEntry{")
                .append("id=")
                .append(id)
                .append(", userOperationId=")
                .append(userOperationId)
                .append(", sprEntryId=")
                .append(sprEntryId)
                .append(", currencyId=")
                .append(currencyId)
                .append(", accountDebit=")
                .append(accountDebit)
                .append(", accountCredit=")
                .append(accountCredit)
                .append(", sum=")
                .append(sum)
                .append(", isSpending=")
                .append(isSpending)
                .append(", rate=")
                .append(rate)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование проведенной проводки.
     */
    public static class Builder {
        private UserEntry newUserEntry;

        /**
         * Конструктор
         */
        public Builder() {
            newUserEntry = new UserEntry();
        }

        /**
         * Определение идентификатора {@link UserEntry#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newUserEntry.id = id;
            return this;
        }

        /**
         * Определение номера операции {@link UserEntry#userOperationId}
         *
         * @param userOperationId - номер операции
         * @return Builder
         */
        public Builder addUserOperationId(Long userOperationId) {
            newUserEntry.userOperationId = userOperationId;
            return this;
        }

        /**
         * Определение код пользователя {@link UserEntry#sprEntryId}
         *
         * @param sprEntryId -код пользователя
         * @return Builder
         */

        public Builder addSprEntryId(Long sprEntryId) {
            newUserEntry.sprEntryId = sprEntryId;
            return this;
        }

        /**
         * Определение код валюты {@link UserEntry#currencyId}
         *
         * @param currencyId -код валюты
         * @return Builder
         */
        public Builder addCurrencyId(Long currencyId) {
            newUserEntry.currencyId = currencyId;
            return this;
        }

        /**
         * Определение счет дебит {@link UserEntry#accountDebit}
         *
         * @param accountDebit - счет дебит
         * @return Builder
         */
        public Builder addAccountDebit(String accountDebit) {
            newUserEntry.accountDebit = accountDebit;
            return this;
        }

        /**
         * Определение счет кредит {@link UserEntry#accountCredit}
         *
         * @param accountCredit -  счет кредит
         * @return Builder
         */
        public Builder addAccountCredit(String accountCredit) {
            newUserEntry.accountCredit = accountCredit;
            return this;
        }

        /**
         * Определение сумму операции {@link UserEntry#sum}
         *
         * @param sum - сумму операции
         * @return Builder
         */
        public Builder addSum(Double sum) {
            newUserEntry.sum = sum;
            return this;
        }

        /**
         * Определение признак прихода/расхода {@link UserEntry#isSpending}
         *
         * @param isSpending -спецификация
         * @return Builder
         */
        public Builder addIsSpending(boolean isSpending) {
            newUserEntry.isSpending = isSpending;
            return this;
        }

        /**
         * Определение курс операции {@link UserEntry#rate}
         *
         * @param rate - курс операции
         * @return Builder
         */
        public Builder addRate(Double rate) {
            newUserEntry.rate = rate;
            return this;
        }

        /**
         * Возвращает построенную проведенную проводку
         *
         * @return смена
         */
        public UserEntry build() {
            return newUserEntry;
        }
    }
}
