package com.firstclub.membership.constant;

public final class Constants {

    private Constants() {
    }

    public static final String API_BASE_PATH = "/api/v1";

    public static final class BenefitConfigKey {

        private BenefitConfigKey() {
        }

        public static final String MIN_ORDER_VALUE = "minOrderValue";
        public static final String PERCENT = "percent";
        public static final String CATEGORIES = "categories";
        public static final String HOURS_BEFORE = "hoursBefore";
        public static final String SLA_MINUTES = "slaMinutes";
    }

    public static final class CriterionConfigKey {

        private CriterionConfigKey() {
        }

        public static final String MIN_ORDERS = "minOrders";
        public static final String MIN_MONTHLY_VALUE = "minMonthlyValue";
        public static final String COHORT = "cohort";
    }
}
