package com.zagurskaya.cash.entity;

import java.math.BigDecimal;

/**
 * Executed entry with characteristics <b>id</b>, <b>userOperationId</b>, <b>sprEntryId</b>, <b>currencyId</b>, <b>accountDebit</b>,
 * <b>accountCredit</b>, <b>sum</b>, <b>isSpending</b> и <b>rate</b>.
 */
public class UserEntry {
    /**
     * Identifier
     */
    private Long id;
    /**
     * Executed operation code
     */
    private Long userOperationId;
    /**
     * Entry code
     */
    private Long sprEntryId;
    /**
     * Currency code
     */
    private Long currencyId;
    /**
     * Debit account
     */
    private String accountDebit;
    /**
     * Credit account
     */
    private String accountCredit;
    /**
     * Sum
     */
    private BigDecimal sum;
    /**
     * Shift coming/spending
     */
    private boolean isSpending;
    /**
     * Rate
     */
    private BigDecimal rate;

    /**
     * Get field value {@link UserEntry#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get executed operation code {@link UserEntry#userOperationId}
     *
     * @return executed operation code
     */
    public Long getUserOperationId() {
        return userOperationId;
    }

    /**
     * Get entry code {@link UserEntry#sprEntryId}
     *
     * @return entry code
     */
    public Long getSprEntryId() {
        return sprEntryId;
    }

    /**
     * Get currency {@link UserEntry#currencyId}
     *
     * @return currency
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * Get debit account {@link UserEntry#accountDebit}
     *
     * @return debit account
     */
    public String getAccountDebit() {
        return accountDebit;
    }

    /**
     * Get credit account {@link UserEntry#accountCredit}
     *
     * @return credit account
     */
    public String getAccountCredit() {
        return accountCredit;
    }

    /**
     * Get sum {@link UserEntry#sum}
     *
     * @return sum
     */
    public BigDecimal getSum() {
        return sum;
    }

    /**
     * Get shift coming/spending {@link UserEntry#isSpending}
     *
     * @return shift coming/spending
     */
    public boolean getIsSpending() {
        return isSpending;
    }

    /**
     * Get rate {@link UserEntry#rate}
     *
     * @return rate
     */
    public BigDecimal getRate() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntry userEntry = (UserEntry) o;

        if (isSpending != userEntry.isSpending) return false;
        if (id != null ? !id.equals(userEntry.id) : userEntry.id != null) return false;
        if (userOperationId != null ? !userOperationId.equals(userEntry.userOperationId) : userEntry.userOperationId != null)
            return false;
        if (sprEntryId != null ? !sprEntryId.equals(userEntry.sprEntryId) : userEntry.sprEntryId != null) return false;
        if (currencyId != null ? !currencyId.equals(userEntry.currencyId) : userEntry.currencyId != null) return false;
        if (accountDebit != null ? !accountDebit.equals(userEntry.accountDebit) : userEntry.accountDebit != null)
            return false;
        if (accountCredit != null ? !accountCredit.equals(userEntry.accountCredit) : userEntry.accountCredit != null)
            return false;
        if (sum != null ? !sum.equals(userEntry.sum) : userEntry.sum != null) return false;
        return rate != null ? rate.equals(userEntry.rate) : userEntry.rate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userOperationId != null ? userOperationId.hashCode() : 0);
        result = 31 * result + (sprEntryId != null ? sprEntryId.hashCode() : 0);
        result = 31 * result + (currencyId != null ? currencyId.hashCode() : 0);
        result = 31 * result + (accountDebit != null ? accountDebit.hashCode() : 0);
        result = 31 * result + (accountCredit != null ? accountCredit.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (isSpending ? 1 : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }

    /**
     * construction проведенной проводки.
     */
    public static class Builder {
        private UserEntry newUserEntry;

        /**
         * Constructor
         */
        public Builder() {
            newUserEntry = new UserEntry();
        }

        /**
         * Identifier definition {@link UserEntry#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newUserEntry.id = id;
            return this;
        }

        /**
         * Executed operation code definition {@link UserEntry#userOperationId}
         *
         * @param userOperationId -  executed operation code
         * @return Builder
         */
        public Builder addUserOperationId(Long userOperationId) {
            newUserEntry.userOperationId = userOperationId;
            return this;
        }

        /**
         * Entry code definition {@link UserEntry#sprEntryId}
         *
         * @param sprEntryId - entry code
         * @return Builder
         */

        public Builder addSprEntryId(Long sprEntryId) {
            newUserEntry.sprEntryId = sprEntryId;
            return this;
        }

        /**
         * Currency code definition {@link UserEntry#currencyId}
         *
         * @param currencyId - currency code
         * @return Builder
         */
        public Builder addCurrencyId(Long currencyId) {
            newUserEntry.currencyId = currencyId;
            return this;
        }

        /**
         * Debit account definition {@link UserEntry#accountDebit}
         *
         * @param accountDebit - debit account
         * @return Builder
         */
        public Builder addAccountDebit(String accountDebit) {
            newUserEntry.accountDebit = accountDebit;
            return this;
        }

        /**
         * Credit account definition {@link UserEntry#accountCredit}
         *
         * @param accountCredit -  Credit account
         * @return Builder
         */
        public Builder addAccountCredit(String accountCredit) {
            newUserEntry.accountCredit = accountCredit;
            return this;
        }

        /**
         * Operation sum definition {@link UserEntry#sum}
         *
         * @param sum - operation sum
         * @return Builder
         */
        public Builder addSum(BigDecimal sum) {
            newUserEntry.sum = sum;
            return this;
        }

        /**
         * Shift coming/spending definition {@link UserEntry#isSpending}
         *
         * @param isSpending - shift coming/spending
         * @return Builder
         */
        public Builder addIsSpending(boolean isSpending) {
            newUserEntry.isSpending = isSpending;
            return this;
        }

        /**
         * Operation rate definition {@link UserEntry#rate}
         *
         * @param rate - operation code
         * @return Builder
         */
        public Builder addRate(BigDecimal rate) {
            newUserEntry.rate = rate;
            return this;
        }

        /**
         * Returns the constructed entry
         *
         * @return entry
         */
        public UserEntry build() {
            return newUserEntry;
        }
    }
}
