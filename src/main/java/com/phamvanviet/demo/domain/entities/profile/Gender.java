package com.phamvanviet.demo.domain.entities.profile;

public enum Gender {
        MALE("male"),
        FEMALE("female"),
        OTHER("other");

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        Gender(String name) {
            this.name = name;
        }

}

