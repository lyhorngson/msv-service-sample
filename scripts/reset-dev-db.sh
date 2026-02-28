#!/usr/bin/env bash
# Reset dev database so Flyway runs V1 from scratch.
# Use when you see "missing table [customers]" (Flyway thinks migrations ran but tables are missing).
set -e
DB_NAME="${DB_NAME:-msv_invoice_db}"
DB_USER="${DB_USER:-root}"
DB_PASS="${DB_PASSWORD:-root123}"
echo "Resetting database: $DB_NAME"
mysql -u "$DB_USER" -p"$DB_PASS" -e "DROP DATABASE IF EXISTS \`$DB_NAME\`; CREATE DATABASE \`$DB_NAME\`;"
echo "Done. Start the app with: ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev"
