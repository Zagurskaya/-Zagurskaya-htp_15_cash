package com.zagurskaya.cash.entity;

/**
 * Валюта со свойствами <b>id</b>, <b>iso</b>, <b>nameRU</b> и <b>nameEN</b>.
 */
public class Currency {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * ISO валюты
     */
    private String iso;
    /**
     * Наименование на русском языке
     */
    private String nameRU;
    /**
     * Наименование на нглийском языке
     */
    private String nameEN;

    /**
     * Получение значения поля {@link Currency#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link Currency#iso}
     *
     * @return iso
     */
    public String getIso() {
        return iso;
    }

    /**
     * Получение значения поля {@link Currency#nameRU}
     *
     * @return наименование
     */
    public String getNameRU() {
        return nameRU;
    }

    /**
     * Получение значения поля {@link Currency#nameEN}
     *
     * @return наименование
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

    /**
     * Конструирование валюты.
     */
    public static class Builder {
        private Currency newCurrency;

        /**
         * Конструктор
         */
        public Builder() {
            newCurrency = new Currency();
        }

        /**
         * Определение идентификатора {@link Currency#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newCurrency.id = id;
            return this;
        }

        /**
         * Определение iso {@link Currency#iso}
         *
         * @param iso - iso
         * @return Builder
         */
        public Builder addIso(String iso) {
            newCurrency.iso = iso;
            return this;
        }

        /**
         * Определение наименование {@link Currency#nameRU}
         *
         * @param nameRU - наименование
         * @return Builder
         */
        public Builder addNameRU(String nameRU) {
            newCurrency.nameRU = nameRU;
            return this;
        }

        /**
         * Определение наименование {@link Currency#nameEN}
         *
         * @param nameEN - наименование
         * @return Builder
         */
        public Builder addNameEN(String nameEN) {
            newCurrency.nameEN = nameEN;
            return this;
        }

        /**
         * Возвращает построенную валюту
         *
         * @return валюта
         */
        public Currency build() {
            return newCurrency;
        }
    }
}
