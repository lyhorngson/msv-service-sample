package com.msv.service_sample.constant;

/**
 * Centralized API route constants.
 * All endpoint paths are defined here — never hardcoded in controllers.
 *
 * Convention: REST + RFC 7231
 *   - Plural nouns      : /invoices, /customers
 *   - Kebab-case        : /invoice-items
 *   - Versioned         : /api/v1/...
 *   - Sub-resources     : /invoices/{id}/payments
 *   - Actions (non-CRUD): /invoices/{id}/send, /invoices/{id}/cancel
 */

public final class ApiRoutes {

    private ApiRoutes() {}

    public static final String API_V1 = "/api/v1";

    // ── Invoices ─────────────────────────────────────────────────
    public static final String INVOICES            = API_V1 + "/invoices";
    public static final String INVOICES_BY_ID      = INVOICES + "/{id}";
    public static final String INVOICES_SEND       = INVOICES_BY_ID + "/send";
    public static final String INVOICES_CANCEL     = INVOICES_BY_ID + "/cancel";
    public static final String INVOICES_PAYMENTS   = INVOICES_BY_ID + "/payments";
    public static final String INVOICES_STATUS     = INVOICES_BY_ID + "/status";

    // ── Customers ────────────────────────────────────────────────
    public static final String CUSTOMERS           = API_V1 + "/customers";
    public static final String CUSTOMERS_BY_ID     = CUSTOMERS + "/{id}";
    public static final String CUSTOMERS_INVOICES  = CUSTOMERS_BY_ID + "/invoices";

    // ── Merchants ────────────────────────────────────────────────
    public static final String MERCHANTS           = API_V1 + "/merchants";
    public static final String MERCHANTS_BY_ID     = MERCHANTS + "/{id}";
    public static final String MERCHANTS_INVOICES  = MERCHANTS_BY_ID + "/invoices";
}