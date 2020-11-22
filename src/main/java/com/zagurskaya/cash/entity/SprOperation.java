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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprOperation that = (SprOperation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nameRU != null ? !nameRU.equals(that.nameRU) : that.nameRU != null) return false;
        if (nameEN != null ? !nameEN.equals(that.nameEN) : that.nameEN != null) return false;
        return specification != null ? specification.equals(that.specification) : that.specification == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nameRU != null ? nameRU.hashCode() : 0);
        result = 31 * result + (nameEN != null ? nameEN.hashCode() : 0);
        result = 31 * result + (specification != null ? specification.hashCode() : 0);
        return result;
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
