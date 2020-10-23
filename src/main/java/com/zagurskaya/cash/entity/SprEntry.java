package com.zagurskaya.cash.entity;

/**
 * Entry with characteristics <b>id</b>, <b>name</b>, <b>currencyId</b>, <b>sprOperationId</b>, <b>accountDebit</b>,
 * <b>accountCredit</b>, <b>isSpending</b> и <b>rate</b>.
 */
public class SprEntry {
    /**
     * Identifier
     */
    private Long id;
    /**
     * Name
     */
    private String name;
    /**
     * Currency code
     */
    private Long currencyId;
    /**
     * Operation code
     */
    private Long sprOperationId;
    /**
     * Debit account
     */
    private String accountDebit;
    /**
     * Credit account
     */
    private String accountCredit;
    /**
     * Shift coming/spending
     */
    private boolean isSpending;
    /**
     * Rate
     */
    private Double rate;

    /**
     * Get field value {@link SprEntry#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get field value {@link SprEntry#name}
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get field value {@link SprEntry#currencyId}
     *
     * @return currency code
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Get field value {@link SprEntry#sprOperationId}
     *
     * @return operation code
     */
    public Long getSprOperationId() {
        return sprOperationId;
    }

    /**
     * Get field value {@link SprEntry#accountDebit}
     *
     * @return debit account
     */
    public String getAccountDebit() {
        return accountDebit;
    }

    /**
     * Get field value {@link SprEntry#accountCredit}
     *
     * @return credit account
     */
    public String getAccountCredit() {
        return accountCredit;
    }

    /**
     * Get field value {@link SprEntry#isSpending}
     *
     * @return shift coming/spending
     */
    public boolean getIsSpending() {
        return isSpending;
    }

    /**
     * Get field value {@link SprEntry#rate}
     *
     * @return rate
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
     * SprEntry construction.
     */
    public static class Builder {
        private SprEntry newSprEntry;

        /**
         * Constructor
         */
        public Builder() {
            newSprEntry = new SprEntry();
        }

        /**
         * Identifier definition {@link SprEntry#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newSprEntry.id = id;
            return this;
        }

        /**
         * Name definition {@link SprEntry#name}
         *
         * @param name - name
         * @return Builder
         */
        public Builder addName(String name) {
            newSprEntry.name = name;
            return this;
        }

        /**
         * Currency code definition {@link SprEntry#currencyId}
         *
         * @param currencyId - currency code
         * @return Builder
         */
        public Builder addCurrencyId(Long currencyId) {
            newSprEntry.currencyId = currencyId;
            return this;
        }

        /**
         * Operation code definition {@link SprEntry#sprOperationId}
         *
         * @param sprOperationId - operation code
         * @return Builder
         */
        public Builder addSprOperationId(Long sprOperationId) {
            newSprEntry.sprOperationId = sprOperationId;
            return this;
        }

        /**
         * Debit account definition{@link SprEntry#accountDebit}
         *
         * @param accountDebit - debit account
         * @return Builder
         */
        public Builder addAccountDebit(String accountDebit) {
            newSprEntry.accountDebit = accountDebit;
            return this;
        }

        /**
         * Credit account definition {@link SprEntry#accountCredit}
         *
         * @param accountCredit - credit account
         * @return Builder
         */
        public Builder addAccountCredit(String accountCredit) {
            newSprEntry.accountCredit = accountCredit;
            return this;
        }

        /**
         * Shift coming/spending definition {@link SprEntry#isSpending}
         *
         * @param isSpending -  shift coming/spending
         * @return Builder
         */
        public Builder addIsSpending(boolean isSpending) {
            newSprEntry.isSpending = isSpending;
            return this;
        }

        /**
         * Operation rate definition {@link SprEntry#rate}
         *
         * @param rate -  operation rate
         * @return Builder
         */
        public Builder addRate(Double rate) {
            newSprEntry.rate = rate;
            return this;
        }

        /**
         * Returns the constructed SprOperation
         *
         * @return entry
         */
        public SprEntry build() {
            return newSprEntry;
        }
    }
}
