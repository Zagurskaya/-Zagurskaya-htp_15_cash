package com.zagurskaya.cash.entity;

/**
 * Операция со свойствами <b>id</b>, <b>name</b> и <b>specification</b>.
 */
public class SprOperation {
    /**
     * Идентификатор
     */
    private Long id;
    /**
     * Наименование на русском языке
     */
    private String nameRU;
    /**
     * Наименование на английском языке
     */
    private String nameEN;
    /**
     * Спецификация
     */
    private String specification;

    /**
     * Получение значения поля {@link SprOperation#id}
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Получение значения поля {@link SprOperation#nameRU}
     *
     * @return наименование на русском языке
     */
    public String getNameRU() {
        return nameRU;
    }
    /**
     * Получение значения поля {@link SprOperation#nameEN}
     *
     * @return наименование на английском языке
     */
    public String getNameEN() {
        return nameEN;
    }

    /**
     * Получение спецификации {@link SprOperation#specification}
     *
     * @return спецификация
     */
    public String getSpecification() {
        return specification;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SprOperation{")
                .append("id=")
                .append(id)
                .append(", nameRU=")
                .append(nameRU)
                .append(", nameEN=")
                .append(nameEN)
                .append(", specification=")
                .append(specification)
                .append("}");
        return sb.toString();
    }

    /**
     * Конструирование справочника операция.
     */
    public static class Builder {
        private SprOperation newSprOperation;

        /**
         * Конструктор
         */
        public Builder() {
            newSprOperation = new SprOperation();
        }

        /**
         * Определение идентификатора {@link SprOperation#id}
         *
         * @param id - идентификатор
         * @return Builder
         */
        public Builder addId(Long id) {
            newSprOperation.id = id;
            return this;
        }

        /**
         * Определение наименования операции RU {@link SprOperation#nameRU}
         *
         * @param nameRU - наименование операции RU
         * @return Builder
         */
        public Builder addNameRU(String nameRU) {
            newSprOperation.nameRU = nameRU;
            return this;
        }
        /**
         * Определение наименования операции EN{@link SprOperation#nameEN}
         *
         * @param nameEN - наименование операции EN
         * @return Builder
         */
        public Builder addNameEN(String nameEN) {
            newSprOperation.nameEN = nameEN;
            return this;
        }

        /**
         * Определение спецификации {@link SprOperation#specification}
         *
         * @param specification - спецификация
         * @return Builder
         */
        public Builder addSpecification(String specification) {
            newSprOperation.specification = specification;
            return this;
        }

        /**
         * Возвращает операцию
         *
         * @return операция
         */
        public SprOperation build() {
            return newSprOperation;
        }
    }
}
