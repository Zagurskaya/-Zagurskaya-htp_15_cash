package com.zagurskaya.cash.entity;

/**
 * Проводка со свойствами <b>id</b>, <b>name</b>, <b>currencyId</b>, <b>sprOperationId</b>, <b>accountDebit</b>,
 * <b>accountCredit</b>, <b>isSpending</b> и <b>rate</b>.
 */
public class SprEntry {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Наименование
     */
    private String name;
    /**
     * Код валюты
     */
    private Long currencyId;
    /**
     * Код операции
     */
    private Long sprOperationId;
    /**
     * Счет по дебету
     */
    private String accountDebit;
    /**
     * Счет по кредету
     */
    private String accountCredit;
    /**
     * Признак приход/ расход
     */
    private boolean isSpending;
    /**
     * Курс
     */
    private Double rate;

    /**
     * Получение значения поля {@link SprEntry#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link SprEntry#name}
     *
     * @return наименование
     */
    public String getName() {
        return name;
    }

    /**
     * Получение значения поля {@link SprEntry#currencyId}
     *
     * @return код валюты
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Получение значения поля {@link SprEntry#sprOperationId}
     *
     * @return код операции
     */
    public Long getSprOperationId() {
        return sprOperationId;
    }

    /**
     * Получение значения поля {@link SprEntry#accountDebit}
     *
     * @return счет по дебету
     */
    public String getAccountDebit() {
        return accountDebit;
    }

    /**
     * Получение значения поля {@link SprEntry#accountCredit}
     *
     * @return счет по кредету
     */
    public String getAccountCredit() {
        return accountCredit;
    }

    /**
     * Получение значения поля {@link SprEntry#isSpending}
     *
     * @return признак прихода/расхода
     */
    public boolean getIsSpending() {
        return isSpending;
    }

    /**
     * Получение значения поля {@link SprEntry#rate}
     *
     * @return курс
     */
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SprEntry{")
                .append("id=")
                .append(id)
                .append(", name=")
                .append(name)
                .append(", currencyId=")
                .append(currencyId)
                .append(", sprOperationId=")
                .append(sprOperationId)
                .append(", accountDebit=")
                .append(accountDebit)
                .append(", accountCredit=")
                .append(accountCredit)
                .append(", isSpending=")
                .append(isSpending)
                .append(", rate=")
                .append(rate)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование проводки.
     */
    public static class Builder {
        private SprEntry newSprEntry;

        /**
         * Конструктор
         */
        public Builder() {
            newSprEntry = new SprEntry();
        }

        /**
         * Определение идентификатора {@link SprEntry#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newSprEntry.id = id;
            return this;
        }

        /**
         * Определение наименование {@link SprEntry#name}
         *
         * @param name - наименование
         * @return Builder
         */
        public Builder addName(String name) {
            newSprEntry.name = name;
            return this;
        }

        /**
         * Определение кода валюты {@link SprEntry#currencyId}
         *
         * @param currencyId - код валюты
         * @return Builder
         */
        public Builder addCurrencyId(Long currencyId) {
            newSprEntry.currencyId = currencyId;
            return this;
        }

        /**
         * Определение кода операции {@link SprEntry#sprOperationId}
         *
         * @param sprOperationId - код операции
         * @return Builder
         */
        public Builder addSprOperationId(Long sprOperationId) {
            newSprEntry.sprOperationId = sprOperationId;
            return this;
        }

        /**
         * Определение счета по дебету проводки{@link SprEntry#accountDebit}
         *
         * @param accountDebit - счет по дебету проводки
         * @return Builder
         */
        public Builder addAccountDebit(String accountDebit) {
            newSprEntry.accountDebit = accountDebit;
            return this;
        }

        /**
         * Определение счета по кредиту проводки {@link SprEntry#accountCredit}
         *
         * @param accountCredit - счет по кредиту проводки
         * @return Builder
         */
        public Builder addAccountCredit(String accountCredit) {
            newSprEntry.accountCredit = accountCredit;
            return this;
        }

        /**
         * Определение признака прихода/расхода {@link SprEntry#isSpending}
         *
         * @param isSpending -  признака прихода/расхода
         * @return Builder
         */
        public Builder addIsSpending(boolean isSpending) {
            newSprEntry.isSpending = isSpending;
            return this;
        }

        /**
         * Определение курса операции {@link SprEntry#rate}
         *
         * @param rate -  курса операции
         * @return Builder
         */
        public Builder addRate(Double rate) {
            newSprEntry.rate = rate;
            return this;
        }

        /**
         * Возвращает построенную проводку
         *
         * @return проводка
         */
        public SprEntry build() {
            return newSprEntry;
        }
    }
}
