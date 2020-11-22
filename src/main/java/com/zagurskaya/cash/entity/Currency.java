package com.zagurskaya.cash.entity;

/**
 * Currency with characteristics <b>id</b>, <b>iso</b>, <b>nameRU</b> и <b>nameEN</b>.
 */
public class Currency {
    /**
     * Identifier
     */
    private Long id;
    /**
     * ISO currency
     */
    private String iso;
    /**
     * Name in Russian
     */
    private String nameRU;
    /**
     * Name in English
     */
    private String nameEN;

    /**
     * Get field value {@link Currency#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get field value {@link Currency#iso}
     *
     * @return iso
     */
    public String getIso() {
        return iso;
    }

    /**
     * Get field value {@link Currency#nameRU}
     *
     * @return name in Russian
     */
    public String getNameRU() {
        return nameRU;
    }

    /**
     * Get field value {@link Currency#nameEN}
     *
     * @return name in English
     */
    public String getNameEN() {
        return nameEN;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Currency{")
                .append("id=")
                .append(id)
                .append(", iso=")
                .append(iso)
                .append(", nameRU=")
                .append(nameRU)
                .append(", nameEN=")
                .append(nameEN)
                .append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (id != null ? !id.equals(currency.id) : currency.id != null) return false;
        if (iso != null ? !iso.equals(currency.iso) : currency.iso != null) return false;
        if (nameRU != null ? !nameRU.equals(currency.nameRU) : currency.nameRU != null) return false;
        return nameEN != null ? nameEN.equals(currency.nameEN) : currency.nameEN == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (iso != null ? iso.hashCode() : 0);
        result = 31 * result + (nameRU != null ? nameRU.hashCode() : 0);
        result = 31 * result + (nameEN != null ? nameEN.hashCode() : 0);
        return result;
    }

    /**
     * Currency construction.
     */
    public static class Builder {
        private Currency newCurrency;

        /**
         * Constructor
         */
        public Builder() {
            newCurrency = new Currency();
        }

        /**
         * Identifier definition {@link Currency#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newCurrency.id = id;
            return this;
        }

        /**
         * Iso definition {@link Currency#iso}
         *
         * @param iso - iso
         * @return Builder
         */
        public Builder addIso(String iso) {
            newCurrency.iso = iso;
            return this;
        }

        /**
         * Name in Russian definition {@link Currency#nameRU}
         *
         * @param nameRU - name in Russian
         * @return Builder
         */
        public Builder addNameRU(String nameRU) {
            newCurrency.nameRU = nameRU;
            return this;
        }

        /**
         * Name in English definition {@link Currency#nameEN}
         *
         * @param nameEN - name in English
         * @return Builder
         */
        public Builder addNameEN(String nameEN) {
            newCurrency.nameEN = nameEN;
            return this;
        }

        /**
         * Returns the constructed currency
         *
         * @return currency
         */
        public Currency build() {
            return newCurrency;
        }
    }
}
