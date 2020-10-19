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
     * Наименование
     */
    private String name;
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
     * Получение значения поля {@link SprOperation#name}
     *
     * @return наименование
     */
    public String getName() {
        return name;
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
                .append(", name=")
                .append(name)
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
         * Определение наименования операции {@link SprOperation#name}
         *
         * @param name - наименование операции
         * @return Builder
         */
        public Builder addName(String name) {
            newSprOperation.name = name;
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
