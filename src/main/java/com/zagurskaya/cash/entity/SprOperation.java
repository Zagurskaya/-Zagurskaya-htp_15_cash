package com.zagurskaya.cash.entity;

/**
 * Operation with characteristics <b>id</b>, <b>name</b> и <b>specification</b>.
 */
public class SprOperation {
    /**
     * Identifier
     */
    private Long id;
    /**
     * Name in Russian
     */
    private String nameRU;
    /**
     * Name in English
     */
    private String nameEN;
    /**
     * Specification
     */
    private String specification;

    /**
     * Get field value {@link SprOperation#id}
     *
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get field value {@link SprOperation#nameRU}
     *
     * @return name in Russian
     */
    public String getNameRU() {
        return nameRU;
    }

    /**
     * Get field value {@link SprOperation#nameEN}
     *
     * @return name in English
     */
    public String getNameEN() {
        return nameEN;
    }

    /**
     * Get specification {@link SprOperation#specification}
     *
     * @return specification
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
     * Construction directory operation.
     */
    public static class Builder {
        private SprOperation newSprOperation;

        /**
         * Constructor
         */
        public Builder() {
            newSprOperation = new SprOperation();
        }

        /**
         * Identifier definition {@link SprOperation#id}
         *
         * @param id - identifier
         * @return Builder
         */
        public Builder addId(Long id) {
            newSprOperation.id = id;
            return this;
        }

        /**
         * Name in Russian definition {@link SprOperation#nameRU}
         *
         * @param nameRU - name in Russian
         * @return Builder
         */
        public Builder addNameRU(String nameRU) {
            newSprOperation.nameRU = nameRU;
            return this;
        }

        /**
         * Name in English definition {@link SprOperation#nameEN}
         *
         * @param nameEN - name in English
         * @return Builder
         */
        public Builder addNameEN(String nameEN) {
            newSprOperation.nameEN = nameEN;
            return this;
        }

        /**
         * Specification definition {@link SprOperation#specification}
         *
         * @param specification - specification
         * @return Builder
         */
        public Builder addSpecification(String specification) {
            newSprOperation.specification = specification;
            return this;
        }

        /**
         * Returns the constructed operation
         *
         * @return operation
         */
        public SprOperation build() {
            return newSprOperation;
        }
    }
}
