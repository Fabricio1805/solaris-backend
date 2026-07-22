package com.solaris.backend.domain.enums;

public enum AppointmentStatus {
        SCHEDULED("Agendado"),
        CONFIRMED("Confirmado"),
        CHECKED_IN("Cliente chegou"),
        IN_PROGRESS("Em atendimento"),
        COMPLETED("Concluído"),
        CANCELLED("Cancelado"),
        NO_SHOW("Não compareceu"),
        RESCHEDULED("Reagendado");

        private final String description;

        AppointmentStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
}
